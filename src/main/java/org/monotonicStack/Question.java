package org.monotonicStack;

import java.util.Stack;

public class Question {


    /**
     * 739. 每日温度
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int temp = stack.peek();
                result[temp] = i - temp;
                stack.pop();
            }
            stack.push(i);
        }

        return result;
    }
}
