/*
 * Problem: 3946. Maximum Number of Items From Sale I
 * Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/
 * Language: java
 * Status: Accepted
 * Runtime: 62 ms (beats 62.50%)
 * Memory: 46.2 MB (beats 93.75%)
 * Date: 2026-11-27 17:49:30
 */

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];

        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt++;
                }
            }
            freeCount[i] = cnt;
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int bonus = freeCount[i];
            if (price > budget) {
                continue;
            }

            // 1. First purchase of item i: +1 item and +bonus free items (0/1 transition)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1 + bonus);
            }

            // 2. Additional purchases of item i: +1 item per copy (unbounded transition)
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        return dp[budget];
    }
}