package com.mr.interview.datastructures;

public class PrintPattern {
	/**
	 Print the Pattern 
	input N=4 
	output : 
	4444444 
	4333334 
	4322234 
	4321234 
	4322234 
	4333334 
	4444444 

	input n=3 
	output : 
	N=3 
	33333 
	32223 
	32123 
	32223 
	33333
	*/

	public void printPattern(int n) {

	    int max = 2*n - 1;
	    for(int i=0; i < max; i++) {
	        for (int j=0; j < max; j++) {
	            int distX = Math.abs(n - i - 1);
	            int distY = Math.abs(n - j - 1);
	            System.out.print(1 + Math.max(distX, distY));
	        }
	        System.out.print("\n");
	    }
	}

	public static final void main(String... args) {
		PrintPattern pp = new PrintPattern();
		pp.printPattern(4);
	}

}
