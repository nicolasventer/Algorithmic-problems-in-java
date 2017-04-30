package hard.newTask;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.StringTokenizer;


// NOT ENOUGH FAST !!!
public class NewTask {

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

	public static void sortedIntNvalues(int n) throws IOException {
		sortedIntNvalues(n, doNothing);
	}

	public static void sortedIntNvalues(int n, OnRead r) throws IOException {
		sortedIntN = new int[n][4];
		boolean stopUse = false;
		for (int i = 0; i < n; i++) {
			sortedIntN[i][0] = in.nextInt();
			sortedIntN[i][1] = i;
			stopUse = stopUse || r.use(i);
		}
		Arrays.sort(sortedIntN, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		for (int i = 0; i < sortedIntN.length; i++)
			sortedIntN[sortedIntN[i][1]][2] = i;
		int value = sortedIntN[0][0];
		indexesNewValue.add(0);
		for (int i = 1; i < sortedIntN.length; i++) {
			if (value != sortedIntN[i][0]) {
				indexesNewValue.add(i);
				value = sortedIntN[i][0];
			}
			sortedIntN[i][3] = indexesNewValue.size() - 1;
		}
		indexesNewValue.add(sortedIntN.length);
	}

	public static void intNPvalues(int n, int p) throws IOException {
		intNPvalues(n, p, doNothing);
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

	// i,0 = value ||| i,1 = indexBeforeSort ||| i,2 = indexAfterSort
	// i,3 = numInterval
	// ==> sortedIntN[sortedInt[i][1]][2] == i == sortedIntN[sortedInt[i][2]][1]
	// ==> indexesNewValue.get(sortedInt[i][3]) <= i
	// ==> i <= indexesNewValue.get(sortedInt[i][3]+1)
	public static int[][] sortedIntN;
	public static ArrayList<Integer> indexesNewValue = new ArrayList<Integer>();

	public static int[][] intNP;

	// variables

	public static int n, m;

	public static int[] nbEnabledBefore;
	public static int[] nbEnabledAfter;

	public static ArrayList<ArrayList<Integer>> indexesGrp3 = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> reverseIndexesGrp3 = new ArrayList<ArrayList<Integer>>();

	// onReads

	public static OnRead printNbTeams = new OnRead() {
		@Override
		public boolean use(int i) {
			changeState(indexesGrp3, reverseIndexesGrp3, intNP[i][1] - 1, intNP[i][0] == 1);
			int nbTeams = 0;
			for (int j = 0; j < indexesGrp3.size(); j++) {
				ArrayList<Integer> grp3 = indexesGrp3.get(j);
				if (grp3.size() == 3)
					nbTeams += nbEnabledBefore[grp3.get(0)] * nbEnabledAfter[grp3.get(grp3.size() - 1)];
			}
			System.out.println(nbTeams);
			return false;
		}
	};

	public static void main(String[] args) {
		try {
			// readValues
			n = in.nextInt();
			sortedIntNvalues(n);
			// useFunctions
			nbEnabledBefore = new int[n];
			nbEnabledAfter = new int[n];
			setNbEnabled();
			setGrp3();
			reverseIndexesGrp3 = getReversedList(indexesGrp3, n);
			// printResult
			m = in.nextInt();
			intNPvalues(m, 2, printNbTeams);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// functions

	public static void setNbEnabled() {
		for (int i = 0; i < nbEnabledBefore.length; i++)
			for (int j = 0; j < nbEnabledBefore.length; j++)
				if (sortedIntN[j][0] <= sortedIntN[i][0])
					if (sortedIntN[j][1] < sortedIntN[i][1])
						nbEnabledBefore[sortedIntN[i][1]]++;
					else if (i != j)
						nbEnabledAfter[sortedIntN[i][1]]++;
	}

	public static void setGrp3() {
		for (int i = 1; i < sortedIntN.length - 2; i++)
			for (int j = i + 1; j < sortedIntN.length - 1 && sortedIntN[j][0] == sortedIntN[i][0]; j++)
				for (int k = j + 1; k < sortedIntN.length && sortedIntN[k][0] == sortedIntN[j][0]; k++)
					if (nbEnabledBefore[sortedIntN[i][1]] * nbEnabledAfter[sortedIntN[k][1]] != 0) {
						ArrayList<Integer> grp3 = new ArrayList<Integer>();
						grp3.add(sortedIntN[i][1]);
						grp3.add(sortedIntN[j][1]);
						grp3.add(sortedIntN[k][1]);
						indexesGrp3.add(grp3);
					}
	}

	// true for add index, false to remove it
	public static void changeState(ArrayList<ArrayList<Integer>> l, ArrayList<ArrayList<Integer>> reverseL, int index,
			boolean add) {
		if (add)
			for (int indexGrp3 : reverseL.get(index))
				addInSortedList(l.get(indexGrp3), index);
		else
			for (int indexGrp3 : reverseL.get(index))
				l.get(indexGrp3).remove((Object) index);
	}

	public static <T extends Comparable<T>> void addInSortedList(ArrayList<T> sortedL, T e) {
		int i = 0;
		while (i < sortedL.size() && sortedL.get(i).compareTo(e) < 0)
			i++;
		sortedL.add(i, e);
	}

	public static ArrayList<ArrayList<Integer>> getReversedList(ArrayList<ArrayList<Integer>> l, int n) {
		ArrayList<ArrayList<Integer>> reverseL = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++)
			reverseL.add(new ArrayList<Integer>());
		for (int i = 0; i < l.size(); i++)
			for (int j = 0; j < l.get(i).size(); j++)
				reverseL.get(l.get(i).get(j)).add(i);
		return reverseL;
	}

}