package org.tree;

import java.util.List;

/**
 * @author 下水道的小老鼠
 */
public class BinaryTree {
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
     * 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        preorder(root);
        return root;
    }

    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        preorder(root.left);
        preorder(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
