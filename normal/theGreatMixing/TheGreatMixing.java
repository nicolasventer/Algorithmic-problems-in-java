package normal.theGreatMixing;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class TheGreatMixing {

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
			System.out.println(getK());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static int getK() throws IOException {
		int n = in.nextInt();
		int nb = Math.min(in.nextInt(), 100000);
		HashSet<Integer> pos = new HashSet<>();
		HashSet<Integer> neg = new HashSet<>();
		if (n == 0 || n == 1000) {
			for (int i = 0; i < nb; i++)
				if (in.nextInt() == n)
					return 1;
			return -1;
		}
		int kmax = 1000;
		for (int i = 0; i < nb; i++) {
			int t = in.nextInt() - n;
			if (t == 0)
				return 1;
			else if (t > 0) {
				pos.add(t);
				for (int q : neg) {
					int pab = gcd(t, -q);
					kmax = Math.min(kmax, t / pab - q / pab);
				}
			} else {
				neg.add(t);
				for (int p : pos) {
					int pab = gcd(p, -t);
					kmax = Math.min(kmax, p / pab - t / pab);
				}
				if (kmax == 1)
					return 1;
			}
		}
		if (pos.isEmpty() || neg.isEmpty())
			return -1;

		HashSet<Integer> allSums = new HashSet<>();
		allSums.addAll(pos);
		allSums.addAll(neg);
		for (int k = 1; k < kmax; k++) {
			HashSet<Integer> newAllSums = new HashSet<>();
			for (int sum : allSums) {
				for (int p : pos) {
					if (Math.abs(sum + p) < 1000)
						newAllSums.add(sum + p);
					if (sum == 0)
						return k;
					if ((k + 1) * p < 1000)
						newAllSums.add((k + 1) * p);
				}
				for (int q : neg) {
					if (Math.abs(sum + q) < 1000)
						newAllSums.add(sum + q);
					if (sum == 0)
						return k + 1;
					if (-(k + 1) * q < 1000)
						newAllSums.add((k + 1) * q);
				}
			}
			allSums = newAllSums;
		}
		return kmax;
	}

}