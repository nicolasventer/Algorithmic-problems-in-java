package easy.distancesToZero;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DistancesToZero {

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

	public static int n;
	public static LinkedList<Integer> nodes = new LinkedList<Integer>();
	public static LinkedList<Integer> distanceToNode = new LinkedList<Integer>();

	// onReads

	public static OnRead getNodes = new OnRead() {
		@Override
		public boolean use(int i) {
			if (intN[i] == 0) {
				nodes.add(i);
				distanceToNode.add(0);
			}
			return false;
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			intNvalues(n, getNodes);
			Arrays.fill(intN, Integer.MAX_VALUE);
			// useFunctions
			setDistance();
			// printResult
			for (int d : intN)
				System.out.print(d + " ");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

	public static void setDistance() {
		while (!nodes.isEmpty())
			setDistance(nodes.poll(), distanceToNode.poll());
	}

	private static void setDistance(int index, int distance) {
		if (intN[index] > distance) {
			intN[index] = distance;
			if (index > 0) {
				nodes.add(index - 1);
				distanceToNode.add(distance + 1);
			}
			if (index < n - 1) {
				nodes.add(index + 1);
				distanceToNode.add(distance + 1);
			}
		}
	}
}