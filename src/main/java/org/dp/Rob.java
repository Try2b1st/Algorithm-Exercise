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

    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    public int robII(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int left = robRange(nums, 0, nums.length - 2);
        int right = robRange(nums, 1, nums.length - 1);

        return Math.max(left, right);
    }

    public int robRange(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 337. 打家劫舍 III
     *
     * @param root
     * @return
     */
    public int robIII(TreeNode root) {
        int[] result = traversal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] traversal(TreeNode cur) {
        if (cur == null) {
            return new int[]{0, 0};
        }

        //0-不偷 1-偷
        int[] result = new int[2];

        int[] left = traversal(cur.left);
        int[] right = traversal(cur.right);

        //不偷当前节点
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        //偷当前节点
        result[1] = cur.val + left[0] + right[0];
        return result;
    }
}
