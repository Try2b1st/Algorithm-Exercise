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
        LCR.string.Question question1 = new LCR.string.Question();

        int[] postorder = new int[]{4,9,6,5,8};
        int[] post = postorder;
        Arrays.sort(postorder);
        int[] mid = postorder;
        System.out.println(Arrays.toString(post));
        System.out.println(Arrays.toString(mid));
    }
}




