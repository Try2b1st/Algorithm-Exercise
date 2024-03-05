package org.ex;


import org.ex.graphTheory.Connection;
import org.ex.graphTheory.Question;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        Daily daily = new Daily();
        Connection connection = new Connection();
        System.out.println(daily.possibleToStamp(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}, 2, 2));
    }
}