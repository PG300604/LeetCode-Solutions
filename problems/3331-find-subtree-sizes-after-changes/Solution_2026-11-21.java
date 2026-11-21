/*
 * Problem: 3331. Find Subtree Sizes After Changes
 * Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/
 * Language: java
 * Status: Accepted
 * Runtime: 171 ms (beats 60.46%)
 * Memory: 291.9 MB (beats 48.84%)
 * Date: 2026-11-21 07:30:52
 */

import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // Build initial tree (node 0 is the root)
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }
        
        // Track the deepest ancestor for each character 'a'-'z'
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int[] newParent = Arrays.copyOf(parent, n);
        
        // 1. Update parents based on closest matching character ancestor
        reparentDFS(0, adj, s, lastSeen, newParent);
        
        // 2. Rebuild tree using the updated parent pointers
        List<Integer>[] newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }
        
        // 3. Calculate subtree sizes on the modified tree
        int[] ans = new int[n];
        countSubtreeDFS(0, newAdj, ans);
        
        return ans;
    }
    
    private void reparentDFS(int u, List<Integer>[] adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];
        
        // If an ancestor has the same character, update parent to the closest one
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }
        
        // Set current node as the closest ancestor for its character before exploring children
        lastSeen[charIdx] = u;
        
        for (int v : adj[u]) {
            reparentDFS(v, adj, s, lastSeen, newParent);
        }
        
        // Backtrack: restore previous ancestor state for sibling branches
        lastSeen[charIdx] = prevAncestor;
    }
    
    private int countSubtreeDFS(int u, List<Integer>[] newAdj, int[] ans) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += countSubtreeDFS(v, newAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}