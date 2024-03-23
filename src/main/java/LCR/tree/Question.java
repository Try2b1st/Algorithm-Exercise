package LCR.tree;

import java.util.*;

public class Question {

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
     * LCR 124. 推理二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode deduceTree(int[] preorder, int[] inorder) {
        return myDeduceTree(preorder, inorder);
    }

    public TreeNode myDeduceTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        int headVal = preorder[0];
        TreeNode head = new TreeNode(headVal);

        int headValIndex = 0;
        for (; headValIndex < n; headValIndex++) {
            if (headVal == inorder[headValIndex]) {
                break;
            }
        }
        int[] leftPre = Arrays.copyOfRange(preorder, 1, headValIndex + 1);
        int[] leftIn = Arrays.copyOfRange(inorder, 0, headValIndex);
        TreeNode left = myDeduceTree(leftPre, leftIn);

        int[] rightPre = Arrays.copyOfRange(preorder, headValIndex + 1, n);
        int[] rightIn = Arrays.copyOfRange(inorder, headValIndex + 1, n);
        TreeNode right = myDeduceTree(rightPre, rightIn);

        head.left = left;
        head.right = right;
        return head;
    }


    /**
     * LCR 143. 子结构判断
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return dfsToIsSubStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 当根节点相等，判断结构
     *
     * @param a
     * @param b
     * @return
     */
    public boolean dfsToIsSubStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }

        return dfsToIsSubStructure(a.left, b.left) && dfsToIsSubStructure(a.right, b.right);
    }


    /**
     * LCR 144. 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        changeLeftRight(root);
        return root;
    }

    public void changeLeftRight(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        changeLeftRight(root.left);
        changeLeftRight(root.right);
    }


    /**
     * LCR 145. 判断对称二叉树
     *
     * @param root
     * @return
     */
    public boolean checkSymmetricTree(TreeNode root) {
        return dfsToCheckSymmetricTree(root.left, root.right);
    }

    public boolean dfsToCheckSymmetricTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return dfsToCheckSymmetricTree(left.left, right.right) && dfsToCheckSymmetricTree(left.right, right.left);
        }
        return false;
    }


    /**
     * LCR 149. 彩灯装饰记录 I
     *
     * @param root
     * @return
     */
    public int[] decorateRecord(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        deque.addLast(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.poll();
                if (temp.left != null) deque.addLast(temp.left);
                if (temp.right != null) deque.addLast(temp.right);
                list.add(temp.val);
            }
        }
        int n = list.size();
        int[] result = new int[n];
        for(int i = 0; i< n;i++){
            result[i] = list.get(i);
        }

        return result;
    }


    /**
     * LCR 150. 彩灯装饰记录 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> decorateRecord2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        Deque<TreeNode> deque = new LinkedList<>();

        deque.addLast(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.poll();
                if (temp.left != null) deque.addLast(temp.left);
                if (temp.right != null) deque.addLast(temp.right);
                list.add(temp.val);
            }
            lists.add(list);
        }


        return lists;
    }

}
