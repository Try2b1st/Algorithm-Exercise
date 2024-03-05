package org.ex.tree;

import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class Traversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        /**
         * 递归
         */
//        List<Integer> result = new ArrayList<>();
//        preorder(root, result);
//        return result;

        /**
         * 迭代
         */
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current != null) {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
        return result;
    }

    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }


    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        /**
         * 递归
         */
//        List<Integer> result = new ArrayList<>();
//        inorder(root, result);
//        return result;

        /**
         * 迭代
         */
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                result.add(current.val);
                current = current.right;
            }
        }
        return result;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }


    /**
     * 145. 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        /**
         * 递归
         */
//        List<Integer> result = new ArrayList<>();
//        postorder(root, result);
//        return result;

        /**
         * 迭代
         */
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current != null) {
                if (current.left != null) {
                    stack.push(current.left);
                }
                if (current.right != null) {
                    stack.push(current.right);
                }
            }
        }
        Collections.reverse(result);
        return result;
    }

    public void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }


    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                list.add(current.val);
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 107. 二叉树的层序遍历 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                list.add(current.val);
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
            result.add(list);
        }
        Collections.reverse(result);
        return result;
    }


    /**
     * 199. 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                if (i == size - 1) {
                    result.add(current.val);
                }
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
        }
        return result;
    }

    /**
     * 637. 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                sum += current.val;
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }


    public class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 429. N 叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = deque.pop();
                list.add(current.val);
                if (current.children != null) {
                    for (Node temp : current.children) {
                        deque.addLast(temp);
                    }
                }
            }
            result.add(list);
        }
        return result;
    }


    /**
     * 515. 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = deque.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                max = Math.max(max, current.val);
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
            result.add(max);
        }
        return result;
    }


    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            result++;
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
        }
        return result;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            result++;
            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pop();
                if (current.left == null && current.right == null) {
                    return result;
                }
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }
        }
        return result;
    }


    /**
     * 559. N 叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return traversal(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    public TreeNode traversal(int[] preorder, int preorderBegin, int preorderEnd, int[] inorder, int inorderBegin, int inorderEnd, Map<Integer, Integer> map) {
        if (preorderBegin == preorderEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preorderBegin]);
        if (preorderEnd - preorderBegin == -1) {
            return root;
        }

        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = map.get(root.val);
        int leftPreorderBegin = preorderBegin + 1;
        int leftPreorderEnd = leftPreorderBegin + (leftInorderEnd - leftInorderBegin);

        int rightInorderBegin = map.get(root.val) + 1;
        int rightInorderEnd = inorderEnd;
        int rightPreorderBegin = leftPreorderEnd;
        int rightPreorderEnd = preorderEnd;
        root.left = traversal(preorder, leftPreorderBegin, leftPreorderEnd, inorder, leftInorderBegin, leftInorderEnd, map);

        root.right = traversal(preorder, rightPreorderBegin, rightPreorderEnd, inorder, rightInorderBegin, rightInorderEnd, map);
        return root;
    }
}




















