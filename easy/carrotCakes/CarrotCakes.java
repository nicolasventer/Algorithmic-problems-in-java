package easy.carrotCakes;
import java.io.*;
import java.util.StringTokenizer;

public class CarrotCakes {

	// variables

	public static int n, t, k, d;

	// functions

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			t = in.nextInt();
			k = in.nextInt();
			d = in.nextInt();
			// useFunctions
			// printResult
			if ((n + k - 1) / k * t > t + d)
				System.out.println("YES");
			else
				System.out.println("NO");
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
}