package hard.policeStations;
import java.io.*;
import java.util.ArrayList;
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

	public static void main(String[] args) {
		try {
			int n = in.nextInt();
			int k = in.nextInt();
			int d = in.nextInt();
			boolean[] visited = new boolean[n];
			LinkedList<Integer> toVisit = new LinkedList<Integer>();
			for (int i = 0; i < k; i++) {
				int indexPolice = in.nextInt() - 1;
				visited[indexPolice] = true;
				toVisit.push(indexPolice);
			}
			ArrayList<ArrayList<Integer[]>> links = new ArrayList<ArrayList<Integer[]>>(n);
			for (int i = 0; i < n; i++)
				links.add(new ArrayList<Integer[]>());
			for (int i = 0; i < n - 1; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				links.get(u).add(new Integer[] { v, i });
				links.get(v).add(new Integer[] { u, i });
			}
			boolean[] toKeep = new boolean[n - 1];
			int nbToKeep = 0;
			if (d != 0)
				while (!toVisit.isEmpty()) {
					int u = toVisit.pop();
					for (Integer[] link : links.get(u)) {
						int v = link[0];
						int i = link[1];
						if (!visited[v]) {
							toVisit.add(v);
							toKeep[i] = true;
							nbToKeep++;
							visited[v] = true;
						}
					}
				}
			System.out.println(n - 1 - nbToKeep);
			for (int i = 0; i < toKeep.length; i++)
				if (!toKeep[i])
					System.out.print((i + 1) + " ");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}