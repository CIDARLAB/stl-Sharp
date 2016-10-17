/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.flat;

import hyness.stl.BooleanBinaryNode;
import hyness.stl.BooleanLeaf;
import hyness.stl.BooleanUnaryNode;
import hyness.stl.ConcatenationNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.ImplicationNode;
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
public class STLflat implements Module {
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
    public STLflat(TreeNode module) {
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
            HashMap<Pair<String, Boolean>, String> map, Boolean side) {
        
        if(node instanceof BooleanLeaf) { // do nothing
        } else if(node instanceof LinearPredicateLeaf) { // translate variable name
            LinearPredicateLeaf pnode = (LinearPredicateLeaf)node;
            Pair<String, Boolean> key = new Pair<String, Boolean>(new String(pnode.variable), side);
//            System.out.println("LP: " + pnode + " key: " + key + " map: " + map);
            if(map.containsKey(key)) {
                pnode.variable = map.get(key);
            } else if(map.containsValue(pnode.variable)) {
                throw new IllegalArgumentException("Translation is ambiguous!");
            }
        } else if(node instanceof BooleanBinaryNode) {
            translate(((BooleanBinaryNode)node).left, map, side);
            translate(((BooleanBinaryNode)node).right, map, side);
        } else if(node instanceof BooleanUnaryNode) {
            translate(((BooleanUnaryNode)node).child, map, side);
        } else if(node instanceof TemporalBinaryNode) {
            translate(((TemporalBinaryNode)node).left, map, side);
            translate(((TemporalBinaryNode)node).right, map, side);
        } else if(node instanceof TemporalUnaryNode) {
            translate(((TemporalUnaryNode)node).child, map, side);
        } else if(node instanceof ConcatenationNode) {
            translate(((ConcatenationNode)node).left, map, side);
            translate(((ConcatenationNode)node).right, map, side);
        } else if(node instanceof ParallelNode) {
            translate(((ParallelNode)node).left, map, side);
            translate(((ParallelNode)node).right, map, side);
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
    public TreeNode toSTL() {
        return translate(toSTL(this.module), maps.get(IOMAP), null);
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
            if (mod instanceof STLflat) {
                stlb += ((STLflat) mod).toSTL().toString();
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
    public TreeNode toSTL(TreeNode node) {
        if (node instanceof ModuleLeaf) {
            Module mod = this.modules.get(((ModuleLeaf)node).name);
            if (mod instanceof TreeNode) {
                return (TreeNode) mod;
            }
            else {
                return ((STLflat) mod).toSTL();
            }
        } else if (node instanceof ModuleNode) {
            TreeNode ret;
            ModuleNode mnode = (ModuleNode)node;
            HashMap<Pair<String, Boolean>, String> map = maps.get(mnode.map);
            TreeNode left = translate(toSTL(mnode.left), map, true);
//            System.out.println("----");
            TreeNode right = translate(toSTL(mnode.right), map, false);
            
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
                case CONCAT:
                    ret = new ConcatenationNode(left, right);
                    break;
                case PARALLEL:
                    ret = new ParallelNode(left,right);
                    break;
                default:
                    throw new UnsupportedOperationException(
                            "Modules can only be composed using disjunction, "
                            + "conjunction, implication or concatenation or parallel!");
            }
            return ret;
        } else {
            throw new IllegalArgumentException("Argument node must be of type "
                    + "ModuleLead or ModuleNode!");
        }
    }
}
