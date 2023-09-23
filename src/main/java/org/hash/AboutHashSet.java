package org.hash;

import java.util.Arrays;
import java.util.HashSet;

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

    /**
     * @param nums1
     * @param nums2
     * @return
     */
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


}
