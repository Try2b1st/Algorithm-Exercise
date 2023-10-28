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
        System.out.println();
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
                if(result < dp[i][j]){
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

    }
}
