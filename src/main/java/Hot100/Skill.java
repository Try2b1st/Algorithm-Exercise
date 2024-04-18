package Hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skill {

    /**
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums)  // 1. 遍历 nums 执行异或运算
            x ^= num;
        return x;            // 2. 返回出现一次的数字 x
    }


    /**
     * 169. 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int ans = 0;
        int count = 0;

        for (int x : nums) {
            if (count == 0) ans = x;
            count += x == ans ? 1 : -1;
        }
        return ans;
    }


    /**
     * 75. 颜色分类
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p1 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0] = 0;
                if (p0 < p1) {
                    nums[i] = nums[p1];
                    nums[p1] = 1;
                }
                p0++;
                p1++;
            } else if (nums[i] == 1) {
                nums[i] = nums[p1];
                nums[p1] = 1;
                p1++;
            }
        }
    }


    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        for (int i = n - 1; i > 0; i--) {
            int x = nums[i - 1];
            if (x < nums[i]) {
                int index = i + 1;
                while (index < n && x < nums[index]) {
                    index++;
                }
                index--;
                nums[i - 1] = nums[index];
                nums[index] = x;
                reNums(nums, i, n - 1);
                return;
            }
        }
        reNums(nums, 0, n - 1);
    }

    public void reNums(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }


    /**
     * 287. 寻找重复数
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;

        int left = -1;
        int right = n;

        while (left + 1 < right) {
            int count = 0;
            int mid = left + (right - left) / 2;
            for (int x : nums) {
                if(mid >= x){
                    count++;
                }
            }
            if(count >= mid){
                right = mid;
            }else{
                left = mid;
            }
        }
        return right;
    }
}




