package normal.igorToWork;
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class IgorToWork {

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

	public static class MyPoint {
		public int x;
		public int y;
		public int dirX;
		public int dirY;
		public int nbTurns = 0;

		public MyPoint(int x, int y, int dirX, int dirY) {
			this.x = x;
			this.y = y;
			this.dirX = dirX;
			this.dirY = dirY;
		}

	}

	public static void main(String[] args) {
		try {
			int n = in.nextInt();
			int m = in.nextInt();
			String[] tab = new String[n];
			int xS = 0, yS = 0;
			int xT = 0, yT = 0;
			for (int i = 0; i < n; i++) {
				tab[i] = in.next();
				if (tab[i].contains("S")) {
					xS = i;
					yS = tab[i].indexOf("S");
				}
				if (tab[i].contains("T")) {
					xT = i;
					yT = tab[i].indexOf("T");
				}
			}
			LinkedList<MyPoint> l = new LinkedList<MyPoint>();
			boolean[][] visited = new boolean[n][m];
			for (MyPoint p : getPushList(xS, yS, xT, yT))
				if (p.nbTurns <= 2 && p.x >= 0 && p.x < tab.length && p.y >= 0 && p.y < tab[0].length()
						&& !visited[p.x][p.y])
					l.push(p);
			if (browse(l, visited, tab, xT, yT))
				System.out.println("YES");
			else
				System.out.println("NO");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean browse(LinkedList<MyPoint> l, boolean[][] visited, String[] tab, int xT, int yT) {
		while (!l.isEmpty()) {
			MyPoint p0 = l.pop();
			visited[p0.x][p0.y] = true;
			switch (tab[p0.x].charAt(p0.y)) {
			case 'T':
				return true;
			case '*':
				return browse(l, visited, tab, xT, yT);
			default:
				MyPoint[] pl = getPushList(p0.x + p0.dirX, p0.y + p0.dirY, xT, yT);
				for (MyPoint p1 : pl) {
					p1.nbTurns = p0.nbTurns + (p0.dirX != p1.dirX || p0.dirY != p1.dirY ? 1 : 0);
					if (p1.nbTurns <= 2 && p1.x >= 0 && p1.x < tab.length && p1.y >= 0 && p1.y < tab[0].length()
							&& !visited[p1.x][p1.y])
						l.push(p1);
				}
				break;
			}
		}
		return false;
	}

	private static MyPoint[] getPushList(int xS, int yS, int xT, int yT) {
		if (Math.abs(xS - xT) > Math.abs(yS - yT))
			return new MyPoint[] { new MyPoint(xS, yS, xS > xT ? -1 : 1, 0), new MyPoint(xS, yS, 0, yS > yT ? -1 : 1),
					new MyPoint(xS, yS, 0, yS > yT ? 1 : -1), new MyPoint(xS, yS, xS > xT ? 1 : -1, 0) };
		else
			return new MyPoint[] { new MyPoint(xS, yS, 0, yS > yT ? -1 : 1), new MyPoint(xS, yS, xS > xT ? -1 : 1, 0),
					new MyPoint(xS, yS, xS > xT ? 1 : -1, 0), new MyPoint(xS, yS, 0, yS > yT ? 1 : -1) };
	}

}