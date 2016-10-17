/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.composition.Compose;
import hyness.stl.grammar.flat.STLflat;
import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
import java.math.BigDecimal;
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
 * @author prash
 */
public class DistanceMetricTest {
    
    public static String module1;
    public static String module2;
    public static String module3;
    public static String cascade1;
    public static String cascade2;
    public static String cascade3;
    public static String cascade4;
    public static String cascade5;
    public static String cascade6;
    
    public static String spec1;
     
    public static String spec2;
    
    public static String spec3;
    
    
    public DistanceMetricTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        module1 = "phi1(x1,x2)\n"
                + "\n"
                + "phi1 = ((F[6.06,45.1] x2 < 1.12E05) && (((G[44.2,44.8] x1 < 9.95E04) && (G[8.74,41.6] x2 > 1.12E05) ) || (F[44.2,44.8] x1 > 9.95E04) ))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: y }\n"
                + "io {x1: u, x2: y}\n"
                + "limits [{u : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
         
        module2 = "phi1(x1,x2)\n"
                + "\n"
                + "phi1 = (((G[13.7,45.3] x2 < 9.41E07) && (((G[1.25,2.95] x2 < 7.02E07) && (F[45.1,45.8] x1 > 3.63E05) ) || (F[1.25,2.95] x2 > 7.02E07) )) || ((F[13.7,45.3] x2 > 9.41E07) && (G[31.5,43.7] x2 < 9.41E07) ))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: y }\n"
                + "io {x1: u, x2: y}\n"
                + "limits [{u : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        spec1 = "phi1(u1) >>_m1 phi2(u1)\n"
                + "\n"
                + "phi1 = (u1 < 6)\n"
                + "phi2 = (u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1, u1@right: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        
        spec2 = "phi1(u1)\n"
                + "\n"
                + "phi1 = (u1 < 7)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        
        spec3 = "phi1(u1)\n"
                + "\n"
                + "phi1 = (u1 >= 2)\n"
                + "\n"
                + "m1 { u1@left: u1 }\n"
                + "io {u1: u1}\n"
                + "limits [{u1 : {max:10,min:0}}]\n"
                ;
        
        module3 = "phi1(x1,x2)\n"
                + "\n"
                + "phi1 = (((G[15.5,45.7] x2 < 5.67E06) && (((G[1.01,3.69] x2 < 3.93E06) && (G[25.8,45.3] x1 > 4.51E05) ) || (F[1.01,3.69] x2 > 3.93E06) )) || ((F[15.5,45.7] x2 > 5.67E06) && (G[27.5,42.3] x2 < 5.67E06) ))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: y }\n"
                + "io {x1: u, x2: y}\n"
                + "limits [{u : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        cascade1 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[7.13,46] x3 > 4.13E06) && (G[3.57,4.02] x1 < 3.14E07) ) || ((F[7.13,46] x3 < 4.13E06) && ((G[1.23,15.1] x1 < 2.97E07) && (((G[42.1,44.6] x2 < 8.9E06) && (F[41.3,43.2] x3 > 3.61E06) ) || ((F[42.1,44.6] x2 > 8.9E06) && (G[1,46] x1 < 1.48E07) )))))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        
        cascade2 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[9.21,46] x3 > 4.1E06) && (G[1,46] x2 < 5.98E06) ) || ((F[9.21,46] x3 < 4.1E06) && ((G[21.4,42.3] x1 < 3.71E07) && (((G[40.9,43.1] x2 < 5.98E06) && (F[40.5,45] x3 > 3.7E06) ) || (F[40.9,43.1] x2 > 5.98E06) ))))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        cascade3 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[9.47,45.4] x3 > 2.72E06) && ((G[1,46] x2 < 9.46E06) && (G[18.6,30.2] x1 > 1.67E07) )) || ((F[9.47,45.4] x3 < 2.72E06) && ((G[26.3,44.8] x1 < 1.6E07) && (((G[43.3,45.1] x2 < 9.46E06) && (F[40.9,44.7] x3 > 2.37E06) ) || (F[43.3,45.1] x2 > 9.46E06) ))))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        cascade4 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[10.2,46] x3 > 3.11E06) && (G[3.57,3.82] x1 < 3.95E07) ) || ((F[10.2,46] x3 < 3.11E06) && ((G[3.6,28.1] x1 < 3.83E07) && (((G[45.4,46] x2 < 4.82E06) && (F[40.2,44.8] x3 > 2.58E06) ) || (F[45.4,46] x2 > 4.82E06) ))))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        cascade5 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[9.07,45.3] x3 > 4.42E06) && (G[6.49,44.7] x2 < 1.3E07) ) || ((F[9.07,45.3] x3 < 4.42E06) && (((G[43.5,43.7] x2 < 1.3E07) && (G[18.3,39.7] x3 > 4.42E06) ) || (F[43.5,43.7] x2 > 1.3E07) )))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
        
        cascade6 = "phi1(x1,x2,x3)\n"
                + "\n"
                + "phi1 = (((G[10.3,46] x3 > 9.01E06) && (G[1,5.03] x1 < 4.78E07) ) || ((F[10.3,46] x3 < 9.01E06) && ((G[1.59,13] x1 < 4.51E07) && (((G[45.1,46] x2 < 1.24E07) && (F[45.3,45.9] x3 > 7.44E06) ) || (F[45.1,46] x2 > 1.24E07) ))))\n"
                + "\n"
                + "m1 { x1@left: u, x2@left: a, x3@left: y }\n"
                + "io {x1: u, x3: y}\n"
                + "limits [{u : {max:1E09,min:0}},{a : {max:1E09,min:0}},{y : {max:1E09,min:0}}]\n"
                ;
         
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
        
