import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class StrongPinChecker {
    public static int strongPinChanges(String pinCode) {
        int n = pinCode.length();

        // Step 1: Check which character types are missing
        boolean hasLower = false, hasUpper = false, hasDigit = false;

        for (char c : pinCode.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Step 2: Check for three consecutive repeating characters
        int replace = 0;
        int i = 2;
        List<Integer> repeats = new ArrayList<>();

        while (i < n) {
            if (pinCode.charAt(i) == pinCode.charAt(i - 1) &&
                pinCode.charAt(i - 1) == pinCode.charAt(i - 2)) {
                int len = 3;
                while (i + 1 < n && pinCode.charAt(i + 1) == pinCode.charAt(i)) {
                    len++;
                    i++;
                }
                repeats.add(len);
                i++;
            } else {
                i++;
            }
        }

        for (int len : repeats) {
            replace += len / 3;
        }

        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        } else if (n <= 20) {
            return Math.max(missingTypes, replace);
        } else {
            int deletions = n - 20;
            int overLen = deletions;

            // Try to reduce replacements using deletions
            for (int j = 0; j < repeats.size() && overLen > 0; j++) {
                int len = repeats.get(j);
                int need = len % 3 == 0 ? 1 : (len % 3 == 1 ? 2 : 3);
                if (overLen >= need) {
                    repeats.set(j, len - need);
                    overLen -= need;
                } else {
                    repeats.set(j, len - overLen);
                    overLen = 0;
                }
            }

            // Recalculate replacements after deletions
            replace = 0;
            for (int len : repeats) {
                replace += len / 3;
            }

            return deletions + Math.max(missingTypes, replace);
        }
    }

    public static void main(String[] args) {
        System.out.println(strongPinChanges("X1!"));       // Output: 3
        System.out.println(strongPinChanges("123456"));    // Output: 2
        System.out.println(strongPinChanges("Aa1234!"));   // Output: 0
        System.out.println(strongPinChanges("aaaaaa"));    // Output: 2
        System.out.println(strongPinChanges("aA1aaaaaaaAAAAAAA123")); // Output: 3
    }
}

//To solve this problem, we need to determine the minimum number of changes required to transform a given PIN into a strong PIN according to the
// specified security criteria. The criteria include length constraints, character type requirements, and the absence of three consecutive repeating 
//characters. The solution involves analyzing the PIN for these criteria and calculating the minimal operations (insert, delete, or replace) needed to
// meet all requirements.