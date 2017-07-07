/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.area;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ckmadsen
 */
public class AreaBoxes {
    
    private Map<String, Set<Box>> boxes;
    
    public AreaBoxes(STLflat spec) {
        boxes = nodeToBoxes(spec.toSTL(false), spec.limitsMap);
    }
    
    private Map<String, Set<Box>> nodeToBoxes(TreeNode node, HashMap<String, HashMap<String, Double>> limitsMap) {
        switch (node.op) {
            case CONCAT:
                // TODO: Special case of shift and conjunction
                break;
            case PARALLEL:
                // TODO: Special case of conjunction
                break;
            case JOIN:
                // TODO: Special case of parallel
                break;
            case IMPLIES:
                // TODO: Special case of conjunction
                break;
            case BOOL:
                return new HashMap<String, Set<Box>>();
            case PRED:
                LinearPredicateLeaf pred = (LinearPredicateLeaf) node;
                Box box = null;
                switch (pred.rop) {
                    case EQ:
                        box = new Box(new BigDecimal(0), new BigDecimal(0), new BigDecimal(pred.threshold), new BigDecimal(pred.threshold));
                        break;
                    case LT:
                    case LE:
                        box = new Box(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(pred.threshold));
                        break;
                    case GT:
                    case GE:
                        box = new Box(new BigDecimal(0), new BigDecimal(0), new BigDecimal(pred.threshold), new BigDecimal(limitsMap.get(pred.variable).get("max")));
                        break;
                }
                if (box != null) {
                    Map<String, Set<Box>> boxMap = new HashMap<String, Set<Box>>();
                    Set<Box> boxes = new HashSet<Box>();
                    boxes.add(box);
                    boxMap.put(pred.variable, boxes);
                    return boxMap;
                }
                break;
            case ALWAYS:
                AlwaysNode always = (AlwaysNode) node;
                Map<String, Set<Box>> childBoxMap = nodeToBoxes(always.child, limitsMap);
                Map<String, Set<Box>> boxMap = new HashMap<String, Set<Box>>();
                for (String key : childBoxMap.keySet()) {
                    Set<Box> boxes = new HashSet<Box>();
                    for (Box b : childBoxMap.get(key)) {
                        boxes.add(new Box(new BigDecimal(always.low + b.getLowerTime().doubleValue()), new BigDecimal(always.high + b.getUpperTime().doubleValue()), b.getLowerBound(), b.getUpperBound()));
                    }
                    boxMap.put(key, boxes);
                }
                return boxMap;
            case EVENT:
                // TODO: Abstract to a set of globally properties and convert to boxes
                break;
            case UNTIL:
                // TODO: Figure out how to convert to boxes
                break;
            case AND:
                if (node instanceof ModuleNode) {
                    return mergeBoxesConjunction(nodeToBoxes(((ModuleNode) node).left, limitsMap), nodeToBoxes(((ModuleNode) node).right, limitsMap));
                }
                else {
                    return mergeBoxesConjunction(nodeToBoxes(((ConjunctionNode) node).left, limitsMap), nodeToBoxes(((ConjunctionNode) node).right, limitsMap));
                }
            case OR:
                break;
            case NOT:
                break;
            case NOP:
                return new HashMap<String, Set<Box>>();
        }
        return new HashMap<String, Set<Box>>();
    }
    
    private Map<String, Set<Box>> mergeBoxesConjunction(Map<String, Set<Box>> leftBoxes, Map<String, Set<Box>> rightBoxes) {
        Map<String, Set<Box>> boxMap = new HashMap<String, Set<Box>>();
        for (String key : leftBoxes.keySet()) {
            if (rightBoxes.containsKey(key)) {
                Set<Box> boxes = new HashSet<Box>();
                for (Box left : leftBoxes.get(key)) {
                    boolean matched = false;
                    for (Box right : rightBoxes.get(key)) {
                        if (left.getUpperTime().compareTo(right.getLowerTime()) < 0 || right.getUpperTime().compareTo(left.getLowerTime()) < 0) {
                            break;
                        }
                        // else...
                    }
                    if (!matched) {
                        boxes.add(left);
                    }
                }
                boxMap.put(key, boxes);
            }
            else {
                boxMap.put(key, leftBoxes.get(key));
            }
        }
        for (String key : rightBoxes.keySet()) {
            if (!leftBoxes.containsKey(key)) {
                boxMap.put(key, rightBoxes.get(key));
            }
        }
        return boxMap;
    }
    
    private class Box {
        
        private BigDecimal lowerTime, upperTime, lowerBound, upperBound;
        
        public Box (BigDecimal lowerTime, BigDecimal upperTime, BigDecimal lowerBound, BigDecimal upperBound) {
            this.lowerTime = lowerTime;
            this.upperTime = upperTime;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
        
        public BigDecimal getLowerTime() {
            return lowerTime;
        }
        
        public BigDecimal getUpperTime() {
            return upperTime;
        }
        
        public BigDecimal getLowerBound() {
            return lowerBound;
        }
        
        public BigDecimal getUpperBound() {
            return upperBound;
        }
        
    }
    
}
