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

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[] current = new int[prices.length - 1];

        for (int i = 1; i < prices.length; i++) {
            current[i - 1] = prices[i] - prices[i - 1];
        }

        int sum = 0;
        for (int x : current) {
            if (x > 0) {
                sum += x;
            }
        }
        return sum;
    }

    /**
     * 55. 跳跃游戏
     * 贪心算法思路，是计算棋子最远可到达哪，因为我们可以选择走几步，
     * 所以只要要在可到达的地方，我们就有一个走法，所以最远可到达地只要超过数组长度，即可。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int able = 0;

        for (int i = 0; i <= able && i < nums.length; i++) {
            able = Math.max(able, i + nums[i]);
            if (able >= nums.length) {
                return true;
            }
        }

        return false;
    }
}
