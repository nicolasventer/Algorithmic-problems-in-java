package easy.cuttingCarot;
import java.io.*;
import java.util.StringTokenizer;

public class CuttingCarrot {

	// variables
	public static int n, h;
	public static double aeraByRabbit, firstHight;

	// functions

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			h = in.nextInt();
			// useFunctions
			aeraByRabbit = 0.5 * h / n;
			firstHight = Math.sqrt(2 * h * aeraByRabbit);
			// printResult
			for (int i = 1; i < n; i++)
				System.out.print(firstHight * Math.sqrt(i) + " ");
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