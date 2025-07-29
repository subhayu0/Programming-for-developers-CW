package Magic;

import java.util.*;

public class MagicalWordPower {
    static class Palindrome {
        int start, end, length;
        Palindrome(int s, int e) {
            start = s;
            end = e;
            length = e - s + 1;
        }
    }

    public static int maxMagicalPower(String M) {
        int n = M.length();
        List<Palindrome> palindromes = new ArrayList<>();

        // Step 1: Find all odd-length palindromes
        for (int center = 0; center < n; center++) {
            int left = center, right = center;
            while (left >= 0 && right < n && M.charAt(left) == M.charAt(right)) {
                int length = right - left + 1;
                if (length % 2 == 1) {
                    palindromes.add(new Palindrome(left, right));
                }
                left--;
                right++;
            }
        }

        int maxProduct = 0;
        int size = palindromes.size();

        // Step 2: Compare all pairs
        for (int i = 0; i < size; i++) {
            Palindrome p1 = palindromes.get(i);
            for (int j = i + 1; j < size; j++) {
                Palindrome p2 = palindromes.get(j);
                if (p1.end < p2.start || p2.end < p1.start) {
                    int product = p1.length * p2.length;
                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        String M1 = "xyzyxabc";
        System.out.println("Output: " + maxMagicalPower(M1)); // 5

        String M2 = "levelwowracecar";
        System.out.println("Output: " + maxMagicalPower(M2)); // 35
    }
}
//The program finds all odd-length palindromes in a given string and calculates the maximum product of lengths of two non-overlapping palindromes.
//It uses a center-expansion technique to identify palindromes and then compares pairs of palindromes to ensure they do not overlap.
//The time complexity is O(n^2) in the worst case, where n is the length of the string, due to the nested loops for comparing pairs of palindromes.
//This approach efficiently handles the problem without generating all possible substrings, focusing only on palind substrings.
//The program is designed to handle strings of moderate length efficiently, making it suitable for practical applications involving palindrome detection and analysis.