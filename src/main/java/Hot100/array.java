package Hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class array {
    /**
     * 189. 轮转数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        int l = start;
        int r = end;

        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }


    /**
     * 238. 除自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];

        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int temp = 1;
        for (int i = length - 2; i >= 0; i--) {
            temp *= nums[i + 1];
            result[i] *= temp;
        }

        return result;
    }


    /**
     * 41. 缺失的第一个正数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }


    /**
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> h = new HashSet<>();
        Set<Integer> l = new HashSet<>();
        int x = matrix.length;
        int y = matrix[0].length;

        for (int i = 0; i < x; i++) {
            if (h.contains(i)) {
                continue;
            }
            for (int j = 0; j < y; j++) {
                if (l.contains(j)) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    h.add(i);
                    l.add(j);
                }
            }
        }
        for (int temp : h) {
            setHZeroes(matrix, temp, y);
        }
        for (int temp : l) {
            setLZeroes(matrix, temp, x);
        }
    }

    public void setHZeroes(int[][] matrix, int h, int maxL) {
        for (int i = 0; i < maxL; i++) {
            matrix[h][i] = 0;
        }
    }

    public void setLZeroes(int[][] matrix, int l, int maxH) {
        for (int i = 0; i < maxH; i++) {
            matrix[i][l] = 0;
        }
    }
}
