/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.sharp;

import hyness.stl.BooleanBinaryNode;
import hyness.stl.BooleanLeaf;
import hyness.stl.BooleanUnaryNode;
import hyness.stl.ConcatenationNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.ImplicationNode;
import hyness.stl.JoinNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.Module;
import hyness.stl.ModuleLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Pair;
import hyness.stl.ParallelNode;
import hyness.stl.TemporalBinaryNode;
import hyness.stl.TemporalUnaryNode;
import hyness.stl.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

/**
 * @author Cristian-Ioan Vasile
 *
 */
public class STLSharp implements Module {
    public static final String IOMAP = "io";
    
    public TreeNode module;
    
    public HashMap<String, Module> modules;
    
    public HashMap<String, HashMap<Pair<String, Boolean>, String>> maps;
    public HashMap<String, HashMap<String,Double>> limitsMap;
    
    public double getMumax(){
        double max = Double.POSITIVE_INFINITY;
        //Set<String> signals = new HashSet<String>();
        for(String signal:this.limitsMap.keySet()){
            double diff = this.limitsMap.get(signal).get("max") - this.limitsMap.get(signal).get("min");
            if(diff < max){
                max = diff;
            }
        }
        
        return max;
    }
    
    
    /**
     * 
     */
    public STLSharp(TreeNode module) {
        this.module = module;
        this.modules = new HashMap<String, Module>();
        this.maps = new HashMap<String, HashMap<Pair<String, Boolean>,String>>();
        limitsMap = new HashMap<String,HashMap<String,Double>>();
    }
    
    /**
     * 
     * @param node
     * @param map
     * @return
     */
    public static TreeNode translate(TreeNode node,
            HashMap<Pair<String, Boolean>, String> map, Boolean side, Set<String> duplicatedValues) {
        
        if(node instanceof BooleanLeaf) { // do nothing
        } else if(node instanceof LinearPredicateLeaf) { // translate variable name
            LinearPredicateLeaf pnode = (LinearPredicateLeaf)node;
            Pair<String, Boolean> key = new Pair<String, Boolean>(new String(pnode.variable), side);
//            System.out.println("LP: " + pnode + " key: " + key + " map: " + map);
            if(map.containsKey(key)) {
                if (duplicatedValues.contains(map.get(key))) {
                    return null;
                }
                pnode.variable = map.get(key);
            } else if(map.containsValue(pnode.variable)) {
                throw new IllegalArgumentException("Translation is ambiguous!");
            }
        } else if(node instanceof BooleanBinaryNode) {
            TreeNode left = translate(((BooleanBinaryNode)node).left, map, side, duplicatedValues);
            TreeNode right = translate(((BooleanBinaryNode)node).right, map, side, duplicatedValues);
            if (left == null) {
                return right;
            }
            else if (right == null) {
                return left;
            }
            ((BooleanBinaryNode)node).left = left;
            ((BooleanBinaryNode)node).right = right;
        } else if(node instanceof BooleanUnaryNode) {
            TreeNode child = translate(((BooleanUnaryNode)node).child, map, side, duplicatedValues);
            if (child == null) {
                return null;
            }
        } else if(node instanceof TemporalBinaryNode) {
            TreeNode left = translate(((TemporalBinaryNode)node).left, map, side, duplicatedValues);
            TreeNode right = translate(((TemporalBinaryNode)node).right, map, side, duplicatedValues);
            if (left == null && right == null) {
                return null;
            }
            else if (left == null) {
                ((TemporalBinaryNode)node).right = new BooleanLeaf(true);
            }
            else if (right == null) {
                ((TemporalBinaryNode)node).left = new BooleanLeaf(true);
            }
        } else if(node instanceof TemporalUnaryNode) {
            TreeNode child = translate(((TemporalUnaryNode)node).child, map, side, duplicatedValues);
            if (child == null) {
                return null;
            }
        } else if(node instanceof JoinNode) {
            TreeNode left = translate(((JoinNode)node).left, map, side, duplicatedValues);
            TreeNode right = translate(((JoinNode)node).right, map, side, duplicatedValues);
            if (left == null) {
                return right;
            }
            else if (right == null) {
                return left;
            }
            ((JoinNode)node).left = left;
            ((JoinNode)node).right = right;
        } else if(node instanceof ConcatenationNode) {
            TreeNode left = translate(((ConcatenationNode)node).left, map, side, duplicatedValues);
            TreeNode right = translate(((ConcatenationNode)node).right, map, side, duplicatedValues);
            if (left == null) {
                return right;
            }
            else if (right == null) {
                return left;
            }
            ((ConcatenationNode)node).left = left;
            ((ConcatenationNode)node).right = right;
        } else if(node instanceof ParallelNode) {
            TreeNode left = translate(((ParallelNode)node).left, map, side, duplicatedValues);
            TreeNode right = translate(((ParallelNode)node).right, map, side, duplicatedValues);
            if (left == null) {
                return right;
            }
            else if (right == null) {
                return left;
            }
            ((ParallelNode)node).left = left;
            ((ParallelNode)node).right = right;
        }
        else {
            throw new IllegalArgumentException("Unknown operation in STL formula!");
        }
        return node;
    }
    
