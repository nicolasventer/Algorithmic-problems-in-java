package normal.mikeAndGcdProblem;
import java.io.*;
import java.util.StringTokenizer;

public class MikeAndGcdProblem {

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
			int n = in.nextInt();
			int[] l = new int[n + 1];
			int minGcd = 0;
			for (int i = 0; i < n; i++) {
				l[i] = in.nextInt();
				minGcd = gcd(minGcd, l[i]);
			}
			int nbMove = 0;
			if (minGcd == 1) {
				for (int i = 0; i < n; i++)
					if (l[i] % 2 == 1)
						if (l[i + 1] % 2 == 1) {
							nbMove++;
							l[i] = 0;
							l[i + 1] = 0;
						} else {
							nbMove += 2;
							l[i] = 0;
						}
			}
			System.out.println("YES");
			System.out.println(nbMove);
			in.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}