/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.examples;

import hyness.stl.grammar.flat.STLflatAbstractSyntaxTreeExtractor;
import hyness.stl.metrics.Utilities;
import java.io.File;

/**
 *
 * @author prash
 */
public class STLexamples {
    public static STLflatAbstractSyntaxTreeExtractor getSTLflatFromFile(String filepath){
        STLflatAbstractSyntaxTreeExtractor stl = new STLflatAbstractSyntaxTreeExtractor();
        String filecontent = Utilities.getFileContentAsString(filepath);
        stl = STLflatAbstractSyntaxTreeExtractor.getSTLflatAbstractSyntaxTreeExtractor(filecontent);
        return stl;
    }
    
    
}
