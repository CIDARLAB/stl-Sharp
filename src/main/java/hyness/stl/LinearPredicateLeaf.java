/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

/**
 * LinearPredicateLeaf class represents simple linear predicates.
 * 
 * @author Cristian-Ioan Vasile
 *
 */
public class LinearPredicateLeaf extends TreeNode {
    /**
     * Name of the variable used in the predicate.
     */
    public String variable;
    /**
     * Value of the spatial parameter used a threshold in the predicate.
     */
    public double threshold;
    /**
     * Type of inequality used in the predicate.
     */
    public RelOperation rop;
    
    /**
     * 
     * @param rop
     * @param var
     * @param th
     */
    public LinearPredicateLeaf(RelOperation rop, String var, double th) {
        super(Operation.PRED);
        this.rop = rop;
        this.variable = var;
        this.threshold = th;
    }
    
    public double robustness(Trace s, double t) {
        double value = s.eval(this.variable, t);
        switch(this.rop) {
            case LT: case LE:
                value = this.threshold - value;
                break;
            case GT: case GE:
                value = value - this.threshold;
                break;
            case EQ:
                value = -Math.abs(this.threshold - value);
                break;
            default:
                throw new RuntimeException("Unknown relation!");
        }
        return value;
    }
    
    public String toString() {
        return "(" + variable + " " + rop + " " + threshold + ")";
    } 
}
