/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.main;

import hyness.stl.composition.Compose;
import hyness.stl.grammar.flat.STLflat;
import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
import hyness.stl.metrics.CostFunction;
import hyness.stl.metrics.HausdorffDistanceMetric;
import hyness.stl.metrics.Utilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ckmadsen
 */
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        if (args.length < 3 || args.length == 1 && args[0].equals("-help")) {
            System.out.println("Usage:  java -jar <STL-sharp-jar-name>.jar [OPTION]... [FILE]...");
            System.out.println("Options:");
            System.out.println("    -compose=OPERATOR   Composes two STL# formulae contained within the first two files and outputs");
            System.out.println("                        the composed formula into the third file");
            System.out.println("                        OPERATOR can be AND, OR, JOIN, PARALLEL, or CONCAT");
            System.out.println("                        If OPERATOR is CONCAT or JOIN, then a mapping file is required as a fourth file");
            System.out.println("                        Examples:");
            System.out.println("                        java -jar STLSharp-jar-with-dependencies.jar -compose=PARALLEL mod1.txt mod2.txt comp.txt");
            System.out.println("                        java -jar STLSharp-jar-with-dependencies.jar -compose=JOIN mod1.txt mod2.txt comp.txt map.txt");
            System.out.println();
            System.out.println("    -distance=METHOD    Computes the distance between two STL# formulae contained within two files");
            System.out.println("                        METHOD can be either HAUSDORFF or COSTFUNCTION");
            System.out.println("                        Example:");
            System.out.println("                        java -jar STLSharp-jar-with-dependencies.jar -distance=COSTFUNCTION form1.txt form2.txt");
            System.out.println();
            System.out.println("    -help               Prints usage information");
        }
        else if (args[0].startsWith("-compose")) {
            STLflatAbstractSyntaxTreeExtractor stlspec1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[1]));
            STLflatAbstractSyntaxTreeExtractor stlspec2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[2]));
            String operator = args[0].split("=")[1];
            STLflat result = null;
            Map<String, List<String>> mapping = new HashMap<String, List<String>>();
            BufferedReader br = null;
            String line = "";
            switch (operator) {
                case "AND":
                    result = Compose.composeWithAnd(stlspec1.spec, stlspec2.spec);
                    break;
                case "OR":
                    result = Compose.composeWithOr(stlspec1.spec, stlspec2.spec);
                    break;
                case "PARALLEL":
                    result = Compose.composeWithParallel(stlspec1.spec, stlspec2.spec);
                    break;
                case "CONCAT":
                    //TODO:  Figure out how to input mapping
                    br = new BufferedReader(new FileReader(args[4]));
                    while ((line = br.readLine()) != null) {
                        String[] splitLine = line.split("=");
                        List<String> map = mapping.get(splitLine[0]);
                        if (map == null) {
                            map = new ArrayList<String>();
                        }
                        map.add(splitLine[1]);
                        mapping.put(splitLine[0], map);
                    }                    
                    result = Compose.composeWithConcatenate(stlspec1.spec, stlspec2.spec, mapping);
                    break;
                case "JOIN":
                    //TODO:  Figure out how to input mapping
                    br = new BufferedReader(new FileReader(args[4]));
                    while ((line = br.readLine()) != null) {
                        String[] splitLine = line.split("=");
                        List<String> map = mapping.get(splitLine[0]);
                        if (map == null) {
                            map = new ArrayList<String>();
                        }
                        map.add(splitLine[1]);
                        mapping.put(splitLine[0], map);
                    }                    
                    result = Compose.composeWithJoin(stlspec1.spec, stlspec2.spec, mapping);
                    break;
                default:
                    System.out.println("Invalid OPERATOR.  OPERATOR can be AND, OR, PARALLEL, or CONCAT");
                    break;
            }
            if (result != null) {
                try (PrintWriter writer = new PrintWriter(args[3], "UTF-8")) {
                    writer.println(result.toString());
                    writer.close();
                }
            }
        }
        else if (args[0].startsWith("-distance")) {
            STLflatAbstractSyntaxTreeExtractor stlspec1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[1]));
            STLflatAbstractSyntaxTreeExtractor stlspec2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[2]));
            String method = args[0].split("=")[1];
            BigDecimal val = new BigDecimal(Double.MAX_VALUE);
            switch (method) {
                case "HAUSDORFF":
                    HausdorffDistanceMetric dist = new HausdorffDistanceMetric();
                    val = dist.computeDistance(stlspec1.spec, stlspec2.spec);
                    break;
                case "COSTFUNCTION":
                    CostFunction cost = new CostFunction();
                    cost.setAlphaF(1);
                    cost.setAlphaFprime(1);
                    cost.setAlphaG(1);
                    cost.setAlphaGprime(1);
                    val = cost.computeDistance(stlspec1.spec, stlspec2.spec, false);
                    break;
                default:
                    System.out.println("Invalid METHOD.  METHOD can be either HAUSDORFF or COSTFUNCTION");
                    break;
            }
            System.out.println("This distance between these STL# formulae is:");
            System.out.println(val);
        }
    }
    
}
