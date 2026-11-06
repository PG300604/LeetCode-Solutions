/*
 * Problem: 2833. Count Zero Request Servers
 * Link: https://leetcode.com/problems/count-zero-request-servers/
 * Language: java
 * Status: Accepted
 * Runtime: 30 ms (beats 81.73%)
 * Memory: 131.5 MB (beats 99.04%)
 * Date: 2026-11-06 18:43:55
 */

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        // 1. Sort logs by timestamp ascending
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 2. Pair queries with their original index and sort them by time
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i]; // Query time
            sortedQueries[i][1] = i;          // Original index
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Frequency array to keep track of request counts for each server in the current window
        int[] serverCounts = new int[n + 1];
        int activeServers = 0;
        
        int left = 0;  // Removes logs that fall out of the window [q - x, q]
        int right = 0; // Adds logs that enter the window
        
        // 3. Process sorted queries using two pointers
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            // Define the window limits
            int windowStart = queryTime - x;
            int windowEnd = queryTime;
            
            // Expand the window: Add logs that occur up to windowEnd
            while (right < logs.length && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCounts[serverId] == 0) {
                    activeServers++;
                }
                serverCounts[serverId]++;
                right++;
            }
            
            // Shrink the window: Remove logs that occur strictly before windowStart
            while (left < logs.length && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCounts[serverId]--;
                if (serverCounts[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // 4. Record the result for the original query location
            ans[originalIndex] = n - activeServers;
        }
        
        return ans;
    }
}
