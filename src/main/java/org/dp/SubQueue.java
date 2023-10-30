package org.dp;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class SubQueue {

    /**
     * 300. 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //初始化
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        int result = 0;
        for (int j : dp) {
            if (result < j) {
                result = j;
            }
        }
        return result;
    }

    /**
     * 674. 最长连续递增序列
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        //初始化
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = Math.max(dp[i], 1 + dp[i - 1]);
            }
        }
        System.out.println(Arrays.toString(dp));
        int result = 0;
        for (int j : dp) {
            if (result < j) {
                result = j;
            }
        }
        return result;
    }


    /**
     * 718. 最长重复子数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        //确定dp和初始化
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            if (nums1[0] == nums2[i]) {
                dp[0][i] = 1;
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (result < dp[i][j]) {
                    result = dp[i][j];
                }
            }
        }
        return result;
    }


    /**
     * 1143. 最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        //确定dp和初始化
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[text1.length()][text2.length()];
    }


    /**
     * 1035. 不相交的线
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        int result = Integer.MIN_VALUE;
        for (int x : dp) {
            if (x > result) {
                result = x;
            }
        }
        return result;
    }


    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }

    /**
     * 115. 不同的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }


    /**
     * 583. 两个字符串的删除操作
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length() + 1; i++) dp[i][0] = i;
        for (int j = 1; j <= word2.length() + 1; j++) dp[0][j] = j;

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
