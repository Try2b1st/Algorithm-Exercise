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
        System.out.println(daily.sumSubarrayMins(new int[]{71,55,82,55}));
    }
}