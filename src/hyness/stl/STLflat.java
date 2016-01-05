/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

import java.util.HashMap;

/**
 * @author Cristian-Ioan Vasile
 *
 */
public class STLflat {
    public static final String IOMAP = "io";
    
    TreeNode module;
    
    HashMap<String, TreeNode> modules;
    
    HashMap<String, HashMap<Pair<String, Boolean>, String>> maps;
    
    /**
     * 
     */
    public STLflat(TreeNode module) {
        this.module = module;
        this.modules = new HashMap<String, TreeNode>();
        this.maps = new HashMap<String, HashMap<Pair<String, Boolean>,String>>();
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
        } else {
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
    
    /**
     * 
     * @param node
     * @return
     */
    public TreeNode toSTL(TreeNode node) {
        if (node instanceof ModuleLeaf) {
            return this.modules.get(((ModuleLeaf)node).name);
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
                default:
                    throw new UnsupportedOperationException(
                            "Modules can only be composed using disjunction, "
                            + "conjunction, implication or concatenation!");
            }
            return ret;
        } else {
            throw new IllegalArgumentException("Argument node must be of type "
                    + "ModuleLead or ModuleNode!");
        }
    }
}
