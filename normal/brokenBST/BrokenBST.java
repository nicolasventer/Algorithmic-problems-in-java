package normal.brokenBST;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BrokenBST {

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

	public static ArrayList<Integer> notFound = new ArrayList<Integer>();
	public static HashSet<Integer> found = new HashSet<Integer>();

	public static void nbFails(int[][] tree, int index, int minValue, int maxValue) {
		if (index != -2) {
			if (tree[index][0] < minValue || tree[index][0] > maxValue)
				notFound.add(tree[index][0]);
			else
				found.add(tree[index][0]);
			nbFails(tree, tree[index][1], minValue, Math.min(maxValue, tree[index][0]));
			nbFails(tree, tree[index][2], Math.max(minValue, tree[index][0]), maxValue);
		}
	}

	public static void main(String[] args) {
		try {
			int n = in.nextInt();
			int[][] tree = new int[n][3];
			HashSet<Integer> notRoot = new HashSet<Integer>();
			for (int i = 0; i < n; i++) {
				tree[i][0] = in.nextInt();
				tree[i][1] = in.nextInt() - 1;
				notRoot.add(tree[i][1]);
				tree[i][2] = in.nextInt() - 1;
				notRoot.add(tree[i][2]);
			}
			int root = 0;
			while (notRoot.contains(root))
				root++;
			nbFails(tree, root, -2147483647, 2147483647);
			notFound.removeAll(found);
			System.out.println(notFound.size());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}