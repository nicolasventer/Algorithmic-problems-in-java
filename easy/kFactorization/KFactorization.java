package easy.kFactorization;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KFactorization {

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
			int div = 2;
			ArrayList<Integer> factors = new ArrayList<Integer>();
			while (factors.size() < k - 1 && div <= Math.sqrt(n)) {
				while (factors.size() < k - 1 && div <= Math.sqrt(n) && n % div == 0) {
					factors.add(div);
					n /= div;
				}
				div += 2;
				if (div == 4)
					div = 3;
			}
			if (factors.size() < k - 1)
				System.out.println(-1);
			else {
				for (Integer i : factors)
					System.out.print(i + " ");
				System.out.println(n);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}