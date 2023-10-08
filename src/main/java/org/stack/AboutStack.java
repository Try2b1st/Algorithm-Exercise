package org.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
}
