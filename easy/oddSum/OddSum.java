package easy.oddSum;

import java.io.*;
import java.util.StringTokenizer;

public class OddSum {

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
			int[] odds = new int[n];
			int sum = 0;
			int minPos = 9999;
			int maxNeg = -9999;
			for (int i = 0; i < n; i++) {
				odds[i] = in.nextInt();
				if (odds[i] > 0) {
					sum += odds[i];
					if (odds[i] % 2 != 0)
						minPos = Math.min(minPos, odds[i]);
				} else {
					if (odds[i] % 2 != 0)
						maxNeg = Math.max(maxNeg, odds[i]);
				}
			}
			if (sum % 2 == 0)
				sum += Math.max(-minPos, maxNeg);
			System.out.println(sum);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}