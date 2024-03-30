package LCR.dp;

import java.util.Arrays;

public class Question {

    int MOD = 1000000007;

    /**
     * LCR 126. 斐波那契数
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];

        //初始化
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }


    /**
     * LCR 127. 跳跃训练
     *
     * @param num
     * @return
     */
    public int trainWays(int num) {
        if (num <= 1) return 1;
        int[] dp = new int[num + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < num + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[num];
    }


    /**
     * LCR 137. 模糊搜索验证
     *
     * @param s
     * @param p
     * @return
     */
    public boolean articleMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for (int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 2.
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 3.
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * LCR 161. 连续天数的最高销售额
     *
     * @param sales
     * @return
     */
    public int maxSales(int[] sales) {
        int n = sales.length;
        int[] dp = new int[n];
        dp[0] = sales[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + sales[i], sales[i]);
        }
        Arrays.sort(dp);
        return dp[0];
    }


    /**
     * LCR 165. 解密数字
     *
     * @param ciphertext
     * @return
     */
    public int crackNumber(int ciphertext) {
        String s = String.valueOf(ciphertext);
        int n = s.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            String temp = s.substring(i - 2, i);
            if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n];
    }


    /**
     * LCR 166. 珠宝的最高价值
     *
     * @param frame
     * @return
     */
    public int jewelleryValue(int[][] frame) {
        int n = frame.length;
        int m = frame[0].length;

        //0 下 1右
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = frame[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + frame[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + frame[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + frame[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * LCR 185. 统计结果概率
     *
     * @param num
     * @return
     */
    public double[] statisticsProbability(int num) {
        double[] result = new double[6];
        Arrays.fill(result, 1.0 / 6.0);

        for (int i = 2; i <= num; i++) {
            double[] temp = new double[i * 5 + 1];
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += result[j] * (1.0 / 6.0);
                }
            }
            result = temp;
        }
        return result;
    }


    /**
     * LCR 187. 破冰游戏
     *
     * @param num
     * @param target
     * @return
     */
    public int iceBreakingGame(int num, int target) {
        int x = 0;
        for (int i = 2; i < num; i++) {
            x = (x + target) % num;
        }
        return x;
    }

}














