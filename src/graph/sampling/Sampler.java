package graph.sampling;

import java.io.IOException;

import graph.Graph;
import graph.model.WattsStrogatz;

public class Sampler {

	public static void validateWS() throws IOException {
		int n = 1000, k = 10;
		for (int i = 0; i < 21; i++) {
			double p = Math.pow(10.0, -4.0 + i / 5.0);
			System.out.println(p);
			for(int j = 1; j <= 20; j++){
				Graph g = WattsStrogatz.createGraph(n, k, p, false);
				g.exportEdgeList("D:/ClassicalModelSamples/WS/" + i + "_" + j + ".csv", ",",
					false, false);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		validateWS();
	}

}
