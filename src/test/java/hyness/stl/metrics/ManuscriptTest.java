/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.grammar.sharp.STLSharpAbstractSyntaxTreeExtractor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ckmadsen
 */
public class ManuscriptTest {
    
    public static String constitutive;
    
    public static String induction;
    
    public static String latch;
    
    public static String desired;
    
    
    public ManuscriptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
               
        constitutive = "phi1(x)\n"
                + "\n"
                + "phi1 = ((((G[0.0,30.0](x <= 41.5206640959)) && (G[30.0,300.0](x <= 31.5206640959))) && ((G[0.0,20.0](x >= 31.5206640959)) && (G[20.0,240.0](x >= 21.5206640959)))) && ((G[240.0,250.0](x >= 11.520664095899999)) && (G[250.0,300.0](x >= 21.5206640959))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:350,min:0}}]\n"
                ;
        
        induction = "phi1(x)\n"
                + "\n"
                + "phi1 = ((((((G[0.0,40.0](x <= 10.0)) && (G[40.0,170.0](x <= 20.0))) && ((G[170.0,200.0](x <= 30.0)) && (G[200.0,300.0](x <= 20.0)))) && (((G[0.0,230.0](x >= 0.0)) && (G[230.0,250.0](x >= -10.0))) && (G[250.0,300.0](x >= 0.0)))) || ((((((G[0.0,10.0](x <= 10.0)) && (G[10.0,30.0](x <= 20.0))) && ((G[30.0,40.0](x <= 30.0)) && (G[40.0,80.0](x <= 40.0)))) && (((G[80.0,120.0](x <= 50.0)) && (G[120.0,140.0](x <= 60.0))) && ((G[140.0,150.0](x <= 70.0)) && (G[150.0,160.0](x <= 80.0))))) && ((((G[160.0,180.0](x <= 90.0)) && (G[180.0,190.0](x <= 100.0))) && ((G[190.0,210.0](x <= 110.0)) && (G[210.0,230.0](x <= 120.0)))) && (((G[230.0,280.0](x <= 130.0)) && (G[280.0,300.0](x <= 120.0))) && ((G[0.0,20.0](x >= 0.0)) && (G[20.0,40.0](x >= 10.0)))))) && (((((G[40.0,50.0](x >= 20.0)) && (G[50.0,90.0](x >= 30.0))) && ((G[90.0,130.0](x >= 40.0)) && (G[130.0,150.0](x >= 50.0)))) && (((G[150.0,160.0](x >= 60.0)) && (G[160.0,170.0](x >= 70.0))) && ((G[170.0,190.0](x >= 80.0)) && (G[190.0,200.0](x >= 90.0))))) && (((G[200.0,220.0](x >= 100.0)) && (G[220.0,240.0](x >= 110.0))) && ((G[240.0,270.0](x >= 120.0)) && (G[270.0,300.0](x >= 110.0))))))) || (((((((G[0.0,10.0](x <= 20.0)) && (G[10.0,20.0](x <= 40.0))) && ((G[20.0,30.0](x <= 50.0)) && (G[30.0,40.0](x <= 70.0)))) && (((G[40.0,50.0](x <= 90.0)) && (G[50.0,60.0](x <= 100.0))) && ((G[60.0,70.0](x <= 110.0)) && (G[70.0,80.0](x <= 130.0))))) && ((((G[80.0,90.0](x <= 140.0)) && (G[90.0,100.0](x <= 150.0))) && ((G[100.0,110.0](x <= 160.0)) && (G[110.0,120.0](x <= 180.0)))) && (((G[120.0,130.0](x <= 190.0)) && (G[130.0,140.0](x <= 210.0))) && ((G[140.0,150.0](x <= 230.0)) && (G[150.0,160.0](x <= 250.0)))))) && (((((G[160.0,170.0](x <= 270.0)) && (G[170.0,180.0](x <= 280.0))) && ((G[180.0,200.0](x <= 290.0)) && (G[200.0,230.0](x <= 300.0)))) && (((G[230.0,250.0](x <= 310.0)) && (G[250.0,260.0](x <= 300.0))) && ((G[260.0,270.0](x <= 280.0)) && (G[270.0,280.0](x <= 270.0))))) && ((((G[280.0,290.0](x <= 250.0)) && (G[290.0,300.0](x <= 240.0))) && ((G[0.0,10.0](x >= 0.0)) && (G[10.0,20.0](x >= 10.0)))) && (((G[20.0,30.0](x >= 20.0)) && (G[30.0,40.0](x >= 30.0))) && ((G[40.0,50.0](x >= 40.0)) && (G[50.0,60.0](x >= 50.0))))))) && (((((G[60.0,90.0](x >= 60.0)) && (G[90.0,120.0](x >= 70.0))) && ((G[120.0,130.0](x >= 80.0)) && (G[130.0,140.0](x >= 90.0)))) && (((G[140.0,150.0](x >= 110.0)) && (G[150.0,160.0](x >= 120.0))) && ((G[160.0,170.0](x >= 140.0)) && (G[170.0,180.0](x >= 150.0))))) && ((((G[180.0,200.0](x >= 170.0)) && (G[200.0,220.0](x >= 180.0))) && ((G[220.0,240.0](x >= 190.0)) && (G[240.0,250.0](x >= 200.0)))) && (((G[250.0,270.0](x >= 190.0)) && (G[270.0,290.0](x >= 180.0))) && (G[290.0,300.0](x >= 170.0)))))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:350,min:0}}]\n"
                ;
        
