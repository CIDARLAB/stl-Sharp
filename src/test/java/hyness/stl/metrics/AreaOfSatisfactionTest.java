/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.grammar.flat.STLflat;
import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
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
        STLflat spec1 = null;
        STLflat spec2 = null;
        AreaOfSatisfaction instance = new AreaOfSatisfaction();
        BigDecimal expResult = null;
        BigDecimal result = instance.computeDistance(spec1, spec2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        STLflatAbstractSyntaxTreeExtractor stlSpecExtract = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec);
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
        stlSpecExtract = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec);
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
        stlSpecExtract = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec);
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
        stlSpecExtract = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(spec);
        expResult = new BigDecimal(56);
        result = instance.computeArea(stlSpecExtract.spec);
        System.out.println(expResult.doubleValue() + " = " + result.doubleValue() + "?");
        assertEquals(expResult, result);
    }
    
}
