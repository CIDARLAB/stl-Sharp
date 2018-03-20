/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.metrics;

import hyness.stl.grammar.sharp.STLSharpAbstractSyntaxTreeExtractor;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
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
    public static String desiredhigh;
    public static String desiredlow;
    
    public static String spec1;
    
    public static String spec2;
    
    public static String spec3;
    
    public static String spec4;
    
    public static String spec5;
    
    public static String spec6;
    
    public static String alwaysTrue;
    
    
    public ManuscriptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
               
        constitutive = "phi1(x)\n"
                + "\n"
                + "phi1 = (((G[0.0,300.0](x <= 41.5206640959)) && (G[0.0,240.0](x >= 21.5206640959))) && ((G[240.0,260.0](x >= 1.520664095899999)) && (G[260.0,300.0](x >= 21.5206640959))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:320,min:0}}]\n"
                ;
        
        induction = "phi1(x)\n"
                + "\n"
                //+ "phi1 = ((((((G[0.0,40.0](x <= 10.0)) && (G[40.0,170.0](x <= 20.0))) && ((G[170.0,200.0](x <= 30.0)) && (G[200.0,300.0](x <= 20.0)))) && (((G[0.0,230.0](x >= 0.0)) && (G[230.0,250.0](x >= -10.0))) && (G[250.0,300.0](x >= 0.0)))) || ((((((G[0.0,10.0](x <= 10.0)) && (G[10.0,30.0](x <= 20.0))) && ((G[30.0,40.0](x <= 30.0)) && (G[40.0,80.0](x <= 40.0)))) && (((G[80.0,120.0](x <= 50.0)) && (G[120.0,140.0](x <= 60.0))) && ((G[140.0,150.0](x <= 70.0)) && (G[150.0,160.0](x <= 80.0))))) && ((((G[160.0,180.0](x <= 90.0)) && (G[180.0,190.0](x <= 100.0))) && ((G[190.0,210.0](x <= 110.0)) && (G[210.0,230.0](x <= 120.0)))) && (((G[230.0,280.0](x <= 130.0)) && (G[280.0,300.0](x <= 120.0))) && ((G[0.0,20.0](x >= 0.0)) && (G[20.0,40.0](x >= 10.0)))))) && (((((G[40.0,50.0](x >= 20.0)) && (G[50.0,90.0](x >= 30.0))) && ((G[90.0,130.0](x >= 40.0)) && (G[130.0,150.0](x >= 50.0)))) && (((G[150.0,160.0](x >= 60.0)) && (G[160.0,170.0](x >= 70.0))) && ((G[170.0,190.0](x >= 80.0)) && (G[190.0,200.0](x >= 90.0))))) && (((G[200.0,220.0](x >= 100.0)) && (G[220.0,240.0](x >= 110.0))) && ((G[240.0,270.0](x >= 120.0)) && (G[270.0,300.0](x >= 110.0))))))) || (((((((G[0.0,10.0](x <= 20.0)) && (G[10.0,20.0](x <= 40.0))) && ((G[20.0,30.0](x <= 50.0)) && (G[30.0,40.0](x <= 70.0)))) && (((G[40.0,50.0](x <= 90.0)) && (G[50.0,60.0](x <= 100.0))) && ((G[60.0,70.0](x <= 110.0)) && (G[70.0,80.0](x <= 130.0))))) && ((((G[80.0,90.0](x <= 140.0)) && (G[90.0,100.0](x <= 150.0))) && ((G[100.0,110.0](x <= 160.0)) && (G[110.0,120.0](x <= 180.0)))) && (((G[120.0,130.0](x <= 190.0)) && (G[130.0,140.0](x <= 210.0))) && ((G[140.0,150.0](x <= 230.0)) && (G[150.0,160.0](x <= 250.0)))))) && (((((G[160.0,170.0](x <= 270.0)) && (G[170.0,180.0](x <= 280.0))) && ((G[180.0,200.0](x <= 290.0)) && (G[200.0,230.0](x <= 300.0)))) && (((G[230.0,250.0](x <= 310.0)) && (G[250.0,260.0](x <= 300.0))) && ((G[260.0,270.0](x <= 280.0)) && (G[270.0,280.0](x <= 270.0))))) && ((((G[280.0,290.0](x <= 250.0)) && (G[290.0,300.0](x <= 240.0))) && ((G[0.0,10.0](x >= 0.0)) && (G[10.0,20.0](x >= 10.0)))) && (((G[20.0,30.0](x >= 20.0)) && (G[30.0,40.0](x >= 30.0))) && ((G[40.0,50.0](x >= 40.0)) && (G[50.0,60.0](x >= 50.0))))))) && (((((G[60.0,90.0](x >= 60.0)) && (G[90.0,120.0](x >= 70.0))) && ((G[120.0,130.0](x >= 80.0)) && (G[130.0,140.0](x >= 90.0)))) && (((G[140.0,150.0](x >= 110.0)) && (G[150.0,160.0](x >= 120.0))) && ((G[160.0,170.0](x >= 140.0)) && (G[170.0,180.0](x >= 150.0))))) && ((((G[180.0,200.0](x >= 170.0)) && (G[200.0,220.0](x >= 180.0))) && ((G[220.0,240.0](x >= 190.0)) && (G[240.0,250.0](x >= 200.0)))) && (((G[250.0,270.0](x >= 190.0)) && (G[270.0,290.0](x >= 180.0))) && (G[290.0,300.0](x >= 170.0)))))))\n"
                //+ "phi1 = (((((G[0.0,40.0](x <= 10.0)) && (G[40.0,170.0](x <= 20.0))) && ((G[170.0,200.0](x <= 30.0)) && (G[200.0,300.0](x <= 20.0)))) && (((G[0.0,230.0](x >= 0.0)) && (G[230.0,250.0](x >= -10.0))) && (G[250.0,300.0](x >= 0.0)))) || (((((((G[0.0,10.0](x <= 20.0)) && (G[10.0,20.0](x <= 40.0))) && ((G[20.0,30.0](x <= 50.0)) && (G[30.0,40.0](x <= 70.0)))) && (((G[40.0,50.0](x <= 90.0)) && (G[50.0,60.0](x <= 100.0))) && ((G[60.0,70.0](x <= 110.0)) && (G[70.0,80.0](x <= 130.0))))) && ((((G[80.0,90.0](x <= 140.0)) && (G[90.0,100.0](x <= 150.0))) && ((G[100.0,110.0](x <= 160.0)) && (G[110.0,120.0](x <= 180.0)))) && (((G[120.0,130.0](x <= 190.0)) && (G[130.0,140.0](x <= 210.0))) && ((G[140.0,150.0](x <= 230.0)) && (G[150.0,160.0](x <= 250.0)))))) && (((((G[160.0,170.0](x <= 270.0)) && (G[170.0,180.0](x <= 280.0))) && ((G[180.0,200.0](x <= 290.0)) && (G[200.0,230.0](x <= 300.0)))) && (((G[230.0,250.0](x <= 310.0)) && (G[250.0,260.0](x <= 300.0))) && ((G[260.0,270.0](x <= 280.0)) && (G[270.0,280.0](x <= 270.0))))) && ((((G[280.0,290.0](x <= 250.0)) && (G[290.0,300.0](x <= 240.0))) && ((G[0.0,10.0](x >= 0.0)) && (G[10.0,20.0](x >= 10.0)))) && (((G[20.0,30.0](x >= 20.0)) && (G[30.0,40.0](x >= 30.0))) && ((G[40.0,50.0](x >= 40.0)) && (G[50.0,60.0](x >= 50.0))))))) && (((((G[60.0,90.0](x >= 60.0)) && (G[90.0,120.0](x >= 70.0))) && ((G[120.0,130.0](x >= 80.0)) && (G[130.0,140.0](x >= 90.0)))) && (((G[140.0,150.0](x >= 110.0)) && (G[150.0,160.0](x >= 120.0))) && ((G[160.0,170.0](x >= 140.0)) && (G[170.0,180.0](x >= 150.0))))) && ((((G[180.0,200.0](x >= 170.0)) && (G[200.0,220.0](x >= 180.0))) && ((G[220.0,240.0](x >= 190.0)) && (G[240.0,250.0](x >= 200.0)))) && (((G[250.0,270.0](x >= 190.0)) && (G[270.0,290.0](x >= 180.0))) && (G[290.0,300.0](x >= 170.0)))))))\n"
                //+ "phi1 = (((((G[0.0,160.0](x <= 20.0)) && (G[160.0,200.0](x <= 40.0))) && ((G[200.0,300.0](x <= 20.0)) && (G[0.0,220.0](x >= 0.0)))) && ((G[220.0,260.0](x >= -20.0)) && (G[260.0,300.0](x >= 0.0)))) || ((((((G[0.0,20.0](x <= 40.0)) && (G[20.0,40.0](x <= 80.0))) && ((G[40.0,60.0](x <= 100.0)) && (G[60.0,80.0](x <= 140.0)))) && (((G[80.0,100.0](x <= 160.0)) && (G[100.0,120.0](x <= 180.0))) && ((G[120.0,140.0](x <= 220.0)) && (G[140.0,160.0](x <= 260.0))))) && ((((G[160.0,180.0](x <= 280.0)) && (G[180.0,220.0](x <= 300.0))) && ((G[220.0,260.0](x <= 320.0)) && (G[260.0,280.0](x <= 280.0)))) && (((G[280.0,300.0](x <= 260.0)) && (G[0.0,20.0](x >= 0.0))) && ((G[20.0,40.0](x >= 20.0)) && (G[40.0,60.0](x >= 40.0)))))) && ((((G[60.0,120.0](x >= 60.0)) && (G[120.0,140.0](x >= 80.0))) && ((G[140.0,160.0](x >= 100.0)) && (G[160.0,180.0](x >= 140.0)))) && (((G[180.0,200.0](x >= 160.0)) && (G[200.0,280.0](x >= 180.0))) && (G[280.0,300.0](x >= 160.0))))))\n"
                + "phi1 = ((((((G[0.0,160.0](x <= 20.0)) && (G[160.0,200.0](x <= 40.0))) && ((G[200.0,300.0](x <= 20.0)) && (G[0.0,220.0](x >= 0.0)))) && ((G[220.0,260.0](x >= -20.0)) && (G[260.0,300.0](x >= 0.0)))) || (((((G[0.0,20.0](x <= 20.0)) && (G[20.0,80.0](x <= 40.0))) && ((G[80.0,140.0](x <= 60.0)) && (G[140.0,160.0](x <= 80.0)))) && (((G[160.0,180.0](x <= 100.0)) && (G[180.0,220.0](x <= 120.0))) && ((G[220.0,280.0](x <= 140.0)) && (G[280.0,300.0](x <= 120.0))))) && ((((G[0.0,40.0](x >= 0.0)) && (G[40.0,100.0](x >= 20.0))) && ((G[100.0,160.0](x >= 40.0)) && (G[160.0,180.0](x >= 60.0)))) && (((G[180.0,200.0](x >= 80.0)) && (G[200.0,240.0](x >= 100.0))) && ((G[240.0,260.0](x >= 120.0)) && (G[260.0,300.0](x >= 100.0))))))) || ((((((G[0.0,20.0](x <= 40.0)) && (G[20.0,40.0](x <= 80.0))) && ((G[40.0,60.0](x <= 100.0)) && (G[60.0,80.0](x <= 140.0)))) && (((G[80.0,100.0](x <= 160.0)) && (G[100.0,120.0](x <= 180.0))) && ((G[120.0,140.0](x <= 220.0)) && (G[140.0,160.0](x <= 260.0))))) && ((((G[160.0,180.0](x <= 280.0)) && (G[180.0,220.0](x <= 300.0))) && ((G[220.0,260.0](x <= 320.0)) && (G[260.0,280.0](x <= 280.0)))) && (((G[280.0,300.0](x <= 260.0)) && (G[0.0,20.0](x >= 0.0))) && ((G[20.0,40.0](x >= 20.0)) && (G[40.0,60.0](x >= 40.0)))))) && ((((G[60.0,120.0](x >= 60.0)) && (G[120.0,140.0](x >= 80.0))) && ((G[140.0,160.0](x >= 100.0)) && (G[160.0,180.0](x >= 140.0)))) && (((G[180.0,200.0](x >= 160.0)) && (G[200.0,280.0](x >= 180.0))) && (G[280.0,300.0](x >= 160.0))))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:320,min:0}}]\n"
                ;
        
        latch = "phi1(x)\n"
                + "\n"
                + "phi1 = ((((((G[0.0,60.0](x <= 10.0)) && (G[60.0,180.0](x <= 20.0))) && ((G[180.0,210.0](x <= 30.0)) && (G[210.0,240.0](x <= 40.0)))) && (((G[240.0,260.0](x <= 50.0)) && (G[260.0,280.0](x <= 60.0))) && ((G[280.0,300.0](x <= 70.0)) && (G[0.0,300.0](x >= 0.0))))) || ((((((G[0.0,10.0](x <= 10.0)) && (G[10.0,30.0](x <= 20.0))) && ((G[30.0,50.0](x <= 30.0)) && (G[50.0,90.0](x <= 40.0)))) && (((G[90.0,130.0](x <= 50.0)) && (G[130.0,210.0](x <= 60.0))) && ((G[210.0,240.0](x <= 70.0)) && (G[240.0,250.0](x <= 80.0))))) && ((((G[250.0,260.0](x <= 100.0)) && (G[260.0,270.0](x <= 110.0))) && ((G[270.0,280.0](x <= 120.0)) && (G[280.0,290.0](x <= 140.0)))) && (((G[290.0,300.0](x <= 150.0)) && (G[0.0,20.0](x >= 0.0))) && ((G[20.0,40.0](x >= 10.0)) && (G[40.0,60.0](x >= 20.0)))))) && (((((G[60.0,100.0](x >= 30.0)) && (G[100.0,140.0](x >= 40.0))) && ((G[140.0,220.0](x >= 50.0)) && (G[220.0,250.0](x >= 60.0)))) && (((G[250.0,260.0](x >= 70.0)) && (G[260.0,270.0](x >= 90.0))) && ((G[270.0,280.0](x >= 100.0)) && (G[280.0,290.0](x >= 110.0))))) && (G[290.0,300.0](x >= 130.0))))) || (((((((G[0.0,10.0](x <= 20.0)) && (G[10.0,20.0](x <= 30.0))) && ((G[20.0,30.0](x <= 40.0)) && (G[30.0,40.0](x <= 50.0)))) && (((G[40.0,50.0](x <= 60.0)) && (G[50.0,70.0](x <= 70.0))) && ((G[70.0,90.0](x <= 80.0)) && (G[90.0,110.0](x <= 90.0))))) && ((((G[110.0,190.0](x <= 100.0)) && (G[190.0,210.0](x <= 110.0))) && ((G[210.0,220.0](x <= 120.0)) && (G[220.0,240.0](x <= 130.0)))) && (((G[240.0,250.0](x <= 140.0)) && (G[250.0,260.0](x <= 150.0))) && ((G[260.0,270.0](x <= 170.0)) && (G[270.0,280.0](x <= 180.0)))))) && (((((G[280.0,290.0](x <= 190.0)) && (G[290.0,300.0](x <= 200.0))) && ((G[0.0,10.0](x >= 0.0)) && (G[10.0,20.0](x >= 10.0)))) && (((G[20.0,30.0](x >= 20.0)) && (G[30.0,40.0](x >= 30.0))) && ((G[40.0,50.0](x >= 40.0)) && (G[50.0,60.0](x >= 50.0))))) && ((((G[60.0,80.0](x >= 60.0)) && (G[80.0,100.0](x >= 70.0))) && ((G[100.0,120.0](x >= 80.0)) && (G[120.0,200.0](x >= 90.0)))) && (((G[200.0,220.0](x >= 100.0)) && (G[220.0,230.0](x >= 110.0))) && ((G[230.0,250.0](x >= 120.0)) && (G[250.0,260.0](x >= 130.0))))))) && (((G[260.0,270.0](x >= 140.0)) && (G[270.0,280.0](x >= 150.0))) && ((G[280.0,290.0](x >= 170.0)) && (G[290.0,300.0](x >= 180.0))))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:320,min:0}}]\n"
                ;
        
        desired = "phi1(x)\n"
                + "\n"
                //+ "phi1 = (G[0,100](x<100) && G[0,100](x>0) && G[100,200](x<270) && G[100,200](x>60) && G[200,300](x<320) && G[200,300](x>160)) || (G[0,100](x<50) && G[0,100](x>0) && G[100,200](x<100) && G[100,200](x>40) && G[200,300](x<130) && G[200,300](x>90)) || (G[0,300](x<40) && G[0,300](x>0))\n"
                + "phi1 = ((G[0,300](x<40) && G[0,300](x>0))|| (G[0,125](x<200) && G[125,300](x<320) && G[0,150](x>0) && G[150,200](x>100) && G[200,300](x>150)))\n" 
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:320,min:0}}]\n"
                ;
        
        desiredlow = "phi1(x)\n"
                + "\n"
                //+ "phi1 = (G[0,100](x<100) && G[0,100](x>0) && G[100,200](x<270) && G[100,200](x>60) && G[200,300](x<320) && G[200,300](x>160)) || (G[0,100](x<50) && G[0,100](x>0) && G[100,200](x<100) && G[100,200](x>40) && G[200,300](x<130) && G[200,300](x>90)) || (G[0,300](x<40) && G[0,300](x>0))\n"
                + "phi1 = (G[0,300](x<40) && G[0,300](x>0))\n" 
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:320,min:0}}]\n"
                ;
        
        desiredhigh = "phi1(x)\n"
                + "\n"
                //+ "phi1 = (G[0,100](x<100) && G[0,100](x>0) && G[100,200](x<270) && G[100,200](x>60) && G[200,300](x<320) && G[200,300](x>160)) || (G[0,100](x<50) && G[0,100](x>0) && G[100,200](x<100) && G[100,200](x>40) && G[200,300](x<130) && G[200,300](x>90)) || (G[0,300](x<40) && G[0,300](x>0))\n"
                + "phi1 = (G[0,125](x<200) && G[125,300](x<320) && G[0,150](x>0) && G[150,200](x>100) && G[200,300](x>150))\n" 
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:350,min:0}}]\n"
                ;
        

        alwaysTrue = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,20]((x>=0) && (x<=1)))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec1 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,20]((x>=0.2) && (x<=0.4)))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec2 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,20]((x>=0.2) && (x<=0.44)))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec3 = "phi1(x)\n"
                + "\n"
                + "phi1 = (F[0,20]((x>=0.2) && (x<=0.4)))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec4 = "phi1(x)\n"
                + "\n"
                + "phi1 = ((G[0,20]((x>=0.2) && (x<=0.4))) && (F[0,20]((x>=0.2) && (x<=0.44))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec5 = "phi1(x)\n"
                + "\n"
                + "phi1 = ((G[0,10]((x>=0.2) && (x<=0.4))) && (G[12,20]((x>=0.2) && (x<=0.44))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
                ;
        
        spec6 = "phi1(x)\n"
                + "\n"
                + "phi1 = (G[0,16](F[0,4]((x>=0.2) && (x<=0.4))))\n"
                + "\n"
                + "m1 { x@left: x }\n"
                + "io {x: x}\n"
                + "limits [{x : {max:1,min:0}}]\n"
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
     * Test of synthetic biology case study formulae.
     */
    //@Test
    public void testSynBioSpecs() {
                
        STLSharpAbstractSyntaxTreeExtractor desiredSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(desired);
        STLSharpAbstractSyntaxTreeExtractor constitutiveSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(constitutive);
        STLSharpAbstractSyntaxTreeExtractor latchSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(latch);
        STLSharpAbstractSyntaxTreeExtractor inductionSTL = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(induction);
        
        AreaOfSatisfaction aos = new AreaOfSatisfaction();
        
        System.out.println("Distance between desired and constitutive: " + aos.computeDistance(desiredSTL.spec, constitutiveSTL.spec, false, 0.1, 300, true));
//        System.out.println("Distance between constitutive and desired: " + aos.computeDistance(constitutiveSTL.spec, desiredSTL.spec, false, 100));
        System.out.println("Distance between desired and induction: " + aos.computeDistance(desiredSTL.spec, inductionSTL.spec, false, 0.1, 300, true));
//        System.out.println("Distance between induction and desired: " + aos.computeDistance(inductionSTL.spec, desiredSTL.spec, false, 100));
        System.out.println("Distance between desired and latch: " + aos.computeDistance(desiredSTL.spec, latchSTL.spec, false, 0.1, 300, true));
//        System.out.println("Distance between latch and desired: " + aos.computeDistance(latchSTL.spec, desiredSTL.spec, false, 100));

    }
    
    @Test
    public void testTLI(){
        String basefp = Utilities.getFilepath() + Utilities.getSeparater() + "resources" + Utilities.getSeparater() + "manuscript" + Utilities.getSeparater();
        String thresh0 = Utilities.getFileContentAsString(basefp + "thresh0.txt");
        String thresh1 = Utilities.getFileContentAsString(basefp + "thresh1.txt");
        String thresh2 = Utilities.getFileContentAsString(basefp + "thresh2.txt");
        String thresh3 = Utilities.getFileContentAsString(basefp + "thresh3.txt");
        String thresh4 = Utilities.getFileContentAsString(basefp + "thresh4.txt");
        String thresh5 = Utilities.getFileContentAsString(basefp + "thresh5.txt");
        String thresh6 = Utilities.getFileContentAsString(basefp + "thresh6.txt");
        String thresh7 = Utilities.getFileContentAsString(basefp + "thresh7.txt");
        String gt = Utilities.getFileContentAsString(basefp + "gt.txt");
        
        String treetli = Utilities.getFileContentAsString(basefp + "treetli.json");
        JSONArray arr = new JSONArray(treetli);
        
        
        String sgt = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + gt + "\n"
                + "\n"
                + "m1 { x_0@left:x_0, x_1@left:x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s0 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh0 + "\n"
                + "\n"
                + "m1 { x_0@left:x_0,x_1@left:x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s1 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh1 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s2 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh2 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s3 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh3 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s4 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh4 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s5 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh5 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s6 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh6 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        String s7 = "phi1(x_0,x_1)\n"
                + "\n"
                + "phi1 =" + thresh7 + "\n"
                + "\n"
                + "m1 { x_0@left: x_0, x_1@left: x_1}\n"
                + "io {x_0: x_0,x_1: x_1}\n"
                + "limits [{x_0 : {max:1,min:0}}, {x_1 : {max:1,min:0}}]\n"
                ;
        
        STLSharpAbstractSyntaxTreeExtractor stlgt = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(sgt);
        
        STLSharpAbstractSyntaxTreeExtractor stl0 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s0);
        STLSharpAbstractSyntaxTreeExtractor stl1 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s1);
        STLSharpAbstractSyntaxTreeExtractor stl2 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s2);
        STLSharpAbstractSyntaxTreeExtractor stl3 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s3);
        STLSharpAbstractSyntaxTreeExtractor stl4 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s4);
        STLSharpAbstractSyntaxTreeExtractor stl5 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s5);
        STLSharpAbstractSyntaxTreeExtractor stl6 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s6);
        STLSharpAbstractSyntaxTreeExtractor stl7 = STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(s7);
        
        AreaOfSatisfaction aos = new AreaOfSatisfaction();
    
        System.out.println("SD(gt,stl0) = " + aos.computeDistance(stlgt.spec, stl0.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl1) = " + aos.computeDistance(stlgt.spec, stl1.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl2) = " + aos.computeDistance(stlgt.spec, stl2.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl3) = " + aos.computeDistance(stlgt.spec, stl3.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl4) = " + aos.computeDistance(stlgt.spec, stl4.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl5) = " + aos.computeDistance(stlgt.spec, stl5.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl6) = " + aos.computeDistance(stlgt.spec, stl6.spec, false, 0.1, 10, true));
        System.out.println("SD(gt,stl7) = " + aos.computeDistance(stlgt.spec, stl7.spec, false, 0.1, 10, true));
        
    
    }   
    
    /**
     * Test of computing the symmetric difference between the table formulae.
     */
    //@Test
    public void testTableFormulae() {
        
        List<STLSharpAbstractSyntaxTreeExtractor> formulae = new ArrayList<STLSharpAbstractSyntaxTreeExtractor>();
        
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(alwaysTrue));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec1));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec2));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec3));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec4));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec5));
        formulae.add(STLSharpAbstractSyntaxTreeExtractor.getSTLSharpAbstractSyntaxTreeExtractor(spec6));
        
        AreaOfSatisfaction aos = new AreaOfSatisfaction();
        
        for (int i = 0; i < formulae.size(); i ++) {
            for (int j = 0; j < formulae.size(); j ++) {
                System.out.println("Distance between spec" + i + " and spec" + j + ": " + aos.computeDistance(formulae.get(i).spec, formulae.get(j).spec, false, 1, 20, false));
            }
        }
    }
    
}
