package normal.olegAndShares;
import java.io.*;
import java.util.StringTokenizer;

public class OlegAndShares {

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

	public static int gcd(int a, int b) {
		return b == 0 ? a : a % b == 0 ? b : gcd(b, a % b);
	}

	public static void main(String[] args) {
		try {
			long n = in.nextInt();
			long k = in.nextInt();
			long min = in.nextInt();
			long result = 0;
			boolean impossible = false;
			for (int i = 1; i < n; i++) {
				long diff = in.nextInt() - min;
				impossible = impossible || diff % k != 0;
				long q = diff / k;
				if (q < 0) { // a[i] < min
					min = min + diff;
					result += -q * i;
				} else
					result += q;
			}
			if (impossible)
				System.out.println(-1);
			else
				System.out.println(result);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}