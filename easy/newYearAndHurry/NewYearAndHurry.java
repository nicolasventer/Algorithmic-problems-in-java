package easy.newYearAndHurry;

import java.io.*;
import java.util.StringTokenizer;

public class NewYearAndHurry {

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
			int nbProblems = in.nextInt();
			int timeRemaning = (240 - in.nextInt()) / 5;
			for (int i = 1; i <= nbProblems; i++) {
				timeRemaning -= i;
				if (timeRemaning < 0) {
					System.out.println(i - 1);
					return;
				}
			}
			System.out.println(nbProblems);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}