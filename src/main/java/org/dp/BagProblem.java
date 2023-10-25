package org.dp;

import java.util.List;
import java.util.Scanner;

public class BagProblem {
    public void bagTwoQuestion(int[] weight, int[] values, int bagWeight) {
        //确定dp数组
        int[][] dp = new int[values.length][bagWeight + 1];

        //初始化
        for (int i = weight[0]; i < bagWeight + 1; i++) {
            dp[0][i] = values[0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < bagWeight + 1; j++) {
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + values[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[values.length - 1][bagWeight]);
    }

    public void bagOneQuestion(int[] weight, int[] values, int bagWeight) {
        //确定dp数组
        int[] dp = new int[bagWeight + 1];

        //初始化
        for (int i = weight[0]; i < bagWeight + 1; i++) {
            dp[i] = values[0];
        }

        for (int i = 1; i < values.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + values[i]);
            }
        }

        System.out.println(dp[bagWeight]);
    }

    /**
     * 416. 分割等和子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        int[] dp = new int[sum + 1];


        for (int num : nums) {
            for (int j = sum; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[sum] == sum;
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int avg = sum / 2;
        int[][] dp = new int[stones.length][avg + 1];

        //初始化
        for (int i = stones[0]; i < avg + 1; i++) {
            dp[0][i] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j < avg + 1; j++) {
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return Math.abs(sum - dp[stones.length - 1][avg] * 2);
    }

    /**
     * 494. 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        int[] dp = new int[(sum + target) / 2 + 1];

        //初始化
        dp[0] = 1;

        for (int num : nums) {
            for (int j = dp.length - 1; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[dp.length - 1];
    }

    /**
     * 474. 一和零
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int zero = 0;
            int one = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= one; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 完全背包问题
     */
    public void wanQBeiBao() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = sc.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }

        int[] dp = new int[v + 1];

        for (int i = 0; i < n; i++) {
            for (int j = weights[i]; j < v + 1; j++) {
                dp[j] = Math.max(dp[j - weights[i]] + values[i], dp[j]);
            }
        }

        System.out.println(dp[v]);
    }

    /**
     * 518. 零钱兑换 II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        //初始化
        dp[0] = 1;
        for (int i = coins[0]; i < amount + 1; i++) {
            dp[i] += dp[i - coins[0]];
        }


        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }


    /**
     * 377. 组合总和 Ⅳ
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];

        //初始化
        dp[0] = 1;

        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 322. 零钱兑换
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int max = Integer.MAX_VALUE - 1;

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = max;
        }


        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                if (j - coin == 0 || dp[j - coin] != 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    /**
     * 279. 完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {

        //初始化
        int max = Integer.MAX_VALUE - 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = max;
        }

        for (int x = 1; x * x <= n; x++) {
            int sum = x * x;
            for (int i = sum; i < n + 1; i++) {
                if (i - sum == 0 || dp[i - sum] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - sum] + 1);
                }
            }
        }
        return dp[n];
    }

    /**
     * 139. 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] dp = new Boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1;i<s.length() + 1;i++){
            dp[i] = false;
        }

        for (int i = 0; i < dp.length; i++) {
            for (String word : wordDict) {
                if(i >= word.length() && s.startsWith(word, i-word.length())){
                    dp[i] = dp[i - word.length()] || dp[i];
                }
            }
        }
        return dp[s.length()];
    }
}
