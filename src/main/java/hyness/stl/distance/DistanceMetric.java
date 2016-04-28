/**
 * Copyright (C) 2015-2016 Prashant Vaidyanathan <prash@bu.edu> and Cristian Ioan Vasile <cvasile@bu.edu>
 * CIDAR LAB & Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.distance;

import hyness.stl.ConcatenationNode;
import hyness.stl.ModuleNode;
import hyness.stl.Operation;
import hyness.stl.Pair;
import hyness.stl.ParallelNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author prash
 */
public class DistanceMetric {
    
    
    private HashMap<String, TreeNode> spec1Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec1Maps;
    private HashMap<String, TreeNode> spec2Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec2Maps;
    private AtomicInteger counter;
    public DistanceMetric(){
        spec1Modules = new HashMap();
        spec2Modules = new HashMap();
        spec1Maps = new HashMap();
        spec2Maps = new HashMap();
        counter = new AtomicInteger(0);
    }
    public double computeDistance(STLflat spec1, STLflat spec2){
        spec1Modules = spec1.modules;
        spec2Modules = spec2.modules;
        
        spec1Maps = spec1.maps;
        spec2Maps = spec2.maps;
        return computeDistance(spec1.module,spec2.module);
    }
    
    
    private double computeDistance(TreeNode spec1,TreeNode spec2){
        double dist = Double.NaN;
        
        System.out.println("Iteration :: " + counter.getAndIncrement());
        System.out.println("Spec1  :: " + spec1.toString());
        System.out.println("Spec2  :: " + spec2.toString());
        
        //Either one has a Concatenation operator
        if (spec1.op.equals(Operation.CONCAT)) {
            if(spec1 instanceof ModuleNode){
                TreeNode left = spec1Modules.get((((ModuleNode)spec1).left).toString());
                TreeNode right = spec1Modules.get((((ModuleNode)spec1).right).toString());
                return max(computeDistance(left,spec2),computeDistance(right,spec2));
            }
            return max(computeDistance(((ConcatenationNode) spec1).left, spec2), computeDistance(((ConcatenationNode) spec1).right, spec2));
        } else if(spec2.op.equals(Operation.CONCAT)) {
            if(spec2 instanceof ModuleNode){
                TreeNode left = spec2Modules.get((((ModuleNode)spec2).left).toString());
                TreeNode right = spec2Modules.get((((ModuleNode)spec2).right).toString());
                return max(computeDistance(spec1,left),computeDistance(spec1,right));
            }
            return max(computeDistance(spec1, ((ConcatenationNode) spec2).left), computeDistance(spec1, ((ConcatenationNode) spec2).right));
        }
        
        //Either one of them has a Parallel operator
        else if (spec1.op.equals(Operation.PARALLEL)) {
            if (spec1 instanceof ModuleNode) {
                TreeNode left = spec1Modules.get((((ModuleNode) spec1).left).toString());
                TreeNode right = spec1Modules.get((((ModuleNode) spec1).right).toString());
            }
        } else if (spec2.op.equals(Operation.PARALLEL)){
            if (spec2 instanceof ModuleNode) {
                TreeNode left = spec2Modules.get((((ModuleNode) spec2).left).toString());
                TreeNode right = spec2Modules.get((((ModuleNode) spec2).right).toString());
            }
        }
        //One of them is an Implies.. Need some sort of negation operator
         else if (spec1.op.equals(Operation.IMPLIES)) {
            if (spec1 instanceof ModuleNode) {
                TreeNode left = spec1Modules.get((((ModuleNode) spec1).left).toString());
                TreeNode right = spec1Modules.get((((ModuleNode) spec1).right).toString());
            }
        } else if (spec2.op.equals(Operation.IMPLIES)){
            if (spec2 instanceof ModuleNode) {
                TreeNode left = spec2Modules.get((((ModuleNode) spec2).left).toString());
                TreeNode right = spec2Modules.get((((ModuleNode) spec2).right).toString());
            }
        }
        
        /*
        else if (spec1.op.equals(Operation.)) {
            if (spec1 instanceof ModuleNode) {
                TreeNode left = spec1Modules.get((((ModuleNode) spec1).left).toString());
                TreeNode right = spec1Modules.get((((ModuleNode) spec1).right).toString());
            }
        } else if (spec2.op.equals(Operation.)){
            if (spec2 instanceof ModuleNode) {
                TreeNode left = spec2Modules.get((((ModuleNode) spec2).left).toString());
                TreeNode right = spec2Modules.get((((ModuleNode) spec2).right).toString());
            }
        }
        */
    
        
        return dist;
    }
    
    
    public static double max(double num1, double num2){
        if(num1 > num2)
            return num1;
        return num2;
    }
}
