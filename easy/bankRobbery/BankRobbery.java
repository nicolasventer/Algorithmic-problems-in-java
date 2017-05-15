package easy.bankRobbery;
import java.io.*;
import java.util.StringTokenizer;

public class BankRobbery {

	// variables
	public static int leftGuard, rightGuard;
	public static int n;
	public static int nbNotes = 0;
	// functions
	
	public static OnRead setNbNotes = new OnRead() {
		@Override
		public boolean use(int i) {
			if (intN[i] > leftGuard && intN[i] < rightGuard)
				nbNotes++;
			return false;
		}
	};
	
	public static void main(String[] args) {
		try {
			// readValues
			in.nextInt(); // oleg
			leftGuard = in.nextInt();
			rightGuard = in.nextInt();
			n = in.nextInt();
			intNvalues(n, setNbNotes);
			// useFunctions
			// printResult
			System.out.println(nbNotes);
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
	
	public static void intNvalues(int n) throws IOException {
		intNvalues(n, doNothing);
	}

	public static void intNvalues(int n, OnRead r) throws IOException {
		intN = new int[n];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			intN[i] = in.nextInt();
			stopUse = stopUse || r.use(i);
		}
	}
	
	public static int[] intN;

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