package easy.findTheBone;
import java.io.*;
import java.util.StringTokenizer;

public class FindTheBone {

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
			int nbCups = in.nextInt();
			int nbHoles = in.nextInt();
			int nbSwap = in.nextInt();
			boolean[] holes = new boolean[nbCups];
			for (int i = 0; i < nbHoles; i++)
				holes[in.nextInt() - 1] = true;
			int currentPos = 1;
			for (int i = 0; i < nbSwap && !holes[currentPos - 1]; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				if (currentPos == u || currentPos == v)
					currentPos = u + v - currentPos;
			}
			System.out.print(currentPos);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}