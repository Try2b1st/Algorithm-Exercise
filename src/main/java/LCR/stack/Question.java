package LCR.stack;

import java.util.ArrayList;
import java.util.Stack;

public class Question {

    /**
     * LCR 125. 图书整理 II
     * 两个堆栈实现队列
     */
    public class CQueue {
        private Stack<Integer> stackIn;
        private Stack<Integer> stackOut;

        public CQueue() {
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void appendTail(int value) {
            stackIn.push(value);
        }

        public int deleteHead() {
            if (!stackOut.isEmpty()) {
                return stackOut.pop();
            }
            if (stackIn.isEmpty()) {
                return -1;
            }
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.pop();

        }
    }


    /**
     * LCR 147. 最小栈
     */
    public class MinStack {
        private Stack<Integer> main;
        private Stack<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            main = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            main.push(x);
            if (min.isEmpty() || x <= min.peek()) {
                min.push(x);
            }
        }

        public void pop() {
            if(min.peek().equals(main.pop())){
                min.pop();
            }
        }

        public int top() {
            return main.peek();
        }

        public int getMin() {
            return min.peek();
        }

    }
}
