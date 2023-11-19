package org;


import org.graphTheory.Question;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();

        System.out.println(question.largestIsland(new int[][]{{1, 0}, {0, 1}}));
    }
}