package org;


import org.dp.DynamicProgramming;
import org.hash.AboutHashMap;
import org.stack.AboutStack;
import org.string.AboutString;
import org.tree.BinaryTree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        int[] nums = new int[]{1,1,1};
        dynamicProgramming.findTargetSumWays(nums,1);
    }
}