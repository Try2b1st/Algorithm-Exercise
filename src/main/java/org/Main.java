package org;

import org.dp.ShareProblem;
import org.dp.SubQueue;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        SubQueue subQueue = new SubQueue();
        System.out.println(subQueue.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}