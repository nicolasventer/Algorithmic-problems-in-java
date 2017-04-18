package easy.newYearAndNorthPole;

import java.io.*;
import java.util.StringTokenizer;

public class NewYearAndNorthPole {

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
			int[] pos = { 0, 20000 };
			for (int i = 0; i < n; i++) {
				int v = in.nextInt();
				String d = in.next();
				switch (d) {
				case "South":
					pos[1] -= v;
					break;
				case "North":
					pos[1] += v;
					break;
				case "East":
					if (pos[1] == 0 || pos[1] == 20000) {
						System.out.println("NO");
						return;
					}
					break;
				case "West":
					if (pos[1] == 0 || pos[1] == 20000) {
						System.out.println("NO");
						return;
					}
					break;
				default:
					break;
				}
				if (pos[1] < 0 || pos[1] > 20000)
					break;
			}
			if (pos[1] == 20000)
				System.out.println("YES");
			else
				System.out.println("NO");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}