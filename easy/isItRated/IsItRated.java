package easy.isItRated;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IsItRated {

	// variables
	
	public static int n;
	public static int min = Integer.MAX_VALUE;
	public static boolean rated = false, unrated = false;
	
	// functions
	
	public static OnRead setMinAndIsRated = new OnRead() {
		@Override
		public boolean use(int i) {
			rated = rated || intNP[i][0] != intNP[i][1];
			unrated = unrated || intNP[i][0] > min;
			if (min > intNP[i][0])
				min = intNP[i][0];
			return false;
		}
	};
	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			intNPvalues(n, 2, setMinAndIsRated);
			// useFunctions
			// printResult
			if (rated)
				System.out.println("rated");
			else if (unrated)
				System.out.println("unrated");
			else System.out.println("maybe");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// utils
	
	public static interface OnRead {
		public boolean use(int i);
	}

	public static OnRead doNothing = new OnRead() {
		@Override
		public boolean use(int i) {
			return false;
		}
	};

	public static void intNPvalues(int n, int p) throws IOException {
		intNPvalues(n, p, doNothing);
	}

	public static void intNPvalues(int n, int p, OnRead r) throws IOException {
		intNP = new int[n][p];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++)
				intNP[i][j] = in.nextInt();
			stopUse = stopUse || r.use(i);
		}
	}

	public static int[][] intNP;

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

}
