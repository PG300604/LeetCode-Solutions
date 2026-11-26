/*
 * Problem: 3360. Stone Removal Game
 * Link: https://leetcode.com/problems/stone-removal-game/
 * Language: java
 * Status: Accepted
 * Runtime: 0 ms (beats 100.00%)
 * Memory: 41.7 MB (beats 60.38%)
 * Date: 2026-11-26 18:11:25
 */

class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;
        
        while (n >= stonesToRemove && stonesToRemove > 0) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }
        
        // If it's Bob's turn when the loop ends, Bob couldn't make a move, so Alice wins
        return !isAliceTurn;
    }
}