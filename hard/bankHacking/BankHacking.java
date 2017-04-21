package hard.bankHacking;
import java.io.*;
import java.util.StringTokenizer;

public class BankHacking {

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
			int[] banks = new int[n];
			int[] nbLinksWithMax = new int[n];
			int[] nbLinksWithMaxMinus1 = new int[n];
			int vMax = Integer.MIN_VALUE;
			int firstIndexMax = 0;
			for (int i = 0; i < n; i++) {
				banks[i] = in.nextInt();
				if (vMax < banks[i]) {
					firstIndexMax = i;
					vMax = banks[i];
				}
			}
			int nbVmax = 0;
			int nbVmaxMinus1 = 0;
			for (int i = 0; i < n; i++) {
				if (banks[i] == vMax) {
					nbVmax++;
					nbLinksWithMax[i]++;
				}
				if (banks[i] == vMax - 1) {
					nbVmaxMinus1++;
					nbLinksWithMaxMinus1[i]++;
				}
			}
			for (int i = 0; i < n - 1; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				if (banks[u] == vMax)
					nbLinksWithMax[v]++;
				if (banks[v] == vMax)
					nbLinksWithMax[u]++;
				if (banks[u] == vMax - 1)
					nbLinksWithMaxMinus1[v]++;
				if (banks[v] == vMax - 1)
					nbLinksWithMaxMinus1[u]++;
			}
			int result;
			if (!hasNode(nbLinksWithMax, nbVmax))
				result = vMax + 2;
			else if (nbVmax == 1 && isNode(nbLinksWithMaxMinus1[firstIndexMax], nbVmaxMinus1))
				result = vMax;
			else
				result = vMax + 1;
			System.out.println(result);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean hasNode(int[] nbLinks, int sizeLink) {
		for (int nb : nbLinks)
			if (isNode(nb, sizeLink))
				return true;
		return false;
	}

	private static boolean isNode(int nb, int sizeLink) {
		return nb == sizeLink;
	}

}