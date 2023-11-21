package org;


import org.graphTheory.Question;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        Daily daily = new Daily();
        System.out.println(daily.minDeletion(new int[]{1, 1, 2, 2, 3, 3}));
    }
}