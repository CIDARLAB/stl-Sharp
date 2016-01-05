/**
 * Copyright (C) 2015-2016 Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author Cristian-Ioan Vasile
 *
 */
public class Trace {
    // delimiter used in CSV file
    public static String delimiter = ",";
    
    public HashMap<String, Vector<Double>> data;
    public double timestep;
    
    /**
     * 
     * @param variables
     * @param data
     * @param timestep
     */
    public Trace(String[] variables, double[][] data, double timestep) {
        Vector<Double> d;
        this.timestep = timestep;
        this.data = new HashMap<String, Vector<Double>>();
        for(int i=0; i < variables.length; i++) {
            d = new Vector<Double>();
            for(int j=0; j < data[i].length; j++) {
                d.add(data[i][j]);
            }
            this.data.put(variables[i], d);
        }
    }
    
    /**
     * 
     * @param data
     * @param timestep
     */
    public Trace(HashMap<String, Vector<Double>> data, double timestep) {
        this.timestep = timestep;
        this.data = data;
    }
    
    /**
     * 
     * @param variable
     * @param t
     * @return
     */
    public double eval(String variable, double t) {
        return this.data.get(variable).get((int)Math.floor(t/timestep));
    }
    
    /**
     * 
     * @param filename
     * @param timestep
     * @return
     */
    public static Trace loadFromFile(String filename, double timestep) {
        BufferedReader fileReader = null;
        HashMap<String, Vector<Double>> data = null;
        
        try {
            String line;
            String[] tokens;
            data = new HashMap<String, Vector<Double>>();
            fileReader = new BufferedReader(new FileReader(filename));
            
            // header
            line = fileReader.readLine();
            String[] signals = line.split(delimiter);
            for(String signal : signals) {
                data.put(signal, new Vector<Double>());
            }
            // read the file line by line
            while((line = fileReader.readLine()) != null) {
                tokens = line.split(delimiter);
                assert tokens.length == signals.length;
                for(int i=0; i<tokens.length; i++) {
                    data.get(signals[i]).add(Double.valueOf(tokens[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Trace(data, timestep);
    }
}
