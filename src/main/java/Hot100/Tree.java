package Hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        K = k;
        midToKthSmallest(root);
        return list.get(k - 1);
    }

    private void midToKthSmallest(TreeNode root) {
        if (root == null) return;
        if (list.size() == K) return;

        midToKthSmallest(root.left);
        list.add(root.val);
        midToKthSmallest(root.right);
    }


    /**
     * 114. 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode pre = root.left;

                while (pre.right != null) {
                    pre = pre.right;
                }

                pre.right = root.right;
                root.right = root.left;
                root.left = null;

                root = root.right;
            }
        }
    }


    /**
     * 437. 路径总和 III
     *
     * @param root
     * @param targetSum
     * @return
     */
    Map<Long, Integer> prefixMap;
    int target;

    public int pathSum(TreeNode root, int targetSum) {
        prefixMap = new HashMap<>();
        target = targetSum;

        prefixMap.put(0L, 1);

        return recur(root,0L);
    }

    private int recur(TreeNode root, Long curSum) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        curSum += root.val;

        res += prefixMap.getOrDefault(curSum - target, 0);
        prefixMap.put(curSum, prefixMap.getOrDefault(curSum, 0) + 1);

        int left = recur(root.left, curSum);
        int right = recur(root.right, curSum);

        prefixMap.put(curSum,prefixMap.get(curSum) - 1);

        return res + left + right;
    }


}
