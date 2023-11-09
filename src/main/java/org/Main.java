package org;

import org.Track.*;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;
import org.greedly.Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple();
        System.out.println(simple.canCompleteCircuitBest(new int[]{4,5,2,6,5,3}, new int[]{3,2,7,3,2,9}));
    }
}