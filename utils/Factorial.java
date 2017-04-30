package utils;

import java.util.ArrayList;

public class Factorial {

	private static ArrayList<Long> initFactList() {
		ArrayList<Long> result = new ArrayList<Long>();
		result.add(1l);
		return result;
	}

	public static ArrayList<Long> factList = initFactList();

	public static long fact(int i) {
		if (i >= factList.size())
			factList.add(1l * i * fact(i - 1));
		return factList.get(i);
	}

	public static long binomialCoefficient(int n, int p) {
		return n < p ? 0 : fact(n) / (fact(p) * fact(n - p));
	}

}
