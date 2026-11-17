/*
 * Problem: 2911. Minimum Changes to Make K Semi-palindromes
 * Link: https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
 * Language: java
 * Status: Accepted
 * Runtime: 98 ms (beats 93.33%)
 * Memory: 46.6 MB (beats 43.33%)
 * Date: 2026-11-17 17:55:41
 */

import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // 1. Precompute proper divisors for every length from 2 to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d * d <= len; d++) {
                if (len % d == 0) {
                    if (d < len) divisors[len].add(d);
                    int other = len / d;
                    if (other != d && other < len) divisors[len].add(other);
                }
            }
        }

        // 2. Precompute minimum changes for every substring s[i..j]
        int INF = 1_000_000;
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = INF;
                for (int d : divisors[len]) {
                    minChanges = Math.min(minChanges, getChanges(chars, i, j, d));
                }
                cost[i][j] = minChanges;
            }
        }

        // 3. DP to partition s[0..n-1] into k semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome has length at least 2, so i >= 2 * p
            for (int i = 2 * p; i <= n; i++) {
                // Previous prefix must fit p - 1 semi-palindromes, so m >= 2 * (p - 1)
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[m][p - 1] != INF && cost[m][i - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[m][p - 1] + cost[m][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    // Helper to calculate changes needed for substring chars[start..end] with step d
    private int getChanges(char[] chars, int start, int end, int d) {
        int changes = 0;
        for (int r = 0; r < d; r++) {
            int left = start + r;
            int right = end - (d - 1 - r);
            while (left < right) {
                if (chars[left] != chars[right]) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}