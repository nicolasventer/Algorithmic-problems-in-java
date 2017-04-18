package hard.volatileKite;

import java.io.*;
import java.util.StringTokenizer;

public class VolatileKite {

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
			int[] pX = new int[n + 2];
			int[] pY = new int[n + 2];
			for (int i = 0; i < n; i++) {
				pX[i] = in.nextInt();
				pY[i] = in.nextInt();
			}
			pX[n] = pX[0];
			pY[n] = pY[0];
			pX[n + 1] = pX[1];
			pY[n + 1] = pY[1];
			double result = 2147483647;
			for (int i = 1; i <= n; i++) {
				double diffX = pX[i + 1] - pX[i - 1];
				double diffY = pY[i + 1] - pY[i - 1];
				double temp = Math.abs(diffX * (pY[i] - pY[i + 1]) - diffY * (pX[i] - pX[i + 1]));
				result = Math.min(result, temp / Math.sqrt(diffX * diffX + diffY * diffY));
			}
			System.out.println(result / 2);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}