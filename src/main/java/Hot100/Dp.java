package Hot100;

import java.util.*;

public class Dp {

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        //初始化
        List<Integer> origin1 = new ArrayList<>();
        origin1.add(1);
        result.add(origin1);
        if (numRows == 1) {
            return result;
        }

        List<Integer> origin2 = new ArrayList<>();
        origin2.add(1);
        origin2.add(1);
        result.add(origin2);
        if (numRows == 2) {
            return result;
        }

        for (int i = 0; i < numRows - 2; i++) {
            List<Integer> list = new ArrayList<>();
            List<Integer> temp = result.get(i + 1);
            list.add(1);
            for (int j = 0; j <= i; j++) {
                list.add(temp.get(j) + temp.get(j + 1));
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }


    /**
     * 152. 乘积最大子数组
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int curMax = nums[0];
        int curMin = nums[0];
        int ans = nums[0];

        for (int i = 1; i < n; i++) {
            int max = curMax;
            int min = curMin;

            curMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            curMin = Math.min(nums[i], Math.min(max * nums[i], min * nums[i]));
            ans = Math.max(ans, curMax);
        }

        return ans;
    }


    /**
     * 32. 最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        Deque<Integer> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    int count = 2;
                    while (deque.getLast() != 1) {
                        count++;
                        deque.removeLast();
                    }
                    deque.removeLast();
                    for (int i = 0; i < count; i++) {
                        deque.addLast(0);
                    }
                }
            } else {
                stack.push(c);
                deque.addLast(1);
            }
        }

        int ans = 0;
        int n = deque.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int x = deque.removeFirst();
            if (x == 0) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 0;
            }
        }
        ans = Math.max(ans, count);
        return ans;
    }


    /**
     * 64. 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] result = new int[m][n];

        result[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            result[0][i] = result[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            result[i][0] = result[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + grid[i][j];
            }
        }
        return result[m - 1][n - 1];
    }


    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        String ans = s.substring(0, 1);

        for (int i = 1; i <= len - 1; i++) {
            int left = i - 1;
            int right = i + 1;
            char c = s.charAt(i);
            int curLen = 1;
            while (left >= 0 && s.charAt(left) == c) {
                curLen++;
                left--;
            }
            while (right < len && s.charAt(right) == c) {
                curLen++;
                right++;
            }

            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                ans = s.substring(left + 1, right);
            }
        }
        return ans;
    }
}












