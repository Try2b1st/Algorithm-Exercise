package org;


import org.array.Matrix;
import org.array.SlidingWindow;
import org.hash.AboutHashSet;
import org.hash.HashTable;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        AboutHashSet aboutHashSet = new AboutHashSet();
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{1, 1};

        System.out.println(Arrays.toString(aboutHashSet.intersect(nums1, nums2)));

    }
}