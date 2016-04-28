/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.distance;

import hyness.stl.ConcatenationNode;
import hyness.stl.ImplicationNode;
import hyness.stl.ModuleNode;
import hyness.stl.ParallelNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
import hyness.stl.grammar.flat.STLflatLexer;
import hyness.stl.grammar.flat.STLflatParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prash
 */
public class DistanceMetricTest {
    
    public static String spec1;
    public static String spec2;
    
    public DistanceMetricTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        spec1 = "phi1(u1,u2,y1,y2) >>_m1 phi2(u1,u2,y1,y2)\n"
                + "\n"
                + "phi1 = (!(u1 < 10) && (u2 > 2)) => (F[0, 2] y1 > 2 || G[1, 3] y2 <= 8)\n"
                + "phi2 = ((u1 >= 1) && (u3 <= 5)) => (G[1, 4] y1 < 7 && F[0, 7] y2 >= 3)\n"
                + "\n"
                + "m1 { u1@left: u1, u2@left: u2, y1@left: a1, y2@left: a2, u1@right: a1, u2@right: a2, y1@right: y1, y2@right: y2 }\n"
                + "io {u1: u1, u2: u2, y1: y}\n";
         
        spec2 = "phi1(u1,u2,y1,y2) >>_m1 phi2(u1,u2,y1,y2)\n"
                + "\n"
                + "phi1 = (!(u1 < 10) && (u2 > 2)) => (F[0, 2] y1 > 2 || G[1, 3] y2 <= 8)\n"
                + "phi2 = ((u1 >= 1) && (u3 <= 5)) => (G[1, 4] y1 < 7 && F[0, 7] y2 >= 3)\n"
                + "\n"
                + "m1 { u1@left: u1, u2@left: u2, y1@left: a1, y2@left: a2, u1@right: a1, u2@right: a2, y1@right: y1, y2@right: y2 }\n"
                + "io {u1: u1, u2: u2, y1: y}\n";
         
         
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of computeDistance method, of class DistanceMetric.
     */
    @Test
    public void testComputeDistance() {
        
        STLflatAbstractSyntaxTreeExtractor stlspec1 = new STLflatAbstractSyntaxTreeExtractor();
        STLflatAbstractSyntaxTreeExtractor stlspec2 = new STLflatAbstractSyntaxTreeExtractor();
        
        stlspec1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec1);
        stlspec2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec2);
        
        DistanceMetric dist = new DistanceMetric();
        
        
        dist.computeDistance(stlspec1.spec, stlspec2.spec);
        
        /*if(stlspec1.spec.module instanceof ModuleNode){
            System.out.println("Spec 1 is a Module Node");
            TreeNode leftname = ((ModuleNode)stlspec1.spec.module).left;
            TreeNode rightname = ((ModuleNode)stlspec1.spec.module).right;
            
            TreeNode left = stlspec1.spec.modules.get(leftname.toString());
            TreeNode right = stlspec1.spec.modules.get(rightname.toString());
            
            System.out.println("stlspec1.spec.module type :: " + stlspec1.spec.module.getClass());
            System.out.println("left :: " + left.getClass());
            System.out.println("left op :: " + left.op);
            System.out.println("left to string :: \n" + left.toString());
            TreeNode leftleft = ((ImplicationNode)left).left;
            TreeNode leftright = ((ImplicationNode)left).right;
            
            System.out.println("leftleft class " + leftleft.getClass());
            System.out.println("leftleft to string :: \n" + leftleft.toString());
            System.out.println("leftright class " + leftright.getClass());
            System.out.println("leftright to string :: \n" + leftright.toString());
            
            
            if(left instanceof ParallelNode){
                System.out.println("Left of Spec 1 is a parallel node");
            }
            
        }
        else if(stlspec1.spec.module instanceof ConcatenationNode){
            System.out.println("Spec 1 is a Concat Node");
        }
        
    
        
        System.out.println("\n\nSTL Spec1 :: " + stlspec1.spec.toString());*/
        
    }
    
}
