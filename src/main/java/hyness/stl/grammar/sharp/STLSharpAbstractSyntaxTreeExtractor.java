/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.sharp;

import hyness.stl.AlwaysNode;
import hyness.stl.ConcatenationNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.ImplicationNode;
import hyness.stl.JoinNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.ModuleLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.NotNode;
import hyness.stl.Operation;
import hyness.stl.Pair;
import hyness.stl.ParallelNode;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import hyness.stl.UntilNode;
import hyness.stl.metrics.HausdorffDistanceMetric;
import java.util.HashMap;
import java.util.Vector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;


/**
 * 
 * @author Cristian-Ioan Vasile
 */
public class STLSharpAbstractSyntaxTreeExtractor extends STLSharpBaseVisitor<TreeNode>{
    
    public STLSharp spec = null;
    
    public static double maximumRobustness = 1e12;
    
    @Override
    public TreeNode visitSpecification(STLSharpParser.SpecificationContext ctx) {
        TreeNode module = visit(ctx.spec);
        spec = new STLSharp(module);
//        System.out.println("Spec: " + module);
        
        // add module descriptions
        for(STLSharpParser.ModuleDescriptionContext mctx : ctx.moduleDescription()) {
            String moduleName = mctx.moduleName.getText();
//            System.out.println("Module: " + moduleName + " = " + visit(mctx.moduleFormula));
            spec.modules.put(moduleName, visit(mctx.moduleFormula));
        }
        // add translation maps
        for(STLSharpParser.TranslationMapContext tctx : ctx.translationMap()) {
            String mapName = tctx.tmapName.getText();
//            System.out.println("Map: " + mapName);
            HashMap<Pair<String, Boolean>, String> map = new HashMap<Pair<String, Boolean>, String>();
            // populate map with translation pairs
            for(STLSharpParser.TranslationPairContext tpctx : tctx.translationPair()) {
                Pair<String, Boolean> key;
                Boolean side = null;
                if(tpctx.moduleName != null) {
                    if(tpctx.moduleName.getText().equals("left")) {
                        side = true;
                    } else if(tpctx.moduleName.getText().equals("right")) {
                        side = false;
                    } else {
                        throw new IllegalArgumentException("Expected either left or right side");
                    }
                }
                key = new Pair<String, Boolean>(tpctx.key.getText(), side);
//                System.out.println("Key: " + key + " -> " + tpctx.value.getText());
                map.put(key, tpctx.value.getText());
            }
            spec.maps.put(mapName, map);
        }
        
        // add limit maps
        STLSharpParser.LimitMapContext lctx = ctx.limitMap();
        String lmapName = lctx.lmapName.getText(); //Relevant?
        for(STLSharpParser.LimitPairContext lpctx : lctx.limitPair()){
            double maxVal = Double.valueOf(lpctx.maxValue.getText());
            double minVal = Double.valueOf(lpctx.minValue.getText());
            String signal = lpctx.sigName.getText();
            HashMap<String,Double> maxmin = new HashMap<String,Double>();
            maxmin.put("max", maxVal);
            maxmin.put("min", minVal);
            spec.limitsMap.put(signal, maxmin);
        }
        
        return null;
    }
    
    @Override
    public TreeNode visitModuleOp(STLSharpParser.ModuleOpContext ctx) {
        Operation op = Operation.getCode(ctx.op.getText());
        String map = ctx.tmap.getText();
        return new ModuleNode(op, visit(ctx.left), visit(ctx.right), map);
    }
    
    @Override
    public TreeNode visitModuleLeaf(STLSharpParser.ModuleLeafContext ctx) {
        String name = ctx.moduleName.getText();
        Vector<String> signals = new Vector<String>();
        // add signal names to vector
        for(TerminalNode var : ctx.VARIABLE()) {
            signals.add(var.getText());
        }
        return new ModuleLeaf(name, signals);
    }
    
