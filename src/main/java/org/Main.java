package org;

import org.Track.Combination;
import org.Track.Palindrome;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Combination combination = new Combination();
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.partition("aab"));
    }
}