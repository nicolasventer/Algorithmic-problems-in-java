package normal.maximalGCD;
import java.io.*;
import java.util.StringTokenizer;

public class MaximalGCD {

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

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public void close() throws IOException {
			reader.close();
		}
	}

	// variables

	public static long n, k;

	// onReads

	public static void main(String[] args) {
		try {
			n = in.nextLong();
			k = in.nextLong();
			// readValues
			// useFunctions
			// printResult
			long sumK = sum(k);
			if (k >= 141421 || n < sumK)
				System.out.println(-1);
			else {
				long ratio = n / sumK;
				if (n % ratio != 0)
					ratio = getNewRatio(n, ratio);
				for (int i = 1; i <= k - 1; i++)
					System.out.print(ratio * i + " ");
				System.out.println(n - ratio * (sumK - k));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

	public static long sum(long k) {
		return k * (k + 1) / 2;
	}

	public static long getNewRatio(long n, long maxRatio) {
		long result = 1;
		long maxI = Math.min(maxRatio * maxRatio, n);
		for (Long i = 1l; i * i <= maxI; i++)
			if (n % i == 0)
				if (n / i <= maxRatio)
					return n / i;
				else
					result = i;
		return result;
	}

}