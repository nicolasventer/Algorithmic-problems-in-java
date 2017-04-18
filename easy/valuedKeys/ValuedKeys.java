package easy.valuedKeys;

import java.io.*;
import java.util.StringTokenizer;

public class ValuedKeys {

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
			String x = in.next();
			String y = in.next();
			char[] z = y.toCharArray();
			for (int i = 0; i < z.length; i++)
				if (x.charAt(i) < y.charAt(i)) {
					System.out.println("-1");
					return;
				}
			System.out.println(z);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}