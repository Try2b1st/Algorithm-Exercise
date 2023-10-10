package org.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 下水道的小老鼠
 */
public class TempTraversal {
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node current = deque.pop();
                if (i == size - 1) {
                    current.next = null;
                }else{
                    current.next = deque.peek();
                }
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
            if (deque.peekLast() != null) {
                deque.peekLast().next = null;
            }
        }
        return root;
    }



}
