package normal.ColoredBalls;
import java.io.*;
import java.util.StringTokenizer;

public class ColoredBalls {

	// variables

	public static int n;
	public static long min = Long.MAX_VALUE;

	// functions

	public static OnRead setMin = new OnRead() {
		@Override
		public boolean use(int i) {
			if (longN[i] < min)
				min = longN[i];
			return false;
		}
	};

	public static long getNbSet() {
		System.out.println();
		long nbSet = 0;
		long minPlus1 = min + 1;
		for (long l : longN)
			nbSet += (l + min) / minPlus1;
		return nbSet;
	}

	public static boolean changeMin() {
		for (long l : longN) {
			long q = l/min;
			if (l-q*min > q) {
				min = l/(q+1);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			longNvalues(n, setMin);
			// useFunctions
			while (changeMin())
				;
			// printResult
			System.out.println(getNbSet());
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

	public static void longNvalues(int n) throws IOException {
		longNvalues(n, doNothing);
	}

	public static void longNvalues(int n, OnRead r) throws IOException {
		longN = new long[n];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			longN[i] = in.nextLong();
			stopUse = stopUse || r.use(i);
		}
	}

	public static long[] longN;
}