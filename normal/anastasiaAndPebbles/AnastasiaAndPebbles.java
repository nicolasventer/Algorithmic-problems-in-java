package normal.anastasiaAndPebbles;
import java.io.*;
import java.util.StringTokenizer;

public class AnastasiaAndPebbles {

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
			int k = in.nextInt();
			double nbDays = 0;
			for (int i = 0; i < n; i++) {
				int w = in.nextInt();
				int q = w / (2 * k);
				nbDays += q;
				int r = w - q * (2 * k);
				if (r > 0)
					if (r <= k)
						nbDays += 0.5;
					else
						nbDays++;
			}
			System.out.println((int) Math.ceil(nbDays));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}