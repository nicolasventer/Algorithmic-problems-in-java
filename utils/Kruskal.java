package utils;

import java.util.ArrayList;

public class Kruskal {

	public static interface OnRead {
		public boolean use(int i);
	}

	public static OnRead doNothing = new OnRead() {
		@Override
		public boolean use(int i) {
			return false;
		}
	};
	
	public static int[][] graph;
	
	////
	
	public static OnRead atMerge = doNothing;

	public static int getSet(int i) {
		return set[i] = set[i] == i ? i : getSet(set[i]);
	}

	public static OnRead kruskal = new OnRead() {
		@Override
		public boolean use(int i) {
			int a = getSet(graph[i][0]);
			int b = getSet(graph[i][1]);
			if (a != b) {
				atMerge.use(i);
				set[a] = b;
			}
			return false;
		}
	};

	public static ArrayList<ArrayList<Integer>> getallSets() {
		ArrayList<Integer> allNumSet = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < set.length; i++) {
			int numSet = getSet(i);
			int j = 0;
			while (j < allNumSet.size() && numSet != allNumSet.get(j))
				j++;
			if (j == allNumSet.size()) {
				allNumSet.add(j);
				result.add(new ArrayList<Integer>());
			}
			result.get(j).add(i);
		}
		return result;
	}

	public static int[] set;
	
}
