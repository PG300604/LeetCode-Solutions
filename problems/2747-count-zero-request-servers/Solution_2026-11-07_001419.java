/*
 * Problem: 2747. Count Zero Request Servers
 * Link: https://leetcode.com/problems/count-zero-request-servers/
 * Language: java
 * Status: Accepted
 * Runtime: 26 ms (beats 100.00%)
 * Memory: 135.5 MB (beats 76.92%)
 * Date: 2026-11-06 18:44:19
 */

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int q = queries.length;
        Integer[] idx = new Integer[q];
        for (int i = 0; i < q; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> queries[a] - queries[b]);
        int[] cnt = new int[n + 1];
        int[] res = new int[q];
        int l = 0, r = 0, active = 0;
        for (int i : idx) {
            int t = queries[i];
            while (r < logs.length && logs[r][1] <= t) {
                if (cnt[logs[r][0]]++ == 0) active++;
                r++;
            }
            while (l < r && logs[l][1] < t - x) {
                if (--cnt[logs[l][0]] == 0) active--;
                l++;
            }
            res[i] = n - active;
        }
        return res;
    }
}