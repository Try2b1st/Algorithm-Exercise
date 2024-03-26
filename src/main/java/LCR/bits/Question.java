package LCR.bits;

public class Question {

    /**
     * LCR 133. 位 1 的个数
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int result = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }
        return result;
    }


    /**
     * LCR 134. Pow(x, n)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x == 0 || n == 0) {
            return 0d;
        }

        double ans = 0.0;

        long b = n;
        if (n < 0) {
            x = 1/x;
            b *= -1;
        }

        while (b > 0) {
            if ((b & 1) == 1) {
                ans += x;
            }
            x = x * x;
            b >>= 1;
        }
        return ans;
    }
}