    /**
     * 
     * @return
     */
    public TreeNode toSTL(boolean ignoreInternal) {
        List<String> values = new ArrayList<String>();
        Set<String> duplicatedValues = new HashSet<String>();
        if (ignoreInternal) {
            for (String val : maps.get(IOMAP).values()) {
                if (values.contains(val)) {
                    duplicatedValues.add(val);
                }
                values.add(val);
            }
        }
        return translate(toSTL(this.module, ignoreInternal), maps.get(IOMAP), null, duplicatedValues);
    }
    
    @Override
    public String toString(){
        String stlb = "";
//        stlb += "Module :: \n";
        String moduleString = module.toString();
        while (moduleString.startsWith("(") && moduleString.endsWith(")")) {
            moduleString = moduleString.substring(1, moduleString.length() - 1);
        }
        String mapName = "";
        for (String key : maps.keySet()) {
            if (!key.equals("io")) {
                mapName = key;
            }
        }
        String[] split = moduleString.split(" ");
        moduleString = "";
        for (String string : split) {
            if (string.equals("=>") || string.equals("&&") || string.equals("||") || string.equals(">>") || string.equals("#")) {
                moduleString += string + "_" + mapName + " ";
            }
            else {
                moduleString += string + " ";
            }
        }
        moduleString = moduleString.substring(0, moduleString.length() - 1);
        stlb += moduleString + "\n\n";
//        stlb += "\nModule Map :: \n";
        for (String m : modules.keySet()) {
            Module mod = modules.get(m);
            stlb += m + " = ";
            if (mod instanceof STLSharp) {
                stlb += ((STLSharp) mod).toSTL(false).toString();
            }
            else {
                stlb += mod.toString();
            }
            stlb += "\n";
        }
        stlb += "\n";
//        stlb += this.modules;
        for (String key : maps.keySet()) {
            if (!key.equals("io")) {
                stlb += key + " { ";
                for (Pair<String, Boolean> varPair : maps.get(key).keySet()) {
                    if (varPair.right) {
                        stlb += varPair.left + "@left: " + maps.get(key).get(varPair) + ", ";
                    }
                    else {
                        stlb += varPair.left + "@right: " + maps.get(key).get(varPair) + ", ";
                    }
                }
                if (maps.get(key).keySet().size() > 0) {
                    stlb = stlb.substring(0, stlb.length() - 2);
                }
                stlb += "  }\n";
            }
        }
        stlb += "io { ";
        for (Pair<String, Boolean> varPair : maps.get("io").keySet()) {
            stlb += varPair.left + ": " + maps.get("io").get(varPair) + ", ";
        }
        if (maps.get("io").keySet().size() > 0) {
            stlb = stlb.substring(0, stlb.length() - 2);
        }
        stlb += "  }\n";
        stlb += "limits [";
        for (String key : limitsMap.keySet()) {
            stlb += "{" + key + " : {";
            stlb += "max:" + limitsMap.get(key).get("max") + ",";
            stlb += "min:" + limitsMap.get(key).get("min");
            stlb += "}},";
        }
        if (limitsMap.keySet().size() > 0) {
            stlb = stlb.substring(0, stlb.length() - 1);
        }
        stlb += "]\n";
//        stlb += "\nMaps :: \n";
//        stlb += this.maps;
        return stlb;
    }
    
    /**
     * 
     * @param node
     * @return
     */
    public TreeNode toSTL(TreeNode node, boolean ignoreInternal) {
        if (node instanceof ModuleLeaf) {
            Module mod = this.modules.get(((ModuleLeaf)node).getName());
            if (mod instanceof TreeNode) {
                return (TreeNode) mod;
            }
            else {
                return ((STLSharp) mod).toSTL(ignoreInternal);
            }
        } else if (node instanceof ModuleNode) {
            TreeNode ret;
            ModuleNode mnode = (ModuleNode)node;
            HashMap<Pair<String, Boolean>, String> map = maps.get(mnode.map);
            List<String> values = new ArrayList<String>();
            Set<String> duplicatedValues = new HashSet<String>();
            if (ignoreInternal) {
                for (String val : map.values()) {
                    if (values.contains(val)) {
                        duplicatedValues.add(val);
                    }
                    values.add(val);
                }
            }
            TreeNode left = translate(toSTL(mnode.left, ignoreInternal), map, true, duplicatedValues);
//            System.out.println("----");
            TreeNode right = translate(toSTL(mnode.right, ignoreInternal), map, false, duplicatedValues);
            
            switch(mnode.op) {
                case OR:
                    ret = new DisjunctionNode(left, right);
                    break;
                case AND:
                    ret = new ConjunctionNode(left, right);
                    break;
                case IMPLIES:
                    ret = new ImplicationNode(left, right);
                    break;
                case JOIN:
                    ret = new JoinNode(left, right);
                    break;
                case CONCAT:
                    ret = new ConcatenationNode(left, right);
                    break;
                case PARALLEL:
                    ret = new ParallelNode(left,right);
                    break;
                default:
                    throw new UnsupportedOperationException(
                            "Modules can only be composed using disjunction, "
                            + "conjunction, implication, join, concatenation, or parallel!");
            }
            if (left == null) {
                ret = right;
            }
            else if (right == null) {
                ret = left;
            }
            return ret;
        } else {
            throw new IllegalArgumentException("Argument node must be of type "
                    + "ModuleLead or ModuleNode!");
        }
    }
}
