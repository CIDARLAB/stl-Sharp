/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.grammar.sharp.STLSharp;
import hyness.stl.grammar.sharp.STLSharpAbstractSyntaxTreeExtractor;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ckmadsen
 */
public class AreaOfSatisfactionTest {
    
    public AreaOfSatisfactionTest() {
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
     * Test of computeDistance method, of class AreaOfSatisfaction.
     */
    @Test
    public void testComputeDistance() {
        System.out.println("computeDistance");
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        
        String spec1 = "phi1(u1, u2)\n"
                + "\n"
                + "phi1 = (G[0,10] u1 >= 2) && (G[0,10] u2 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1, u2@left: u2 }\n"
                + "io {u1: u1, u2: u2}\n"
                + "limits [{u1 : {max:10,min:0}}, {u2 : {max:10,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1);
        
        String spec2 = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[0,10] u1 >= 2) && (G[0,10] u1 < 4)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2);

        BigDecimal expResult =  new BigDecimal(-120);
        BigDecimal result1 = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract2.spec, false, 1);
        BigDecimal result2 = instance.computeDistance(stlSpecExtract2.spec, stlSpecExtract1.spec, false, 1);
        System.out.println(result1.doubleValue());
        System.out.println(result2.doubleValue());
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(result1, result2);
    }
    
    @Test
    public void testComputeDistance2() {
        System.out.println("computeDistance-2");
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        
        String spec1 = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[0,10] u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1);
        
        String spec2 = "phi1(u1)\n"
                + "\n"
                + "phi1 = (F[0,5] u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2);
        
        BigDecimal expResult =  new BigDecimal(0);
        BigDecimal result1 = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract2.spec, false, 1);
        BigDecimal result2 = instance.computeDistance(stlSpecExtract2.spec, stlSpecExtract1.spec, false, 1);
        System.out.println(result1.doubleValue());
        System.out.println(result2.doubleValue());
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(result1, result2);
        
    }
    
    @Test
    public void testComputeDistance3(){
        
        System.out.println("computeDistance-3");
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        
        String spec1 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,2](x<13) && G[0,2](x>0) && G[2,10](x<13) && G[2,10](x>2)) || (G[0,10](x<1) && G[0,10](x>0))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1);
        
        String spec2 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,2](x<13) && G[0,2](x>0) && G[2,10](x<13) && G[2,10](x>2))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2);
        
        String spec3 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,10](x<1) && G[0,10](x>0))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract3 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec3);
        
        BigDecimal expResult =  new BigDecimal(122);
        BigDecimal result = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract1.spec, false, 1);
//        System.out.println(result.doubleValue());
        assertEquals(expResult, result);
        BigDecimal expResult2 =  new BigDecimal(-118);
        BigDecimal result1 = instance.computeDistance(stlSpecExtract2.spec, stlSpecExtract3.spec, false, 1);
        BigDecimal result2 = instance.computeDistance(stlSpecExtract3.spec, stlSpecExtract2.spec, false, 1);
//        System.out.println(result1.doubleValue());
//        System.out.println(result2.doubleValue());
        assertEquals(expResult2, result1);
        assertEquals(expResult2, result2);
        assertEquals(result1, result2);
        
        
    }
    
    @Test
    public void testComputeDistance4(){
        System.out.println("computeDistance-3");
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        
        String spec1 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,2](x<13) && G[0,2](x>0) && G[2,10](x<13) && G[2,10](x>2)) || (G[0,10](x<1) && G[0,10](x>0))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1);
        
        String spec2 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,2](x<13) && G[0,2](x>0) && G[2,10](x<13) && G[2,10](x>2))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2);
        
        String spec3 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,10](x<1) && G[0,10](x>0))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:15,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract3 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec3);
        
        BigDecimal expResult =  new BigDecimal(106);
        BigDecimal result1 = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract2.spec, false, 1);
        BigDecimal result2 = instance.computeDistance(stlSpecExtract2.spec, stlSpecExtract1.spec, false, 1);
//        System.out.println(result1.doubleValue());
//        System.out.println(result2.doubleValue());
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(result1, result2);
        
        BigDecimal expResult2 =  new BigDecimal(-102);
        BigDecimal result3 = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract3.spec, false, 1);
        BigDecimal result4 = instance.computeDistance(stlSpecExtract3.spec, stlSpecExtract1.spec, false, 1);
//        System.out.println(result1.doubleValue());
//        System.out.println(result2.doubleValue());
        assertEquals(expResult2, result3);
        assertEquals(expResult2, result4);
        assertEquals(result3, result4);
    }
    /**
     * Test of computeArea method, of class AreaOfSatisfaction.
     */
    @Test
    public void testComputeArea() {
        System.out.println("computeArea Test");
        System.out.println();
        System.out.println("Expected = Computed");
        System.out.println("-------------------");
        
        String spec = "phi1(u1, u2)\n"
                + "\n"
                + "phi1 = (G[0,10] u1 >= 2) && (G[0,10] u2 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1, u2@left: u2 }\n"
                + "io {u1: u1, u2: u2}\n"
                + "limits [{u1 : {max:10,min:0}}, {u2 : {max:10,min:0}}]\n"
                ;
        STLSharpAbstractSyntaxTreeExtractor stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        BigDecimal expResult = new BigDecimal(160);
        BigDecimal result = instance.computeArea(stlSpecExtract.spec, 0.1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
        
        spec = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[0,10] u1 >= 2) && (G[0,10] u1 < 4)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(20);
        result = instance.computeArea(stlSpecExtract.spec, 0.1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
        
        spec = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[0,5] u1 >= 2) && (G[3,7] u1 < 6)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(44);
        result = instance.computeArea(stlSpecExtract.spec, 0.1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
        
        spec = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[3,7] u1 < 6) && (G[0,5] u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(44);
        result = instance.computeArea(stlSpecExtract.spec, 0.1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
        
        spec = "phi1(u1)\n"
                + "\n"
                + "phi1 = (G[0,5] u1 >= 2) || (G[3,7] u1 < 6)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(56);
        result = instance.computeArea(stlSpecExtract.spec, 0.1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
        
        spec = "phi1(u1)\n"
                + "\n"
                + "phi1 = (F[0,5] u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        stlSpecExtract = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(40);
        result = instance.computeArea(stlSpecExtract.spec, 1);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
    }
    
}
