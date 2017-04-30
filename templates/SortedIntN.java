package templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SortedIntN {

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

	public static void sortedIntNvalues(int n) throws IOException {
		sortedIntNvalues(n, doNothing);
	}

	public static void sortedIntNvalues(int n, OnRead r) throws IOException {
		sortedIntN = new int[n][4];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			sortedIntN[i][0] = in.nextInt();
			sortedIntN[i][1] = i;
			stopUse = stopUse || r.use(i);
		}
		Arrays.sort(sortedIntN, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		for (int i = 0; i < sortedIntN.length; i++)
			sortedIntN[sortedIntN[i][1]][2] = i;
		int value = sortedIntN[0][0];
		indexesNewValue.add(0);
		for (int i = 1; i < sortedIntN.length; i++) {
			if (value != sortedIntN[i][0]) {
				indexesNewValue.add(i);
				value = sortedIntN[i][0];
			}
			sortedIntN[i][3] = indexesNewValue.size() - 1;
		}
		indexesNewValue.add(sortedIntN.length);
	}

	// i,0 = value ||| i,1 = indexBeforeSort ||| i,2 = indexAfterSort
	// i,3 = numInterval
	// ==> sortedIntN[sortedInt[i][1]][2] == i == sortedIntN[sortedInt[i][2]][1]
	// ==> indexesNewValue.get(sortedInt[i][3]) <= i
	// ==> i <= indexesNewValue.get(sortedInt[i][3]+1)
	public static int[][] sortedIntN;
	public static ArrayList<Integer> indexesNewValue = new ArrayList<Integer>();

}