//        STLflatAbstractSyntaxTreeExtractor stlmodule1 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlmodule2 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlmodule3 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade1 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade2 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade3 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade4 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade5 = new STLflatAbstractSyntaxTreeExtractor();
//        STLflatAbstractSyntaxTreeExtractor stlcascade6 = new STLflatAbstractSyntaxTreeExtractor();
//        
//        stlmodule1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(module1);
//        stlmodule2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(module2);
//        stlmodule3 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(module3);
//        stlcascade1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade1);
//        stlcascade2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade2);
//        stlcascade3 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade3);
//        stlcascade4 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade4);
//        stlcascade5 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade5);
//        stlcascade6 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade6);
//        
//        Map<String, List<String>> mapping = new HashMap<String, List<String>>();
//        Map<String, List<String>> mapping2 = new HashMap<String, List<String>>();
//        
//        List<String> signal = new ArrayList<String>();
//        List<String> signal2 = new ArrayList<String>();
//        
//        signal.add("u");
//        signal2.add("u");
//        
//        mapping.put("y", signal);
//        mapping2.put("y", signal2);
//        
//        STLflat mod1mod2 = Compose.composeWithConcatenate(stlmodule1.spec, stlmodule2.spec, mapping);
//        STLflat mod1mod3 = Compose.composeWithConcatenate(stlmodule1.spec, stlmodule3.spec, mapping2);
//        STLflat mod2mod1 = Compose.composeWithConcatenate(stlmodule2.spec, stlmodule1.spec, mapping);
//        STLflat mod2mod3 = Compose.composeWithConcatenate(stlmodule2.spec, stlmodule3.spec, mapping);
//        STLflat mod3mod1 = Compose.composeWithConcatenate(stlmodule3.spec, stlmodule1.spec, mapping);
//        STLflat mod3mod2 = Compose.composeWithConcatenate(stlmodule3.spec, stlmodule2.spec, mapping);
//        
//        
//        DistanceMetric dist = new DistanceMetric();
//        DistanceMetric dist2 = new DistanceMetric();
//        
//        //System.out.println("Spec 1 mumax :: " + stlspec1.spec.getMumax());
//        //System.out.println("Spec 2 mumax :: " + stlspec2.spec.getMumax());
//        
//        System.out.println("STL Example ::  " + stlmodule1.spec.toString()+"\n\n");
//        System.out.println("STL Example ::  " + stlmodule2.spec.toString()+"\n\n");
//        
//        BigDecimal valmod1mod2_cas1 = dist.computeDistance(mod1mod2, stlcascade2.spec);
//        System.out.println("distance(Mod1+Mod2,Cas1): " + valmod1mod2_cas1);
//        BigDecimal valmod1mod2_cas2 = dist2.computeDistance(mod1mod2, stlcascade2.spec);
//        BigDecimal valmod1mod2_cas3 = dist.computeDistance(mod1mod2, stlcascade3.spec);
//        BigDecimal valmod1mod2_cas4 = dist.computeDistance(mod1mod2, stlcascade4.spec);
//        BigDecimal valmod1mod2_cas5 = dist.computeDistance(mod1mod2, stlcascade5.spec);
//        BigDecimal valmod1mod2_cas6 = dist.computeDistance(mod1mod2, stlcascade6.spec);
//        
//        BigDecimal valmod1mod3_cas1 = dist.computeDistance(mod1mod3, stlcascade1.spec);
//        BigDecimal valmod1mod3_cas2 = dist.computeDistance(mod1mod3, stlcascade2.spec);
//        BigDecimal valmod1mod3_cas3 = dist.computeDistance(mod1mod3, stlcascade3.spec);
//        BigDecimal valmod1mod3_cas4 = dist.computeDistance(mod1mod3, stlcascade4.spec);
//        BigDecimal valmod1mod3_cas5 = dist.computeDistance(mod1mod3, stlcascade5.spec);
//        BigDecimal valmod1mod3_cas6 = dist.computeDistance(mod1mod3, stlcascade6.spec);
//        
//        BigDecimal valmod2mod1_cas1 = dist.computeDistance(mod2mod1, stlcascade1.spec);
//        BigDecimal valmod2mod1_cas2 = dist.computeDistance(mod2mod1, stlcascade2.spec);
//        BigDecimal valmod2mod1_cas3 = dist.computeDistance(mod2mod1, stlcascade3.spec);
//        BigDecimal valmod2mod1_cas4 = dist.computeDistance(mod2mod1, stlcascade4.spec);
//        BigDecimal valmod2mod1_cas5 = dist.computeDistance(mod2mod1, stlcascade5.spec);
//        BigDecimal valmod2mod1_cas6 = dist.computeDistance(mod2mod1, stlcascade6.spec);
//        
//        BigDecimal valmod2mod3_cas1 = dist.computeDistance(mod2mod3, stlcascade1.spec);
//        BigDecimal valmod2mod3_cas2 = dist.computeDistance(mod2mod3, stlcascade2.spec);
//        BigDecimal valmod2mod3_cas3 = dist.computeDistance(mod2mod3, stlcascade3.spec);
//        BigDecimal valmod2mod3_cas4 = dist.computeDistance(mod2mod3, stlcascade4.spec);
//        BigDecimal valmod2mod3_cas5 = dist.computeDistance(mod2mod3, stlcascade5.spec);
//        BigDecimal valmod2mod3_cas6 = dist.computeDistance(mod2mod3, stlcascade6.spec);
//        
//        BigDecimal valmod3mod1_cas1 = dist.computeDistance(mod3mod1, stlcascade1.spec);
//        BigDecimal valmod3mod1_cas2 = dist.computeDistance(mod3mod1, stlcascade2.spec);
//        BigDecimal valmod3mod1_cas3 = dist.computeDistance(mod3mod1, stlcascade3.spec);
//        BigDecimal valmod3mod1_cas4 = dist.computeDistance(mod3mod1, stlcascade4.spec);
//        BigDecimal valmod3mod1_cas5 = dist.computeDistance(mod3mod1, stlcascade5.spec);
//        BigDecimal valmod3mod1_cas6 = dist.computeDistance(mod3mod1, stlcascade6.spec);
//        
//        BigDecimal valmod3mod2_cas1 = dist.computeDistance(mod3mod2, stlcascade1.spec);
//        BigDecimal valmod3mod2_cas2 = dist.computeDistance(mod3mod2, stlcascade2.spec);
//        BigDecimal valmod3mod2_cas3 = dist.computeDistance(mod3mod2, stlcascade3.spec);
//        BigDecimal valmod3mod2_cas4 = dist.computeDistance(mod3mod2, stlcascade4.spec);
//        BigDecimal valmod3mod2_cas5 = dist.computeDistance(mod3mod2, stlcascade5.spec);
//        BigDecimal valmod3mod2_cas6 = dist.computeDistance(mod3mod2, stlcascade6.spec);
//        

        List<String> modules = new ArrayList<String>();
        List<String> cascades = new ArrayList<String>();
        
        modules.add(module1);
        modules.add(module2);
        modules.add(module3);
        
        cascades.add(cascade1);
        cascades.add(cascade2);
        cascades.add(cascade3);
        cascades.add(cascade4);
        cascades.add(cascade5);
        cascades.add(cascade6);
        
