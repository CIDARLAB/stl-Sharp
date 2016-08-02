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
import hyness.stl.grammar.flat.STLflat;
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
    
        public static STLflat composeWithAnd(STLflat left, STLflat right) {
            return composeWithOperator(left, right, hyness.stl.Operation.AND);
	}
        
        public static STLflat composeWithOr(STLflat left, STLflat right) {
            return composeWithOperator(left, right, hyness.stl.Operation.OR);
	}
        
        public static STLflat composeWithParallel(STLflat left, STLflat right) {
            return composeWithOperator(left, right, hyness.stl.Operation.PARALLEL);
	}
        
        private static STLflat composeWithOperator(STLflat left, STLflat right, Operation operation) {
            int i = 1;
            HashMap<Pair<String, Boolean>,String> m1Map = new HashMap<Pair<String, Boolean>,String>();
            HashMap<Pair<String, Boolean>,String> ioMap = new HashMap<Pair<String, Boolean>,String>();
            HashMap<String,HashMap<String,Double>> limitsMap = new HashMap<String,HashMap<String,Double>>();
            Vector<String> leftPorts = new Vector<String>();
            leftPorts.add("phi1");
            for (Pair<String, Boolean> pair : left.maps.get("io").keySet()) {
                leftPorts.add(left.maps.get("io").get(pair));
		m1Map.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), true), "io" + i);
		ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
		HashMap<String,Double> limits = new HashMap<String,Double>();
		limits.put("min", left.limitsMap.get(left.maps.get("io").get(pair)).get("min"));
		limits.put("max", left.limitsMap.get(left.maps.get("io").get(pair)).get("max"));
		limitsMap.put("io" + i, limits);
		i++;
            }
            Vector<String> rightPorts = new Vector<String>();
            rightPorts.add("phi2");
            for (Pair<String, Boolean> pair : right.maps.get("io").keySet()) {
                rightPorts.add(right.maps.get("io").get(pair));
		m1Map.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), false), "io" + i);
		ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
		HashMap<String,Double> limits = new HashMap<String,Double>();
		limits.put("min", right.limitsMap.get(right.maps.get("io").get(pair)).get("min"));
		limits.put("max", right.limitsMap.get(right.maps.get("io").get(pair)).get("max"));
		limitsMap.put("io" + i, limits);
		i++;
            }
            TreeNode leftNode = new ModuleLeaf("phi1", leftPorts);
            TreeNode rightNode = new ModuleLeaf("phi2", rightPorts);
            TreeNode operationNode = new ModuleNode(operation, leftNode, rightNode, "m1");
            STLflat stl = new STLflat(operationNode);
            stl.modules.put("phi1", left);
            stl.modules.put("phi2", right);
            stl.maps.put("m1", m1Map);
            stl.maps.put("io", ioMap);
            stl.limitsMap = limitsMap;
            return stl;
        }
        
        public static STLflat composeWithConcatenate(STLflat left, STLflat right, List<Pair<String, String>> internalMapping) {
            int i = 1;
            HashMap<Pair<String, Boolean>,String> m1Map = new HashMap<Pair<String, Boolean>,String>();
            HashMap<Pair<String, Boolean>,String> ioMap = new HashMap<Pair<String, Boolean>,String>();
            HashMap<String,HashMap<String,Double>> limitsMap = new HashMap<String,HashMap<String,Double>>();
            List<String> usedLeftPorts = new ArrayList<String>();
            List<String> usedRightPorts = new ArrayList<String>();
            for (int j = 0; j < internalMapping.size(); j++) {
                m1Map.put(new Pair<String, Boolean>(internalMapping.get(j).left, true), "a" + (j + 1));
                m1Map.put(new Pair<String, Boolean>(internalMapping.get(j).right, false), "a" + (j + 1));
                usedLeftPorts.add(internalMapping.get(j).left);
                usedRightPorts.add(internalMapping.get(j).right);
            }
            Vector<String> leftPorts = new Vector<String>();
            leftPorts.add("phi1");
            for (Pair<String, Boolean> pair : left.maps.get("io").keySet()) {
                if (!usedLeftPorts.contains(left.maps.get("io").get(pair))) {
                    leftPorts.add(left.maps.get("io").get(pair));
                    m1Map.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), true), "io" + i);
                    ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
                    HashMap<String,Double> limits = new HashMap<String,Double>();
                    limits.put("min", left.limitsMap.get(left.maps.get("io").get(pair)).get("min"));
                    limits.put("max", left.limitsMap.get(left.maps.get("io").get(pair)).get("max"));
                    limitsMap.put("io" + i, limits);
                    i++;
                }
            }
            Vector<String> rightPorts = new Vector<String>();
            rightPorts.add("phi2");
            for (Pair<String, Boolean> pair : right.maps.get("io").keySet()) {
                if (!usedRightPorts.contains(right.maps.get("io").get(pair))) {
                    rightPorts.add(right.maps.get("io").get(pair));
                    m1Map.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), false), "io" + i);
                    ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
                    HashMap<String,Double> limits = new HashMap<String,Double>();
                    limits.put("min", right.limitsMap.get(right.maps.get("io").get(pair)).get("min"));
                    limits.put("max", right.limitsMap.get(right.maps.get("io").get(pair)).get("max"));
                    limitsMap.put("io" + i, limits);
                    i++;
                }
            }
            TreeNode leftNode = new ModuleLeaf("phi1", leftPorts);
            TreeNode rightNode = new ModuleLeaf("phi2", rightPorts);
            TreeNode operationNode = new ModuleNode(Operation.CONCAT, leftNode, rightNode, "m1");
            STLflat stl = new STLflat(operationNode);
            stl.modules.put("phi1", left);
            stl.modules.put("phi2", right);
            stl.maps.put("m1", m1Map);
            stl.maps.put("io", ioMap);
            stl.limitsMap = limitsMap;
            return stl;
	}
    
}
