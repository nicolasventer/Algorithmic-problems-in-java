package easy.mikeAndStrings;
import java.io.*;
import java.util.StringTokenizer;

public class MikeAndStrings {

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
			String[] text = new String[n];
			for (int i = 0; i < n; i++)
				text[i] = in.next();
			int minSum = Integer.MAX_VALUE;
			for (int j = 0; j < text[0].length(); j++) {
				String currentText = text[0].substring(j) + text[0].substring(0, j);
				int sum = 0;
				for (int i = 0; i < n; i++) {
					int k = 0;
					while (k < text[0].length()
							&& !currentText.equals(text[i].substring(k) + text[i].substring(0, k)))
						k++;
					if (k == text[0].length()) {
						System.out.println("-1");
						return;
					}
					sum += k;
				}
				minSum = Math.min(minSum, sum);
			}
			System.out.println(minSum);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}