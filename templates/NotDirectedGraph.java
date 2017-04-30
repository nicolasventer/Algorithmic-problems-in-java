package templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class NotDirectedGraph {

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

	public static OnRead addToQueue = new OnRead() {
		@Override
		public boolean use(int i) {
			visited[i] = true;
			queue.add(i);
			return false;
		}
	};

	public static OnRead pushToQueue = new OnRead() {
		@Override
		public boolean use(int i) {
			visited[i] = true;
			queue.push(i);
			return false;
		}
	};

	public static void graphValues(int nbNodes, int nbLinks) throws IOException {
		graphValues(nbNodes, nbLinks, doNothing);
	}

	// 2 children or link between 2 indexes
	public static void graphValues(int nbNodes, int nbLinks, OnRead r) throws IOException {
		graph = new int[nbLinks][2];
		parent = new int[nbNodes];
		boolean stopUse = false;
		for (int i = 0; i < nbNodes; i++)
			links.add(new ArrayList<Integer>());
		for (int i = 0; i < nbLinks; i++) {
			graph[i][0] = in.nextInt() - 1; // first
			graph[i][1] = in.nextInt() - 1; // snd
			links.get(graph[i][0]).add(i);
			links.get(graph[i][1]).add(i);
			stopUse = stopUse || r.use(i);
		}
	}

	public static int[][] graph;
	public static ArrayList<ArrayList<Integer>> links = new ArrayList<ArrayList<Integer>>();
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
				addToQueue.use(root);
				while (!queue.isEmpty()) {
					int current = queue.poll();
					parent[current] = -1;
					visited[current] = true;
					for (int neighbour : links.get(current))
						if (!visited[neighbour])
							addToQueue.use(neighbour); // bfs
				}
			}
		return result;
	}

	public static int getNeighbour(int current, int link) {
		return graph[link][0] + graph[link][1] - current;
	}
	
	public static void initSearch(int n, int nbBool) throws IOException {
		visited = new boolean[n];
		for (int i = 0; i < nbBool; i++) {
			int j = in.nextInt() - 1;
			addToQueue.use(j);
		}
	}

	private static boolean cleanSearch(boolean toReturn) {
		Arrays.fill(visited, false);
		return toReturn;
	}

	// use only for non directed graph
	public static boolean bfs(OnRead r) {
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int link : links.get(current)) {
				int neighbour = getNeighbour(current, link);
				if (!visited[neighbour]) {
					if (r.use(link))
						return cleanSearch(true);
					addToQueue.use(neighbour);
				}
			}
		}
		return cleanSearch(false);
	}

	public static boolean dfs(OnRead r) {
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int link : links.get(current)) {
				int neighbour = getNeighbour(current, link);
				if (!visited[neighbour]) {
					if (r.use(link))
						return cleanSearch(true);
					pushToQueue.use(neighbour);
				}
			}
		}
		return cleanSearch(false);
	}
}
