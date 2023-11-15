package org;

import org.Track.*;
import org.dp.Palindromic;
import org.dp.ShareProblem;
import org.dp.SubQueue;
import org.greedly.Simple;
import org.monotonicStack.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        Daily daily = new Daily();
        System.out.println(question.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}