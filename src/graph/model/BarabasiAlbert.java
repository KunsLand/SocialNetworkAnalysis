package graph.model;

import graph.Graph;

public class BarabasiAlbert {

	public static Graph createGraph(int m0, int m, int t) {
		if (m0 < 1 || m0 < m)
			return null;
		int[][] adj = createInitialGraph(m0, t);
		for (int i = 0; i < t; i++) {
			int[] degs = Graph.getDegrees(adj);
			int sumDeg = 0;
			for (int j = 0; j < degs.length; j++) {
				sumDeg += degs[j];
			}
			for (int j = 0; j < degs.length; j++) {
				double p = (degs[i] + 0.0) / (sumDeg + 0.0);
				if (Math.random() <= p) {
				}
			}
		}
		return null;
	}

	public static int[][] createInitialGraph(int m0, int t) {
		int n = m0 + t;
		int[][] adj = new int[n][n];
		for(int i = 0; i < m0; i++){
			for(int j = i + 1; j < m0; j++){
				adj[i][j] = 1;
				adj[j][i] = 1;
			}
		}
		return adj;
	}

	public static void main(String[] args) {
		createInitialGraph(5, 200000);
	}

}
