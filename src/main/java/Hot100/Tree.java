package Hot100;

import java.util.ArrayList;
import java.util.List;

public class Tree {

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
     * 543. 二叉树的直径
     *
     * @param root
     * @return
     */
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return -1; // 下面 +1 后，对于叶子节点就刚好是 0
        int lLen = dfs(node.left) + 1; // 左子树最大链长+1
        int rLen = dfs(node.right) + 1; // 右子树最大链长+1
        ans = Math.max(ans, lLen + rLen); // 两条链拼成路径
        return Math.max(lLen, rLen); // 当前子树最大链长
    }


    /**
     * 230. 二叉搜索树中第K小的元素
     *
     * @param root
     * @param k
     * @return
     */
    List<Integer> list;
    int K;
    public int kthSmallest(TreeNode root, int k) {
        list = new ArrayList<>();
        K=k;
        midToKthSmallest(root);
        return list.get(k - 1);
    }
    private void midToKthSmallest(TreeNode root){
        if(root == null) return;
        if(list.size() == K) return;

        midToKthSmallest(root.left);
        list.add(root.val);
        midToKthSmallest(root.right);
    }



}
