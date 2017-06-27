/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.area;

import hyness.stl.ConjunctionNode;
import hyness.stl.ModuleNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ckmadsen
 */
public class AreaBoxes {
    
    private Map<String, List<Box>> boxes;
    
    public AreaBoxes(STLflat spec) {
        boxes = nodeToBoxes(spec.toSTL(false), spec.limitsMap);
    }
    
    private Map<String, List<Box>> nodeToBoxes(TreeNode node, HashMap<String, HashMap<String, Double>> limitsMap) {
        switch (node.op) {
            case CONCAT:
                break;
            case PARALLEL:
                break;
            case JOIN:
                break;
            case IMPLIES:
                break;
            case BOOL:
                break;
            case PRED:
                break;
            case ALWAYS:
                break;
            case EVENT:
                break;
            case UNTIL:
                break;
            case AND:
                if (node instanceof ModuleNode) {
                    nodeToBoxes(((ModuleNode) node).left, limitsMap);
                    nodeToBoxes(((ModuleNode) node).right, limitsMap);
                }
                else {
                    nodeToBoxes(((ConjunctionNode) node).left, limitsMap);
                    nodeToBoxes(((ConjunctionNode) node).right, limitsMap);
                }
                break;
            case OR:
                break;
            case NOT:
                break;
            case NOP:
                break;
        }
        return null;
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
