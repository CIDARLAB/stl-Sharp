/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Operation;
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
public class AreaOfSatisfaction {
    
    public BigDecimal computeDistance(STLflat spec1, STLflat spec2) {
        return computeDistance(nodeToBoxes(spec1.toSTL(false), spec1.limitsMap), nodeToBoxes(spec2.toSTL(false), spec2.limitsMap));
    }
    
    public BigDecimal computeDistance(Map<String, Set<Box>> boxes1, Map<String, Set<Box>> boxes2) {
        Map<String, Set<Box>> boxes = mergeBoxes(boxes1, boxes2, Operation.NOP);
        double leftOverArea = 0.0;
        for (String var : boxes.keySet()) {
            for (Box box : boxes.get(var)) {
                leftOverArea += (box.getUpperTime().doubleValue() - box.getLowerTime().doubleValue()) * (box.getUpperBound().doubleValue() - box.getLowerBound().doubleValue());
            }
        }
        return new BigDecimal(leftOverArea);
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
                    return mergeBoxes(nodeToBoxes(((ModuleNode) node).left, limitsMap), nodeToBoxes(((ModuleNode) node).right, limitsMap), Operation.AND);
                }
                else {
                    return mergeBoxes(nodeToBoxes(((ConjunctionNode) node).left, limitsMap), nodeToBoxes(((ConjunctionNode) node).right, limitsMap), Operation.AND);
                }
            case OR:
                if (node instanceof ModuleNode) {
                    return mergeBoxes(nodeToBoxes(((ModuleNode) node).left, limitsMap), nodeToBoxes(((ModuleNode) node).right, limitsMap), Operation.OR);
                }
                else {
                    return mergeBoxes(nodeToBoxes(((ConjunctionNode) node).left, limitsMap), nodeToBoxes(((ConjunctionNode) node).right, limitsMap), Operation.OR);
                }
            case NOT:
                break;
            case NOP:
                return new HashMap<String, Set<Box>>();
        }
        return new HashMap<String, Set<Box>>();
    }

    private Map<String, Set<Box>> mergeBoxes(Map<String, Set<Box>> leftBoxes, Map<String, Set<Box>> rightBoxes, Operation op) {
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
                            for (int i = 0; i < noOverlap.size(); i++) {
                                BigDecimal lower = ((BigDecimal) noOverlap.get(i).getLower());
                                BigDecimal upper = ((BigDecimal) noOverlap.get(i).getUpper());
                                if (upper.compareTo(right.getLowerTime()) > 0) {
                                    if (upper.compareTo(right.getUpperTime()) > 0) {
                                        noOverlap.add(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
                                        noOverlap.set(i + 1, new Range<BigDecimal, BigDecimal>(new BigDecimal(right.getUpperTime().doubleValue()), new BigDecimal(upper.doubleValue())));
                                        i++;
                                    }
                                    else {
                                        noOverlap.set(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(lower.doubleValue()), new BigDecimal(right.getLowerTime().doubleValue())));
                                    }
                                }
                            }
                            if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(right.getLowerTime(), left.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
                                else if (op == Operation.OR) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                        boxes.add(new Box(right.getLowerTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                    }
                                    else {
                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                    }
                                }
                                else if (op == Operation.NOP) {
                                    //TODO: Remove the overlap area.
                                }
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                            }
                            else {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
                                else if (op == Operation.OR) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                        boxes.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                    }
                                    else {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                    }
                                }
                                else if (op == Operation.NOP) {
                                    //TODO: Remove the overlap area.
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < noOverlap.size(); i++) {
                                BigDecimal lower = ((BigDecimal) noOverlap.get(i).getLower());
                                BigDecimal upper = ((BigDecimal) noOverlap.get(i).getUpper());
                                if (lower.compareTo(right.getUpperTime()) < 0) {
                                    if (upper.compareTo(right.getUpperTime()) > 0) {
                                        noOverlap.set(i, new Range<BigDecimal, BigDecimal>(new BigDecimal(right.getUpperTime().doubleValue()), new BigDecimal(upper.doubleValue())));
                                    }
                                }
                            }
                            temp.add(new Box(right.getLowerTime(), left.getLowerTime(), right.getLowerBound(), right.getUpperBound()));
                            if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(left.getLowerTime(), left.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
                                else if (op == Operation.OR) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                        boxes.add(new Box(left.getLowerTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                    }
                                    else {
                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                    }
                                }
                                else if (op == Operation.NOP) {
                                    //TODO: Remove the overlap area.
                                }
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                            }
                            else {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
                                else if (op == Operation.OR) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                        boxes.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                    }
                                    else {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                    }
                                }
                                else if (op == Operation.NOP) {
                                    //TODO: Remove the overlap area.
                                }
                            }
                        }
                    }
                    rightQueue.addAll(temp);
                    for (Range range : noOverlap) {
                        BigDecimal lowerTime = (BigDecimal) range.getLower();
                        BigDecimal upperTime = (BigDecimal) range.getUpper();
                        if (lowerTime.compareTo(upperTime) < 0) {
                            boxes.add(new Box(lowerTime, upperTime, left.getLowerBound(), left.getUpperBound()));
                        }
                    }
                }
                while (!rightQueue.isEmpty()) {
                    boxes.add(rightQueue.poll());
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

        public Box(BigDecimal lowerTime, BigDecimal upperTime, BigDecimal lowerBound, BigDecimal upperBound) {
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
