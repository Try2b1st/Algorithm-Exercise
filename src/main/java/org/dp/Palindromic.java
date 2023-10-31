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

    /**
     * 516. 最长回文子序列
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][s.length() - 1];
    }
}
