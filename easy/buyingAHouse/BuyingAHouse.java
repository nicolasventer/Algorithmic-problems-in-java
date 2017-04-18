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

	public static void main(String[] args) {
		try {
			int nbHouses = in.nextInt();
			int numCrush = in.nextInt() - 1;
			int money = in.nextInt();
			int[] houses = new int[nbHouses];
			for (int i = 0; i < nbHouses; i++)
				houses[i] = in.nextInt();
			int k = 1;
			while (k < nbHouses && !(numCrush - k >= 0 && houses[numCrush - k] != 0 && houses[numCrush - k] <= money)
					&& !(numCrush + k < nbHouses && houses[numCrush + k] != 0 && houses[numCrush + k] <= money)) {
				k++;
			}
			System.out.print(k * 10);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}