package org;

import org.Track.*;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;
import org.greedly.Simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple();
        System.out.println(Arrays.deepToString(simple.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 16}})));
    }
}