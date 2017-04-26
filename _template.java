import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _template {

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

	public static interface OnRead2 {
		public boolean use(int i, int j);
	}

	public static void intNvalues(int n) throws IOException {
		intNvalues(n, doNothing);
	}

	public static void intNvalues(int n, OnRead r) throws IOException {
		intN = new int[n];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			intN[i] = in.nextInt();
			stopUse = stopUse || r.use(i);
		}
	}

	public static void charNvalues() throws IOException {
		charN = in.next().toCharArray();
	}

	public static void boolNvalues(int n, int nbBool) throws IOException {
		boolNvalues(n, nbBool, doNothing);
	}

	public static void boolNvalues(int n, int nbBool, OnRead r) throws IOException {
		boolN = new boolean[n];
		boolean stopUse = false;
		for (int i = 0; i < nbBool; i++) {
			int j = in.nextInt() - 1;
			boolN[j] = true;
			stopUse = stopUse || r.use(j);
		}
	}

	public static void intNPvalues(int n, int p) throws IOException {
		intNPvalues(n, p, doNothing);
	}

	public static void intNPvalues(int n, int p, OnRead r) throws IOException {
		intNP = new int[n][p];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++)
				intNP[i][j] = in.nextInt();
			stopUse = stopUse || r.use(i);
		}
	}

	public static void charNPvalues(int n) throws IOException {
		charNPvalues(n, doNothing);
	}

	public static void charNPvalues(int n, OnRead r) throws IOException {
		charNP = new char[n][];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			charNP[i] = in.next().toCharArray();
			stopUse = stopUse || r.use(i);
		}
	}

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
					for (int neighbour : links.get(current))
						if (!visited[neighbour])
							addToQueue.use(neighbour); // bfs
				}
			}
		return result;
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
	
	private static boolean cleanSearch(boolean toReturn) {
		Arrays.fill(visited, false);
		return toReturn;
	}

	// use only for non directed graph
	public static boolean searchNotDirected(boolean bfs, OnRead r) {
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int link : links.get(current)) {
				int neighbour = getNeighbour(current, link);
				if (!visited[neighbour]) {
					if (r.use(link))
						return cleanSearch(true);
					if (bfs)
						addToQueue.use(neighbour); // bfs
					else
						pushToQueue.use(neighbour); // dfs
				}
			}
		}
		return cleanSearch(false);
	}

	public static int[] intN;
	public static char[] charN;
	public static boolean[] boolN;

	public static int[][] intNP;
	public static char[][] charNP;

	public static int[][] graph;
	public static ArrayList<ArrayList<Integer>> links = new ArrayList<ArrayList<Integer>>();
	public static int[] parent;

	private static boolean[] visited;
	private static LinkedList<Integer> queue = new LinkedList<Integer>();

	// variables

	// onReads

	public static void main(String[] args) {
		try {
			// readValues
			// useFunctions
			// printResult
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

}