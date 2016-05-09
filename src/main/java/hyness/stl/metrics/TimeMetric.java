/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.AlwaysNode;
import hyness.stl.BooleanLeaf;
import hyness.stl.ConcatenationNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.NotNode;
import hyness.stl.ParallelNode;
import hyness.stl.TreeNode;
import hyness.stl.UntilNode;

/**
 *
 * @author prash
 */
public class TimeMetric {
    
    
    public static double computeTimeHorizon(TreeNode node){
        double time = 0;
        
        if(node instanceof LinearPredicateLeaf){
            return 0;
        }
        if(node instanceof BooleanLeaf){
            return 0;
        }
        if(node instanceof ModuleNode){
            ModuleNode mnode = (ModuleNode)node;
            switch(mnode.op){
                //NOP, NOT, OR, AND, UNTIL, EVENT, ALWAYS, PRED, IMPLIES, BOOL, CONCAT, PARALLEL;
                case AND:
                case OR:
                    return max(computeTimeHorizon(mnode.left),computeTimeHorizon(mnode.right));
                case IMPLIES:
                    return max(computeTimeHorizon(mnode.left.negate()), computeTimeHorizon(mnode.right));
                case CONCAT:
                    return (computeTimeHorizon(mnode.left) + computeTimeHorizon(mnode.right));
                case PARALLEL:
                    return max(computeTimeHorizon(mnode.left),computeTimeHorizon(mnode.right));
                default:
                    break;
            }
            
        }
        if(node instanceof ConjunctionNode){
            ConjunctionNode cnode = (ConjunctionNode)node;
            return max(computeTimeHorizon(cnode.left),computeTimeHorizon(cnode.right));
        }
        if(node instanceof DisjunctionNode){
            DisjunctionNode dnode = (DisjunctionNode)node;
            return max(computeTimeHorizon(dnode.left),computeTimeHorizon(dnode.right));
        }
        if(node instanceof NotNode){
            return (computeTimeHorizon(((NotNode)node).child));
        }
        if(node instanceof UntilNode){
            UntilNode unode = (UntilNode)node;
            return (unode.high  + max(computeTimeHorizon(unode.left),computeTimeHorizon(unode.right)));
        }
        if(node instanceof EventNode){
            EventNode enode = (EventNode)node;
            return (enode.high + computeTimeHorizon(enode.child)); 
        }
        if(node instanceof AlwaysNode){
            AlwaysNode anode = (AlwaysNode)node;
            return (anode.high + computeTimeHorizon(anode.child));
        }
        if(node instanceof ConcatenationNode){
            ConcatenationNode cnode = (ConcatenationNode)node;
            return (computeTimeHorizon(cnode.left) + computeTimeHorizon(cnode.right));
        }
        if(node instanceof ParallelNode){
            ParallelNode pnode = (ParallelNode)node;
            return max(computeTimeHorizon(pnode.left),computeTimeHorizon(pnode.right));
        }
        return time;
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
}
