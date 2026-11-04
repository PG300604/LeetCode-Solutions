/**
 * Problem: Best Time to Buy and Sell Stock II
 * Problem Number: 122
 * Date: 2026-08-22
 */
class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;

        // Start from index 1 and compare each day to the previous day
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += (prices[i] - prices[i - 1]);
            }
        }

        return totalProfit;
    }
}
