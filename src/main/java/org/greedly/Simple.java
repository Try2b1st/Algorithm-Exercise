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

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int[] ableNums = new int[nums.length];
        Arrays.fill(ableNums, Integer.MAX_VALUE);
        ableNums[0] = 0;
        int able = 0;

        for (int i = 0; i <= able && i < nums.length; i++) {
            able = Math.max(able, i + nums[i]);
            for (int j = i + 1; j <= able && j < nums.length; j++) {
                ableNums[j] = Math.min(ableNums[j], ableNums[i] + 1);
            }
        }
        return ableNums[ableNums.length - 1];
    }

    public int jumpBest(int[] nums) {
        int nextDistance = 0;
        int curDistance = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nextDistance, nums[i] + i);
            if (i == curDistance) {
                result++;
                curDistance = nextDistance;
                if (nextDistance > nums.length) {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < k; i++) {
            nums[0] = -nums[0];
        }
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * 134. 加油站
     * 暴力解法
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] sums = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            sums[i] = gas[i] - cost[i];
        }

        for (int i = 0; i < sums.length; i++) {
            int sum = 0;
            if (sums[i] < 0) {
                continue;
            }
            boolean flag = true;
            for (int count = 0; count < sums.length; count++) {
                sum += sums[(i + count) % sums.length];

                if (sum <= -1) {
                    flag = false;
                    break;
                }
                if (sum == 0 && count != sums.length - 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 134. 加油站 贪心算法
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitBest(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum <= 0) {
                start = i + 1;
                curSum = 0;
            }
        }

        if (totalSum < 0) {
            return -1;
        }
        return start;
    }

    /**
     * 135. 分发糖果
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);

        for (int i = 1; i < result.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = Math.max(result[i], result[i - 1] + 1);
            }
        }
        System.out.println(Arrays.toString(result));
        for (int i = result.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                result[i - 1] = Math.max(result[i] + 1, result[i - 1]);
            }
        }
        System.out.println(Arrays.toString(result));
        int sum = 0;
        for (int x : result) {
            sum += x;
        }
        return sum;
    }

    /**
     * 860. 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        //0-5 1-10 2-20
        int[] money = new int[3];
        for (int bill : bills) {
            if (bill == 5) {
                money[0] += 1;
            } else if (bill == 10) {
                money[1] += 1;
                if (money[0] > 0) {
                    money[0] -= 1;
                } else {
                    return false;
                }
            } else if (bill == 20) {
                money[2] += 1;
                if(money[1] >0 && money[0]>0){
                    money[0] -= 1;
                    money[1] -= 1;
                }else if(money[0] > 2){
                    money[0] -= 3;
                }else{
                    return false;
                }
            }
        }

        return true;
    }
}
