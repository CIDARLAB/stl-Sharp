/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Operation;
import hyness.stl.TreeNode;
import hyness.stl.grammar.sharp.STLSharp;
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
    
    private BigDecimal threshold;
    
    public AreaOfSatisfaction(BigDecimal threshold) {
        this.threshold = threshold;
    }
    
    public BigDecimal computeDistance(STLSharp spec1, STLSharp spec2, boolean ignoreInternal) {
        List<Map<String, Set<Box>>> listOfBoxes1 = nodeToBoxes(spec1.toSTL(ignoreInternal), spec1.limitsMap);
        List<Map<String, Set<Box>>> listOfBoxes2 = nodeToBoxes(spec2.toSTL(ignoreInternal), spec2.limitsMap);
        Set<String> signals = new HashSet<String>();
        for (Map<String, Set<Box>> boxMap : listOfBoxes1) {
            for (String key : boxMap.keySet()) {
                signals.add(key);
            }
        }
        for (Map<String, Set<Box>> boxMap : listOfBoxes2) {
            for (String key : boxMap.keySet()) {
                signals.add(key);
            }
        }
        return computeDistance(listOfBoxes1, listOfBoxes2, signals);
    }
    
    public BigDecimal computeDistance(STLSharp spec1, STLSharp spec2, boolean ignoreInternal, Set<String> signals) {
        return computeDistance(nodeToBoxes(spec1.toSTL(ignoreInternal), spec1.limitsMap), nodeToBoxes(spec2.toSTL(ignoreInternal), spec2.limitsMap), signals);
    }
    
    private BigDecimal computeDistance(List<Map<String, Set<Box>>> listOfBoxes1, List<Map<String, Set<Box>>> listOfBoxes2, Set<String> signals) {
        return computeArea(mergeBoxes(listOfBoxes1, listOfBoxes2, Operation.NOP), signals);
    }
    
    public boolean computeCompatibility(STLSharp spec1, STLSharp spec2, Map<String, String> signals, BigDecimal maxCompatibilityThreshold) {
        Set<String> signals1 = signals.keySet();
        Set<String> signals2 = new HashSet<String>();
        for (String value : signals.values()) {
            signals2.add(value);
        }
        BigDecimal totalArea = computeArea(spec1, signals1).add(computeArea(spec2, signals2));
        List<Map<String, Set<Box>>> listOfBoxes1 = nodeToBoxes(spec1.toSTL(false), spec1.limitsMap);
        List<Map<String, Set<Box>>> listOfBoxes2 = nodeToBoxes(spec2.toSTL(false), spec2.limitsMap);
        Set<String> sigs = new HashSet<String>();
        int counter = 0;
        for (String key : signals.keySet()) {
            String sig = "" + counter;
            for (Map<String, Set<Box>> boxMap : listOfBoxes1) {
                boxMap.put(sig, boxMap.get(key));
                boxMap.remove(key);
            }
            for (Map<String, Set<Box>> boxMap : listOfBoxes2) {
                boxMap.put(sig, boxMap.get(signals.get(key)));
                boxMap.remove(signals.get(key));
            }
            sigs.add(sig);
            counter ++;
        }
        BigDecimal differenceArea = computeDistance(listOfBoxes1, listOfBoxes2, sigs);
        return maxCompatibilityThreshold.compareTo(differenceArea.divide(totalArea)) >= 0;
    }
    
    public BigDecimal computeArea(STLSharp spec) {
        List<Map<String, Set<Box>>> listOfBoxes = nodeToBoxes(spec.toSTL(false), spec.limitsMap);
        Set<String> signals = new HashSet<String>();
        for (Map<String, Set<Box>> boxMap : listOfBoxes) {
            for (String key : boxMap.keySet()) {
                signals.add(key);
            }
        }
        return computeArea(listOfBoxes, signals);
    }
    
    public BigDecimal computeArea(STLSharp spec, Set<String> signals) {
        return computeArea(nodeToBoxes(spec.toSTL(false), spec.limitsMap), signals);
    }
    
    private BigDecimal computeArea(List<Map<String, Set<Box>>> listOfBoxes, Set<String> signals) {
        BigDecimal area = new BigDecimal(0.0);
        for (Map<String, Set<Box>> boxMap : listOfBoxes) {
            BigDecimal boxSetArea = new BigDecimal(0.0);
            for (String var : boxMap.keySet()) {
                for (Box box : boxMap.get(var)) {
                    boxSetArea = boxSetArea.add((box.getUpperTime().subtract(box.getLowerTime())).multiply((box.getUpperBound().subtract(box.getLowerBound()))));
                }
            }
            area = max(area, boxSetArea);
        }
        return area;
    }

    private List<Map<String, Set<Box>>> nodeToBoxes(TreeNode node, HashMap<String, HashMap<String, Double>> limitsMap) {
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
                return new ArrayList<Map<String, Set<Box>>>();
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
                    List<Map<String, Set<Box>>> listofBoxMaps = new ArrayList<Map<String, Set<Box>>>();
                    listofBoxMaps.add(boxMap);
                    return listofBoxMaps;
                }
                break;
            case ALWAYS:
                AlwaysNode always = (AlwaysNode) node;
                List<Map<String, Set<Box>>> listofBoxMaps = nodeToBoxes(always.child, limitsMap);
                for (Map<String, Set<Box>> boxMap : listofBoxMaps) {
                    for (String key : boxMap.keySet()) {
                        for (Box b : boxMap.get(key)) {
                            b.setLowerTime(new BigDecimal(always.low).add(b.getLowerTime()));
                            b.setUpperTime(new BigDecimal(always.high).add(b.getUpperTime()));
                        }
                    }
                }
                return listofBoxMaps;
            case EVENT:
                EventNode event = (EventNode) node;
                if (threshold.doubleValue() > (event.high - event.low)) {
                    TreeNode newNode = new AlwaysNode(event.child, event.low, event.low + threshold.doubleValue());
                    for (double i = event.low + threshold.doubleValue(); i < event.high; i += threshold.doubleValue()) {
                        newNode = new DisjunctionNode(newNode, new AlwaysNode(event.child, i, Math.min(i + threshold.doubleValue(), event.high)));
                    }
                    return nodeToBoxes(newNode, limitsMap);
                }
                else {
                    return nodeToBoxes(new AlwaysNode(event.child, event.low, event.high), limitsMap);
                }
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
                    List<Map<String, Set<Box>>> orBoxes = new ArrayList<Map<String, Set<Box>>>();
                    orBoxes.addAll(nodeToBoxes(((ModuleNode) node).left, limitsMap));
                    orBoxes.addAll(nodeToBoxes(((ModuleNode) node).right, limitsMap));
                    return orBoxes;
                }
                else {
                    List<Map<String, Set<Box>>> orBoxes = new ArrayList<Map<String, Set<Box>>>();
                    orBoxes.addAll(nodeToBoxes(((DisjunctionNode) node).left, limitsMap));
                    orBoxes.addAll(nodeToBoxes(((DisjunctionNode) node).right, limitsMap));
                    return orBoxes;
                }
            case NOT:
                break;
            case NOP:
                return new ArrayList<Map<String, Set<Box>>>();
        }
        return new ArrayList<Map<String, Set<Box>>>();
    }
    
    private List<Map<String, Set<Box>>> mergeBoxes(List<Map<String, Set<Box>>> leftBoxes, List<Map<String, Set<Box>>> rightBoxes, Operation op) {
        List<Map<String, Set<Box>>> listOfBoxMaps = new ArrayList<Map<String, Set<Box>>>();
        for (Map<String, Set<Box>> boxMapLeft : leftBoxes) {
            for (Map<String, Set<Box>> boxMapRight : rightBoxes) {
                listOfBoxMaps.add(mergeBoxesHelper(boxMapLeft, boxMapRight, op));
            }
        }
        return listOfBoxMaps;
    }

    private Map<String, Set<Box>> mergeBoxesHelper(Map<String, Set<Box>> leftBoxes, Map<String, Set<Box>> rightBoxes, Operation op) {
        Map<String, Set<Box>> boxMap = new HashMap<String, Set<Box>>();
        for (String key : leftBoxes.keySet()) {
            if (rightBoxes.containsKey(key)) {
                Set<Box> boxes = new HashSet<Box>();
                List<Box> orOverlapBoxes = new ArrayList<Box>();
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
//                                else if (op == Operation.OR) {
//                                    modifyOrOverlapBoxes(orOverlapBoxes, right.getLowerTime(), left.getUpperTime());
//                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
//                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
//                                        orOverlapBoxes.add(new Box(right.getLowerTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
//                                    }
//                                    else {
//                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                }
                                else if (op == Operation.NOP) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                    }
                                    else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(right.getLowerTime(), left.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                ((LinkedList) leftQueue).add(0, new Box(right.getLowerTime(), left.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                            }
                                        }
                                        left = new Box(right.getLowerTime(), left.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                    }
                                    else {
                                        temp.add(new Box(right.getLowerTime(), left.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(right.getLowerTime(), left.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                left = new Box(right.getLowerTime(), left.getUpperTime(), right.getUpperBound(), left.getUpperBound());
                                            }
                                        }
                                    }
                                }
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                            }
                            else {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
//                                else if (op == Operation.OR) {
//                                    modifyOrOverlapBoxes(orOverlapBoxes, right.getLowerTime(), right.getUpperTime());
//                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
//                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
//                                        orOverlapBoxes.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
//                                    }
//                                    else {
//                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                }
                                else if (op == Operation.NOP) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                    }
                                    else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                ((LinkedList) leftQueue).add(0, new Box(right.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                            }
                                        }
                                        left = new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                    }
                                    else {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                left = new Box(right.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound());
                                            }
                                        }
                                    }
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
//                                else if (op == Operation.OR) {
//                                    modifyOrOverlapBoxes(orOverlapBoxes, left.getLowerTime(), left.getUpperTime());
//                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
//                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
//                                        orOverlapBoxes.add(new Box(left.getLowerTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
//                                    }
//                                    else {
//                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                }
                                else if (op == Operation.NOP) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                    }
                                    else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(left.getLowerTime(), left.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                ((LinkedList) leftQueue).add(0, new Box(left.getLowerTime(), left.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                            }
                                        }
                                        left = new Box(left.getLowerTime(), left.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                    }
                                    else {
                                        temp.add(new Box(left.getLowerTime(), left.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(left.getLowerTime(), left.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                left = new Box(left.getLowerTime(), left.getUpperTime(), right.getUpperBound(), left.getUpperBound());
                                            }
                                        }
                                    }
                                }
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                            }
                            else {
                                if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                }
