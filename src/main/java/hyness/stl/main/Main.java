/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.main;

import hyness.stl.composition.Compose;
import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
import hyness.stl.metrics.DistanceMetric;
import hyness.stl.metrics.Utilities;
import java.math.BigDecimal;

/**
 *
 * @author ckmadsen
 */
public class Main {
    
    public static void main(String[] args) {
        if (args.length < 3 || args.length == 1 && args[0].equals("-help")) {
            System.out.println("Usage:  java -jar STLb.jar [OPTION]... [FILE]...");
            System.out.println("Options:");
            System.out.println("    -compose=OPERATOR   Composes two STLb formulae contained within the first two files and outputs");
            System.out.println("                        the composed formula into the third file");
            System.out.println("                        OPERATOR can be AND, OR, PARALLEL, or CONCAT");
            System.out.println("    -distance           Computes the distance between two STLb formulae contained within two files");
            System.out.println("    -help               Prints usage information");
        }
        else if (args[0].startsWith("-compose")) {
            STLflatAbstractSyntaxTreeExtractor stlspec1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[1]));
            STLflatAbstractSyntaxTreeExtractor stlspec2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[2]));
            String operator = args[0].split("=")[1];
            switch (operator) {
                case "AND":
                    Compose.composeWithAnd(stlspec1.spec, stlspec2.spec);
                    break;
                case "OR":
                    Compose.composeWithOr(stlspec1.spec, stlspec2.spec);
                    break;
                case "PARALLEL":
                    Compose.composeWithParallel(stlspec1.spec, stlspec2.spec);
                    break;
                case "CONCAT":
                    //TODO:  Figure out how to input mapping
                    //Compose.composeWithConcatenate(stlspec1.spec, stlspec2.spec);
                    break;
                default:
                    break;
            }
        }
        else if (args[0].startsWith("-distance")) {
            STLflatAbstractSyntaxTreeExtractor stlspec1 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[1]));
            STLflatAbstractSyntaxTreeExtractor stlspec2 = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(Utilities.getFileContentAsString(args[2]));
            DistanceMetric dist = new DistanceMetric();
            BigDecimal val = dist.computeDistance(stlspec1.spec, stlspec2.spec);
            System.out.println("This distance between these STLb formulae is:");
            System.out.println(val);
        }
    }
    
}
