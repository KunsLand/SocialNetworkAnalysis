package graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CondenseGraph {
	Map<Integer, Set<Integer>> graph;

	public CondenseGraph() {
		graph = new HashMap<Integer, Set<Integer>>();
	}

	public CondenseGraph(Map<Integer, Set<Integer>> graph) {
		this.graph = graph;
	}

	public void addVertex(int v) {
		if (!graph.containsKey(v)) {
			graph.put(v, new HashSet<Integer>());
		}
	}

	public void addEdge(int source, int target) {
		graph.get(source).add(target);
	}

	public Map<Integer, Integer> getDegrees() {
		Map<Integer, Integer> degrees = new HashMap<Integer, Integer>();
		for (Integer key : graph.keySet()) {
			degrees.put(key, graph.get(key).size());
		}
		return degrees;
	}

	public void display() {
		for (int key : graph.keySet()) {
			System.out.print(key + ":\t");
			for (int target : graph.get(key)) {
				System.out.print(target + "\t");
			}
			System.out.println();
		}
	}

	public void exportEdgeList(String file, String separator,
			boolean zeroIndexed) throws IOException {
		System.out.println("Exporting ...");
		File f = new File(file);
		if (!f.exists()) {
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();
			f.createNewFile();
		}
		StringBuilder sb = new StringBuilder();
		for (int key : graph.keySet()) {
			for (int target : graph.get(key)) {
				if (zeroIndexed) {
					sb.append(key + separator + target + "\n");
				} else {
					sb.append((key + 1) + separator + (target + 1) + "\n");
				}
			}
		}
		PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
		out.println(sb.toString());
		out.close();
		System.out.println("Done!");
	}

	public static void main(String[] args) {
	}

}
