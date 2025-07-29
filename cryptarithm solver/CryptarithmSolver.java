import java.util.*;

public class CryptarithmSolver {

    public static void main(String[] args) {
        solveCryptarithm("CODE", "BUG", "DEBUG");
    }

    static void solveCryptarithm(String word1, String word2, String result) {
        String allWords = word1 + word2 + result;
        Set<Character> uniqueLetters = new HashSet<>();
        for (char c : allWords.toCharArray()) {
            uniqueLetters.add(c);
        }

        if (uniqueLetters.size() > 10) {
            System.out.println("Too many unique letters. Cannot assign unique digits.");
            return;
        }

        List<Character> letters = new ArrayList<>(uniqueLetters);
        int[] digits = new int[10];
        boolean[] used = new boolean[10];
        Arrays.fill(digits, -1);

        permuteAndCheck(letters, new int[letters.size()], 0, used, word1, word2, result);
    }

    static void permuteAndCheck(List<Character> letters, int[] currentMapping, int index,
                                boolean[] used, String word1, String word2, String result) {
        if (index == letters.size()) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < letters.size(); i++) {
                map.put(letters.get(i), currentMapping[i]);
            }

            // Prevent leading zeros
            if (map.get(word1.charAt(0)) == 0 || map.get(word2.charAt(0)) == 0 || map.get(result.charAt(0)) == 0) {
                return;
            }

            long num1 = wordToNumber(word1, map);
            long num2 = wordToNumber(word2, map);
            long res = wordToNumber(result, map);

            if (num1 + num2 == res) {
                System.out.println("✅ Solution Found!");
                System.out.println(word1 + " → " + num1);
                System.out.println(word2 + " → " + num2);
                System.out.println(result + " → " + res);
                System.out.println("Letter Mapping: " + map);
                System.exit(0);
            }
            return;
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (!used[digit]) {
                used[digit] = true;
                currentMapping[index] = digit;
                permuteAndCheck(letters, currentMapping, index + 1, used, word1, word2, result);
                used[digit] = false;
            }
        }
    }

    static long wordToNumber(String word, Map<Character, Integer> map) {
        long num = 0;
        for (char c : word.toCharArray()) {
            num = num * 10 + map.get(c);
        }
        return num;
    }
}

//Cryptarithms are puzzles where words represent numbers, and each letter is a unique digit from 0–9. The goal is to assign digits to letters such that
//the arithmetic operation (like addition) is correct. No two letters can have the same digit, and words must not start with 0.