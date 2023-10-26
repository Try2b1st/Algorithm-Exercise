package org;

import org.dp.ShareProblem;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        ShareProblem shareProblem = new ShareProblem();
        System.out.println(shareProblem.maxProfit(2,new int[]{2,4,1}));
    }
}