package hard.weirdJourney;
import java.io.*;
import java.util.StringTokenizer;

public class WeirdJourney {

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

	public static int find(int x, int[] dfs) {
		if (dfs[x] == x)
			return x;
		else
			return dfs[x] = find(dfs[x], dfs);
	}

	public static void main(String[] args) {
		try {
			int n = in.nextInt();
			int m = in.nextInt();
			long[] nbLink = new long[n];
			boolean[] visited = new boolean[n];
			long nbSame = 0;
			int[] dfs = new int[n];
			for (int i = 0; i < n; i++)
				dfs[i] = i;
			for (int i = 0; i < m; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				if (u != v) {
					nbLink[u]++;
					nbLink[v]++;
				} else
					nbSame++;
				visited[u] = visited[v] = true;
				int a = find(u, dfs);
				int b = find(v, dfs);
				if (a != b) // if u & v not in same set, join them
					dfs[a] = b;
			}
			int nbRoots = 0;
			for (int i = 0; i < n; i++)
				if (visited[i] && dfs[i] == i)
					nbRoots++;
			if (nbRoots > 1) {
				System.out.println(0);
				return;
			}
			long nbDiff = m - nbSame;
			long result = 0;
			for (int i = 0; i < n; i++)
				if (nbLink[i] >= 2)
					result += sumN(nbLink[i] - 1);
			result += nbSame * nbDiff;
			result += sumN(nbSame - 1);
			System.out.println(result);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static long sumN(long n) {
		return n * (n + 1) / 2;
	}

}
