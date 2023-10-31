package org;

import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        SubQueue subQueue = new SubQueue();
        Palindromic palindromic = new Palindromic();
        System.out.println(palindromic.countSubstrings("abc"));
    }
}