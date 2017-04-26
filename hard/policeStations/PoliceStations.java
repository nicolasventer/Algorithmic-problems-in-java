package hard.policeStations;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PoliceStations {

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

	public static OnRead notDirected = new OnRead() {
		@Override
		public boolean use(int i) {
			links.get(graph[i][1]).add(i);
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

	public static void graphValues(int n, int p) throws IOException {
		graphValues(n, p, doNothing);
	}

	public static void graphValues(int n, int p, OnRead r) throws IOException {
		graph = new int[p][2];
		boolean stopUse = false;
		for (int i = 0; i < n; i++)
			links.add(new ArrayList<Integer>());
		for (int i = 0; i < p; i++) {
			graph[i][0] = in.nextInt() - 1;
			graph[i][1] = in.nextInt() - 1;
			links.get(graph[i][0]).add(i);
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

	public static boolean[] boolN;

	public static int[][] graph;
	public static ArrayList<ArrayList<Integer>> links = new ArrayList<ArrayList<Integer>>();

	public static boolean[] visited;
	public static LinkedList<Integer> queue = new LinkedList<Integer>();

	// variables

	public static int n, k, d;

	public static boolean[] toKeep;
	public static int nbToKeep = 0;

	// onReads

	public static OnRead getNbToKeep = new OnRead() {
		@Override
		public boolean use(int i) {
			if (!toKeep[i]) {
				toKeep[i] = true;
				nbToKeep++;
			}
			return false;
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			k = in.nextInt();
			d = in.nextInt();
			visited = new boolean[n];
			boolNvalues(n, k, addToQueue); // boolN == visited
			graphValues(n, n - 1, notDirected);
			// useFunctions
			toKeep = new boolean[n - 1];
			if (d != 0)
				searchNotDirected(true, getNbToKeep);
			// printResult
			System.out.println(toKeep.length - nbToKeep);
			for (int i = 0; i < toKeep.length; i++)
				if (!toKeep[i])
					System.out.print((i + 1) + " ");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

}