package org.hash;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class AboutHashMap {

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (hashMap.containsKey(j) && hashMap.get(j) != i) {
                return new int[]{i, hashMap.get(j)};
            }
            hashMap.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 454. 四数相加 II
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                if (map.containsKey(i + j)) {
                    map.put(i + j, map.get(i + j) + 1);
                } else {
                    map.put(i + j, 1);
                }
            }
        }
        int count = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                int sum = -i - j;
                if (map.containsKey(sum)) {
                    count += map.get(sum);
                }
            }
        }
        return count;
    }


    /**
     * 15. 三数之和
     * 双指针法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return lists;
        }

        for (int i = 0; i < nums.length; i++) {
            //对第一个元素去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    lists.add(list);

                    //对 left 和 right 去重
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }
        return lists;
    }
}
