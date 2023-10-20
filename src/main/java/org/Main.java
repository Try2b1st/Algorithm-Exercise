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
        Scanner sc = new Scanner(System.in);
        int length  = sc.nextInt();
        int bagSize = sc.nextInt();
        int[] weight = new int[length];
        int[] values = new int[length];
        for(int i = 0; i< length;i++){
            weight[i] = sc.nextInt();
        }
        for(int i = 0; i< length;i++){
            values[i] = sc.nextInt();
        }

        dynamicProgramming.bagOneQuestion(weight,values,bagSize);
    }
}