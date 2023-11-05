package org;

import org.Track.Combination;
import org.Track.Palindrome;
import org.Track.Permutations;
import org.Track.Subsets;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(new int[]{1,2,3}));
    }
}