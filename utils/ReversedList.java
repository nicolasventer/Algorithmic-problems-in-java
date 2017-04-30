package utils;

import java.util.ArrayList;

public class ReversedList {

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
