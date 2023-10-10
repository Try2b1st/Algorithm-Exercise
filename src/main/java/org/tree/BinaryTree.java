package org.tree;

import java.util.ArrayList;
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


    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {

        if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        } else if (right == null && left == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }
        //外
        boolean leftFlag = compare(left.left, right.right);
        //里
        boolean rightFlag = compare(left.right, right.left);

        return leftFlag && rightFlag;
    }

    /**
     * 572. 另一棵树的子树
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        List<TreeNode> list = new ArrayList<>();
        find(root,list,subRoot.val);
        for (TreeNode temp : list){
            //经过修改的compare 不是代码上方发compare
            if(compare(temp,subRoot)){
                return true;
            }
        }
        return false;
    }

    public void find(TreeNode root, List<TreeNode> list, int val) {
        if (root == null) {
            return;
        }
        if (root.val == val) {
            list.add(root);
        }
        find(root.left, list, val);
        find(root.right, list, val);
    }

}