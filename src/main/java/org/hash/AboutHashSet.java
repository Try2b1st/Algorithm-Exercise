package org.hash;

import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class AboutHashSet {

    /**
     * 349. 两个数组的交集
     * 暴力解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionBad(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    hashSet.add(nums1[i]);
                }
            }
        }
        return hashSet.stream().mapToInt(x -> x).toArray();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> resultSet = new HashSet<>();
        HashSet<Integer> nums1Set = new HashSet<>();
        for (int i : nums1) {
            nums1Set.add(i);
        }

        for (int i : nums2) {
            if (nums1Set.contains(i)) {
                resultSet.add(i);
            }
        }
        return resultSet.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 350. 两个数组的交集 II
     * 暴力解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectBad(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i : nums1) {
            if (!hashMap1.containsKey(i)) {
                hashMap1.put(i, 1);
            } else {
                hashMap1.put(i, hashMap1.get(i) + 1);
            }
        }
        for (int i : nums2) {
            if (hashMap1.containsKey(i)) {
                hashMap1.put(i, hashMap1.get(i) - 1);
                list.add(i);
                if (hashMap1.get(i) == 0) {
                    hashMap1.remove(i);
                }
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 双指针
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length, length2 = nums2.length;
        int[] array = new int[Math.min(length1, length2)];
        int index = 0;
        int slow = 0;

        for (int i : nums1) {
            while (slow < nums2.length && nums2[slow] < i) {
                slow++;
            }
            if (slow < nums2.length && nums2[slow] == i) {
                array[index++] = i;
                slow++;
            }
        }
        return Arrays.copyOfRange(array, 0, index);
    }


    /**
     * 202. 快乐数
     * 哈希表探测
     *
     * @param n
     * @return
     */
    public boolean isHappyByHash(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        int sum = 0;
        while (true) {
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 1) {
                break;
            } else {
                if (hashSet.contains(sum)) {
                    return false;
                }
                hashSet.add(sum);
                n = sum;
                sum = 0;
            }
        }
        return true;
    }

    /**
     * 202. 快乐数
     * 快慢指针
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            for (int i = 0; i < 1; i++) {
                int sum = 0;
                while (slow > 0) {
                    sum += Math.pow(slow % 10, 2);
                    slow /= 10;
                }
                slow = sum;
            }
            for (int i = 0; i < 2; i++) {
                int sum = 0;
                while (fast > 0) {
                    sum += Math.pow(fast % 10, 2);
                    fast /= 10;
                }
                if (sum == 1) {
                    return true;
                } else {
                    fast = sum;
                }
            }
        } while (fast != slow);
        return false;
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target){
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

}
