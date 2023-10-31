package org.dp;

import java.util.Arrays;

public class Palindromic {

    /**
     * 647. 回文子串
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];

        int result = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j || j - i == 1) {
                        result++;
                        dp[i][j] = true;
                    } else {
                        if (dp[i + 1][j - 1]) {
                            result++;
                            dp[i][j] = true;
                        }
                    }
                } else {
                    dp[i][j] = false;
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return result;
    }
}
