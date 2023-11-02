package org;

import org.Track.Combination;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Combination combination = new Combination();
        System.out.println(combination.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}