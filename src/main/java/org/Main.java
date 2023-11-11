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
        System.out.println(simple.findMinArrowShots(new int[][]{
                {0, 9}, {1, 8}, {7, 8}, {1, 6}, {9, 16}, {7, 13},
                {7, 10}, {6, 11}, {6, 9}, {9, 13}
        }));
    }
}