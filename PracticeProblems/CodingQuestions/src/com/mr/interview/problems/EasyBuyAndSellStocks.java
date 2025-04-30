package com.mr.interview.problems;

public class EasyBuyAndSellStocks {

    /**
     * 121. Best Time to Buy and Sell Stock
     *      Easy
     *      You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *      You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     *      Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     *      Example 1:
     *      Input: prices = [7,1,5,3,6,4]
     *      Output: 5
     *      Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *      Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     *
     *      Example 2:
     *      Input: prices = [7,6,4,3,1]
     *      Output: 0
     *      Explanation: In this case, no transactions are done and the max profit = 0.
     *      Constraints:
     *      1 <= prices.length <= 105
     *      0 <= prices[i] <= 104
     */

    public int maxProfitOnePass(int[] prices) {

        // Keep track of the best buy day so far.
        int minSoFar = Integer.MAX_VALUE;
        int largestDifference = 0;

        for (int day = 0; day < prices.length; day++) {
            if (prices[day] < minSoFar) {
                // Update minSoFar
                minSoFar = prices[day];
            } else {
                largestDifference = Math.max(largestDifference, prices[day] - minSoFar);
            }
        }

        return largestDifference;

        // Time complexity = O(n) ( 1 loop iterating through the length of the array n)
        // Space complexity = constatnt = O(1) - only largestDifference tracking, nd minSoFar.
    }


    public int maxProfitBruteForce(int[] prices) {
        int largestDifference = 0;
        final int n = prices.length;
        for (int buyday = 0; buyday < n; buyday++) {
            for (int sellday = buyday+1; sellday < n; sellday++) {
                int currentDifference = prices[sellday] - prices[buyday];

                if (currentDifference > largestDifference) {
                    largestDifference = currentDifference;
                }
            }
        }

        return largestDifference;

        // Time complexity = O(n^2) ( 2 loops and iterating through the length of the array n)
        // Space comlexity = constatnt = O(1) - only largestDifference tracking.
    }



}
