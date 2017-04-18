package hard.newYearAndFireworks;

import java.io.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.StringTokenizer;

public class NewYearAndFireworks {

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

	public static class Point {
		int x;
		int y;

		int dirX = 0;
		int dirY = 0;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Point) {
				Point p = (Point) obj;
				return x == p.x && y == p.y && dirX == p.dirX && dirY == p.dirY;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return new SimpleEntry<Integer, Integer>(new SimpleEntry<Integer, Integer>(x, y).hashCode(),
					new SimpleEntry<Integer, Integer>(dirX, dirY).hashCode()).hashCode();
		}

	}

	public static void main(String[] args) {
		try {
			int n = in.nextInt();
			HashSet<Point> extremity = new HashSet<>();
			int[] t = new int[n];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				t[i] = in.nextInt();
				sum += t[i];
			}
			int size = 2 * sum - 1;
			int[][] tab = new int[size][size];
			int result = 0;
			for (int i = 0; i < t[0]; i++) {
				result += 1 - tab[0 + size / 2][i + size / 2];
				tab[0 + size / 2][i + size / 2] = 1;
			}
			Point p0 = new Point(size / 2, t[0] - 1 + size / 2);
			p0.dirX = 0;
			p0.dirY = 1;
			extremity.add(p0);
			for (int i = 1; i < n; i++) {
				HashSet<Point> newExtremity = new HashSet<>();
				for (Point p : extremity) {
					int newDirX0;
					int newDirX1;
					int newDirY0;
					int newDirY1;
					if (p.dirX == 0) {
						newDirX0 = 1;
						newDirX1 = -1;
						newDirY0 = p.dirY;
						newDirY1 = p.dirY;
					} else if (p.dirY == 0) {
						newDirX0 = p.dirX;
						newDirX1 = p.dirX;
						newDirY0 = 1;
						newDirY1 = -1;
					} else {
						newDirX0 = p.dirX;
						newDirX1 = 0;
						newDirY0 = 0;
						newDirY1 = p.dirY;
					}
					for (int j = 1; j <= t[i]; j++) {
						result += 1 - tab[p.x + newDirX0 * j][p.y + newDirY0 * j];
						tab[p.x + newDirX0 * j][p.y + newDirY0 * j] = 1;
						result += 1 - tab[p.x + newDirX1 * j][p.y + newDirY1 * j];
						tab[p.x + newDirX1 * j][p.y + newDirY1 * j] = 1;
					}
					Point toAdd0 = new Point(p.x + newDirX0 * t[i], p.y + newDirY0 * t[i]);
					toAdd0.dirX = newDirX0;
					toAdd0.dirY = newDirY0;
					newExtremity.add(toAdd0);
					Point toAdd1 = new Point(p.x + newDirX1 * t[i], p.y + newDirY1 * t[i]);
					toAdd1.dirX = newDirX1;
					toAdd1.dirY = newDirY1;
					newExtremity.add(toAdd1);
				}
				extremity = newExtremity;
			}
			System.out.println(result);
			in.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}