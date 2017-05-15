package templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SortedLongN {

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

	public static void sortedLongNvalues(int n) throws IOException {
		sortedLongNvalues(n, doNothing);
	}

	public static void sortedLongNvalues(int n, OnRead r) throws IOException {
		sortedLongtN = new long[n][4];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			sortedLongtN[i][0] = in.nextInt();
			sortedLongtN[i][1] = i;
			stopUse = stopUse || r.use(i);
		}
		Arrays.sort(sortedLongtN, (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
		for (int i = 0; i < sortedLongtN.length; i++)
			sortedLongtN[(int) sortedLongtN[i][1]][2] = i;
		long value = sortedLongtN[0][0];
		indexesNewValue.add(0);
		for (int i = 1; i < sortedLongtN.length; i++) {
			if (value != sortedLongtN[i][0]) {
				indexesNewValue.add(i);
				value = sortedLongtN[i][0];
			}
			sortedLongtN[i][3] = indexesNewValue.size() - 1;
		}
		indexesNewValue.add(sortedLongtN.length);
	}

	// i,0 = value ||| i,1 = indexBeforeSort ||| i,2 = indexAfterSort
	// i,3 = numInterval
	// ==> sortedLongtN[sortedLongtN[i][1]][2] == i == sortedIntN[sortedLongtN[i][2]][1]
	// ==> indexesNewValue.get(sortedLongtN[i][3]) <= i
	// ==> i <= indexesNewValue.get(sortedLongtN[i][3]+1)
	public static long[][] sortedLongtN;
	public static ArrayList<Integer> indexesNewValue = new ArrayList<Integer>();

}
