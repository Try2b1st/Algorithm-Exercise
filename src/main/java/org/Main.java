package org;

import org.Track.Combination;
import org.Track.Palindrome;
import org.Track.Subsets;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Subsets subsets= new Subsets();
        System.out.println(subsets.subsets(new int[]{1,2,3}));
    }
}