        latch = "phi1(x)\n"
                + "\n"
                + "phi1 = ((((((G[0.0,60.0](x <= 10.0)) && (G[60.0,180.0](x <= 20.0))) && ((G[180.0,210.0](x <= 30.0)) && (G[210.0,240.0](x <= 40.0)))) && (((G[240.0,260.0](x <= 50.0)) && (G[260.0,280.0](x <= 60.0))) && ((G[280.0,300.0](x <= 70.0)) && (G[0.0,300.0](x >= 0.0))))) || ((((((G[0.0,10.0](x <= 10.0)) && (G[10.0,30.0](x <= 20.0))) && ((G[30.0,50.0](x <= 30.0)) && (G[50.0,90.0](x <= 40.0)))) && (((G[90.0,130.0](x <= 50.0)) && (G[130.0,210.0](x <= 60.0))) && ((G[210.0,240.0](x <= 70.0)) && (G[240.0,250.0](x <= 80.0))))) && ((((G[250.0,260.0](x <= 100.0)) && (G[260.0,270.0](x <= 110.0))) && ((G[270.0,280.0](x <= 120.0)) && (G[280.0,290.0](x <= 140.0)))) && (((G[290.0,300.0](x <= 150.0)) && (G[0.0,20.0](x >= 0.0))) && ((G[20.0,40.0](x >= 10.0)) && (G[40.0,60.0](x >= 20.0)))))) && (((((G[60.0,100.0](x >= 30.0)) && (G[100.0,140.0](x >= 40.0))) && ((G[140.0,220.0](x >= 50.0)) && (G[220.0,250.0](x >= 60.0)))) && (((G[250.0,260.0](x >= 70.0)) && (G[260.0,270.0](x >= 90.0))) && ((G[270.0,280.0](x >= 100.0)) && (G[280.0,290.0](x >= 110.0))))) && (G[290.0,300.0](x >= 130.0))))) || (((((((G[0.0,10.0](x <= 20.0)) && (G[10.0,20.0](x <= 30.0))) && ((G[20.0,30.0](x <= 40.0)) && (G[30.0,40.0](x <= 50.0)))) && (((G[40.0,50.0](x <= 60.0)) && (G[50.0,70.0](x <= 70.0))) && ((G[70.0,90.0](x <= 80.0)) && (G[90.0,110.0](x <= 90.0))))) && ((((G[110.0,190.0](x <= 100.0)) && (G[190.0,210.0](x <= 110.0))) && ((G[210.0,220.0](x <= 120.0)) && (G[220.0,240.0](x <= 130.0)))) && (((G[240.0,250.0](x <= 140.0)) && (G[250.0,260.0](x <= 150.0))) && ((G[260.0,270.0](x <= 170.0)) && (G[270.0,280.0](x <= 180.0)))))) && (((((G[280.0,290.0](x <= 190.0)) && (G[290.0,300.0](x <= 200.0))) && ((G[0.0,10.0](x >= 0.0)) && (G[10.0,20.0](x >= 10.0)))) && (((G[20.0,30.0](x >= 20.0)) && (G[30.0,40.0](x >= 30.0))) && ((G[40.0,50.0](x >= 40.0)) && (G[50.0,60.0](x >= 50.0))))) && ((((G[60.0,80.0](x >= 60.0)) && (G[80.0,100.0](x >= 70.0))) && ((G[100.0,120.0](x >= 80.0)) && (G[120.0,200.0](x >= 90.0)))) && (((G[200.0,220.0](x >= 100.0)) && (G[220.0,230.0](x >= 110.0))) && ((G[230.0,250.0](x >= 120.0)) && (G[250.0,260.0](x >= 130.0))))))) && (((G[260.0,270.0](x >= 140.0)) && (G[270.0,280.0](x >= 150.0))) && ((G[280.0,290.0](x >= 170.0)) && (G[290.0,300.0](x >= 180.0))))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:350,min:0}}]\n"
                ;
        
        desired = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,100](x<100) && G[0,100](x>0) && G[100,200](x<270) && G[100,200](x>60) && G[200,300](x<320) && G[200,300](x>160)) || (G[0,100](x<50) && G[0,100](x>0) && G[100,200](x<100) && G[100,200](x>50) && G[200,300](x<130) && G[200,300](x>100)) || (G[0,300](x<40) && G[0,300](x>0))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:350,min:0}}]\n"
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
    public void testManuscriptSpecs() {
                
        STLSharpAbstractSyntaxTreeExtractor desiredSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(desired);
        STLSharpAbstractSyntaxTreeExtractor constitutiveSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(constitutive);
        STLSharpAbstractSyntaxTreeExtractor latchSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(latch);
        STLSharpAbstractSyntaxTreeExtractor inductionSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(induction);
        
        AreaOfSatisfaction aos = new AreaOfSatisfaction();
        
        System.out.println("Distance between desired and constitutive: " + aos.computeDistance(desiredSTL.spec, constitutiveSTL.spec, false, 100));
//        System.out.println("Distance between constitutive and desired: " + aos.computeDistance(constitutiveSTL.spec, desiredSTL.spec, false, 100));
        System.out.println("Distance between desired and induction: " + aos.computeDistance(desiredSTL.spec, inductionSTL.spec, false, 100));
//        System.out.println("Distance between induction and desired: " + aos.computeDistance(inductionSTL.spec, desiredSTL.spec, false, 100));
        System.out.println("Distance between desired and latch: " + aos.computeDistance(desiredSTL.spec, latchSTL.spec, false, 100));
//        System.out.println("Distance between latch and desired: " + aos.computeDistance(latchSTL.spec, desiredSTL.spec, false, 100));

    }
    
}