//                                else if (op == Operation.OR) {
//                                    modifyOrOverlapBoxes(orOverlapBoxes, left.getLowerTime(), right.getUpperTime());
//                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
//                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
//                                        orOverlapBoxes.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
//                                    }
//                                    else {
//                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
//                                    }
//                                }
                                else if (op == Operation.NOP) {
                                    if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                    }
                                    else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                ((LinkedList) leftQueue).add(0, new Box(left.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                            }
                                        }
                                        left = new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                    }
                                    else {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                            temp.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        }
                                        else {
                                            if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                                left = new Box(left.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound());
                                            }
                                        }
                                    }
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
                for (Box b : orOverlapBoxes) {
                    boxes.add(b);
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
    
    private void modifyOrOverlapBoxes(List<Box> orOverlapBoxes, BigDecimal lower, BigDecimal upper) {
        for (int i = 0; i < orOverlapBoxes.size(); i++) {
            Box b = orOverlapBoxes.get(i);
            if (!(b.getUpperTime().compareTo(lower) <= 0 || upper.compareTo(b.getLowerTime()) <= 0)) {
                if (b.getLowerTime().compareTo(lower) < 0) {
                    orOverlapBoxes.set(i, new Box(new BigDecimal(b.getLowerTime().doubleValue()), new BigDecimal(lower.doubleValue()), new BigDecimal(b.getLowerBound().doubleValue()), new BigDecimal(b.getUpperBound().doubleValue())));
                    if (b.getUpperTime().compareTo(upper) > 0) {
                        orOverlapBoxes.add(i+1, new Box(new BigDecimal(upper.doubleValue()), new BigDecimal(b.getUpperTime().doubleValue()), new BigDecimal(b.getLowerBound().doubleValue()), new BigDecimal(b.getUpperBound().doubleValue())));
                        i++;
                    }
                }
                else if (b.getUpperTime().compareTo(upper) > 0) {
                    orOverlapBoxes.set(i, new Box(new BigDecimal(upper.doubleValue()), new BigDecimal(b.getUpperTime().doubleValue()), new BigDecimal(b.getLowerBound().doubleValue()), new BigDecimal(b.getUpperBound().doubleValue())));
                }
                else {
                    orOverlapBoxes.remove(i);
                    i--;
                }
            }
        }
    }

    private BigDecimal max(BigDecimal num1, BigDecimal num2) {
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

    private BigDecimal min(BigDecimal num1, BigDecimal num2) {
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

        private BigDecimal lowerTime, upperTime, lowerBound, upperBound;

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
        
        public void setLowerTime(BigDecimal lowerTime) {
            this.lowerTime = lowerTime;
        }

        public void setUpperTime(BigDecimal upperTime) {
            this.upperTime = upperTime;
        }

        public void setLowerBound(BigDecimal lowerBound) {
            this.lowerBound = lowerBound;
        }

        public void setUpperBound(BigDecimal upperBound) {
            this.upperBound = upperBound;
        }

    }
}
