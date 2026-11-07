/*
 * Problem: 2012. Process Tasks Using Servers
 * Link: https://leetcode.com/problems/process-tasks-using-servers/
 * Language: java
 * Status: Accepted
 * Runtime: 258 ms (beats 26.05%)
 * Memory: 169.1 MB (beats 76.21%)
 * Date: 2026-11-07 20:42:16
 */

import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // freeServers: [weight, index]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // busyServers: [availableTime, weight, index]
        // Use long for availableTime to prevent integer overflow
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        // Initially, all servers are free
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long curTime = 0;

        for (int i = 0; i < m; i++) {
            // curTime can never be behind the task arrival index i
            curTime = Math.max(curTime, (long) i);

            // Free up servers whose tasks have finished by curTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= curTime) {
                long[] done = busyServers.poll();
                freeServers.offer(new int[]{(int) done[1], (int) done[2]});
            }

            // If no servers are available, jump time to the earliest completion
            if (freeServers.isEmpty()) {
                curTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= curTime) {
                    long[] done = busyServers.poll();
                    freeServers.offer(new int[]{(int) done[1], (int) done[2]});
                }
            }

            // Pick the best available server
            int[] best = freeServers.poll();
            ans[i] = best[1];

            // Schedule the server in busyServers
            busyServers.offer(new long[]{curTime + tasks[i], best[0], best[1]});
        }

        return ans;
    }
}