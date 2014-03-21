package com.mr.interview.amazon;
 
public class Fibonacci {
	// implement function that returns the nth value in a fibonacci series
	
	int mNumber = 0;
	
	public Fibonacci(int number) {
		mNumber = number; 
	}

	public void print() {
		int n = recursiveDuplicateTrees(mNumber);
		System.out.println("Fibonacci(" + mNumber + ") Recursive with Duplicate Trees = " + n);

		n = orderNEfficient(mNumber);
		System.out.println("Fibonacci(" + mNumber + ") O(n) efficient = " + n);
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
		
		for(int i = 2; i < n; i++) {
			fb = fbMinus1 + fbMinus2;

			fbMinus2 = fbMinus1;
			fbMinus1 = fb;
		}

		return fb;
	}

	public static void main(String... args) {
		Fibonacci fb = new Fibonacci(6);
		fb.print();		
	}

}


