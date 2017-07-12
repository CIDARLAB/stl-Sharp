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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
                Queue<Box> leftQueue = new LinkedList<Box>();
                Queue<Box> rightQueue = new LinkedList<Box>();
                for (Box left : leftBoxes.get(key)) {
                    leftQueue.add(left);
                }
                for (Box right : rightBoxes.get(key)) {
                    rightQueue.add(right);
                }
                while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
                    while (!leftQueue.isEmpty()) {
                        Box left = leftQueue.poll();
                        List<Range> noOverlap = new ArrayList<Range>();
                        noOverlap.add(new Range<BigDecimal, BigDecimal>(new BigDecimal(left.getLowerTime().doubleValue()), new BigDecimal(left.getUpperTime().doubleValue())));
                        Queue<Box> temp = new LinkedList<Box>();
                        while (!rightQueue.isEmpty()) {
                            Box right = rightQueue.poll();
                            if (left.getUpperTime().compareTo(right.getLowerTime()) <= 0 || right.getUpperTime().compareTo(left.getLowerTime()) <= 0) {
                                temp.add(right);
                            }
                            else if (left.getLowerTime().compareTo(right.getLowerTime()) <= 0) {
                                for (int i = 0; i < noOverlap.size(); i ++) {
                                    BigDecimal lower = ((BigDecimal) noOverlap.get(i).getLower());
                                    BigDecimal upper = ((BigDecimal) noOverlap.get(i).getUpper());
                                    if (upper.compareTo(right.getLowerTime()) > 0) {
                                        if (upper.compareTo(right.getUpperTime()) > 0) {
                                            noOverlap.add(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
                                            noOverlap.set(i+1, new Range<BigDecimal, BigDecimal>(new BigDecimal(right.getUpperTime().doubleValue()), new BigDecimal(upper.doubleValue())));
                                            i++;
                                        }
                                        else {
                                            noOverlap.set(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
                                        }
                                    }
                                }
                                if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
                                    if (!(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                    }
                                    temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                }
                                else {
                                    if (!(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                    }
                                }
                            }
                            else {
                                for (int i = 0; i < noOverlap.size(); i ++) {
                                    BigDecimal lower = ((BigDecimal) noOverlap.get(i).getLower());
                                    BigDecimal upper = ((BigDecimal) noOverlap.get(i).getUpper());
                                    if (lower.compareTo(right.getUpperTime()) < 0) {
                                        if (upper.compareTo(right.getUpperTime()) < 0) {
//                                            noOverlap.add(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
//                                            noOverlap.set(i+1, new Range<BigDecimal, BigDecimal>(new BigDecimal(right.getUpperTime().doubleValue()), new BigDecimal(upper.doubleValue())));
//                                            i++;
                                        }
                                        else {
//                                            noOverlap.set(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
                                        }
                                    }
                                }
//                                if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
//                                    if (!(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
//                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                    temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
//                                }
//                                else {
//                                    if (!(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
//                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                }
                            }
                        }
                        rightQueue.addAll(temp);
//                        if (lowerTime.compareTo(upperTime) < 0) {
//                            boxes.add(new Box(lowerTime, upperTime, left.getLowerBound(), left.getUpperBound()));
//                        }
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
    
    public static BigDecimal max(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            return num2;
        }
        else if (num2 == null) {
            return num1;
        }
        else if (num1.compareTo(num2) < 0) {
            return num2;
        }
        else {
            return num1;
        }
    }

    public static BigDecimal min(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            return num2;
        }
        else if (num2 == null) {
            return num1;
        }
        else if (num1.compareTo(num2) > 0) {
            return num2;
        }
        else {
            return num1;
        }
    }

    private class Range<X, Y> {

        private final X lower;
        private final Y upper;

        public Range(X lower, Y upper) {
            this.lower = lower;
            this.upper = upper;
        }
        
        public X getLower() {
            return lower;
        }
        
        public Y getUpper() {
            return upper;
        } 
    }
    
    private class Box {
        
        private final BigDecimal lowerTime, upperTime, lowerBound, upperBound;
        
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
