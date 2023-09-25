package org;


import org.array.Matrix;
import org.array.SlidingWindow;
import org.hash.AboutHashMap;
import org.hash.AboutHashSet;
import org.hash.HashTable;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        AboutHashMap aboutHashMap = new AboutHashMap();
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println(aboutHashMap.threeSum(array));

    }
}