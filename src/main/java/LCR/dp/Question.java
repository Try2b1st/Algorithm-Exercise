package LCR.dp;

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
}
