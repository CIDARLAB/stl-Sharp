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
        BigDecimal result = instance.computeDistance(stlSpecExtract1.spec, stlSpecExtract2.spec, false);
        System.out.println(result.doubleValue());
        assertEquals(expResult, result);
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
        BigDecimal result = instance.computeArea(stlSpecExtract.spec);
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
        result = instance.computeArea(stlSpecExtract.spec);
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
        result = instance.computeArea(stlSpecExtract.spec);
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
        result = instance.computeArea(stlSpecExtract.spec);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
    }
    
}
