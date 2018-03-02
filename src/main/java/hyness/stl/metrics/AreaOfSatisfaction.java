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
//import javax.swing.JFrame;

/**
 *
 * @author ckmadsen
 */
public class AreaOfSatisfaction {
    
    public BigDecimal computeDistance(STLSharp spec1, STLSharp spec2, boolean ignoreInternal, int eventuallySteps) {
        Map<String, Set<Box>> boxes1 = nodeToBoxes(spec1.toSTL(ignoreInternal), spec1.limitsMap, eventuallySteps);
        Map<String, Set<Box>> boxes2 = nodeToBoxes(spec2.toSTL(ignoreInternal), spec2.limitsMap, eventuallySteps);
        Set<String> signals = new HashSet<String>();
        for (String key : boxes1.keySet()) {
            signals.add(key);
        }
        for (String key : boxes2.keySet()) {
            if (!signals.contains(key)) {
                signals.add(key);
            }
        }
        return computeDistance(boxes1, boxes2, signals);
    }
    
    public BigDecimal computeDistance(STLSharp spec1, STLSharp spec2, boolean ignoreInternal, Set<String> signals, int eventuallySteps) {
        return computeDistance(nodeToBoxes(spec1.toSTL(ignoreInternal), spec1.limitsMap, eventuallySteps), nodeToBoxes(spec2.toSTL(ignoreInternal), spec2.limitsMap, eventuallySteps), signals);
    }
    
    private BigDecimal computeDistance(Map<String, Set<Box>> boxes1, Map<String, Set<Box>> boxes2, Set<String> signals) {
        List<Map<String, Set<Box>>> boxes = mergeBoxes(boxes1, boxes2, Operation.NOP);
        Map<String, Set<Box>> distinctBoxes = boxes.get(0);
        Map<String, Set<Box>> overlapBoxes = boxes.get(1);
        double overlap = computeArea(overlapBoxes, signals).doubleValue();
        double distinct = computeArea(distinctBoxes, signals).doubleValue();
//        System.out.println("Distinct:");
//        for (String var : distinctBoxes.keySet()) {
//            if (signals.contains(var)) {
//                for (Box box : distinctBoxes.get(var)) {
//                    System.out.println("x(" + box.getLowerTime().doubleValue() + ", " + box.getUpperTime().doubleValue() + "); y(" + box.getLowerBound().doubleValue() + ", " + box.getUpperBound().doubleValue() + ")");
//                }
//            }
//        }
//        System.out.println();
//        System.out.println("Overlap:");
//        for (String var : overlapBoxes.keySet()) {
//            if (signals.contains(var)) {
//                for (Box box : overlapBoxes.get(var)) {
//                    System.out.println("x(" + box.getLowerTime().doubleValue() + ", " + box.getUpperTime().doubleValue() + "); y(" + box.getLowerBound().doubleValue() + ", " + box.getUpperBound().doubleValue() + ")");
//                }
//            }
//        }
//        System.out.println();
//        System.out.println("Distance:");
//        System.out.println(result.doubleValue());
//        System.out.println();

//        return new BigDecimal((overlap - distinct)/(overlap + distinct));
        return new BigDecimal(distinct/(overlap + distinct));
    }
    
    public boolean computeCompatibility(STLSharp spec1, STLSharp spec2, Map<String, String> signals, double maxCompatibilityThreshold, int eventuallySteps) {
        Set<String> signals1 = signals.keySet();
        Set<String> signals2 = new HashSet<String>();
        for (String value : signals.values()) {
            signals2.add(value);
        }
        double totalArea = computeArea(spec1, signals1, eventuallySteps).doubleValue() + computeArea(spec2, signals2, eventuallySteps).doubleValue();
        Map<String, Set<Box>> boxes1 = nodeToBoxes(spec1.toSTL(false), spec1.limitsMap, eventuallySteps);
        Map<String, Set<Box>> boxes2 = nodeToBoxes(spec2.toSTL(false), spec2.limitsMap, eventuallySteps);
        Map<String, Set<Box>> modifiedBoxes1 = new HashMap<String, Set<Box>>();
        Map<String, Set<Box>> modifiedBoxes2 = new HashMap<String, Set<Box>>();
        Set<String> sigs = new HashSet<String>();
        int counter = 0;
        for (String key : signals.keySet()) {
            String sig = "" + counter;
            modifiedBoxes1.put(sig, boxes1.get(key));
            modifiedBoxes2.put(sig, boxes2.get(signals.get(key)));
            sigs.add(sig);
            counter ++;
        }
        double differenceArea = computeDistance(modifiedBoxes1, modifiedBoxes2, sigs).doubleValue();
        return maxCompatibilityThreshold >= (differenceArea / totalArea);
    }
    
