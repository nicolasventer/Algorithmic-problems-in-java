package easy.viciousKeyboard;

import java.io.*;
import java.util.StringTokenizer;

public class ViciousKeyboard {

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
			String t = in.next();
			int nbVk = 0;
			int oneAdd = 0;
			for (int i = 0; i < t.length() - 1; i++) {
				if (t.charAt(i) == 'V' && t.charAt(i + 1) == 'K') {
					nbVk++;
					i++;
				} else if (oneAdd == 0) {
					if (i < t.length() - 2 && t.charAt(i + 1) == 'V' && t.charAt(i + 2) == 'K') {
					} else if (t.charAt(i) == 'V' || t.charAt(i + 1) == 'K') {
						oneAdd = 1;
						i++;
					}
				}
			}
			System.out.println(nbVk + oneAdd);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}