/*
 * Problem: 1606. Find Servers That Handled Most Number of Requests
 * Link: https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
 * Language: java
 * Status: Accepted
 * Runtime: 114 ms (beats 91.88%)
 * Memory: 98.9 MB (beats 93.13%)
 * Date: 2026-11-09 19:53:59
 */

import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];

        // Min-heap tracking busy servers: [freeTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Two min-heaps for available servers based on their relation to (i % k)
        // availableRight: servers >= (i % k)
        // availableLeft: servers < (i % k) (used for wrap-around)
        PriorityQueue<Integer> availableRight = new PriorityQueue<>();
        PriorityQueue<Integer> availableLeft = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            availableRight.offer(i);
        }

        int n = arrival.length;
        for (int i = 0; i < n; i++) {
            int time = arrival[i];
            int duration = load[i];
            int target = i % k;

            // When target wraps around to 0, all servers in availableLeft
            // are now >= target, so swap/merge them into availableRight.
            if (target == 0) {
                while (!availableLeft.isEmpty()) {
                    availableRight.offer(availableLeft.poll());
                }
            } else {
                // Move servers from availableRight that are now behind target into availableLeft
                while (!availableRight.isEmpty() && availableRight.peek() < target) {
                    availableLeft.offer(availableRight.poll());
                }
            }

            // Release all servers that finished before or at current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int serverId = busy.poll()[1];
                if (serverId >= target) {
                    availableRight.offer(serverId);
                } else {
                    availableLeft.offer(serverId);
                }
            }

            // Select the best available server
            int chosenServer = -1;
            if (!availableRight.isEmpty()) {
                chosenServer = availableRight.poll();
            } else if (!availableLeft.isEmpty()) {
                chosenServer = availableLeft.poll();
            } else {
                // All servers are busy; drop request
                continue;
            }

            // Assign request to chosen server
            busy.offer(new int[]{time + duration, chosenServer});
            count[chosenServer]++;
        }

        // Find max requests handled
        int maxRequests = 0;
        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        // Collect all servers that handled maxRequests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}