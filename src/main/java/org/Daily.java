package org;

import java.util.*;

public class Daily {

    /**
     * 11.10 每日一题
     * 2300. 咒语和药水的成功对数
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] result = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int start = 0;
            int end = potions.length - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if ((long) potions[mid] * spells[i] >= success) {
                    result[i] = potions.length - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return result;
    }

    /**
     * 11.11 每日一题
     * 765. 情侣牵手
     * 暴力解法
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i += 2) {
            int target = -1;
            if (row[i] == 0) {
                target = 1;
            } else {
                if (row[i] % 2 == 1) {
                    target = (row[i] / 2 * 2);
                } else {
                    target = row[i] + 1;
                }
            }
            if (row[i + 1] == target) {
                continue;
            }
            for (int j = i + 2; j < row.length; j++) {
                if (row[j] == target) {
                    int temp = row[i + 1];
                    row[i + 1] = row[j];
                    row[j] = temp;
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    /**
     * 11.14 每日一题
     * 1334. 阈值距离内邻居最少的城市
     *
     * @param n
     * @param edges
     * @param distanceThreshold
     * @return
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = i == j ? 0 : 10001;
            }
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int weight = edge[2];

            distance[x][y] = weight;
            distance[y][x] = weight;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }


        int[] ans = {Integer.MAX_VALUE / 2, -1};
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }

    /**
     * 11.15 每日一题
     * 2656. K 个元素的最大和
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return k * max + (k * k - k) / 2;
    }

    /**
     * 11.16 每日一题
     * 2760. 最长奇偶子数组
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int result = 0;
        int dp = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < threshold) {
                dp = 0;
            } else if (i == nums.length - 1 || nums[i] % 2 != nums[i - 1] % 2) {
                dp++;
            } else {
                dp = 1;
            }
            if (nums[i] % 2 == 0) {
                result = Math.max(result, dp);
            }
        }
        return result;
    }

    /**
     * 11.17 每日一题
     * 2736. 最大和查询
     *
     * @param nums1
     * @param nums2
     * @param queries
     * @return
     */
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums1[i];
            sortedNums[i][1] = nums2[i];
        }
        Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = i;
            sortedQueries[i][1] = queries[i][0];
            sortedQueries[i][2] = queries[i][1];
        }
        Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<int[]>();
        int[] answer = new int[q];
        Arrays.fill(answer, -1);
        int j = 0;
        for (int[] query : sortedQueries) {
            int i = query[0], x = query[1], y = query[2];
            while (j < n && sortedNums[j][0] >= x) {
                int[] pair = sortedNums[j];
                int num1 = pair[0], num2 = pair[1];
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                    stack.add(new int[]{num2, num1 + num2});
                }
                j++;
            }
            int k = binarySearch(stack, y);
            if (k < stack.size()) {
                answer[i] = stack.get(k)[1];
            }
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     * 11.18 每日一题
     * 2342. 数位和相等数对的最大和
     *
     * @param nums
     * @return
     */
    public int maximumSum(int[] nums) {
        int[][] sum = new int[82][2];
        boolean flag = true;

        for (int num : nums) {
            int temp = num;
            int sumTemp = 0;
            while (temp > 0) {
                sumTemp += temp % 10;
                temp /= 10;
            }
            if (sum[sumTemp][0] == 0) {
                sum[sumTemp][0] = num;
            } else if (sum[sumTemp][1] == 0) {
                sum[sumTemp][1] = num;
                flag = false;
            } else {
                if (sum[sumTemp][0] >= sum[sumTemp][1] && num > sum[sumTemp][1]) {
                    sum[sumTemp][1] = num;
                } else if (sum[sumTemp][1] >= sum[sumTemp][0] && num > sum[sumTemp][0]) {
                    sum[sumTemp][0] = num;
                }
            }
        }
        System.out.println(Arrays.deepToString(sum));

        if (flag) {
            return -1;
        }
        int max = -1;
        for (int[] i : sum) {
            if (i[0] == 0 || i[1] == 0) {
                continue;
            }
            max = Math.max(max, i[0] + i[1]);
        }
        return max;
    }


    /**
     * 11.21 每日一题
     * 2216. 美化数组的最少删除数
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if ((stack.size() - 1) % 2 == 0 && nums[i] == stack.peek()) {
                result++;
                continue;
            }
            stack.push(nums[i]);
        }
        if (stack.size() % 2 != 0) {
            result++;
        }
        return result;
    }


    /**
     * 11.22 每日一题
     * 2304. 网格中的最小路径代价
     *
     * @param grid
     * @param moveCost
     * @return
     */
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;

        //dp[i][j]是到第i行第j列的最小代价
        int[][] dp = new int[m][n];
        for (int[] temp : dp) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        dp[0] = grid[0];

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i : dp[m - 1]) {
            if (i < result) {
                result = i;
            }
        }

        return result;
    }

    /**
     * 11.23 每日一题
     * 1410. HTML 实体解析器
     *
     * @param text
     * @return
     */
    public String entityParser(String text) {
        return text.replaceAll("&quot;", "\"")
                .replaceAll("&apos;", "'")
                .replaceAll("&amp;", "&")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt", "<")
                .replaceAll("&frasl;", "/");
    }


    /**
     * 11.24 每日一题
     * 2824. 统计和小于目标的下标对数目
     *
     * @param nums
     * @param target
     * @return
     */
    public int countPairs(List<Integer> nums, int target) {
        int result = 0;

        Collections.sort(nums);
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            if (nums.get(left) + nums.get(right) < target) {
                result += right - left;
                left++;
            } else {
                right--;
            }
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
     * 11.25 每日一题
     * 1457. 二叉树中的伪回文路径
     *
     * @param root
     * @return
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] current = new int[10];
        return dsf(root,current);
    }
    public int dsf(TreeNode root,int[] counter) {
        if(root == null){
            return 0;
        }
        counter[root.val]++;
        int res = 0;
        if (root.left == null && root.right == null) {
            if(isPseudoPalindrome(counter)){
                return 1;
            }
        }else{
            res = dsf(root.left,counter) + dsf(root.right,counter);
        }
        counter[root.val]--;
        return res;
    }

    public boolean isPseudoPalindrome(int[] counter) {
        int odd = 0;
        for (int value : counter) {
            if (value % 2 == 1) {
                odd++;
            }
        }
        return odd <= 1;
    }
}
