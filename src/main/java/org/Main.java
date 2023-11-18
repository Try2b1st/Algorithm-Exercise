package org;


import org.graphTheory.Question;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        Daily daily = new Daily();
        System.out.println(daily.maximumSum(new int[]{1,9,1,3,10}));
    }
}