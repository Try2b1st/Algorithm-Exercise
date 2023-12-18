package org;

import org.greedly.Simple;

import java.util.*;
import java.util.function.DoubleToIntFunction;

public class Daily {

    /**
     * 11.10 每日一题
     * 2300. 咒语和药水的成功对数
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] result = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int start = 0;
            int end = potions.length - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if ((long) potions[mid] * spells[i] >= success) {
                    result[i] = potions.length - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return result;
    }

    /**
     * 11.11 每日一题
     * 765. 情侣牵手
     * 暴力解法
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i += 2) {
            int target = -1;
            if (row[i] == 0) {
                target = 1;
            } else {
                if (row[i] % 2 == 1) {
                    target = (row[i] / 2 * 2);
                } else {
                    target = row[i] + 1;
                }
            }
            if (row[i + 1] == target) {
                continue;
            }
            for (int j = i + 2; j < row.length; j++) {
                if (row[j] == target) {
                    int temp = row[i + 1];
                    row[i + 1] = row[j];
                    row[j] = temp;
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    /**
     * 11.14 每日一题
     * 1334. 阈值距离内邻居最少的城市
     *
     * @param n
     * @param edges
     * @param distanceThreshold
     * @return
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = i == j ? 0 : 10001;
            }
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int weight = edge[2];

            distance[x][y] = weight;
            distance[y][x] = weight;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }


        int[] ans = {Integer.MAX_VALUE / 2, -1};
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }

    /**
     * 11.15 每日一题
     * 2656. K 个元素的最大和
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return k * max + (k * k - k) / 2;
    }

    /**
     * 11.16 每日一题
     * 2760. 最长奇偶子数组
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int result = 0;
        int dp = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < threshold) {
                dp = 0;
            } else if (i == nums.length - 1 || nums[i] % 2 != nums[i - 1] % 2) {
                dp++;
            } else {
                dp = 1;
            }
            if (nums[i] % 2 == 0) {
                result = Math.max(result, dp);
            }
        }
        return result;
    }

    /**
     * 11.17 每日一题
     * 2736. 最大和查询
     *
     * @param nums1
     * @param nums2
     * @param queries
     * @return
     */
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums1[i];
            sortedNums[i][1] = nums2[i];
        }
        Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = i;
            sortedQueries[i][1] = queries[i][0];
            sortedQueries[i][2] = queries[i][1];
        }
        Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<int[]>();
        int[] answer = new int[q];
        Arrays.fill(answer, -1);
        int j = 0;
        for (int[] query : sortedQueries) {
            int i = query[0], x = query[1], y = query[2];
            while (j < n && sortedNums[j][0] >= x) {
                int[] pair = sortedNums[j];
                int num1 = pair[0], num2 = pair[1];
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                    stack.add(new int[]{num2, num1 + num2});
                }
                j++;
            }
            int k = binarySearch(stack, y);
            if (k < stack.size()) {
                answer[i] = stack.get(k)[1];
            }
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     * 11.18 每日一题
     * 2342. 数位和相等数对的最大和
     *
     * @param nums
     * @return
     */
    public int maximumSum(int[] nums) {
        int[][] sum = new int[82][2];
        boolean flag = true;

        for (int num : nums) {
            int temp = num;
            int sumTemp = 0;
            while (temp > 0) {
                sumTemp += temp % 10;
                temp /= 10;
            }
            if (sum[sumTemp][0] == 0) {
                sum[sumTemp][0] = num;
            } else if (sum[sumTemp][1] == 0) {
                sum[sumTemp][1] = num;
                flag = false;
            } else {
                if (sum[sumTemp][0] >= sum[sumTemp][1] && num > sum[sumTemp][1]) {
                    sum[sumTemp][1] = num;
                } else if (sum[sumTemp][1] >= sum[sumTemp][0] && num > sum[sumTemp][0]) {
                    sum[sumTemp][0] = num;
                }
            }
        }
        System.out.println(Arrays.deepToString(sum));

        if (flag) {
            return -1;
        }
        int max = -1;
        for (int[] i : sum) {
            if (i[0] == 0 || i[1] == 0) {
                continue;
            }
            max = Math.max(max, i[0] + i[1]);
        }
        return max;
    }


    /**
     * 11.21 每日一题
     * 2216. 美化数组的最少删除数
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if ((stack.size() - 1) % 2 == 0 && nums[i] == stack.peek()) {
                result++;
                continue;
            }
            stack.push(nums[i]);
        }
        if (stack.size() % 2 != 0) {
            result++;
        }
        return result;
    }


    /**
     * 11.22 每日一题
     * 2304. 网格中的最小路径代价
     *
     * @param grid
     * @param moveCost
     * @return
     */
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;

        //dp[i][j]是到第i行第j列的最小代价
        int[][] dp = new int[m][n];
        for (int[] temp : dp) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        dp[0] = grid[0];

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i : dp[m - 1]) {
            if (i < result) {
                result = i;
            }
        }

        return result;
    }

    /**
     * 11.23 每日一题
     * 1410. HTML 实体解析器
     *
     * @param text
     * @return
     */
    public String entityParser(String text) {
        return text.replaceAll("&quot;", "\"")
                .replaceAll("&apos;", "'")
                .replaceAll("&amp;", "&")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt", "<")
                .replaceAll("&frasl;", "/");
    }


    /**
     * 11.24 每日一题
     * 2824. 统计和小于目标的下标对数目
     *
     * @param nums
     * @param target
     * @return
     */
    public int countPairs(List<Integer> nums, int target) {
        int result = 0;

        Collections.sort(nums);
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            if (nums.get(left) + nums.get(right) < target) {
                result += right - left;
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

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
     * 11.25 每日一题
     * 1457. 二叉树中的伪回文路径
     *
     * @param root
     * @return
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] current = new int[10];
        return dsf(root, current);
    }

    public int dsf(TreeNode root, int[] counter) {
        if (root == null) {
            return 0;
        }
        counter[root.val]++;
        int res = 0;
        if (root.left == null && root.right == null) {
            if (isPseudoPalindrome(counter)) {
                return 1;
            }
        } else {
            res = dsf(root.left, counter) + dsf(root.right, counter);
        }
        counter[root.val]--;
        return res;
    }

    public boolean isPseudoPalindrome(int[] counter) {
        int odd = 0;
        for (int value : counter) {
            if (value % 2 == 1) {
                odd++;
            }
        }
        return odd <= 1;
    }


    /**
     * 11.26 每日一题
     * 828. 统计子串中的唯一字符
     *
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] l = new int[n], r = new int[n];
        int[] idx = new int[26];
        Arrays.fill(idx, -1);
        for (int i = 0; i < n; i++) {
            int u = cs[i] - 'A';
            l[i] = idx[u];
            idx[u] = i;
        }
        Arrays.fill(idx, n);
        for (int i = n - 1; i >= 0; i--) {
            int u = cs[i] - 'A';
            r[i] = idx[u];
            idx[u] = i;
        }
        for (int i = 0; i < n; i++) ans += (i - l[i]) * (r[i] - i);
        return ans;
    }

    /**
     * 11.27 每日一题
     * 907. 子数组的最小值之和
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = (int) 1e9 + 7;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] l = new int[arr.length];
        int[] r = new int[arr.length];
        int result = 0;
        Arrays.fill(l, -1);
        Arrays.fill(r, arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.getLast()] >= arr[i]) {
                r[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }
        while (!deque.isEmpty()) {
            deque.pop();
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && arr[deque.getLast()] > arr[i]) {
                l[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }

        for (int i = 0; i < arr.length; i++) {
            result += (long) (i - l[i]) * (r[i] - i) % MOD * arr[i] % MOD;
            result %= MOD;
        }

        return result;
    }

    /**
     * 11.29 每日一题
     */
    class SmallestInfiniteSet {
        boolean[] vis = new boolean[1010];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        int idx = 1;

        public int popSmallest() {
            int ans = -1;
            if (!q.isEmpty()) {
                ans = q.poll();
                vis[ans] = false;
            } else {
                ans = idx++;
            }
            return ans;
        }

        public void addBack(int x) {
            if (x >= idx || vis[x]) return;
            if (x == idx - 1) {
                idx--;
            } else {
                q.add(x);
                vis[x] = true;
            }
        }
    }


    /**
     * 11.30 每日一题
     * 1657. 确定两个字符串是否接近
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] array1 = new int[26];
        int[] array2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            array1[word1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < word2.length(); i++) {
            array2[word2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (array1[i] + array2[i] == 0) continue;
            if (array1[i] == 0 || array2[i] == 0) return false;
        }

        Arrays.sort(array1);
        Arrays.sort(array2);

        int count = 25;
        while (count > 0 && (array1[count] > 0 || array2[count] > 0)) {
            if (array1[count] != array2[count]) {
                return false;
            }
            count--;
        }

        return true;
    }

    /**
     * 12.01 每日一题
     * 2661. 找出叠涂元素
     *
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        int result = Integer.MAX_VALUE;

        for (int[] temp : mat) {
            int x = Integer.MIN_VALUE;
            for (int i : temp) {
                int value = map.get(i);
                if (x < value) {
                    x = value;
                }
            }
            if (x < result) {
                result = x;
            }
        }

        for (int i = 0; i < mat[0].length; i++) {
            int x = Integer.MIN_VALUE;
            for (int[] ints : mat) {
                int value = map.get(ints[i]);
                if (x < value) {
                    x = value;
                }
            }
            if (x < result) {
                result = x;
            }
        }

        return result;
    }


    /**
     * 12.02 每日一题
     * 1094. 拼车
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];

        for (int[] trip : trips) {
            nums[trip[1]] += trip[0];
            nums[trip[2]] -= trip[0];
        }

        for (int i = 1; i < 1001; i++) {
            nums[i] += nums[i - 1];
            if (nums[i] > capacity) {
                return false;
            }
        }
        return true;
    }


    /**
     * 12.03 每日一题
     * 1423. 可获得的最大点数
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        if (cardPoints.length == k) {
            for (int i : cardPoints) {
                sum += i;
            }
            return sum;
        }

        int[] left = new int[k];
        int[] right = new int[k];

        left[0] = cardPoints[0];
        for (int i = 1; i < k; i++) {
            left[i] = left[i - 1] + cardPoints[i];
        }

        int length = cardPoints.length - 1;
        right[0] = cardPoints[length];
        for (int i = 1; i < k; i++) {
            right[i] = right[i - 1] + cardPoints[length - i];
        }

        sum = left[k - 1];

        for (int i = 0; i <= k - 2; i++) {
            sum = Math.max(left[k - 2 - i] + right[i], sum);
        }

        sum = Math.max(sum, right[k - 1]);

        return sum;
    }


    /**
     * 12.04 每日一题
     * 1038. 从二叉搜索树到更大和树
     *
     * @param root
     * @return
     */
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        dfsToBstToGst(root);
        return root;
    }

    public void dfsToBstToGst(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsToBstToGst(root.right);
        sum += root.val;
        root.val = sum;
        dfsToBstToGst(root.left);
    }

    /**
     * 12.05 每日一题
     * 2477. 到达首都的最少油耗
     *
     * @param roads
     * @param seats
     * @return
     */
    long res = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length;
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] e : roads) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        dfs(0, -1, seats, g);
        return res;
    }

    public int dfs(int cur, int fa, int seats, List<Integer>[] g) {
        int peopleSum = 1;
        for (int ne : g[cur]) {
            if (ne != fa) {
                int peopleCnt = dfs(ne, cur, seats, g);
                peopleSum += peopleCnt;
                res += (peopleCnt + seats - 1) / seats;
            }
        }
        return peopleSum;
    }


    /**
     * 12.06 每日一题
     * 2646. 最小化旅行的价格总和
     *
     * @param n
     * @param edges
     * @param price
     * @param trips
     * @return
     */
    int end;
    int[][] myEdgs;
    int[] cnt, myPrice;
    List<Integer>[] g;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {

        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        cnt = new int[n];
        myEdgs = edges;

        for (int[] temp : trips) {
            temp[1] = end;
            dfsToMinimumTotalPrice(temp[0], -1);
        }

        this.myPrice = price;
        int[] res = dpToMinimumTotalPrice(0, -1);

        return Math.min(res[0], res[1]);
    }

    public boolean dfsToMinimumTotalPrice(int start, int fa) {
        if (end == start) {
            cnt[end]++;
            return true;
        }

        for (int y : g[start]) {
            if (y != fa && dfsToMinimumTotalPrice(y, start)) {
                cnt[start]++;
                return true;
            }
        }

        return false;
    }

    public int[] dpToMinimumTotalPrice(int start, int fa) {
        int no = myPrice[start] * cnt[start];
        int yes = myPrice[start] / 2 * cnt[start];

        for (int temp : g[start]) {
            if (temp != fa) {
                int[] res = dpToMinimumTotalPrice(temp, start);
                no = Math.min(no + res[0], no + res[1]);
                yes += res[0];
            }
        }

        return new int[]{no, yes};
    }


    /**
     * 12.07 每日一题
     * 1466. 重新规划路线
     *
     * @param n
     * @param connections
     * @return
     */
    int result = 0;
    List<int[]>[] path;
    boolean[] flag;

    int[][] myConnections;

    public int minReorder(int n, int[][] connections) {
        path = new ArrayList[n];
        Arrays.setAll(path, e -> new ArrayList<Integer>());
        flag = new boolean[n];
        myConnections = connections;

        for (int[] temp : connections) {
            //0 来 1 出发
            path[temp[0]].add(new int[]{temp[1], 1});
            path[temp[1]].add(new int[]{temp[0], 0});
        }

        dfsToMinReorder(0, -1);

        return result;
    }

    public void dfsToMinReorder(int start, int fa) {

        for (int[] temp : path[start]) {
            if (temp[0] != fa) {
                if (temp[1] == 1) {
                    result++;
                }
                dfsToMinReorder(temp[0], start);
            }
        }
    }


    /**
     * 12.08 每日一题
     * 2008. 出租车的最大盈利
     *
     * @param n
     * @param rides
     * @return
     */
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(o -> o[1]));

        int m = rides.length;
        long[] dp = new long[m];

        for (int i = 0; i < m; i++) {
            int j = binarySearch(rides, i, rides[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j] + rides[i][1] - rides[i][0] + rides[i][2]);
        }

        return dp[n];
    }

    public int binarySearch(int[][] rides, int high, int target) {
        int low = 0;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     * 12.09 每日一题
     * 2048. 下一个更大的数值平衡数
     *
     * @param n
     * @return
     */
    private static final int[] balance = new int[]{
            1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444,
            14444, 22333, 23233, 23323, 23332, 32233, 32323, 32332,
            33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555,
            122333, 123233, 123323, 123332, 132233, 132323, 132332,
            133223, 133232, 133322, 155555, 212333, 213233, 213323,
            213332, 221333, 223133, 223313, 223331, 224444, 231233,
            231323, 231332, 232133, 232313, 232331, 233123, 233132,
            233213, 233231, 233312, 233321, 242444, 244244, 244424,
            244442, 312233, 312323, 312332, 313223, 313232, 313322,
            321233, 321323, 321332, 322133, 322313, 322331, 323123,
            323132, 323213, 323231, 323312, 323321, 331223, 331232,
            331322, 332123, 332132, 332213, 332231, 332312, 332321,
            333122, 333212, 333221, 422444, 424244, 424424, 424442,
            442244, 442424, 442442, 444224, 444242, 444422, 515555,
            551555, 555155, 555515, 555551, 666666, 1224444
    };

    public int nextBeautifulNumber(int n) {
        int i = Arrays.binarySearch(balance, n + 1);
        if (i < 0) {
            i = -i - 1;
        }
        return balance[i];
    }


    /**
     * 12.10 每日一题
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= 2; j++) {
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 12.11 每日一题
     * 1631. 最小体力消耗路径
     *
     * @param heights
     * @return
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 12.12 每日一题
     * 2454. 下一个更大元素 IV
     *
     * @param nums
     * @return
     */
    public int[] secondGreaterElement(int[] nums) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        stack1.push(0);
        for (int i = 1; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();

            while (!stack2.isEmpty() && nums[i] > nums[stack2.peek()]) {
                int temp = stack2.pop();
                result[temp] = nums[i];
            }

            while (!stack1.isEmpty() && nums[i] > nums[stack1.peek()]) {
                int temp = stack1.pop();
                list.add(temp);
            }
            for (int j = list.size() - 1; j >= 0; j--) {
                stack2.push(list.get(j));
            }
            stack1.push(i);
        }

        return result;
    }

    /**
     * 12.13 每日一题
     * 2697. 字典序最小回文串
     *
     * @param s
     * @return
     */
    public String makeSmallestPalindrome(String s) {
        int startIndex = 0;
        int endIndex = s.length() - 1;
        char[] c = s.toCharArray();

        while (startIndex < endIndex) {
            if (c[startIndex] < c[endIndex]) {
                c[endIndex] = c[startIndex];
            } else if (c[startIndex] > c[endIndex]) {
                c[startIndex] = c[endIndex];
            }
            startIndex++;
            endIndex--;
        }

        return new String(c);
    }


    /**
     * 12.14 每日一题
     * 2132. 用邮票贴满网格图
     *
     * @param grid
     * @param stampHeight
     * @param stampWidth
     * @return
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        // 1. 计算 grid 的二维前缀和
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        System.out.println("============s========");
        for (int[] tempS : s) {
            System.out.println(Arrays.toString(tempS));
        }

        // 2. 计算二维差分,即判断是否可以放置印章，可以则更新二位差分数组
        // 为方便第 3 步的计算，在 d 数组的最上面和最左边各加了一行（列），所以下标要 +1
        int[][] diffs = new int[m + 2][n + 2];
        for (int i = stampHeight; i <= m; i++) {
            for (int j = stampWidth; j <= n; j++) {
                int tempI = i - stampHeight + 1;
                int tempJ = j - stampWidth + 1;

                int cnt = s[i][j] - s[i][tempJ - 1] - s[tempI - 1][j] + s[tempI - 1][tempJ - 1];
                if (cnt == 0) {
                    diffs[tempI][tempJ]++;
                    diffs[i + 1][tempJ]--;
                    diffs[tempI][j + 1]--;
                    diffs[i + 1][j + 1]++;
                }
            }
        }
        System.out.println("==========diffs============");
        for (int[] tempS : diffs) {
            System.out.println(Arrays.toString(tempS));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diffs[i + 1][j + 1] += diffs[i][j + 1] + diffs[i + 1][j] - diffs[i][j];
                if (grid[i][j] == 0 && diffs[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }

        System.out.println("==========diffs============");
        for (int[] tempS : diffs) {
            System.out.println(Arrays.toString(tempS));
        }

        return true;
    }

    /**
     * 12.15 每日一题
     * 2415. 反转二叉树的奇数层
     *
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        boolean isOdd = true;
        Queue<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            int s = deque.size();
            Stack<Integer> stack = new Stack<>();
            List<TreeNode> list = new ArrayList<>();

            for (int i = 0; i < s; i++) {
                TreeNode temp = deque.poll();
                if (temp.left != null && isOdd) {
                    list.add(temp);
                    stack.push(temp.left.val);
                    stack.push(temp.right.val);
                }
                if (temp.left != null) {
                    deque.offer(temp.left);
                    deque.offer(temp.right);
                }
            }
            if (isOdd && !stack.isEmpty()) {
                for (TreeNode t : list) {
                    t.left.val = stack.pop();
                    t.right.val = stack.pop();
                }
            }
            isOdd = !isOdd;
        }
        return root;
    }


    /**
     * 12.18 每日一题
     * 162. 寻找峰值
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = l + r >> 1;
            if(nums[mid] < nums[mid + 1]){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return r;
    }

}
