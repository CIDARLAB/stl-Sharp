/**
 * Copyright (C) 2015-2016 Prashant Vaidyanathan <prash@bu.edu> and Cristian Ioan Vasile <cvasile@bu.edu>
 * CIDAR LAB & Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.distance;

import hyness.stl.ConcatenationNode;
import hyness.stl.Operation;
import hyness.stl.Pair;
import hyness.stl.ParallelNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.util.HashMap;

/**
 *
 * @author prash
 */
public class DistanceMetric {
    
    
    private HashMap<String, TreeNode> spec1Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec1Maps;
    private HashMap<String, TreeNode> spec2Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec2Maps;
    
    public DistanceMetric(){
        spec1Modules = new HashMap();
        spec2Modules = new HashMap();
        spec1Maps = new HashMap();
        spec2Maps = new HashMap();
        
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
        
        //Either one has a Concatenation operator
        if(spec1.op.equals(Operation.CONCAT) || spec2.op.equals(Operation.CONCAT)){
            System.out.println("Concatenation Found!");
            System.out.println("Spec 1 :: " + spec1.toString());
            System.out.println("Spec 2 :: " + spec2.toString());
            
            if(spec1.op.equals(Operation.CONCAT)){
                return max(computeDistance(((ConcatenationNode)spec1).left,spec2),computeDistance(((ConcatenationNode)spec1).right,spec2));
            }
            else{
                return max(computeDistance(spec1,((ConcatenationNode)spec2).left),computeDistance(spec1,((ConcatenationNode)spec2).right));
            }
        } 
        //Either one of them has a Parallel operator
        else if(spec1.op.equals(Operation.PARALLEL) || spec2.op.equals(Operation.PARALLEL)){
            if(spec1.op.equals(Operation.PARALLEL)){
                return max(computeDistance(((ParallelNode)spec1).left,spec2),computeDistance(((ParallelNode)spec1).right,spec2));
            }
            else{
                return max(computeDistance(spec1,((ParallelNode)spec2).left),computeDistance(spec1,((ParallelNode)spec2).right));
            }
        }
            
        
        return dist;
    }
    
    
    public static double max(double num1, double num2){
        if(num1 > num2)
            return num1;
        return num2;
    }
}
