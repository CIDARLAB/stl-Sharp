/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyness.stl.composition;

import hyness.stl.ModuleLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Pair;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author ckmadsen
 */
public class Compose {
    
        public static STLflat composeWithAnd(STLflat left, STLflat right) {
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
		TreeNode andNode = new ModuleNode(hyness.stl.Operation.AND, leftNode, rightNode, "m1");
		STLflat stl = new STLflat(andNode);
		stl.modules.put("phi1", left);
		stl.modules.put("phi2", right);
		stl.maps.put("m1", m1Map);
		stl.maps.put("io", ioMap);
		stl.limitsMap = limitsMap;
		return stl;
	}
        
        public static STLflat composeWithOr(STLflat left, STLflat right) {
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
		TreeNode orNode = new ModuleNode(hyness.stl.Operation.OR, leftNode, rightNode, "m1");
		STLflat stl = new STLflat(orNode);
		stl.modules.put("phi1", left);
		stl.modules.put("phi2", right);
		stl.maps.put("m1", m1Map);
		stl.maps.put("io", ioMap);
		stl.limitsMap = limitsMap;
		return stl;
	}
        
        public static STLflat composeWithParallel(STLflat left, STLflat right) {
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
		TreeNode parallelNode = new ModuleNode(hyness.stl.Operation.PARALLEL, leftNode, rightNode, "m1");
		STLflat stl = new STLflat(parallelNode);
		stl.modules.put("phi1", left);
		stl.modules.put("phi2", right);
		stl.maps.put("m1", m1Map);
		stl.maps.put("io", ioMap);
		stl.limitsMap = limitsMap;
		return stl;
	}
    
}
