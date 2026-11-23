/*
 * Problem: 1352. Product of the Last K Numbers
 * Link: https://leetcode.com/problems/product-of-the-last-k-numbers/
 * Language: java
 * Status: Accepted
 * Runtime: 18 ms (beats 70.76%)
 * Memory: 83.6 MB (beats 85.29%)
 * Date: 2026-11-23 16:59:50
 */

import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        // Initialize with 1 as the multiplicative identity
        prefixProducts.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            // Reset the prefix list since any product including this 0 will be 0
            prefixProducts = new ArrayList<>();
            prefixProducts.add(1);
        } else {
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }
    
    public int getProduct(int k) {
        int n = prefixProducts.size();
        // If k reaches or exceeds the elements added since the last 0, the product is 0
        if (k >= n) {
            return 0;
        }
        return prefixProducts.get(n - 1) / prefixProducts.get(n - 1 - k);
    }
}