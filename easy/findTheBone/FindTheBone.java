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

	public static void intNPvalues(int n, int p, OnRead r) throws IOException {
		intNP = new int[n][p];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++)
				intNP[i][j] = in.nextInt();
			stopUse = stopUse || r.use(i);
		}
	}

	public static boolean[] boolN;

	public static int[][] intNP;

	// variables

	public static int nbCups, nbHoles, nbSwap;

	public static int currentPos = 1;

	// onReads

	public static OnRead swapPos = new OnRead() {
		@Override
		public boolean use(int i) {
			if (currentPos == intNP[i][0] || currentPos == intNP[i][1])
				currentPos = intNP[i][0] + intNP[i][1] - currentPos;
			return boolN[currentPos - 1];
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			nbCups = in.nextInt();
			nbHoles = in.nextInt();
			nbSwap = in.nextInt();
			boolNvalues(nbCups, nbHoles);
			intNPvalues(nbSwap, 2, swapPos);
			// useFunctions
			// printResult
			System.out.print(currentPos);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

}