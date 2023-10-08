package org.stack;

import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class AboutStack {

    /**
     * 232. 用栈实现队列
     */
    public static class MyQueue {
        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        public MyQueue() {
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void push(int x) {
            stackIn.push(x);
        }

        public int pop() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.pop();
        }

        public int peek() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.peek();
        }

        public boolean empty() {
            return stackOut.isEmpty() && stackIn.isEmpty();
        }
    }

    /**
     * 225. 用队列实现栈
     */
    public static class MyStack {

        Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            if (queue.isEmpty()) {
                queue.offer(x);
            } else {
                queue.offer(x);
                int size = queue.size();
                while (size-- > 1) {
                    queue.offer(queue.poll());
                }
            }
        }

        public int pop() {
            return queue.isEmpty() ? 0 : queue.poll();
        }

        public int top() {
            return queue.isEmpty() ? 0 : queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }


    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : stack) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int tempX = 0;
        int tempY = 0;
        int result = 0;
        for (String s : tokens) {
            if (Objects.equals(s, "+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (Objects.equals(s, "-")) {
                tempX = stack.pop();
                tempY = stack.pop();
                result = tempY - tempX;
                stack.push(result);
            } else if (Objects.equals(s, "*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (Objects.equals(s, "/")) {
                tempX = stack.pop();
                tempY = stack.pop();
                result = tempY / tempX;
                stack.push(result);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }


    /**
     * 239. 滑动窗口最大值
     * 那么维护元素单调递减的队列就叫做单调队列，即单调递减或单调递增的队列。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        MyDeque myDeque = new MyDeque();
        int size = nums.length - k + 1;
        int[] result = new int[size];

        for (int i = 0; i < k; i++) {
            myDeque.add(nums[i]);
        }

        int count = 0;
        result[count] = myDeque.peek();

        for (int i = k; i < nums.length; i++) {
            myDeque.pop(nums[count++]);
            myDeque.add(nums[i]);
            result[count] = myDeque.peek();
        }
        return result;
    }

    /**
     * 维护一个单调栈
     */
    public static class MyDeque {
        Deque<Integer> deque;

        public MyDeque() {
            deque = new LinkedList<>();
        }

        public void add(int x) {
            while (!deque.isEmpty() && x > deque.getLast()) {
                deque.removeLast();
            }
            deque.add(x);
        }

        public void pop(int x) {
            if (!deque.isEmpty() && x == deque.getFirst()) {
                deque.pop();
            }
        }

        public int peek() {
            return deque.peek();
        }
    }


}
