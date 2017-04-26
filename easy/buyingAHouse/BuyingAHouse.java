package easy.buyingAHouse;

import java.io.*;
import java.util.StringTokenizer;

public class BuyingAHouse {

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
	
	// variables

	public static int nbHouse, numCrush, money;

	public static int houseToBuy = -1;
	public static int distance = Integer.MAX_VALUE;

	// onReads
	
	public static OnRead getHouseToBuy = new OnRead() {
		@Override
		public boolean use(int i) {
			int distI = Math.abs(numCrush - i);
			if (distI > distance)
				return true;
			if (i != numCrush && intN[i] != 0 && intN[i] <= money && distance > distI) {
				houseToBuy = i;
				distance = distI;
			}
			return false;
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			nbHouse = in.nextInt();
			numCrush = in.nextInt() - 1;
			money = in.nextInt();
			intNvalues(nbHouse, getHouseToBuy);
			// useFunctions
			// printResult
			System.out.print(distance * 10);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// functions

}