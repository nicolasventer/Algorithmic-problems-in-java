package normal.newYearAndRating;

import java.io.*;
import java.util.StringTokenizer;

public class NewYearAndRating {

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
			int iMax = 2147483647;
			int[] interval = { -iMax, iMax };
			for (int i = 0; i < n; i++) {
				int c = in.nextInt();
				int d = in.nextInt();
				if (d == 1) {
					interval[0] = Math.max(interval[0], 1900) + c;
					interval[1] = interval[1] == iMax ? iMax : interval[1] + c;
				} else {
					interval[1] = Math.min(interval[1], 1899) + c;
					interval[0] = interval[0] == -iMax ? -iMax : interval[0] + c;
				}
				if (interval[0] > interval[1]) {
					System.out.println("Impossible");
					return;
				}
			}
			System.out.println(interval[1] == iMax ? "Infinity" : interval[1]);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}