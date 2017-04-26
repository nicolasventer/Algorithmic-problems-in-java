package normal.igorToWork;

import java.io.*;
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

	public static void charNPvalues() throws IOException {
		charNP = new char[n][];
		for (int i = 0; i < n; i++)
			charNP[i] = in.next().toCharArray();
	}
	
	public static int n, m;
	public static char[][] charNP;
	
	public static int xS, yS, xT, yT;
	
	public static final int[] dirX = { -1, 0, 1, 0 };
	public static final int[] dirY = { 0, -1, 0, 1 };
	
	public static boolean[][][][] visitedDir;
	
	public static void main(String[] args) {
		try {
			n = in.nextInt();
			m = in.nextInt();
			charNPvalues();
			
			// find start and end positions
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++) {
					if (charNP[i][j] == 'S') {
						xS = i;
						yS = j;
						charNP[i][j] = '.';
					}
					if (charNP[i][j] == 'T') {
						xT = i;
						yT = j;
						charNP[i][j] = '.';
					}
				}
			
			visitedDir = new boolean[4][3][n][m];
			
			for (int d=0; d<4; d++) 
				if (dfs(xS, yS, 0, d)) {
					System.out.println("YES");
					return;
				}
			System.out.println("NO");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean dfs(int x, int y, int turns, int dir) {
		if (x < 0 || x >= n || y<0 || y>=m || charNP[x][y] == '*' || turns > 2)
			return false;
		
		if (x == xT && y == yT)
			return true;
	
		if (visitedDir[dir][turns][x][y])
			return false;
		
		for (int d=0; d<4; d++)
			if ( dfs(x + dirX[d], y + dirY[d], turns + (dir == d ? 0 : 1), d ))
				return true;
		
		visitedDir[dir][turns][x][y] = true;
		
		return false;
	}

}