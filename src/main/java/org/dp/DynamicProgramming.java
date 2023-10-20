package org.dp;

import java.util.Arrays;

public class DynamicProgramming {
    /*
      对于动态规划问题，我将拆解为如下五步曲，这五步都搞清楚了，才能说把动态规划真的掌握了！
      1.确定dp数组（dp table）以及下标的含义
      2.确定递推公式
      3.dp数组如何初始化
      4.确定遍历顺序
      5.举例推导dp数组
     */

    /**
     * 509. 斐波那契数
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;

        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] array = new int[n];
        array[0] = 1;
        array[1] = 2;

        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n - 1];
    }


    /**
     * 746. 使用最小花费爬楼梯
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int size = cost.length;
        int[] dp = new int[size + 1];
        dp[1] = 0;
        dp[0] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[size];
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                array[j] += array[j - 1];
            }
        }

        return array[n - 1];
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int x = obstacleGrid[0].length;
        int y = obstacleGrid.length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[y - 1][x - 1] == 1) {
            return 0;
        } else {
            obstacleGrid[0][0] = 1;
        }

        for (int i = 1; i < x; i++) {
            if (obstacleGrid[0][i] == 1) {
                while (i < x) {
                    obstacleGrid[0][i] = 0;
                    i++;
                }
                break;
            } else {
                obstacleGrid[0][i] = 1;
            }
        }

        for (int i = 1; i < y; i++) {
            if (obstacleGrid[i][0] == 1) {
                while (i < y) {
                    obstacleGrid[i][0] = 0;
                    i++;
                }
                break;
            } else {
                obstacleGrid[i][0] = 1;
            }
        }

        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }


        return obstacleGrid[y - 1][x - 1];
    }

    /**
     * 343. 整数拆分
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        if (n == 2) {
            return 1;
        }
        for (int j = 3; j < n + 1; j++) {
            for (int i = 1; i <= j / 2; i++) {
                dp[j] = Math.max(dp[j], Math.max(i * (j - i), i * dp[j - i]));
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

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
                dp[j] = Math.max(dp[j],dp[j-num] + num);
            }
        }
        return dp[sum] == sum;
    }
}
