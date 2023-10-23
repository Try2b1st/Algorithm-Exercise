package org;


import org.dp.DynamicProgramming;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        int[] nums = new int[]{1, 1, 1};
        System.out.println(dynamicProgramming.findTargetSumWays(nums, 1));
    }
}