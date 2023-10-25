package org.dp;

public class Rob {

    /**
     * 198. 打家劫舍
     *
     * @param nums
     * @return
     */
    public int robI(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        //初始化
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }
}
