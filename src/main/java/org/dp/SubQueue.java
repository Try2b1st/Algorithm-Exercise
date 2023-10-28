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
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],1 + dp[j]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        int result= 0;
        for (int j : dp) {
            if (result < j) {
                result = j;
            }
        }
        return result;
    }
}
