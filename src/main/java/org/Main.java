package org;


import org.dp.DynamicProgramming;

import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();

        System.out.println(dynamicProgramming.combinationSum4(new int[]{1,2,3},4));
    }
}