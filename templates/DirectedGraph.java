package templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DirectedGraph {

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

		public long nextLong() throws IOException {
			return Long.parseLong(next());
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

	public static void graphValues(int nbNodes, int nbLinks) throws IOException {
		graphValues(nbNodes, nbLinks, doNothing);
	}

	public static void graphValues(int nbNodes, int nbLinks, OnRead r) throws IOException {
		graph = new int[nbLinks][3]; // 2 children
		parent = new int[nbNodes];
		visited = new boolean[nbNodes];
		boolean stopUse = false;
		for (int i = 0; i < nbLinks; i++) {
			graph[i][0] = in.nextInt(); // value
			graph[i][1] = in.nextInt() - 1; // left child
			graph[i][2] = in.nextInt() - 1; // right child
			if (graph[i][1] != -2)
				parent[graph[i][1]] = i;
			if (graph[i][2] != -2)
				parent[graph[i][2]] = i;
			stopUse = stopUse || r.use(i);
		}
	}

	public static int[][] graph;
	public static int[] parent;

	private static boolean[] visited;
	private static LinkedList<Integer> queue = new LinkedList<Integer>();

	// functions

	// if only one root
	public static int getRoot() {
		return getRoot(0);
	}

	private static int getRoot(int i) {
		return parent[i] == 0 ? i : getRoot(parent[i]);
	}

	public static ArrayList<Integer> getAllRoots() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < parent.length; i++)
			if (parent[i] != -1) {
				int root = getRoot(i);
				result.add(root);
				// remove set
				visited[root] = true;
				queue.add(root);
				while (!queue.isEmpty()) {
					int current = queue.poll();
					parent[current] = -1;
					visited[current] = true;
					if (graph[current][1] != -2 && !visited[graph[current][1]]) {
						visited[graph[current][1]] = true;
						queue.add(graph[current][1]);
					}
					if (graph[current][2] != -2 && !visited[graph[current][2]]) {
						visited[graph[current][2]] = true;
						queue.add(graph[current][2]);
					}
				}
			}
		return result;
	}

	public static void dfs() {
		Arrays.fill(visited, false);
		dfs(getRoot(), 0);
	}

	public static void dfs(int current, int depth) {
		visited[current] = true;
		if (graph[current][1] != -2 && !visited[graph[current][1]])
			dfs(graph[current][1], depth + 1);
		if (graph[current][2] != -2 && !visited[graph[current][2]])
			dfs(graph[current][2], depth + 1);
	}

}
