package org;

import org.dp.ShareProblem;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        ShareProblem shareProblem = new ShareProblem();
        System.out.println(shareProblem.maxProfit(new int[]{1,2,3,0,2}));
    }
}