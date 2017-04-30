package utils;

import java.util.ArrayList;

public class SortedList {

	public static <T extends Comparable<T>> void addInSortedList(ArrayList<T> sortedL, T e) {
		int i = 0;
		while (i < sortedL.size() && sortedL.get(i).compareTo(e) < 0)
			i++;
		sortedL.add(i, e);
	}

}
