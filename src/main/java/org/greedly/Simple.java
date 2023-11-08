package org.greedly;

import java.util.Arrays;

public class Simple {
    //想清楚局部最优，想清楚全局最优，感觉局部最优是可以推出全局最优，并想不出反例，那么就试一试贪心。

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int sCount = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (sCount >= 0 && g[i] < s[sCount]) {
                sCount--;
                count++;
            }
        }
        return count;
    }

    /**
     * 376. 摆动序列
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int preDiff = 0;
        int curDiff;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    /**
     * 53. 最大子数组和
     * 贪心算法的思路就是，当前的数组和为负数时，如果再往下加的话，会拖累和的大小所以要丛植开头
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int current = 0;

        for (int i : nums) {
            current += i;
            result = Math.max(result, current);
            if (current < 0) {
                current = 0;
            }
        }

        return result;
    }
}
