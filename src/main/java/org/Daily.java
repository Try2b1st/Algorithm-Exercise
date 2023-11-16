package org;

import java.util.Arrays;

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

        for (int i = nums.length - 1 ; i >= 0; i--) {
            if (nums[i] < threshold) {
                dp = 0;
            } else if (i == nums.length - 1 || nums[i] % 2 != nums[i - 1] % 2) {
                dp++;
            } else {
                dp = 1;
            }
            if(nums[i] % 2 == 0){
                result = Math.max(result, dp);
            }
        }
        return result;
    }
}
