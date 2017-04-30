package templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoolN {

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

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public void close() throws IOException {
			reader.close();
		}

	}
	
	public static interface OnRead {
		public boolean use(int i);
	}

	public static OnRead doNothing = new OnRead() {
		@Override
		public boolean use(int i) {
			return false;
		}
	};
	
	public static void boolNvalues(int n, int nbBool) throws IOException {
		boolNvalues(n, nbBool, doNothing);
	}

	public static void boolNvalues(int n, int nbBool, OnRead r) throws IOException {
		boolN = new boolean[n];
		boolean stopUse = false;
		for (int i = 0; i < nbBool; i++) {
			int j = in.nextInt() - 1;
			boolN[j] = true;
			stopUse = stopUse || r.use(j);
		}
	}
	
	public static boolean[] boolN;
}