    public BigDecimal computeArea(STLSharp spec, int eventuallySteps) {
        Map<String, Set<Box>> boxes = nodeToBoxes(spec.toSTL(false), spec.limitsMap, eventuallySteps);
        Set<String> signals = new HashSet<String>();
        for (String key : boxes.keySet()) {
            signals.add(key);
        }
        return computeArea(boxes, signals);
    }
    
    public BigDecimal computeArea(STLSharp spec, Set<String> signals, int eventuallySteps) {
        return computeArea(nodeToBoxes(spec.toSTL(false), spec.limitsMap, eventuallySteps), signals);
    }
    
    private BigDecimal computeArea(Map<String, Set<Box>> boxes, Set<String> signals) {
        BigDecimal area = new BigDecimal(0.0);
        for (String var : boxes.keySet()) {
            if (signals.contains(var)) {
                for (Box box : boxes.get(var)) {
                    area = area.add(box.getUpperTime().subtract(box.getLowerTime()).multiply(box.getUpperBound().subtract(box.getLowerBound())));
                }
            }
        }
//        JFrame frame = new JFrame();
//        frame.setSize(1200,1200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        for (String var : boxes.keySet()) {
//            for (Box box : boxes.get(var)) {
//                int xmin = (int) box.getLowerTime().doubleValue();
//                int xmax = (int) box.getUpperTime().doubleValue();
//                int ymin = (int) box.getLowerBound().doubleValue();
//                int ymax = (int) box.getUpperBound().doubleValue();
//                frame.getGraphics().drawRect(xmin, 800 - ymax, xmax - xmin, ymax - ymin);
//            }
//        }
        return area;
    }

    private Map<String, Set<Box>> nodeToBoxes(TreeNode node, HashMap<String, HashMap<String, Double>> limitsMap, int eventuallySteps) {
        switch (node.op) {
//            case CONCAT:
//                // TODO: Special case of shift and conjunction
//                break;
//            case PARALLEL:
//                // TODO: Special case of conjunction
//                break;
//            case JOIN:
//                // TODO: Special case of parallel
//                break;
//            case IMPLIES:
//                // TODO: Special case of conjunction
//                break;
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
                Map<String, Set<Box>> childBoxMap = nodeToBoxes(always.child, limitsMap, eventuallySteps);
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
                EventNode event = (EventNode) node;
                double eventuallyStepSize = (event.high - event.low) / eventuallySteps;
                TreeNode abstraction = new AlwaysNode(event.child, event.low, Double.min(event.low+eventuallyStepSize, event.high));
                if (event.low+eventuallyStepSize < event.high) {
                    for (double i = event.low+eventuallyStepSize; i < event.high; i += eventuallyStepSize) {
                        abstraction = new DisjunctionNode(abstraction, new AlwaysNode(event.child, i, Double.min(i+eventuallyStepSize, event.high)));
                    }
                }
                return nodeToBoxes(abstraction, limitsMap, eventuallySteps);
            case UNTIL:
                // TODO: Figure out how to convert to boxes
                break;
            case AND:
                if (node instanceof ModuleNode) {
                    return mergeBoxes(nodeToBoxes(((ModuleNode) node).left, limitsMap, eventuallySteps), nodeToBoxes(((ModuleNode) node).right, limitsMap, eventuallySteps), Operation.AND).get(0);
                }
                else {
                    return mergeBoxes(nodeToBoxes(((ConjunctionNode) node).left, limitsMap, eventuallySteps), nodeToBoxes(((ConjunctionNode) node).right, limitsMap, eventuallySteps), Operation.AND).get(0);
                }
            case OR:
                if (node instanceof ModuleNode) {
                    return mergeBoxes(nodeToBoxes(((ModuleNode) node).left, limitsMap, eventuallySteps), nodeToBoxes(((ModuleNode) node).right, limitsMap, eventuallySteps), Operation.OR).get(0);
                }
                else {
                    return mergeBoxes(nodeToBoxes(((DisjunctionNode) node).left, limitsMap, eventuallySteps), nodeToBoxes(((DisjunctionNode) node).right, limitsMap, eventuallySteps), Operation.OR).get(0);
                }
            case NOT:
                // Not currently handled
                break;
            case NOP:
                return new HashMap<String, Set<Box>>();
        }
        return new HashMap<String, Set<Box>>();
    }

