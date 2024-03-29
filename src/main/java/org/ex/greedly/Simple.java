package org.ex.greedly;

import java.util.*;

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
                if (money[1] > 0 && money[0] > 0) {
                    money[0] -= 1;
                    money[1] -= 1;
                } else if (money[0] > 2) {
                    money[0] -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 406. 根据身高重建队列
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list = new LinkedList<>();

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });

        for (int[] person : people) {
            list.add(person[1], person);
        }

        int[][] result = new int[people.length][people[0].length];
        int count = 0;

        for (int[] x : list) {
            result[count][0] = x[0];
            result[count][1] = x[1];
            count++;
        }
        return result;
    }

    /**
     * 452. 用最少数量的箭引爆气球
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                if (point1[0] > point2[0]) {
                    return 1;
                } else if (point1[0] < point2[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }

        return count;
    }

    /**
     * 435. 无重叠区间
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                if (point1[0] > point2[0]) {
                    return 1;
                } else if (point1[0] < point2[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                count++;
            }
        }

        return count;
    }


    /**
     * 763. 划分字母区间
     * O(n*n)
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            for (int j = s.length() - 1; j > i; j--) {
                if (temp == s.charAt(j)) {
                    end = Math.max(j, end);
                    break;
                }
            }
            if (end == i) {
                result.add(end - start + 1);
                start = end + 1;
                end = start;
            } else if (i == s.length() - 1) {
                result.add(i - start + 1);
            }
        }
        return result;
    }

    public List<Integer> partitionLabelsBatter(String s) {
        int[] hash = new int[26];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, hash[s.charAt(i) - 'a']);
            if (end == i) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }


    /**
     * 56. 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] < o2[0]) {
                return -1;
            } else if (o1[0] > o2[0]) {
                return 1;
            } else {
                return 0;
            }
        });

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= result.get(result.size() - 1)[1]) {
                result.set(result.size() - 1, new int[]{
                        result.get(result.size() - 1)[0],
                        Math.max(intervals[i][1], result.get(result.size() - 1)[1])
                });
            } else {
                result.add(intervals[i]);
            }

        }
        int[][] ans = new int[result.size()][2];
        int count = 0;
        for (int[] temp : result) {
            ans[count++] = temp;
        }
        return ans;
    }

    /**
     * 38. 单调递增的数字
     *
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {

        if (n < 10) {
            return n;
        }

        int tempN = n;

        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n = n / 10;
        }

        int[] nums = new int[list.size()];
        int count = nums.length - 1;
        for (int num : list) {
            nums[count--] = num;
        }

        int flag = nums.length;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i]--;
                flag = i + 1;
            }
        }

        if (flag == nums.length) {
            return tempN;
        }

        for (int i = flag; i < nums.length; i++) {
            nums[i] = 9;
        }

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result = result * 10 + nums[i];
        }

        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 968. 监控二叉树
     * 0：该节点无覆盖
     * 1：本节点有摄像头
     * 2：本节点有覆盖
     *
     * @param root
     * @return
     */
    int count = 0;

    public int minCameraCover(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int is = cameras(root);
        if(is == 0){
            count++;
        }
        return count;
    }

    public int cameras(TreeNode root) {
        if (root == null) {
            return 2;
        }

        int left = cameras(root.left);
        int right = cameras(root.right);

        if (left == 2 && right == 2) {
            return 0;
        }
        if((left == 0 && right == 0) || (left == 2 && right == 0) || (left == 0 && right == 2) || (left == 0 && right == 1) || (left == 1 && right == 0)){
            count++;
            return 1;
        }

        return 2;
    }
}
