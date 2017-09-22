/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.composition;

import hyness.stl.ModuleLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Operation;
import hyness.stl.Pair;
import hyness.stl.TreeNode;
import hyness.stl.grammar.sharp.STLSharp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ckmadsen
 */
public class Compose {
    
        public static STLSharp composeWithAnd(STLSharp left, STLSharp right) {
            return composeWithOperator(hyness.stl.Operation.AND, left, right, null);
	}
        
        public static STLSharp composeWithOr(STLSharp left, STLSharp right) {
            return composeWithOperator(hyness.stl.Operation.OR, left, right, null);
	}
        
        public static STLSharp composeWithParallel(STLSharp left, STLSharp right) {
            return composeWithOperator(hyness.stl.Operation.PARALLEL, left, right, null);
	}
        
        public static STLSharp composeWithAnd(STLSharp left, STLSharp right, Map<String, List<String>> internalMapping) {
            return composeWithOperator(hyness.stl.Operation.AND, left, right, internalMapping);
	}
        
        public static STLSharp composeWithOr(STLSharp left, STLSharp right, Map<String, List<String>> internalMapping) {
            return composeWithOperator(hyness.stl.Operation.OR, left, right, internalMapping);
	}
        
        public static STLSharp composeWithConcatenate(STLSharp left, STLSharp right, Map<String, List<String>> internalMapping) {
            return composeWithOperator(hyness.stl.Operation.CONCAT, left, right, internalMapping);
	}
        
        public static STLSharp composeWithJoin(STLSharp left, STLSharp right, Map<String, List<String>> internalMapping) {
            return composeWithOperator(hyness.stl.Operation.JOIN, left, right, internalMapping);
	}
        
        private static STLSharp composeWithOperator(Operation operation, STLSharp left, STLSharp right, Map<String, List<String>> internalMapping) {
            HashMap<Pair<String, Boolean>,String> m1Map = new HashMap<Pair<String, Boolean>,String>();
            HashMap<Pair<String, Boolean>,String> ioMap = new HashMap<Pair<String, Boolean>,String>();
            HashMap<String,HashMap<String,Double>> limitsMap = new HashMap<String,HashMap<String,Double>>();
            Vector<String> leftPorts = new Vector<String>();
            Vector<String> rightPorts = new Vector<String>();
            List<String> usedLeftPorts = new ArrayList<String>();
            List<String> usedRightPorts = new ArrayList<String>();
            int i = 1;
            int j = 1;
            if (internalMapping != null) {
                for (String leftOutput : internalMapping.keySet()) {
                    for (String rightInput : internalMapping.get(leftOutput)) {
                        m1Map.put(new Pair<String, Boolean>(leftOutput, true), "a" + j);
                        m1Map.put(new Pair<String, Boolean>(rightInput, false), "a" + j);
                        HashMap<String,Double> limits = new HashMap<String,Double>();
                        limits.put("min", left.limitsMap.get(leftOutput).get("min"));
                        limits.put("max", left.limitsMap.get(leftOutput).get("max"));
                        limitsMap.put("a" + j, limits);
                        usedLeftPorts.add(leftOutput);
                        usedRightPorts.add(rightInput);
                        j++;
                    }
                }
            }
            for (Pair<String, Boolean> pair : left.maps.get("io").keySet()) {
                leftPorts.add(left.maps.get("io").get(pair));
                if (!usedLeftPorts.contains(left.maps.get("io").get(pair))) {
                    HashMap<String,Double> limits = new HashMap<String,Double>();
                    limits.put("min", left.limitsMap.get(left.maps.get("io").get(pair)).get("min"));
                    limits.put("max", left.limitsMap.get(left.maps.get("io").get(pair)).get("max"));
                    if (internalMapping != null) {
                        m1Map.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), true), "io" + i);
                        ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
                        limitsMap.put("io" + i, limits);
                        i++;
                    }
                    else {
                        m1Map.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), true), left.maps.get("io").get(pair));
                        ioMap.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), null), left.maps.get("io").get(pair));
                        limitsMap.put(left.maps.get("io").get(pair), limits);
                    }
                }
            }
            for (Pair<String, Boolean> pair : right.maps.get("io").keySet()) {
                rightPorts.add(right.maps.get("io").get(pair));
                if (!usedRightPorts.contains(right.maps.get("io").get(pair))) {
                    HashMap<String,Double> limits = new HashMap<String,Double>();
                    limits.put("min", right.limitsMap.get(right.maps.get("io").get(pair)).get("min"));
                    limits.put("max", right.limitsMap.get(right.maps.get("io").get(pair)).get("max"));
                    if (internalMapping != null) {
                        m1Map.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), false), "io" + i);
                        ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
                        limitsMap.put("io" + i, limits);
                        i++;
                    }
                    else {
                        m1Map.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), false), right.maps.get("io").get(pair));
                        ioMap.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), null), right.maps.get("io").get(pair));
                        limitsMap.put(right.maps.get("io").get(pair), limits);
                    }
                }
            }
            TreeNode leftNode = new ModuleLeaf("phi1", leftPorts);
            TreeNode rightNode = new ModuleLeaf("phi2", rightPorts);
            TreeNode operationNode = new ModuleNode(operation, leftNode, rightNode, "m1");
            STLSharp stl = new STLSharp(operationNode);
            stl.modules.put("phi1", left);
            stl.modules.put("phi2", right);
            stl.maps.put("m1", m1Map);
            stl.maps.put("io", ioMap);
            stl.limitsMap = limitsMap;
            return stl;
        }
}

