package graph.model;

import java.util.Random;

import graph.Graph;

public class WattsStrogatz {

	public static Graph createGraph(int n, int k, double p, boolean directed) {
		if (n < 1 || k % 2 != 0 || p < 0 || p > 1)
			return null;
		// generate k-regular graph
		int[][] adj = genereateRegularGraph(n, k);
		// re-wire the connections in graph
		adj = rewireConnections(adj, p, directed);
		return new Graph(adj);
	}

	public static int[][] genereateRegularGraph(int n, int k) {
		int[][] adj = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k / 2; j++) {
				adj[i][(i + j + 1) % n] = 1;
				adj[i][(i - j - 1 + n) % n] = 1;
			}
		}
		return adj;
	}

	public static int[][] rewireConnections(int[][] adj, double p,
			boolean directed) {
		int k = 0;
		for (int i = 0; i < adj.length; i++) {
			if (adj[0][i] != 0)
				k++;
		}
		if (k == 0)
			return adj;
		Random rnd = new Random();
		// k/2 laps in total
		for (int j = 0; j < k / 2; j++) {
			// move around the ring until one lap is completed
			for (int i = 0; i < adj.length; i++) {
				int idx = (i + j + 1) % adj.length;
				// With probability p, reconnect this edge
				// to a vertex chosen uniformly at random over
				// the entire ring, with duplicate edges forbidden.
				// Otherwise, we leave the edge in place.
				if (adj[i][idx] != 0 && Math.random() <= p) {
					int index = rnd.nextInt(adj.length);
					if (adj[i][index] == 0 && index != i) {
						adj[i][index] = 1;
						adj[i][idx] = 0;
						if (!directed) {
							adj[index][i] = 1;
							adj[idx][i] = 0;
						}
					}
				}
			}
		}
		return adj;
	}

	public static void main(String[] args) {
		Graph g = createGraph(8, 4, 0.3, false);
		g.display();
		g.listDegrees();
	}

}
