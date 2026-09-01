class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            // Carry contains common set bits of a and b
            int carry = a & b;

            // Sum of bits where at least one is not set
            a = a ^ b;

            // Carry is shifted by one so that adding it gives the required sum
            b = carry << 1;
        }
        return a;
    }
}
