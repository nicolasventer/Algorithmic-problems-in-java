

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BrokenBST2 {

	public static FastScanner in = new FastScanner(System.in);

	public static class FastScanner {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public FastScanner(InputStream i) {
			reader = new BufferedReader(new InputStreamReader(i));
			tokenizer = new StringTokenizer("");
		}

		public String next() throws IOException {
			while (!tokenizer.hasMoreTokens())
				tokenizer = new StringTokenizer(reader.readLine());
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public void close() throws IOException {
			reader.close();
		}
	}

	public static interface OnRead {
		public boolean use(int i);
	}

	public static OnRead doNothing = new OnRead() {
		@Override
		public boolean use(int i) {
			return false;
		}
	};

	// if only one root
	public static int getRoot() {
		return getRoot(0);
	}

	private static int getRoot(int i) {
		return parent[i] == 0 ? i : getRoot(parent[i]);
	}

	public static void graphValues(int nbNodes, int nbLinks, boolean directed) throws IOException {
		graphValues(nbNodes, nbLinks, directed, doNothing);
	}

	// 2 children or link between 2 indexes
	public static void graphValues(int nbNodes, int nbLinks, boolean directed, OnRead r) throws IOException {
		graph = new int[nbLinks][directed ? 3 : 2];
		parent = new int[nbNodes];
		boolean stopUse = false;
		for (int i = 0; i < nbNodes; i++)
			links.add(new ArrayList<Integer>());
		for (int i = 0; i < nbLinks; i++) {
			if (directed) {
				graph[i][0] = in.nextInt(); // value
				graph[i][1] = in.nextInt() - 1; // left child
				graph[i][2] = in.nextInt() - 1; // right child
				if (graph[i][1] != -2) {
					links.get(i).add(graph[i][1]);
					parent[graph[i][1]] = i;
				}
				if (graph[i][2] != -2) {
					links.get(i).add(graph[i][2]);
					parent[graph[i][2]] = i;
				}
			} else {
				graph[i][0] = in.nextInt() - 1; // first
				graph[i][1] = in.nextInt() - 1; // snd
				links.get(graph[i][0]).add(i);
				links.get(graph[i][1]).add(i);
			}
			stopUse = stopUse || r.use(i);
		}
	}

	public static int getNeighbour(int current, int link) {
		return graph[link][0] + graph[link][1] - current;
	}

	public static int[][] graph;
	public static ArrayList<ArrayList<Integer>> links = new ArrayList<ArrayList<Integer>>();
	public static int[] parent;

	// variables

	public static int n;

	public static ArrayList<Integer> notFound = new ArrayList<Integer>();
	public static HashSet<Integer> found = new HashSet<Integer>();

	// onReads

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			graphValues(n, n, true);
			// useFunctions
			found(getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
			notFound.removeAll(found);
			// printResult
			System.out.println(notFound.size());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

	public static void found(int index, int minValue, int maxValue) {
		if (graph[index][0] < minValue || graph[index][0] > maxValue)
			notFound.add(graph[index][0]);
		else
			found.add(graph[index][0]);
		if (graph[index][1] != -2)
			found(graph[index][1], minValue, Math.min(maxValue, graph[index][0]));
		if (graph[index][2] != -2)
			found(graph[index][2], Math.max(minValue, graph[index][0]), maxValue);
	}
}