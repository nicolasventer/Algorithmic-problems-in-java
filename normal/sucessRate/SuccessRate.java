package normal.sucessRate;


import java.io.*;
import java.util.StringTokenizer;

public class SuccessRate {

	// variables

	// functions

	public static void printNbSubmissions(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			long x = in.nextLong();
			long y = in.nextLong();
			long p = in.nextLong();
			long q = in.nextLong();
			if (p == 0)
				if (x == 0)
					System.out.println(0);
				else
					System.out.println(-1);
			else if (p == q)
				if (x == y)
					System.out.println(0);
				else
					System.out.println(-1);
			else {
				long a = Math.max((y + q - 1) / q, (x + p - 1) / p);
				long nx = a * p;
				long ny = a * q;
				if (nx - x <= ny - y)
					System.out.println(ny - y);
				else {
					long b = nx - x - (ny - y);
					b = (b + q - p - 1) / (q - p);
					System.out.println(ny - y + b * q);
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			// readValues
			// useFunctions
			printNbSubmissions(in.nextInt());
			// printResult
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// utils

	public static interface OnRead {
		public boolean use(int i);
	}

	public static OnRead doNothing = new OnRead() {
		@Override
		public boolean use(int i) {
			return false;
		}
	};

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
}