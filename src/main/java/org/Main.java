package org;


import org.dp.DynamicProgramming;

import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();

        System.out.println(dynamicProgramming.coinChange(new int[]{186,419,83,408},6249));
    }
}