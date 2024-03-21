package LCR.stack;

import java.util.*;

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
            if (min.peek().equals(main.pop())) {
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


    /**
     * LCR 148. 验证图书取出顺序
     * 栈的压入、弹出序列
     *
     * @param putIn
     * @param takeOut
     * @return
     */
    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        int in;
        int max = putIn.length;
        int out = 0;
        Stack<Integer> stack = new Stack<>();

        for (in = 0; in < max; in++) {
            stack.push(putIn[in]);
            while (!stack.isEmpty() && out < max && stack.peek() == takeOut[out]) {
                stack.pop();
                out++;
            }
        }
        return out == max && in == max;
    }


    /**
     * LCR 184. 设计自助结算系统
     */
    public class Checkout {

        private Deque<Integer> pr;
        private Deque<Integer> deque;

        public Checkout() {
            pr = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int get_max() {
            return pr.isEmpty() ? -1 : pr.getFirst();
        }

        public void add(int value) {
            deque.addLast(value);
            while(!pr.isEmpty() && pr.getLast() < value){
                pr.removeLast();
            }
            pr.addLast(value);
        }

        public int remove() {
            if(!deque.isEmpty()){
                if(Objects.equals(pr.getFirst(), deque.getFirst())) pr.removeFirst();
                return deque.removeFirst();
            }
            return -1;
        }
    }

}