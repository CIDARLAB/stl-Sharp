/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.composition;

import hyness.stl.grammar.sharp.STLSharp;
import hyness.stl.grammar.sharp.STLSharpAbstractSyntaxTreeExtractor;
import hyness.stl.metrics.CostFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ckmadsen
 */
public class ComposeTest {
    
    public ComposeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of composeWithAnd method, of class Compose.
     */
    @Test
    public void testCompose() {
        String spec1 = "phi1(in, out)\n"
                + "\n"
                + "phi1 = ((G[0,10] in >= 20) && (G[0,10] out < 10)) && ((G[10,20] in < 20) && (G[10,20] in >= 10) && (G[10,20] out < 20) && (G[10,20] out >= 10)) && ((G[20,30] in < 10) && (G[20,30] out >= 20))\n"
                + "\n"
                + "m1 { in@left: in, out@left: out }\n"
                + "io {in: in, out: out}\n"
                + "limits [{in : {max:30,min:0}}, {out : {max:30,min:0}}]\n"
                ;
        
        String spec2 = "phi1(in, out)\n"
                + "\n"
                + "phi1 = ((G[0,10] in < 10) && (G[0,10] out >= 20)) && ((G[10,20] in < 20) && (G[10,20] in >= 10) && (G[10,20] out < 20) && (G[10,20] out >= 10)) && ((G[20,30] in >= 20) && (G[20,30] out < 10))\n"
                + "\n"
                + "m1 { in@left: in, out@left: out }\n"
                + "io {in: in, out: out}\n"
                + "limits [{in : {max:30,min:0}}, {out : {max:30,min:0}}]\n"
                ;
        
        String spec3 = "phi1(io1, io2)\n"
                + "\n"
                + "phi1 = ((G[0,10] io1 >= 20) && (G[0,10] io2 >= 20)) && ((G[10,20] io1 < 20) && (G[10,20] io1 >= 10) && (G[10,20] io2 < 20) && (G[10,20] io2 >= 10)) && ((G[20,30] io1 < 10) && (G[20,30] io2 < 10))\n"
                + "\n"
                + "m1 { io1@left: io1, io2@left: io2 }\n"
                + "io {io1: io1, io2: io2}\n"
                + "limits [{io1 : {max:30,min:0}}, {io2 : {max:30,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlmodule1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1);
        STLSharpAbstractSyntaxTreeExtractor stlmodule2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2);
        STLSharpAbstractSyntaxTreeExtractor stlmodule3 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec3);
        Map<String, List<String>> mapping = new HashMap<String, List<String>>();
        List<String> signal = new ArrayList<String>();
        signal.add("in");
        mapping.put("out", signal);
        STLSharp composition = Compose.composeWithAnd(stlmodule1.spec, stlmodule2.spec, mapping);
        System.out.println(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1).spec.toSTL(true));
        System.out.println();
        System.out.println(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2).spec.toSTL(true));
        System.out.println();
        System.out.println(Compose.composeWithAnd(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1).spec, STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2).spec, mapping).toSTL(false));
        System.out.println();
        System.out.println(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec3).spec.toSTL(true));
        System.out.println();
        
        CostFunction cost = new CostFunction();
        cost.setAlphaF(1);
        cost.setAlphaFprime(1);
        cost.setAlphaG(1);
        cost.setAlphaGprime(1);
        
        System.out.println(cost.computeDistance(composition, stlmodule3.spec, true));
    }

    /**
     * Test of composeWithAnd method, of class Compose.
     */
//    @Test
//    public void testComposeWithAnd() {
//        System.out.println("composeWithAnd");
//        STLSharp left = null;
//        STLSharp right = null;
//        STLSharp expResult = null;
//        STLSharp result = Compose.composeWithAnd(left, right);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of composeWithOr method, of class Compose.
     */
//    @Test
//    public void testComposeWithOr() {
//        System.out.println("composeWithOr");
//        STLSharp left = null;
//        STLSharp right = null;
//        STLSharp expResult = null;
//        STLSharp result = Compose.composeWithOr(left, right);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of composeWithParallel method, of class Compose.
     */
//    @Test
//    public void testComposeWithParallel() {
//        System.out.println("composeWithParallel");
//        STLSharp left = null;
//        STLSharp right = null;
//        STLSharp expResult = null;
//        STLSharp result = Compose.composeWithParallel(left, right);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of composeWithConcatenate method, of class Compose.
     */
//    @Test
//    public void testComposeWithConcatenate() {
//        System.out.println("composeWithConcatenate");
//        STLSharp left = null;
//        STLSharp right = null;
//        HashMap<String, List<String>> internalMapping = null;
//        STLSharp expResult = null;
//        STLSharp result = Compose.composeWithConcatenate(left, right, internalMapping);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
