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
public class AlwaysNode extends TemporalUnaryNode {
    
    public AlwaysNode(TreeNode child, int l, int h) {
        super(Operation.ALWAYS, child, l, h);
    }
    
    @Override
    public double robustness(Trace s, double t) {
        double value = maximumRobustness;
        double r;
        for(int tu=(int) (t+this.low); tu <= (int) (t+this.high); tu++) {
            r = this.child.robustness(s, tu);
            if(value > r) {
                value = r;
            }
        }
        return value;
    }
    
}
