package easy.mikeAndPalindrome;
import java.io.*;
import java.util.StringTokenizer;

public class MikeAndPalindrome {

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
			boolean hasChanged = false;
			for (int i = 0; i < t.length() / 2; i++)
				if (t.charAt(i) != t.charAt(t.length() - i - 1))
					if (hasChanged) {
						System.out.println("NO");
						return;
					} else
						hasChanged = true;
			if (hasChanged || t.length()%2 == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}