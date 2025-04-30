package com.mr.interview.problems;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
	// implement function that returns the nth value in a fibonacci series
	
	int mNumber = 0;
	
	public Fibonacci(int number) {
		mNumber = number; 
	}

	public void print() {
		long ts  = System.currentTimeMillis();
		int n = recursiveDuplicateTrees(mNumber);
		long te = System.currentTimeMillis();
		System.out.println("Fibonacci(" + mNumber + ") Recursive with Duplicate Trees = " + n + ", time taken: " + (te-ts));

		ts  = System.currentTimeMillis();
		n = orderNEfficient(mNumber);
		te = System.currentTimeMillis();
		System.out.println("Fibonacci(" + mNumber + ") Iterative O(n) efficient = " + n + ", time taken: " + (te-ts));

		ts  = System.currentTimeMillis();
		n = dp_recursive(mNumber);
		te = System.currentTimeMillis();
		System.out.println("Fibonacci(" + mNumber + ") dynamic programming with recursion = " + n + ", time taken: " + (te-ts));

		ts  = System.currentTimeMillis();
		n = dp_tabular(mNumber);
		te = System.currentTimeMillis();
		System.out.println("Fibonacci(" + mNumber + ") dynamic programming with memoization = " + n + ", time taken: " + (te-ts));
	}

	/**
	 * Dynamic programming with recursive calls.
	 * @param n the number
	 * @return the nth value in a fibonacci series.
	 */
	private int dp_recursive(int n) {
		Map<Integer, Integer> fibMap = new HashMap<>();
		// Save fib(0), fib(1)
		fibMap.put(0, 0);
		fibMap.put(1, 1);

		if (!fibMap.containsKey(n)) {
			fibMap.put(n, dp_recursive(n-1) + dp_recursive(n-2));
		}
		return fibMap.get(n);
	}

	private int dp_tabular(int n) {
		if (n <= 1) {
			return n;
		}
		int[] memo = new int[n+1];
		memo[0] = 0;
		memo[1] = 1;

		// Reuses already calculated fibonacci values from memoized table.
		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}
		return memo[n];
	}

	private int recursiveDuplicateTrees(int n) {
		if (n <= 0) {
			//System.out.println(0 + ", ");
			return 0;
		} else if (n == 1) {
			//System.out.println(1 + ", ");
			return 1;
		} else {
			return recursiveDuplicateTrees(n-1) + recursiveDuplicateTrees(n-2);
		}	
	}
	
	private int orderNEfficient(int n) {
		int[] result = {0, 1};
		if (n < 2) {
			return result[n];
		}

		int fbMinus1 = 1;
		int fbMinus2 = 0;
		int fb = 0;

		// O(n) but still not efficient as it does not reuse already calculated fibonacci values.
		for(int i = 2; i <=n; i++) {
			fb = fbMinus1 + fbMinus2;

			fbMinus2 = fbMinus1;
			fbMinus1 = fb;
		}

		return fb;
	}

	public static void main(String... args) {
		Fibonacci fb = new Fibonacci(40);
		fb.print();
	}

}