    /**
     *
     * This method will take two sets of boxes and merge them together based on an operator.
     * For conjunction, the set of merged boxes contains the intersection of the satifaction areas
     * for overlapping time windows plus the satisfaction areas of the non-overlapping areas.
     * For disjuntion, the set of merged boxes contains the union of the satifaction areas
     * for overlapping time windows plus the satisfaction areas of the non-overlapping areas.
     * For NOP, two sets are returned: a set of overlapping satifaction areas and a set of non-overlapping
     * satisfaction areas (index 0 and index 1, respectively).
     * 
     * @param leftBoxes - the satisfaction areas of the expression on the left
     * @param rightBoxes - the satisfaction areas of the expression on the right
     * @param op - the operator (AND, OR, or NOP)
     * @return a list of satisfaction boxes representing the merger of the satisaction areas from each side of the expression
     */
    private List<Map<String, Set<Box>>> mergeBoxes(Map<String, Set<Box>> leftBoxes, Map<String, Set<Box>> rightBoxes, Operation op) {
        List<Map<String, Set<Box>>> boxMap = new ArrayList<Map<String, Set<Box>>>();
        boxMap.add(new HashMap<String, Set<Box>>());
        if (op == Operation.NOP) {
            boxMap.add(new HashMap<String, Set<Box>>());
        }
        for (String key : leftBoxes.keySet()) {
            if (rightBoxes.containsKey(key)) {
                Set<Box> boxes = new HashSet<Box>();
                Set<Box> nopBoxes = null;
                if (op == Operation.NOP) {
                    nopBoxes = new HashSet<Box>();
                }
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
                    Queue<Box> temp = new LinkedList<Box>();
                    boolean overlapped = false;
                    while (!rightQueue.isEmpty()) {
                        Box right = rightQueue.poll();
                        if (left.getUpperTime().compareTo(right.getLowerTime()) <= 0 || right.getUpperTime().compareTo(left.getLowerTime()) <= 0) {
                            temp.add(right);
                        }
                        else if (left.getLowerTime().compareTo(right.getLowerTime()) <= 0) {
                            if (left.getLowerTime().compareTo(right.getLowerTime()) != 0) {
                                leftQueue.add(new Box(left.getLowerTime(), right.getLowerTime(), left.getLowerBound(), left.getUpperBound()));
                            }
                            if (left.getUpperTime().compareTo(right.getUpperTime()) > 0) {
                                leftQueue.add(new Box(right.getUpperTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                left = new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound());
                            }
                            else if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                right = new Box(right.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound());
                                left = new Box(right.getLowerTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound());
                            }
                            else {
                                left = new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound());
                            }
                            if(left.getLowerTime().doubleValue() == 80) {
                                System.out.print("");
                            }
                            if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                overlapped = true;
                                temp.add(new Box(right.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                left = new Box(right.getLowerTime(), right.getUpperTime(), new BigDecimal(-1), new BigDecimal(-1));
                            }
                            else if (op == Operation.OR) {
                                if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                }
                                else {
                                    overlapped = true;
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                }
                            }
                            else if (op == Operation.NOP) {
                                if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                }
                                else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                    if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        addBoxToSet(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getUpperBound()), nopBoxes);
                                    }
                                    else {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                            ((LinkedList) leftQueue).add(new Box(right.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                        }
                                        addBoxToSet(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()), nopBoxes);
                                    }
                                    left = new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                }
                                else {
                                    temp.add(new Box(right.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                    if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                        temp.add(new Box(right.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        addBoxToSet(new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()), nopBoxes);
                                    }
                                    else {
                                        addBoxToSet(new Box(right.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getUpperBound()), nopBoxes);
                                    }
                                    left = new Box(right.getLowerTime(), right.getUpperTime(), right.getUpperBound(), max(left.getUpperBound(), right.getUpperBound()));
                                }
                            }
                        }
                        else {
                            temp.add(new Box(right.getLowerTime(), left.getLowerTime(), right.getLowerBound(), right.getUpperBound()));
                            if (left.getUpperTime().compareTo(right.getUpperTime()) > 0) {
                                leftQueue.add(new Box(right.getUpperTime(), left.getUpperTime(), left.getLowerBound(), left.getUpperBound()));
                                right = new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound());
                                left = new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound());
                            }
                            if (left.getUpperTime().compareTo(right.getUpperTime()) < 0) {
                                temp.add(new Box(left.getUpperTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                right = new Box(left.getLowerTime(), left.getUpperTime(), right.getLowerBound(), right.getUpperBound());
                            }
                            if (op == Operation.AND && !(left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0)) {
                                overlapped = true;
                                temp.add(new Box(left.getLowerTime(), right.getUpperTime(), max(left.getLowerBound(), right.getLowerBound()), min(left.getUpperBound(), right.getUpperBound())));
                                left = new Box(left.getLowerTime(), right.getUpperTime(), new BigDecimal(-1), new BigDecimal(-1));
                            }
                            else if (op == Operation.OR) {
                                if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                }
                                else {
                                    overlapped = true;
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), min(left.getLowerBound(), right.getLowerBound()), max(left.getUpperBound(), right.getUpperBound())));
                                }
                            }
                            else if (op == Operation.NOP) {
                                if (left.getUpperBound().compareTo(right.getLowerBound()) <= 0 || right.getUpperBound().compareTo(left.getLowerBound()) <= 0) {
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()));
                                }
                                else if (left.getLowerBound().compareTo(right.getLowerBound()) <= 0) {
                                    if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        addBoxToSet(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getUpperBound()), nopBoxes);
                                    }
                                    else {
                                        if (left.getUpperBound().compareTo(right.getUpperBound()) != 0) {
                                            ((LinkedList) leftQueue).add(new Box(left.getLowerTime(), right.getUpperTime(), right.getUpperBound(), left.getUpperBound()));
                                        }
                                        addBoxToSet(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), right.getUpperBound()), nopBoxes);
                                    }
                                    left = new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getLowerBound());
                                }
                                else {
                                    temp.add(new Box(left.getLowerTime(), right.getUpperTime(), right.getLowerBound(), left.getLowerBound()));
                                    if (left.getUpperBound().compareTo(right.getUpperBound()) < 0) {
                                        temp.add(new Box(left.getLowerTime(), right.getUpperTime(), left.getUpperBound(), right.getUpperBound()));
                                        addBoxToSet(new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), left.getUpperBound()), nopBoxes);
                                    }
                                    else {
                                        addBoxToSet(new Box(left.getLowerTime(), right.getUpperTime(), left.getLowerBound(), right.getUpperBound()), nopBoxes);
                                    }
                                    left = new Box(left.getLowerTime(), right.getUpperTime(), right.getUpperBound(), max(left.getUpperBound(), right.getUpperBound()));
                                }
                            }
                        }
                    }
                    if (!overlapped) {
                        addBoxToSet(left, boxes);
                    }
                    rightQueue.addAll(temp);
                }
                while (!rightQueue.isEmpty()) {
                    addBoxToSet(rightQueue.poll(), boxes);
                }
                boxMap.get(0).put(key, boxes);
                if (op == Operation.NOP) {
                    boxMap.get(1).put(key, nopBoxes);
                }
            }
            else {
                boxMap.get(0).put(key, leftBoxes.get(key));
            }
        }
        for (String key : rightBoxes.keySet()) {
            if (!leftBoxes.containsKey(key)) {
                boxMap.get(0).put(key, rightBoxes.get(key));
            }
        }
        return boxMap;
    }
    
    private void addBoxToSet(Box box, Set<Box> set) {
        if (box.getLowerTime().compareTo(box.getUpperTime()) < 0 && box.getLowerBound().compareTo(box.getUpperBound()) < 0) {
            Set<Box> temp = new HashSet<Box>();
            for (Box b : set) {
                if (box.isOverlapped(b)) {
                    if (box.getLowerTime().compareTo(b.getLowerTime()) < 0) {
                        temp.add(new Box(box.getLowerTime(), b.getLowerTime(), box.getLowerBound(), box.getUpperBound()));
                    }
                    if (box.getUpperTime().compareTo(b.getUpperTime()) > 0) {
                        temp.add(new Box(b.getUpperTime(), box.getUpperTime(), box.getLowerBound(), box.getUpperBound()));
                    }
                    box = new Box(b.getLowerTime(), b.getUpperTime(), box.getLowerBound(), box.getUpperBound());
                    if (box.getLowerBound().compareTo(b.getLowerBound()) < 0) {
                        temp.add(new Box(box.getLowerTime(), box.getUpperTime(), box.getLowerBound(), b.getLowerBound()));
                        box = new Box(box.getLowerTime(), box.getUpperTime(), b.getLowerBound(), box.getUpperBound());
                    }
                    if (box.getUpperBound().compareTo(b.getUpperBound()) > 0) {
                        box = new Box(box.getLowerTime(), box.getUpperTime(), b.getUpperBound(), box.getUpperBound());
                    }
                    else {
                        box = new Box(box.getLowerTime(), box.getUpperTime(), box.getUpperBound(), box.getUpperBound());
                    }

                }
            }
            temp.add(box);
            set.addAll(temp);
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

        private final BigDecimal lowerTime, upperTime, lowerBound, upperBound;

        public Box(BigDecimal lowerTime, BigDecimal upperTime, BigDecimal lowerBound, BigDecimal upperBound) {
            this.lowerTime = lowerTime;
            this.upperTime = upperTime;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
        
        public boolean isOverlapped(Box box) {
            return !(upperTime.compareTo(box.getLowerTime()) <= 0 || box.getUpperTime().compareTo(lowerTime) <= 0) &&
                    !(upperBound.compareTo(box.getLowerBound()) <= 0 || box.getUpperBound().compareTo(lowerBound) <= 0);
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
