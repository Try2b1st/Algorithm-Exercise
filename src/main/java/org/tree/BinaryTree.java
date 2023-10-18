package org.tree;

import java.util.*;

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
        while (!deque.isEmpty()) {
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

    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return traversal(root, targetSum);
    }

    public boolean traversal(TreeNode root, int count) {
        count -= root.val;
        boolean leftFlag = false;
        boolean rightFlag = false;
        if (root.left == null && root.right == null && count == 0) {
            return true;
        } else if (root.left == null && root.right == null) {
            return false;
        }
        if (root.left != null) {
            leftFlag = traversal(root.left, count);
        }
        if (root.right != null) {
            rightFlag = traversal(root.right, count);
        }
        return leftFlag || rightFlag;
    }

    /**
     * 113. 路径总和 II
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        List<Integer> path = new ArrayList<>();
        traversal(root, targetSum, lists, path);
        return lists;
    }

    public void traversal(TreeNode root, int count, List<List<Integer>> lists, List<Integer> path) {
        count -= root.val;
        path.add(root.val);
        if (root.left == null && root.right == null && count == 0) {
            lists.add(new ArrayList<>(path));
            return;
        } else if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            traversal(root.left, count, lists, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            traversal(root.right, count, lists, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length, map);
    }

    public TreeNode traversal(int[] inorder, int inorderBegin, int inorderEnd, int[] postorder, int postorderBegin, int postorderEnd, Map<Integer, Integer> map) {
        if (postorderBegin == postorderEnd) {
            return null;
        }
        int rootVal = postorder[postorderEnd - 1];
        TreeNode root = new TreeNode(rootVal);

        if (inorderEnd - inorderBegin == 1) {
            return root;
        }

        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = map.get(rootVal);

        int rightInorderBegin = leftInorderEnd + 1;
        int rightInorderEnd = inorderEnd;


        int leftPostorderBegin = postorderBegin;
        int leftPostorderEnd = postorderBegin + (leftInorderEnd - leftInorderBegin);

        int rightPostorderBegin = postorderBegin + (leftInorderEnd - leftInorderBegin);
        int rightPostorderEnd = postorderEnd - 1;

        root.left = traversal(inorder, leftInorderBegin, leftInorderEnd, postorder, leftPostorderBegin, leftPostorderEnd, map);

        root.right = traversal(inorder, rightInorderBegin, rightInorderEnd, postorder, rightPostorderBegin, rightPostorderEnd, map);

        return root;
    }

    /**
     * 654. 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findRoot(nums, 0, nums.length);
    }

    public TreeNode findRoot(int[] nums, int left, int right) {
        if (right - left <= 0) {
            return null;
        }
        int maxIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        int leftLeft = left;
        int leftRight = maxIndex;

        int rightLeft = maxIndex + 1;
        int rightRight = right;

        root.left = findRoot(nums, leftLeft, leftRight);
        root.right = findRoot(nums, rightLeft, rightRight);

        return root;
    }

    /**
     * 617. 合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return preMergeTrees(root1, root2);
    }

    public TreeNode preMergeTrees(TreeNode root1, TreeNode root2) {
        int merge = 0;
        TreeNode root = new TreeNode();
        if (root1 == null && root2 == null) {
            return null;
        } else if (root1 != null && root2 == null) {
            root = root1;
        } else if (root1 == null && root2 != null) {
            root = root2;
        } else {
            merge += root1.val + root2.val;
            root.val = merge;
            root.left = preMergeTrees(root1.left, root2.left);
            root.right = preMergeTrees(root1.right, root2.right);
        }
        return root;
    }

    /**
     * 700. 二叉搜索树中的搜索
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        return preSearch(root, val);
    }

    public TreeNode preSearch(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val < val) {
            return preSearch(root.right, val);
        } else {
            return preSearch(root.left, val);
        }
    }

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }


    int min = Integer.MAX_VALUE;

    /**
     * 530. 二叉搜索树的最小绝对差
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        inn(root);
        int temp = min;
        min = Integer.MAX_VALUE;
        return min;
    }

    public void inn(TreeNode root) {
        if (root == null) {
            return;
        }
        inn(root.left);
        if (pre != null) {
            min = Math.min(root.val - pre.val, min);
        }
        pre = root;
        inn(root.right);
    }

    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    int count = 0;
    int maxCount = 0;
    List<Integer> result = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        search(root);
        int[] x = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            x[i] = result.get(i);
        }
        return x;
    }

    public void search(TreeNode root) {
        if (root == null) {
            return;
        }
        search(root.left);
        if (pre == null) {
            count = 1;
        } else if (pre.val == root.val) {
            count++;
        } else {
            count = 1;
        }
        if (maxCount < count) {
            maxCount = count;
            result.clear();
            result.add(root.val);
        } else if (maxCount == count) {
            result.add(root.val);
        }
        pre = root;
        search(root.right);
    }

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < q.val) {
            return traversal(root, p, q);
        } else {
            return traversal(root, q, p);
        }
    }

    public TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null) {
            return null;
        }
        if (cur.val < p.val) {
            TreeNode right = traversal(cur.right, p, q);
            return right;
        }
        if (cur.val > q.val) {
            TreeNode left = traversal(cur.left, p, q);
            return left;
        }
        return cur;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            insert(root, val);
        }
        return root;
    }

    public void insert(TreeNode cur, int val) {
        if (cur.val > val) {
            if (cur.left == null) {
                cur.left = new TreeNode(val);
            } else {
                insert(cur.left, val);
            }
        } else {
            if (cur.right == null) {
                cur.right = new TreeNode(val);
            } else {
                insert(cur.right, val);
            }
        }
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        //第一种情况：没找到删除的节点，遍历到空节点直接返回了
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            //第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right != null && root.left == null) {
                //第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
                return root.right;
            } else if (root.left != null && root.right == null) {
                //第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
                return root.left;
            } else {
                //第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                return root.right;
            }
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 669. 修剪二叉搜索树
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortArrayMakeBST(nums, 0, nums.length);
    }

    public TreeNode sortArrayMakeBST(int[] nums, int start, int end) {
        if(start == end){
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortArrayMakeBST(nums,start,mid);
        root.right = sortArrayMakeBST(nums,mid+1,end);
        return root;
    }

}
