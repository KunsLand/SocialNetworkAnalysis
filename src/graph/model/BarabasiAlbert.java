package graph.model;

import graph.CondenseGraph;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map;

public class BarabasiAlbert {

	public static CondenseGraph createGraph(int m0, int m, int t) {
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> degrees = new HashMap<Integer, Integer>();
		int cumulativeDegree = 0;
		// create initial graph;
		// p is the possibility of establishing connection between any two
		// vertices;
		System.out.println("Create initial graph ...");
		double initialPossibility = 0.5;
		for (int i = 0; i < m0; i++) {
			graph.put(i, new HashSet<Integer>());
			degrees.put(i, 0);
		}
		for (int i = 0; i < m0; i++) {
			for (int j = i + 1; j < m0; j++) {
				if (Math.random() <= initialPossibility) {
					graph.get(i).add(j);
					graph.get(j).add(i);
					degrees.put(i, degrees.get(i) + 1);
					degrees.put(j, degrees.get(j) + 1);
					cumulativeDegree += 2;
				}
			}
		}
		// generate scale-free network
		Random rnd = new Random();
		double epsilon = 0.0001;
		System.out.println("Generate sacle-free network ...");
		for (int i = 0; i < t; i++) {
			if ((i + 1) % 100 == 0)
				System.out.println("Step: " + (i + 1));
			int n = graph.size(), j = 0;
			Set<Integer> targets = new HashSet<Integer>();
			// find m attach points for the new added vertex;
			while (j < m) {
				int attachPoint = rnd.nextInt(n);
				if (targets.contains(attachPoint))
					continue;
				double p = (degrees.get(attachPoint) + epsilon)
						/ (cumulativeDegree + epsilon);
				if (Math.random() > p)
					continue;
				targets.add(attachPoint);
				j++;
			}
			// add the new vertex and m new edges to the graph
			graph.put(n, targets);
			degrees.put(n, targets.size());
			for (int target : targets) {
				graph.get(target).add(n);
				degrees.put(target, degrees.get(target) + 1);
			}
			cumulativeDegree += 2 * targets.size();
		}
		System.out.println("Generated!");
		return new CondenseGraph(graph);
	}

	public static void main(String[] args) throws IOException {
		int m0 = 5, m = 5, n = 150000;
		CondenseGraph g = createGraph(m0, m, n);
		g.exportEdgeList("D:/ClassicalModelSamples/BA/" + m0 + "_" + m + "_"
				+ n + ".csv", ",", false);
	}

}
