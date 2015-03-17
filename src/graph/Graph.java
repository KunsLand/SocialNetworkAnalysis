package graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Graph {

	private int[][] adj;
	private int n, e;
	private boolean directed;

	public Graph(int n, boolean directed) {
		this.adj = new int[n][n];
		this.n = n;
		this.e = 0;
		this.directed = directed;
	}

	public Graph(int[][] adj) {
		this.adj = adj;
		this.n = adj.length;
		this.e = getNumEdges(adj);
		this.directed = isDirected(adj);
		;
	}

	public int countNodes() {
		return n;
	}

	public int countEdges() {
		return e;
	}

	public int[][] getAdj() {
		return adj;
	}

	public int[] getDegrees() {
		return getDegrees(adj);
	}
	
	public int getDegree(int index){
		return getDegree(adj, index);
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean isSelfLooped() {
		return isSelfLooped(adj);
	}

	public void display() {
		printAdjMatrix(adj);
	}

	public void listDegrees() {
		int[] deg = getDegrees();
		for (int i = 0; i < n; i++)
			System.out.print(i + "\t");
		System.out.println();
		for (int i = 0; i < n; i++)
			System.out.print(deg[i] + "\t");
		System.out.println();
	}

	public void exportEdgeList(String file, String separator, boolean weighted,
			boolean zeroIndexed) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();
			f.createNewFile();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = directed ? 0 : i + 1; j < n; j++) {
				if (adj[i][j] == 0)
					continue;
				String str = weighted ? separator + adj[i][j] + "\n" : "\n";
				if (zeroIndexed)
					sb.append(i + separator + j + str);
				else
					sb.append((i + 1) + separator + (j + 1) + str);
			}
		}
		PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
		out.println(sb.toString());
		out.close();
	}

	public static boolean isDirected(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			for (int j = i + 1; j < adj.length; j++) {
				if (adj[i][j] != adj[j][i])
					return true;
			}
		}
		return false;
	}

	public static boolean isSelfLooped(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			if (adj[i][i] != 0)
				return true;
		}
		return false;
	}

	public static int getNumEdges(int[][] adj) {
		int dimension = adj.length, num = 0;
		boolean directed = isDirected(adj);
		for (int i = 0; i < dimension; i++) {
			int j = directed ? 0 : i;
			while (j < dimension) {
				num += adj[i][j++];
			}
		}
		return num;
	}
	
	public static int[] getDegrees(int[][] adj){
		int n = adj.length;
		int[] deg = new int[n];
		for (int i = 0; i < n; i++) {
			deg[i] = 0;
			for (int j = 0; j < n; j++) {
				deg[i] += adj[i][j];
			}
		}
		return deg;
	}
	
	public static int getDegree(int[][] adj, int index){
		int d = 0;
		for(int i = 0; i < adj.length; i++){
			d += adj[index][i];
		}
		return d;
	}

	public static void printAdjMatrix(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < adj[0].length; j++) {
				System.out.print(adj[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Nodes: " + adj.length + " Edges: "
				+ Graph.getNumEdges(adj));
	}
}
