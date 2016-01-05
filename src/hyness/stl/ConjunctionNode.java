/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

/**
 * @author Cristian-Ioan Vasile
 *
 */
public class ConjunctionNode extends BooleanBinaryNode {

    public ConjunctionNode(TreeNode left, TreeNode right) {
        super(Operation.AND, left, right);
    }

    @Override
    public double robustness(Trace s, double t) {
        return Math.min(left.robustness(s, t), right.robustness(s, t));
    }
    
}
