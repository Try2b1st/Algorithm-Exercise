package org.dp;

import java.util.Arrays;

public class ShareProblem {

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfitI(int[] prices) {

        //初始化
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(prices[i] + dp[i - 1][0], dp[i - 1][1]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        //初始化
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(prices[i] + dp[i - 1][0], dp[i - 1][1]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 123. 买卖股票的最佳时机 III
     *
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        /*
        没有操作 （其实我们也可以不设置这个状态）
        第一次持有股票
        第一次不持有股票
        第二次持有股票
        第二次不持有股票
         */

        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[prices.length - 1][4];

    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][2 * k + 1];
        for (int i = 1; i < 2 * k + 1; i += 2) {
            dp[0][i] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 2 * k + 1; j += 2) {
                if (j == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j], -prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]);
            }
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[prices.length - 1][2 * k];
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /*
        状态一：持有股票状态（今天买入股票，或者是之前就买入了股票然后没有操作，一直持有）不持有股票状态
        这里就有两种卖出股票状态
            状态二：保持卖出股票的状态（两天前就卖出了股票，度过一天冷冻期。或者是前一天就是卖出股票状态，一直没操作）
            状态三：今天卖出股票
        状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天
         */
        //初始化
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][3], dp[i - 1][1]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
            System.out.println(Arrays.toString(dp[i]));
        }

        return Math.max(dp[prices.length - 1][1], Math.max(dp[prices.length - 1][2], dp[prices.length - 1][3]));
    }
}
