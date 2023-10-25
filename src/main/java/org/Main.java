package org;


import org.dp.BagProblem;
import org.dp.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        BagProblem bagProblem = new BagProblem();

        List<String> list = new ArrayList<>();
        list.add("dog");
        list.add("s");
        list.add("gs");
        System.out.println(bagProblem.wordBreak("dogs",list));
    }
}