//        List<String> results = new ArrayList<String>();
//        
//        System.out.println("Distance Results:");
//        for (int i = 0; i < modules.size(); i ++) {
//            for (int j = 0; j < modules.size(); j ++) {
//                for (int k = 0; k < cascades.size(); k ++) {
//                    if (i != j) {
//                        results.add("distance(module" + (i+1) + "+module" + (j+1) + ",cascade" + (k+1) + "): " + getDistanceBetweenModulesAndCascade(modules.get(i), modules.get(j), cascades.get(k)));
//                    }
//                }
//            }
//        }
//        System.out.println("Distance Results:");
//        for (String result : results) {
//            System.out.println(result);
//        }
//        
//        System.out.println(getDistanceBetweenModulesAndCascade(spec2, spec3, spec1));
        
        //System.out.println(spec1);
        
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
    
    public BigDecimal getDistanceBetweenModulesAndCascade(String module1, String module2, String cascade) {
        STLflatAbstractSyntaxTreeExtractor stlmodule1 = new STLflatAbstractSyntaxTreeExtractor();
        STLflatAbstractSyntaxTreeExtractor stlmodule2 = new STLflatAbstractSyntaxTreeExtractor();
        STLflatAbstractSyntaxTreeExtractor stlcascade1 = new STLflatAbstractSyntaxTreeExtractor();
        
        stlmodule1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(module1);
        stlmodule2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(module2);
        stlcascade1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(cascade);
        
        Map<String, List<String>> mapping = new HashMap<String, List<String>>();
        
        List<String> signal = new ArrayList<String>();
        
        signal.add("u");
        
        mapping.put("y", signal);
        
        STLflat mod1mod2 = Compose.composeWithConcatenate(stlmodule1.spec, stlmodule2.spec, mapping);
        
        
        DistanceMetric dist = new DistanceMetric();
        
        return dist.computeDistance(mod1mod2, stlcascade1.spec);
    }
    
}
