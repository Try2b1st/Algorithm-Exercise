package org.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
        find(root, list, subRoot.val);
        for (TreeNode temp : list) {
            //经过修改的compare 不是代码上方发compare
            if (compare(temp, subRoot)) {
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

    /**
     * 222. 完全二叉树的节点个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        /*
          针对完全二叉树的解法
         */
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0;
        int rightDepth = 0;

        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (int) (Math.pow(2, leftDepth + 1) - 1);
        }

        return countNodes(root.left) + countNodes(root.right) + 1;

        /*
          针对普通二叉树的普遍方法
         */
        //return getCount(root);
    }

    public int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getCount(root.left);
        int right = getCount(root.right);
        return left + right + 1;
    }


    /**
     * 110. 平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (getHeight(root) == -1) {
            return false;
        }
        return true;
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.abs(leftHeight - rightHeight);
    }

    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        traversal(root, path, result);
        return result;
    }

    public void traversal(TreeNode root, List<String> path, List<String> result) {
        path.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                stringBuilder.append(path.get(i));
                stringBuilder.append("->");
            }
            stringBuilder.append(path.get(path.size() - 1));
            result.add(stringBuilder.toString());
            return;
        }
        if (root.left != null) {
            traversal(root.left, path, result);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            traversal(root.right, path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);

        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;
        return sum;
    }

    /**
     * 513. 找树左下角的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);

        int result = 0;
        while(!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.pop();
                if (i == 0) {
                    result = temp.val;
                }
                if (temp.left != null) {
                    deque.add(temp.left);
                }
                if (temp.right != null) {
                    deque.add(temp.right);
                }
            }
        }
        return result;
    }

}
