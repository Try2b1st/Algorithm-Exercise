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
        System.out.println(simple.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}