/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.AlwaysNode;
import hyness.stl.BooleanLeaf;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.Module;
import hyness.stl.ModuleNode;
import hyness.stl.Operation;
import hyness.stl.Pair;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class NewDistanceMetric {
    
    
    @Getter
    private BigDecimal muMax;

    private TimeMetric timeMetric1;
    private TimeMetric timeMetric2;
    private HashMap<String, Module> spec1Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec1Maps;
    private HashMap<String, Module> spec2Modules;
    private HashMap<String, HashMap<Pair<String, Boolean>, String>> spec2Maps;
    private HashMap<String, HashMap<String, Double>> limitsMap;
    private AtomicInteger counter;

    
    @Getter
    @Setter
    private BigDecimal alphaG;
    
    @Getter
    @Setter
    private BigDecimal alphaGprime;
    
    @Getter
    @Setter
    private BigDecimal alphaF;
    
    @Getter
    @Setter
    private BigDecimal alphaFprime;
    
    public BigDecimal computeDistance(STLflat spec1, STLflat spec2) {
        setLimitsMap(spec1,spec2);
        spec1Modules = spec1.modules;
        spec2Modules = spec2.modules;
        return computeDistance(spec1.module, spec2.module);
    }
 
    private BigDecimal computeDistance(TreeNode module1, TreeNode module2){
        
        //Module 1 is of Type Concatenation
        if(module1.op.equals(Operation.CONCAT)){
            
        }
        
        //Module 1 is of type Parallel
        if(module1.op.equals(Operation.PARALLEL)){
            
        }
        
        //Module 1 is of type Conjunction
        if(module1.op.equals(Operation.AND)){
            
        }
        
        //Module 1 is of type Disjunction
        if(module1.op.equals(Operation.OR)){
            
        }
        
        //Module 1 is of type Implies
        if(module1.op.equals(Operation.IMPLIES)){
            
        }
        
        //Module 1 is of type Global
        if(module1 instanceof AlwaysNode){
            
        }
        
        //Module 1 is of type Eventually
        if(module1 instanceof EventNode){
            
        }
        
        if(module1 instanceof BooleanLeaf){
            
        }
        
        if(module1 instanceof LinearPredicateLeaf){
            LinearPredicateLeaf linearPredicate1 = (LinearPredicateLeaf) module1;
            if (module2 instanceof LinearPredicateLeaf) {
                LinearPredicateLeaf linearPredicate2 = (LinearPredicateLeaf) module2;
                if(!linearPredicate1.variable.equals(linearPredicate2.variable)){
                    return null;
                }
                if (linearPredicate1.rop.equals(RelOperation.EQ) || linearPredicate1.rop.equals(RelOperation.LE) || linearPredicate1.rop.equals(RelOperation.LT)) {
                    if (linearPredicate2.rop.equals(RelOperation.EQ) || linearPredicate2.rop.equals(RelOperation.LE) || linearPredicate2.rop.equals(RelOperation.LT)) {
                        return BigDecimal.valueOf(linearPredicate2.threshold - linearPredicate1.threshold);
                    } else if (linearPredicate2.rop.equals(RelOperation.GE) || linearPredicate2.rop.equals(RelOperation.GT)) {
                        return BigDecimal.valueOf((2*((this.limitsMap.get(linearPredicate1.variable)).get("max"))) + (linearPredicate2.threshold - linearPredicate1.threshold));
                    }
                } 
                else if(linearPredicate1.rop.equals(RelOperation.GE) || linearPredicate1.rop.equals(RelOperation.GT)) {
                    if (linearPredicate2.rop.equals(RelOperation.EQ) || linearPredicate2.rop.equals(RelOperation.LE) || linearPredicate2.rop.equals(RelOperation.LT)) {
                        return BigDecimal.valueOf((2*((this.limitsMap.get(linearPredicate1.variable)).get("max"))) + (linearPredicate1.threshold - linearPredicate2.threshold));
                    } else if (linearPredicate2.rop.equals(RelOperation.GE) || linearPredicate2.rop.equals(RelOperation.GT)) {
                        return BigDecimal.valueOf(linearPredicate1.threshold - linearPredicate2.threshold);
                    }
                }
            }
            
            if(module2.op.equals(Operation.AND)){
                if(module2 instanceof ModuleNode){
                    TreeNode left = getTreeNodeFromModule(spec2Modules.get((((ModuleNode) module2).left).toString()));
                    TreeNode right = getTreeNodeFromModule(spec2Modules.get((((ModuleNode) module2).right).toString()));
                    return max(computeDistance(linearPredicate1,left),computeDistance(linearPredicate1,right));
                }
                ConjunctionNode conjunctionModule2 = (ConjunctionNode)module2;
                return max(computeDistance(linearPredicate1,conjunctionModule2.left),computeDistance(linearPredicate1,conjunctionModule2.right));
            }
            
            if(module2.op.equals(Operation.OR)){
                if(module2 instanceof ModuleNode){
                    TreeNode left = getTreeNodeFromModule(spec2Modules.get((((ModuleNode) module2).left).toString()));
                    TreeNode right = getTreeNodeFromModule(spec2Modules.get((((ModuleNode) module2).right).toString()));
                    return min(computeDistance(linearPredicate1,left),computeDistance(linearPredicate1,right));
                }
                DisjunctionNode disjunctionModule2 = (DisjunctionNode)module2;
                return min(computeDistance(linearPredicate1,disjunctionModule2.left),computeDistance(linearPredicate1,disjunctionModule2.right));
            }
            
            if(module2 instanceof AlwaysNode){
                AlwaysNode alwaysModule2 = (AlwaysNode)module2;
                if((alwaysModule2.low == 0) && containsVariable(alwaysModule2,linearPredicate1.variable)){
                     
                } else if( (alwaysModule2.low > 0) && !containsVariable(alwaysModule2,linearPredicate1.variable)){
                    
                } else {
                    
                }
            }
            
        }
        
        
        return BigDecimal.ZERO;
    }
    
    private boolean containsVariable(TreeNode module, String variable){
        return false;
    }
    
    private BigDecimal getRecursiveValue(TreeNode module){
        return null;
    }
    
    private TreeNode getTreeNodeFromModule(Module mod) {
        if (mod instanceof TreeNode) {
            return (TreeNode) mod;
        }
        else {
            return ((STLflat) mod).toSTL();
        }
    }
    
    private void setLimitsMap(STLflat spec1, STLflat spec2) {
        
        limitsMap.putAll(spec1.limitsMap);
        for (String signal : spec2.limitsMap.keySet()) {
            if (limitsMap.containsKey(signal)) {
                limitsMap.get(signal).put("max", max(spec1.limitsMap.get(signal).get("max"), spec2.limitsMap.get(signal).get("max")));
                limitsMap.get(signal).put("min", min(spec1.limitsMap.get(signal).get("min"), spec2.limitsMap.get(signal).get("min")));
            } else {
                limitsMap.put(signal, spec2.limitsMap.get(signal));
            }
        }
    }
    
    private static double max(double num1, double num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }

    private static double min(double num1, double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
    
    //Used for conjunction
    public static BigDecimal max(BigDecimal num1, BigDecimal num2){
        
        if(num1 == null || num2 == null){
            return null;
        }
        if(num1.compareTo(num2) == -1){
            return num2;
        } else {
            return num1;
        }
    }
    
    //Used for disjunction
    public static BigDecimal min(BigDecimal num1, BigDecimal num2){
        
        if(num1 == null && num2 == null){
            return null;
        }
        if(num1 == null){
            return num2;
        }
        if(num2 == null){
            return num1;
        }
        if(num1.compareTo(num2) == 1){
            return num2;
        } else {
            return num1;
        }
    }
    
}
