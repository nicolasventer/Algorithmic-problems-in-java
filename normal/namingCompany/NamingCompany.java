package normal.namingCompany;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NamingCompany {

	// variables

	public static char[] oleg, igor, company;
	public static int olegStart, olegEnd, igorStart, igorEnd, companyStart, companyEnd;
	public static boolean olegToPlay = true;

	// functions

	public static void setNameCompany() {
		for (int i = 0; i < oleg.length; i++)
			if (i % 2 == 0)
				if (oleg[olegStart] < igor[igorStart])
					company[companyStart++] = oleg[olegStart++];
				else
					company[companyEnd--] = oleg[olegEnd--];
			else if (igor[igorStart] > oleg[olegStart])
				company[companyStart++] = igor[igorStart--];
			else
				company[companyEnd--] = igor[igorEnd++];
	}

	public static void main(String[] args) {
		try {
			// readValues
			oleg = in.next().toCharArray();
			igor = in.next().toCharArray();
			company = new char[oleg.length];
			int mid = (oleg.length + 1) / 2;
			olegStart = 0;
			olegEnd = mid - 1;
			igorStart = igor.length - 1;
			igorEnd = mid;
			companyStart = olegStart;
			companyEnd = igorStart;
			// useFunctions
			Arrays.sort(oleg);
			Arrays.sort(igor);
			setNameCompany();
			// printResult
			System.out.println(company);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// utils

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