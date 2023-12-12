package org;


import org.graphTheory.Connection;
import org.graphTheory.Question;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        Daily daily = new Daily();
        Connection connection = new Connection();
        System.out.println(Arrays.toString(daily.secondGreaterElement(new int[]{11, 13, 15, 12, 0, 15, 12, 11, 9})));
    }
}