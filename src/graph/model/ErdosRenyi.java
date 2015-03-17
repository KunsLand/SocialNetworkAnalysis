package graph.model;

import java.io.IOException;
import java.util.Random;

import graph.Graph;

public class ErdosRenyi {

	public static Graph createGraph(int n, double p, boolean directed) {
		int[][] adj = new int[n][n];
		if (p > 1)
			return new Graph(adj);
		for (int i = 0; i < n; i++) {
			for (int j = directed ? 0 : i + 1; j < n; j++) {
				if (i != j && Math.random() <= p) {
					adj[i][j]++;
					if (!directed && adj[j][i] == 0)
						adj[j][i]++;
				}
			}
		}
		return new Graph(adj);
	}

	public static Graph createGraph(int n, int e, boolean directed) {
		int[][] adj = new int[n][n];
		int edges = directed ? Math.min(e, n * n - n) : Math.min(e, n * (n - 1)
				/ 2);
		if (edges < e)
			System.err.println("Too many edges => (n=" + n + ", e=" + e
					+ ", directed=" + directed + ")!");
		Random rdn = new Random();
		while (Graph.getNumEdges(adj) < edges) {
			int i = rdn.nextInt(n), j = rdn.nextInt(n);
			if (i == j || adj[i][j] > 0)
				continue;
			adj[i][j]++;
			if (!directed && adj[j][i] == 0)
				adj[j][i]++;
		}
		return new Graph(adj);
	}

	public static void main(String[] args) throws IOException {
		Graph g1 = ErdosRenyi.createGraph(8, 0.1, false);
		Graph g2 = ErdosRenyi.createGraph(8, 10, false);
		Graph g3 = ErdosRenyi.createGraph(8, 0.1, true);
		Graph g4 = ErdosRenyi.createGraph(8, 10, true);
		g1.display();
		g1.listDegrees();
		g2.display();
		g2.listDegrees();
		g3.display();
		g3.listDegrees();
		g4.display();
		g4.listDegrees();
	}

}
