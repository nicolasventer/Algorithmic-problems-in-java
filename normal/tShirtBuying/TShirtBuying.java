package normal.tShirtBuying;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TShirtBuying {

	// variables

	public static int n, m;
	public static int[] indexToBuy = new int[3];

	// functions

	public static OnRead buyTshirt = new OnRead() {
		@Override
		public boolean use(int i) {
			int favoriteColor = intN[i] - 1;
			while (indexToBuy[favoriteColor] < sortedIntN.length
					&& intNP[0][sortedIntN[indexToBuy[favoriteColor]][1]] != intN[i]
					&& intNP[1][sortedIntN[indexToBuy[favoriteColor]][1]] != intN[i]) {
				indexToBuy[favoriteColor]++;
			}
			if (indexToBuy[favoriteColor] == sortedIntN.length)
				in.s.append("-1 ");
			else {
				// remove color
				intNP[0][sortedIntN[indexToBuy[favoriteColor]][1]] = 0;
				intNP[1][sortedIntN[indexToBuy[favoriteColor]][1]] = 0;
				in.s.append(sortedIntN[indexToBuy[favoriteColor]][0]);
				in.s.append(" ");
			}
			return false;
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			sortedIntNvalues(n);
			intNPvalues(2, n);
			m = in.nextInt();
			intNvalues(m, buyTshirt);
			// useFunctions
			// printResult
			System.out.println(in.s);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// utils

	public static FastScanner in = new FastScanner(System.in);

	public static class FastScanner {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
		public StringBuilder s;

		public FastScanner(InputStream i) {
			reader = new BufferedReader(new InputStreamReader(i));
			tokenizer = new StringTokenizer("");
			s = new StringBuilder();
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

	public static int[] intN;
	public static int[][] intNP;

	public static void sortedIntNvalues(int n) throws IOException {
		sortedIntNvalues(n, doNothing);
	}

	public static void sortedIntNvalues(int n, OnRead r) throws IOException {
		sortedIntN = new int[n][3];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			sortedIntN[i][0] = in.nextInt();
			sortedIntN[i][1] = i;
			stopUse = stopUse || r.use(i);
		}
		Arrays.sort(sortedIntN, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
	}

	// i,0 = value ||| i,1 = indexBeforeSort
	public static int[][] sortedIntN;

}