    public TreeNode visitFormula(STLSharpParser.FormulaContext ctx) {
        Operation op = Operation.getCode(ctx.op.getText());
        TreeNode ret = null;
        double low = -1, high = -1;
        switch(op) {
            case OR:
                ret = new DisjunctionNode(visit(ctx.left), visit(ctx.right));
                break;
            case AND:
                ret = new ConjunctionNode(visit(ctx.left), visit(ctx.right));
                break;
            case IMPLIES:
                ret = new ImplicationNode(visit(ctx.left), visit(ctx.right));
                break;
            case NOT:
                ret = new NotNode(visit(ctx.child));
                break;
            case UNTIL:
                low = Double.valueOf(ctx.low.getText());
                high = Double.valueOf(ctx.high.getText());
                ret = new UntilNode(visit(ctx.left), visit(ctx.right), low, high);
                break;
            case EVENT:
                low = Double.valueOf(ctx.low.getText());
                high = Double.valueOf(ctx.high.getText());
                ret = new EventNode(visit(ctx.child), low, high);
                break;
            case ALWAYS:
                low = Double.valueOf(ctx.low.getText());
                high = Double.valueOf(ctx.high.getText());
                ret = new AlwaysNode(visit(ctx.child), low, high);
                break;
            case CONCAT:
                ret = new ConcatenationNode(visit(ctx.left), visit(ctx.right));
                break;
            case JOIN:
                ret = new JoinNode(visit(ctx.left), visit(ctx.right));
                break;
            case PARALLEL:
                ret = new ParallelNode(visit(ctx.left),visit(ctx.right));
                break;
            default:
                System.out.println("default");
                break;
        }
        return ret;
    }
    
    public TreeNode visitBooleanPred(STLSharpParser.BooleanPredContext ctx) {
//        System.out.println("BPRED");
        return visit(ctx.booleanExpr());
    }
    
    public TreeNode visitBooleanExpr(STLSharpParser.BooleanExprContext ctx) {
//        System.out.println("BEXPR " + ctx.left.getText() + " " + ctx.right.getText());
        return new LinearPredicateLeaf(RelOperation.getCode(ctx.op.getText()),
                        ctx.left.getText(),
                        Double.valueOf(ctx.right.getText()));
    }
    
    public TreeNode visitParprop(STLSharpParser.ParpropContext ctx) {
//        System.out.println("PARS");
        return visit(ctx.child);
    }
    
    
    public static STLSharpAbstractSyntaxTreeExtractor getSTLSharpAbstractSyntaxTreeExtractor(String spec){
        STLSharpLexer lexer = new STLSharpLexer(new ANTLRInputStream(spec));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        STLSharpParser parser = new STLSharpParser(tokens);
        ParserRuleContext t = parser.specification();
        
        //System.out.println(t.toStringTree(parser) + "\n");
        
        STLSharpAbstractSyntaxTreeExtractor ast = new STLSharpAbstractSyntaxTreeExtractor();
        ast.visit(t);
        return ast;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String spec = "phi1(u1,u2,y1,y2) #_m1 phi2(u1,u2,y1,y2)\n"
                + "\n"
                + "phi1 = (!(u1 < 10) && (u2 > 2)) => (F[0, 2] y1 > 2 || G[1, 3] y2 <= 8)\n"
                + "phi2 = ((u1 >= 1) && (u2 <= 5)) => (G[1, 4] y1 < 7 && F[0, 7] y2 >= 3)\n"
                + "\n"
                + "m1 { u1@left: u1, u2@left: u2, y1@left: a1, y2@left: a2, u1@right: a1, u2@right: a2, y1@right: y1, y2@right: y2 }\n"
                + "io {u1: u1, u2: u2, y1: y}\n"
                + "limits [{u1 : {max:10,min:0}},{u2:{max:10,min:0}},{y1:{min:0,max:10}},{y2:{min:0,max:10}}]\n"
                ;
        STLSharpLexer lexer = new STLSharpLexer(new ANTLRInputStream(spec));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        STLSharpParser parser = new STLSharpParser(tokens);
        ParserRuleContext t = parser.specification();
        
        System.out.println(t.toStringTree(parser) + "\n");
        
        STLSharpAbstractSyntaxTreeExtractor ast = new STLSharpAbstractSyntaxTreeExtractor();
        ast.visit(t);
        
        System.out.println("toSTL AST::\n" + ast.spec.toSTL(false));
    }

}
