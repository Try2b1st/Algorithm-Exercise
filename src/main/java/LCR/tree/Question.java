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
        for (int i = 0; i < n; i++) {
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


    /**
     * LCR 151. 彩灯装饰记录 III
     *
     * @param root
     * @return
     */
    public List<List<Integer>> decorateRecord3(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        int count = 1;

        deque.addLast(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (count % 2 == 0) {
                    TreeNode temp = deque.poll();
                    if (temp.right != null) deque.addLast(temp.right);
                    if (temp.left != null) deque.addLast(temp.left);
                    list.add(temp.val);
                } else {
                    TreeNode temp = deque.removeLast();
                    if (temp.left != null) deque.addFirst(temp.left);
                    if (temp.right != null) deque.addFirst(temp.right);
                    list.add(temp.val);
                }
            }
            count++;
            lists.add(list);
        }
        return lists;
    }


    /**
     * LCR 152. 验证二叉搜索树的后序遍历序列
     *
     * @param postorder
     * @return
     */
    public boolean verifyTreeOrder(int[] postorder) {
        int n = postorder.length;
        Stack<Integer> stack = new Stack<>();
        int father = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (postorder[i] > father) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                father = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }


    /**
     * LCR 153. 二叉树中和为目标值的路径
     *
     * @param root
     * @param target
     * @return
     */
    List<List<Integer>> result;
    int targetByPathTarget;
    int count = 0;

    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        targetByPathTarget = target;
        List<Integer> list = new ArrayList<>();
        dfsPathTarget(root, list);
        return result;
    }

    public void dfsPathTarget(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        count += root.val;
        list.add(root.val);
        if (count == targetByPathTarget && root.left == null && root.right == null) {
            result.add(new ArrayList<>(list));
            count -= root.val;
            list.remove(list.size() - 1);
            return;
        }

        dfsPathTarget(root.left, list);
        dfsPathTarget(root.right, list);

        count -= root.val;
        list.remove(list.size() - 1);
    }


    public class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;

    /**
     * LCR 155. 将二叉搜索树转化为排序的双向链表
     *
     * @param root
     * @return
     */
    Node pre;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node head = new Node();
        pre = head;

        dfsTreeToDoubleList(root);
        head.right.left = pre;
        pre.right = head.right;

        return head.right;
    }

    public void dfsTreeToDoubleList(Node current) {
        if (current == null) {
            return;
        }

        dfsTreeToDoubleList(current.left);
        pre.right = current;
        current.left = pre;
        pre = current;
        dfsTreeToDoubleList(current.right);
    }


    /**
     * LCR 156. 序列化与反序列化二叉树
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            Deque<TreeNode> deque = new LinkedList<>();
            deque.addLast(root);
            StringBuilder sb = new StringBuilder();
            sb.append(root.val).append(",");

            while (!deque.isEmpty()) {
                int n = deque.size();
                for (int i = 0; i < n; i++) {
                    TreeNode temp = deque.pop();

                    if (temp.left == null) {
                        sb.append("#").append(",");
                    } else {
                        deque.addLast(temp.left);
                        sb.append(temp.left.val).append(",");
                    }
                    if (temp.right == null) {
                        sb.append("#").append(",");
                    } else {
                        deque.addLast(temp.right);
                        sb.append(temp.right.val).append(",");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] arrays = data.split(",");
            int count = 1;
            Deque<TreeNode> deque = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(arrays[0]));
            deque.addLast(root);

            while (!deque.isEmpty()) {
                int n = deque.size();
                for (int i = 0; i < n; i++) {
                    TreeNode temp = deque.pop();
                    if (temp == null) break;
                    String left = arrays[count++];
                    String right = arrays[count++];
                    if (!left.equals("#")) {
                        temp.left = new TreeNode(Integer.parseInt(left));
                        deque.addLast(temp.left);
                    } else {
                        temp.left = null;
                        deque.addLast(null);
                    }
                    if (!right.equals("#")) {
                        temp.right = new TreeNode(Integer.parseInt(right));
                        deque.addLast(temp.right);
                    } else {
                        temp.right = null;
                        deque.addLast(null);
                    }
                }
            }
            return root;
        }

    }


    /**
     * LCR 174. 寻找二叉搜索树中的目标节点
     *
     * @param root
     * @param cnt
     * @return
     */
    List<Integer> sortList;

    public int findTargetNode(TreeNode root, int cnt) {
        sortList = new ArrayList<>();

        dfsToFindTargetNode(root);

        return sortList.get(sortList.size() - cnt);
    }

    public void dfsToFindTargetNode(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsToFindTargetNode(root.left);
        sortList.add(root.val);
        dfsToFindTargetNode(root.right);
    }


    /**
     * LCR 175. 计算二叉树的深度
     *
     * @param root
     * @return
     */
    public int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        while (!deque.isEmpty()) {
            int n = deque.size();
            ans++;
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.pop();
                if (temp.left != null) deque.addLast(temp.left);
                if (temp.right != null) deque.addLast(temp.right);
            }
        }
        return ans;
    }

    public int calculateDepth1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(calculateDepth1(root.left), calculateDepth1(root.right)) + 1;
    }


    /**
     * LCR 176. 判断是否为平衡二叉树
     *
     * @param root
     * @return
     */
    boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = dfsToCount(root.left);
        int right = dfsToCount(root.right);

        return flag && Math.abs(left - right) <= 1;
    }

    public int dfsToCount(TreeNode root) {
        if (!flag) {
            return 0;
        }
        if (root == null) {
            return 0;
        }
        int left = dfsToCount(root.left);
        int right = dfsToCount(root.right);

        if (!(Math.abs(left - right) <= 1)) {
            flag = false;
        }
        return Math.max(left, right) + 1;
    }


    /**
     * LCR 193. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }

        while (root != null) {
            if (root == p || root == q) {
                return root;
            }
            if (p.val < root.val && root.val < q.val) {
                return root;
            }
            if (root.val > q.val) {
                root = root.left;
                continue;
            }
            if (root.val < p.val) {
                root = root.right;
                continue;
            }
        }
        return null;
    }


    /**
     * LCR 194. 寻找二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right,p,q);

        if(left == null && right == null){
            return null;
        }

        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }

        return root;
    }
}
