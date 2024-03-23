package LCR.tree;

import java.util.Arrays;

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
        TreeNode left = myDeduceTree(leftPre,leftIn);

        int[] rightPre = Arrays.copyOfRange(preorder,headValIndex + 1,n);
        int[] rightIn = Arrays.copyOfRange(inorder,headValIndex + 1, n);
        TreeNode right = myDeduceTree(rightPre,rightIn);

        head.left = left;
        head.right = right;
        return head;
    }



}
