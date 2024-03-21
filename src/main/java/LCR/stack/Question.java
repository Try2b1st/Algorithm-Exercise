package LCR.stack;

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
            if(stackIn.isEmpty()){
                return -1;
            }
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.pop();

        }
    }
}
