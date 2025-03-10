package org.ex;


import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    /**
     * 12.20 每日一题
     * 2828. 判别首字母缩略词
     *
     * @param words
     * @param s
     * @return
     */
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            stringBuilder.append(word.charAt(0));
        }
        return s.equals(stringBuilder.toString());
    }


    /**
     * 12.21 每日一题
     * 2866. 美丽塔 II
     *
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                stack1.pop();
            }
            if (stack1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = (long) (i - stack1.peek()) * maxHeights.get(i) + prefix[stack1.peek()];
            }
            stack1.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                stack2.pop();
            }
            if (stack2.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
            }
            stack2.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        return res;
    }


    /**
     * 12.22 每日一题
     * 1671. 得到山形数组的最少删除次数
     *
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray(nums);
        int[] reverse = reverse(nums);
        int[] suf = getLISArray(reverse);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (pre[i] > 1 && suf[n - 1 - i] > 1) {
                ans = Math.max(ans, pre[i] + suf[n - 1 - i] - 1);
            }
        }
        return n - ans;
    }

    public int[] getLISArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public int[] reverse(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }


    /**
     * 12.28 每日一题
     * 2735. 收集巧克力
     *
     * @param nums
     * @param x
     * @return
     */
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        long result = Integer.MAX_VALUE;
        int[] temp = new int[n];
        System.arraycopy(nums, 0, temp, 0, n);
        result = getSum(temp);
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                temp[i] = Math.min(temp[i], nums[(i + k) % n]);
            }
            result = Math.min(result, (long) k * x + getSum(temp));
        }
        return result;
    }

    public long getSum(int[] temp) {
        long sum = 0;
        for (int i : temp) {
            sum += i;
        }
        return sum;
    }


    /**
     * 12.29 每日一题
     * 2706. 购买两块巧克力
     *
     * @param prices
     * @param money
     * @return
     */
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        if (prices[0] + prices[1] > money) {
            return money;
        } else {
            return money - prices[0] - prices[1];
        }
    }


    /**
     * 02.26 每日一题
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    int sumBST = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        scand(root, low, high);
        return sumBST;
    }

    public void scand(TreeNode root, int low, int high) {
        int x = root.val;
        if (root.left != null) {
            scand(root.left, low, high);
        }
        if ((x > low || x == low) && (x < high || x == high)) {
            sumBST += x;
        }
        if (root.right != null) {
            scand(root.right, low, high);
        }
    }


    /**
     * 03.05 每日一题
     * 1976. 到达目的地的方案数
     *
     * @param n     路口数
     * @param roads roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路
     * @return 返回花费 最少时间 到达目的地的 路径数目
     */
    int N = 210, MOD = (int) 1e9 + 7;
    long INF = (long) 1e12;
    int[][] graph = new int[N][N];
    int[] in = new int[N];
    long[] dist = new long[N];
    boolean[] visted = new boolean[N];

    public int countPaths(int n, int[][] roads) {
        int[] dp = new int[n];

        //建立邻接矩阵
        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            int t = road[2];
            graph[x][y] = graph[y][x] = t;
        }

        //迪杰斯特拉得到0到每个点的最短距离
        dijkstra(n);

        //利用最短路径构建图
        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            int t = road[2];
            graph[x][y] = graph[y][x] = 0;
            if (dist[x] + t == dist[y]) {
                graph[x][y] = t;
                in[y]++;
            } else if (dist[y] + t == dist[x]) {
                graph[y][x] = t;
                in[x]++;
            }
        }

        // 跑拓扑排序统计方案数
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        dp[0] = 1;
        while (!deque.isEmpty()) {
            int x = deque.pollFirst();
            for (int i = 0; i < n; i++) {
                if (graph[x][i] == 0) {
                    continue;
                }
                dp[i] += dp[x];
                dp[i] %= MOD;
                in[i]--;
                if (in[i] == 0) {
                    deque.addLast(i);
                }
            }
        }

        return dp[n - 1];
    }

    public void dijkstra(int n) {
        Arrays.fill(dist, INF);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 0; j < n; j++) {
                if (!visted[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            visted[t] = true;
            for (int j = 0; j < n; j++) {
                if (graph[t][j] == 0) continue;
                dist[j] = Math.min(dist[j], dist[t] + graph[t][j]);
            }
        }
    }

    /**
     * 03.06 每日一题
     * 2917. 找出数组中的 K-or 值
     *
     * @param nums 数组
     * @param k    整数
     * @return i
     */
    public int findKOr(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < 31; i++) {
            int count = 0;
            for (int x : nums) {
                count += x >> i & 1;
            }
            if (count >= k) {
                result += 1 << i;
            }
        }
        return result;
    }


    /**
     * 03.07 每日一题
     * 2575. 找出字符串的可整除数组
     * ((a * 10) + b) mod x = (a * 10) mod x + b mod x
     *
     * @param word
     * @param m
     * @return
     */
    public int[] divisibilityArray(String word, int m) {
        int l = word.length();
        int[] result = new int[l];

        long cur = 0;
        for (int i = 0; i < l; i++) {
            char c = word.charAt(i);
            cur = (cur * 10 + c - '0') % m;

            if (cur == 0) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }


    /**
     * 03.08 每日一题
     * 2834. 找出美丽数组的最小和
     *
     * @param n
     * @param target
     * @return
     */
    public int minimumPossibleSum(int n, int target) {
        int x = target / 2;
        int result;
        if (n < x) {
            result = ((1 + x) * x / 2) % MOD;
        } else {
            result = ((1 + n) * n / 2) % MOD;
        }
        if (!(n > x)) {
            return result;
        }
        result += (n - x) * (target) + (n - x - 1) * (n - x) / 2;
        return result;
    }


    /**
     * 03.09 每日一题
     * 2386. 找出数组的第 K 大和
     *
     * @param nums
     * @param k
     * @return
     */
    int cntk;

    public long kSum(int[] nums, int k) {
        long sum = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
            right += nums[i];
        }
        Arrays.sort(nums);

        long left = -1;
        while (left + 1 < right) { // 开区间二分
            long mid = (left + right) / 2;
            cntk = k - 1; // 空子序列算一个
            dfs(0, mid, nums);
            if (cntk == 0) { // 找到 k 个元素和不超过 mid 的子序列
                right = mid;
            } else {
                left = mid;
            }
        }
        return sum - right;
    }

    // 反向递归，增加改成减少，这样可以少传一些参数
    private void dfs(int i, long s, int[] nums) {
        if (cntk == 0 || i == nums.length || s < nums[i]) {
            return;
        }
        cntk--;
        dfs(i + 1, s - nums[i], nums); // 选
        dfs(i + 1, s, nums); // 不选
    }

    /**
     * 03.11 每日一题
     * 2129. 将标题首字母大写
     *
     * @param title
     * @return
     */
    public String capitalizeTitle(String title) {
        int l = 0;
        int r = 1;
        int length = title.length();
        StringBuilder sb = new StringBuilder(title);

        while (r < length) {
            while (r < length && title.charAt(r) != ' ') {
                if (title.charAt(r) <= 'Z') {
                    sb.setCharAt(r, (char) (title.charAt(r) + 32));
                }
                r++;
            }

            if (r - l > 2 && sb.charAt(l) >= 'a') {
                sb.setCharAt(l, (char) (sb.charAt(l) - 32));
            }

            r++;
            l = r;
        }
        return sb.toString();
    }

//    public String capitalizeTitleByEasy(String title) {
//        StringBuilder stringBuilder = new StringBuilder(title);
//        for (String s : title.split(" ")) {
//            if (!stringBuilder.isEmpty()) {
//                stringBuilder.append(' ');
//            }
//            if (s.length() > 2) {
//                stringBuilder.append(s.substring(0, 1).toUpperCase());
//                s = s.substring(1);
//            }
//            stringBuilder.append(s.toLowerCase());
//        }
//        return stringBuilder.toString();
//    }

    /**
     * 1261. 在受污染的二叉树中查找元素
     */
    public class FindElements {
        private final Set<Integer> set = new HashSet<>();

        public FindElements(TreeNode root) {
            dfsToFindElements(root, 0);
        }

        public boolean find(int target) {
            return set.contains(target);
        }

        private void dfsToFindElements(TreeNode root, int x) {
            if (root == null) {
                return;
            }
            root.val = x;
            set.add(x);
            dfsToFindElements(root.left, 2 * x + 1);
            dfsToFindElements(root.right, 2 * x + 2);
        }
    }


    /**
     * 03.13
     * 2864. 最大二进制奇数
     *
     * @param s
     * @return
     */
    public String maximumOddBinaryNumber(String s) {
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        return "1".repeat(count - 1) + "0".repeat(length - count) + "1";
    }


    /**
     * 03.14
     * 2789. 合并后数组中的最大元素
     *
     * @param nums
     * @return
     */
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long result = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= result) {
                result += nums[i];
            } else {
                result = nums[i];
            }
        }
        return result;
    }


    /**
     * 03.15 每日一题
     * 2312. 卖木头块
     *
     * @param m
     * @param n
     * @param prices
     * @return
     */
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];

        for (int[] temp : prices) {
            dp[temp[0]][temp[1]] = temp[2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //垂直切割
                for (int k = 1; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - k] + dp[i][k]);
                }
                //水平切割
                for (int k = 1; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - k][j] + dp[k][j]);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 03.16 每日一题
     * 2684. 矩阵中移动的最大次数
     *
     * @param grid
     * @return
     */
    int[][] next = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
    int maxX = 0;
    int maxY = 0;
    int max = 0;

    public int maxMoves(int[][] grid) {
        maxX = grid.length;
        maxY = grid[0].length;
        for (int i = 0; i < maxY; i++) {
            dfsToMaxMoves(grid, 0, i);
        }
        return max;
    }

    public void dfsToMaxMoves(int[][] grid, int x, int y) {
        max = Math.max(max, y);
        for (int[] temp : next) {
            int nextX = x + temp[0];
            int nextY = y + temp[1];
            if (nextX >= 0 && nextX < maxX && nextY >= 0 && nextY < maxY && grid[nextX][nextY] > grid[x][y]) {
                grid[nextX][nextY] = 0;
                dfsToMaxMoves(grid, nextX, nextY);
            }
        }
    }


    /**
     * 03.17 每日一题
     * 310. 最小高度树
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        /*循环条件当然是经典的不空判断*/
        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 03.18 每日一题
     * 303. 区域和检索 - 数组不可变
     */
    class NumArray {
        private int[] array;

        public NumArray(int[] nums) {
            int n = nums.length;
            array = new int[n];
            array[0] = nums[0];
            for (int i = 1; i < n; i++) {
                array[i] = array[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return array[right];
            } else {
                return array[right] - array[left - 1];
            }
        }
    }


    /**
     * 03.18 前缀和扩展
     * 2602. 使数组元素全部相等的最少操作次数
     *
     * @param nums
     * @param queries
     * @return
     */
    public List<Long> minOperations(int[] nums, int[] queries) {
        List<Long> list = new ArrayList<>();
        int n = nums.length;

        quickSort(nums, 0, n - 1);
        long[] array = new long[n + 1];
        array[0] = 0;
        for (int i = 0; i < n; i++) {
            array[i + 1] = array[i] + nums[i];
        }

        for (int x : queries) {
            int i = -1;
            int j = n + 1;
            while (i + 1 < j) {
                int mid = i + (j - i) / 2;
                if (nums[mid] <= x) {
                    j = mid;
                } else {
                    i = mid;
                }
            }
            list.add(Math.abs((long) x * j - array[j]) + Math.abs(array[n] - array[j] - ((long) x * (n - j))));
        }
        return list;
    }

    public void quickSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int i = l;
        int j = r;
        while (i < j) {
            while (i < j && nums[j] >= nums[l]) {
                j--;
            }
            while (i < j && nums[i] <= nums[l]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, l);
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 03.19 每日一题
     * 1793. 好子数组的最大分数
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumScore(int[] nums, int k) {
        int l = k;
        int r = k;
        int n = nums.length;

        int minNumber = nums[k];
        int result = minNumber;
        l--;
        r++;

        for (int i = 0; i < n; i++) {
            if (r >= n - 1 || (l > 0 && nums[l - 1] > nums[r + 1])) {
                l--;
                minNumber = Math.min(minNumber, nums[l]);
            } else {
                r++;
                minNumber = Math.min(minNumber, nums[r]);
            }
            result = Math.max(result, minNumber * (r - l + 1));
        }
        return result;

    }

    public int maximumScoreByStack(int[] nums, int k) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stack.isEmpty() && x <= nums[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stack.isEmpty() && x <= nums[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int ans = nums[k];
        for (int i = 0; i < n; i++) {
            int l = left[i];
            int r = right[i];
            int number = nums[i];
            if (l < k && r > k) {
                ans = Math.max(ans, number * (r - l - 1));
            }
        }
        return ans;
    }


    /**
     * 03.20 每日一题
     * 1969. 数组元素的最小非零乘积
     *
     * @param p
     * @return
     */
    public int minNonZeroProduct(int p) {
        long result = 1;
        long max = (1L << p) - 1;
        result = max % MOD * pow(max - 1, p - 1) % MOD;
        return (int) result % MOD;
    }

    public long pow(long x, int y) {
        x %= MOD;
        long sum = 1;
        for (int i = 0; i < y; i++) {
            sum = sum * x % MOD;
            x = x * x % MOD;
        }
        return sum;
    }

    /**
     * 每日一题扩展 快速幂
     * 50. Pow(x, n)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1d;
        }
        if (x == 0) {
            return 0d;
        }
        long b = n;
        if (n < 0) {
            x = 1 / x;
            b *= -1;
        }

        double sum = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                sum *= x;
            }
            x *= x;
            b = b >> 1;
        }
        return sum;
    }


    /**
     * 03.21 每日一题
     * 2671. 频率跟踪器
     */
    public class FrequencyTracker {
        private Map<Integer, Integer> map;
        private Map<Integer, Integer> mapCount;

        public FrequencyTracker() {
            map = new HashMap<>();
            mapCount = new HashMap<>();
        }

        public void add(int number) {
            int count = map.getOrDefault(number, 0) + 1;
            if (count - 1 != 0) {
                mapCount.put(count - 1, mapCount.getOrDefault(count - 1, 0) - 1);
            }
            mapCount.put(count, mapCount.getOrDefault(count, 0) + 1);
            map.put(number, count);
        }

        public void deleteOne(int number) {
            if (map.containsKey(number)) {
                int count = map.get(number);
                mapCount.put(count, mapCount.get(count) - 1);
                if (count - 1 == 0) {
                    map.remove(number);
                } else {
                    map.put(number, count);
                    count--;
                    mapCount.put(count, mapCount.getOrDefault(count, 0) + 1);
                }
            }
        }

        public boolean hasFrequency(int frequency) {
            return mapCount.getOrDefault(frequency, 0) > 0;
        }
    }


    /**
     * 03.22
     * 2617. 网格图中最少访问的格子数
     *
     * @param grid
     * @return
     */
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<int[]>[] colHeads = new PriorityQueue[n];
        Arrays.setAll(colHeads, i -> new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        PriorityQueue<int[]> rowH = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int f = 0;

        for (int i = 0; i < m; i++) {
            rowH.clear();
            for (int j = 0; j < n; j++) {
                while (!rowH.isEmpty() && rowH.peek()[1] < j) {
                    rowH.poll();
                }

                PriorityQueue<int[]> colH = colHeads[j];
                while (!colH.isEmpty() && colH.peek()[1] < i) {
                    colH.poll();
                }

                f = i > 0 || j > 0 ? Integer.MAX_VALUE : 1;

                if (!rowH.isEmpty()) {
                    f = rowH.peek()[0] + 1;
                }
                if (!colH.isEmpty()) {
                    f = Math.min(f, colH.peek()[0] + 1);
                }

                int g = grid[i][j];
                if (g > 0 && f < Integer.MAX_VALUE) {
                    rowH.offer(new int[]{f, g + j});
                    colH.offer(new int[]{f, g + i});
                }
            }
        }
        return f < Integer.MAX_VALUE ? f : -1;
    }


    /**
     * 03.23
     * 2549. 统计桌面上的不同数字
     *
     * @param n
     * @return
     */
    public int distinctIntegers(int n) {
        return Math.max(n - 1, 1);
    }


    /**
     * 03.25
     * 518. 零钱兑换 II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 0;

        for (int x : coins) {
            for (int i = x; i < amount + 1; i++) {
                dp[i] = Math.min(dp[i], dp[i - x] + 1);
            }
        }
        return dp[amount];
    }


    /**
     * 03.26
     * 2642. 设计可以求最短路径的图类
     */
    public class Graph {
        // 防止更新最短路时加法溢出
        private static final int INF = Integer.MAX_VALUE / 2;
        int[][] graph;

        public Graph(int n, int[][] edges) {
            graph = new int[n][n];
            for (int[] row : graph) {
                Arrays.fill(row, INF);
            }

            for (int i = 0; i < n; i++) {
                graph[i][i] = 0;
            }

            for (int[] edge : edges) {
                addEdge(edge);
            }
        }

        public void addEdge(int[] edge) {
            graph[edge[0]][edge[1]] = edge[2];
        }

        public int shortestPath(int node1, int node2) {
            int n = graph.length;
            int[] dist = new int[n];
            boolean[] visited = new boolean[n];
            Arrays.fill(dist, INF);
            dist[node1] = 0;
            Arrays.fill(visited, false);
            visited[node1] = true;

            while (true) {
                //找到未访问过的最小节点
                int x = -1;
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && (x == -1 || dist[i] < dist[x])) {
                        x = i;
                    }
                }

                //节点全访问
                if (x < 0 || dist[x] == INF) {
                    return -1;
                }

                //到达目的节点
                if (x == node2) {
                    return dist[x];
                }

                //更新
                visited[x] = true;
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        dist[j] = Math.min(dist[j], dist[x] + graph[x][j]);
                    }
                }
            }
        }
    }

    public class GraphByStack {
        List<int[]>[] graph;

        public GraphByStack(int n, int[][] edges) {
            graph = new ArrayList[n];
            Arrays.setAll(graph, i -> new ArrayList<>());
            for (int[] e : edges) {
                addEdge(e);
            }
        }

        public void addEdge(int[] edge) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        public int shortestPath(int node1, int node2) {
            int[] dist = new int[graph.length];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[node1] = 0;
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            queue.offer(new int[]{0, node1});

            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int d = temp[0];
                int x = temp[1];

                if (x == node2) {
                    return d;
                }

                //x 之前出堆过
                if (d > dist[x]) {
                    continue;
                }

                for (int[] e : graph[x]) {
                    int nextD = e[1];
                    int nextX = e[0];
                    if (nextD + d < dist[nextX]) {
                        dist[nextX] = nextD + d;
                        queue.offer(new int[]{dist[nextX], nextX});
                    }
                }
            }
            return -1;
        }
    }

    public class GraphByFloyd {
        private static final int INF = Integer.MAX_VALUE / 3;
        int[][] graph;

        public GraphByFloyd(int n, int[][] edges) {
            graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0;
            }

            for (int[] e : edges) {
                graph[e[0]][e[1]] = e[2];
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        public void addEdge(int[] edge) {
            int x = edge[0];
            int y = edge[1];
            int d = edge[2];
            int n = graph.length;

            if (d > graph[x][y]) {
                return;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][x] + d + graph[y][j]);
                }
            }
        }

        public int shortestPath(int node1, int node2) {
            int ans = graph[node1][node2];
            return ans < INF ? ans : -1;
        }
    }


    /**
     * 03.27
     * 2580. 统计将重叠区间合并成组的方案数
     *
     * @param ranges
     * @return
     */
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (o1, o2) -> o1[0] - o2[0]);
        int currentMax = -1;
        int MOD = 1000000007;
        int result = 1;
        for (int[] range : ranges) {
            if (currentMax < range[0]) {
                result = result * 2 % MOD;
            }
            currentMax = Math.max(range[1], currentMax);
        }


        return result;
    }


    /**
     * 03.28
     * 1997. 访问完所有房间的第一天
     *
     * @param nextVisit
     * @return
     */
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 0);

        for (int i = 0; i < n - 1; i++) {
            int j = nextVisit[i];
            dp[i + 1] = (2 + dp[i] - dp[j] + dp[i] + MOD) % MOD;
        }
        return (int) dp[n - 1];
    }


    /**
     * 03.29
     * 2908. 元素和最小的山形三元组 I
     *
     * @param nums
     * @return
     */
    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        pre[0] = Integer.MAX_VALUE;
        suf[n - 1] = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            pre[i] = Math.min(pre[i - 1], nums[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.min(suf[i + 1], nums[i + 1]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (pre[i] < nums[i] && suf[i] < nums[i]) {
                ans = Math.min(ans, pre[i] + nums[i] + suf[i]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * 03.30
     * 2952. 需要添加的硬币的最小数量
     *
     * @param coins
     * @param target
     * @return
     */
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int s = 1;
        int ans = 0;
        int i = 0;
        while (s <= target) {
            if (i < coins.length && coins[i] <= s) {
                s += coins[i++];
            } else {
                ans++;
                s *= 2;
            }
        }
        return ans;
    }


    /**
     * 03.31
     * 331. 验证二叉树的前序序列化
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        String[] arrays = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (String temp : arrays) {
            while (!stack.isEmpty() && Objects.equals(stack.peek(), "#")) {
                if (Objects.equals(temp, "#")) {
                    stack.pop();
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
            }
            stack.push(temp);
        }
        return stack.size() == 1 && Objects.equals(stack.peek(), "#");
    }


    /**
     * 04.01
     * 2810. 故障键盘
     *
     * @param s
     * @return
     */
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'i') {
                sb.reverse();
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    /**
     * 04.02
     * 894. 所有可能的真二叉树
     *
     * @param n
     * @return
     */
    private static List<TreeNode>[] f = new ArrayList[11];

    {
        Arrays.setAll(f, i -> new ArrayList<>());

        for (int i = 2; i < 11; i++) {
            for (int j = 1; j < i; j++) {
                for (TreeNode left : f[j]) {
                    for (TreeNode right : f[i - j]) {
                        f[i].add(new TreeNode(0, left, right));
                    }
                }
            }
        }
    }

    public List<TreeNode> allPossibleFBT(int n) {
        return f[n / 2 == 0 ? 0 : (n + 1) / 2];
    }


    /**
     * 1379. 找出克隆二叉树中的相同节点
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return dfsToGetTargetCopy(original, cloned, target);
    }

    private TreeNode dfsToGetTargetCopy(TreeNode original, TreeNode root, TreeNode target) {

        if (original == target) {
            return root;
        }

        if (root.left != null) {
            TreeNode res = dfsToGetTargetCopy(original.left, root.left, target);
            if (res != null) {
                return res;
            }
        }

        if (root.right != null) {
            TreeNode res = dfsToGetTargetCopy(original.left, root.right, target);
            if (res != null) {
                return res;
            }
        }

        return null;
    }


    /**
     * 04.04
     * 2192. 有向无环图中一个节点的所有祖先
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        for (int[] e : edges) {
            g[e[1]].add(e[0]);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            boolean[] vis = new boolean[n];
            Arrays.fill(vis, false);

            dfsToGetAncestors(i, g, vis);
            vis[i] = false;

            for (int j = 0; j < n; j++) {
                if (vis[j]) temp.add(j);
            }

            ans.add(temp);
        }

        return ans;
    }

    private void dfsToGetAncestors(int i, List<Integer>[] g, boolean[] vis) {
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j]) {
                dfsToGetAncestors(j, g, vis);
            }
        }
    }


    /**
     * 04.05
     * 1026. 节点与其祖先之间的最大差值
     *
     * @param root
     * @return
     */
    int maxAncestor = 0;

    public int maxAncestorDiff(TreeNode root) {
        int min = root.val;
        int max = root.val;

        dfsToMaxAncestorDiff(root.left, min, max);
        dfsToMaxAncestorDiff(root.right, min, max);

        return maxAncestor;
    }

    private void dfsToMaxAncestorDiff(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }
        maxAncestor = Math.max(maxAncestor, Math.max(Math.abs(min - root.val), Math.abs(max - root.val)));
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);

        dfsToMaxAncestorDiff(root.left, min, max);
        dfsToMaxAncestorDiff(root.right, min, max);
    }


    /**
     * 04.06
     * 1483. 树节点的第 K 个祖先
     */
    class TreeAncestor {
        private int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n);
            pa = new int[n][m];

            for (int i = 0; i < n; i++) {
                pa[i][0] = parent[i];
            }

            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int p = pa[j][i - 1];
                    pa[j][i] = p < 0 ? -1 : pa[p][i - 1];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k);
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) > 0) {
                    node = pa[node][i];
                    if (node < 0) {
                        break;
                    }
                }
            }
            return node;
        }
    }

    /**
     * 04.07
     * 1600. 王位继承顺序
     */
    class ThroneInheritance {
        class Node {
            String name;
            Node next;
            Node last; // 记录最后一个儿子
            boolean isDeleted = false;

            Node(String _name) {
                name = _name;
            }
        }

        Map<String, Node> map = new HashMap<>();
        Node head = new Node(""), tail = new Node("");

        public ThroneInheritance(String name) {
            Node root = new Node(name);
            root.next = tail;
            head.next = root;
            map.put(name, root);
        }

        public void birth(String pname, String cname) {
            Node node = new Node(cname);
            map.put(cname, node);
            Node p = map.get(pname);
            Node tmp = p;
            while (tmp.last != null) tmp = tmp.last;
            node.next = tmp.next;
            tmp.next = node;
            p.last = node;
        }

        public void death(String name) {
            Node node = map.get(name);
            node.isDeleted = true;
        }

        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            Node tmp = head.next;
            while (tmp.next != null) {
                if (!tmp.isDeleted) ans.add(tmp.name);
                tmp = tmp.next;
            }
            return ans;
        }
    }


    /**
     * 04.08
     * 2009. 使数组连续的最少操作数
     *
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }

        int left = 0;
        int ans = 0;

        for (int i = 1; i < m; i++) {
            if (nums[i] - nums[left] > n - 1) {
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return n - ans;
    }


    /**
     * 04.09
     * 2529. 正整数和负整数的最大计数
     *
     * @param nums
     * @return
     */
    public int maximumCount(int[] nums) {
        int neg = lowerBound(nums, 0);
        int pos = nums.length - lowerBound(nums, 1);
        return Math.max(neg, pos);
    }

    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 此时 left 等于 right - 1
        // 因为 nums[right - 1] < target 且 nums[right] >= target，所以答案是 right
        return right;
    }


    /**
     * 04.10
     * 1702. 修改后的最大二进制字符串
     *
     * @param binary
     * @return
     */
    public String maximumBinaryString(String binary) {
        int i = binary.indexOf('0');
        if (i < 0) { // binary 全是 '1'
            return binary;
        }
        char[] s = binary.toCharArray();
        int cnt1 = 0;
        for (i++; i < s.length; i++) {
            cnt1 += s[i] - '0'; // 统计 [i, n-1] 中 '1' 的个数
        }
        return "1".repeat(s.length - 1 - cnt1) + '0' + "1".repeat(cnt1);
    }


    /**
     * 04.11
     * 1766. 互质树
     *
     * @param nums
     * @param edges
     * @return
     */
    static int MAX = 51;
    static int[][] coprime = new int[MAX][MAX];

    //预处理
    static {
        for (int i = 1; i < MAX; i++) {
            int k = 0;
            for (int j = 1; j < MAX; j++) {
                if (gcd(i, j) == 1) {
                    coprime[i][k++] = j;
                }
            }
        }
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int[] valDepth = new int[MAX];
        int[] valNodeId = new int[MAX];
        dfsToGetCoprimes(0, -1, g, 1, nums, ans, valDepth, valNodeId);
        return ans;
    }

    private void dfsToGetCoprimes(int x, int fa, List<Integer>[] g, int depth, int[] nums, int[] ans, int[] valDepth, int[] valNodeId) {
        int num = nums[x];

        List<Integer> list = g[x];

        int maxDepth = 0;
        for (int j : coprime[num]) {
            if (j == 0) {
                break;
            }
            if (valDepth[j] > maxDepth) {
                maxDepth = valDepth[j];
                ans[x] = valNodeId[j];
            }
        }

        int tempValDepth = valDepth[num];
        int tempValNodeId = valNodeId[num];

        valDepth[num] = depth;
        valNodeId[num] = x;

        for (int i : list) {
            if (i != fa) dfsToGetCoprimes(i, x, g, depth + 1, nums, ans, valDepth, valNodeId);
        }

        valDepth[num] = tempValDepth;
        valNodeId[num] = tempValNodeId;
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }


    /**
     * 04.12
     * 2923. 找到冠军 I
     *
     * @param grid
     * @return
     */
    public int findChampion(int[][] grid) {
        int ans = 0;
        int n = grid.length;

        for (int i = 1; i < n; i++) {
            if (grid[i][ans] == 1) {
                ans = i;
            }
        }

        return ans;
    }


    /**
     * 2924. 找到冠军 II
     *
     * @param n
     * @param edges
     * @return
     */
    public int findChampion(int n, int[][] edges) {
        boolean[] result = new boolean[n];
        Arrays.fill(result, true);


        for (int[] edge : edges) {
            result[edge[1]] = false;
        }

        int count = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (result[i]) {
                ans = i;
                count++;
            }
            if (count >= 2) {
                return -1;
            }
        }
        return ans;
    }


    /**
     * 04.14
     * 705. 设计哈希集合
     */
    public class MyHashSet {
        int[] bs = new int[40000];

        public MyHashSet() {
        }

        public void add(int key) {
            int buckIndex = key / 32;
            int bitIndex = key % 32;
            setVal(buckIndex, bitIndex, true);
        }

        public void remove(int key) {
            int buckIndex = key / 32;
            int bitIndex = key % 32;
            setVal(buckIndex, bitIndex, false);

        }

        public boolean contains(int key) {
            int buckIndex = key / 32;
            int bitIndex = key % 32;
            return getVal(buckIndex, bitIndex);
        }

        private void setVal(int buckIndex, int bitIndex, boolean val) {
            if (val) {
                int i = bs[buckIndex] | (1 << bitIndex);
                bs[buckIndex] = i;
            } else {
                int i = bs[buckIndex] & (~(1 << bitIndex));
                bs[buckIndex] = i;
            }
        }

        private boolean getVal(int buckIndex, int bitIndex) {
            int i = bs[buckIndex] >> bitIndex & 1;
            return i == 1;
        }
    }


    /**
     * 04.15
     * 706. 设计哈希映射
     */
    class MyHashMap {
        class Node {
            int key, value;
            Node next;

            Node(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        // 由于使用的是「链表」，这个值可以取得很小
        Node[] nodes = new Node[10009];

        public void put(int key, int value) {
            // 根据 key 获取哈希桶的位置
            int idx = getIndex(key);
            // 判断链表中是否已经存在
            Node loc = nodes[idx], tmp = loc;
            if (loc != null) {
                Node prev = null;
                while (tmp != null) {
                    if (tmp.key == key) {
                        tmp.value = value;
                        return;
                    }
                    prev = tmp;
                    tmp = tmp.next;
                }
                tmp = prev;
            }
            Node node = new Node(key, value);

            // 头插法
            // node.next = loc;
            // nodes[idx] = node;

            // 尾插法
            if (tmp != null) {
                tmp.next = node;
            } else {
                nodes[idx] = node;
            }
        }

        public void remove(int key) {
            int idx = getIndex(key);
            Node loc = nodes[idx];
            if (loc != null) {
                Node prev = null;
                while (loc != null) {
                    if (loc.key == key) {
                        if (prev != null) {
                            prev.next = loc.next;
                        } else {
                            nodes[idx] = loc.next;
                        }
                        return;
                    }
                    prev = loc;
                    loc = loc.next;
                }
            }
        }

        public int get(int key) {
            int idx = getIndex(key);
            Node loc = nodes[idx];
            if (loc != null) {
                while (loc != null) {
                    if (loc.key == key) {
                        return loc.value;
                    }
                    loc = loc.next;
                }
            }
            return -1;
        }

        int getIndex(int key) {
            // 因为 nodes 的长度只有 10009，对应的十进制的 10011100011001（总长度为 32 位，其余高位都是 0）
            // 为了让 key 对应的 hash 高位也参与运算，这里对 hashCode 进行右移异或
            // 使得 hashCode 的高位随机性和低位随机性都能体现在低 16 位中
            int hash = Integer.hashCode(key);
            hash ^= (hash >>> 16);
            return hash % nodes.length;
        }
    }


    /**
     * 924. 尽量减少恶意软件的传播
     *
     * @param graph
     * @param initial
     * @return
     */
    int nodeId;
    int size;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        boolean[] isInitial = new boolean[n];
        int mn = Integer.MAX_VALUE;
        for (int x : initial) {
            isInitial[x] = true;
            mn = Math.min(mn, x);
        }

        int ans = -1;
        int maxSize = 0;
        for (int i : initial) {
            if (vis[i]) {
                continue;
            }
            nodeId = -1;
            size = 0;
            dfsToMinMalwareSpread(i, graph, vis, isInitial);

            if (nodeId > 0) {
                if (maxSize < size) {
                    ans = nodeId;
                } else if (maxSize == size && nodeId < ans) {
                    ans = nodeId;
                }
            }
        }

        return ans < 0 ? mn : ans;
    }

    private void dfsToMinMalwareSpread(int x, int[][] graph, boolean[] vis, boolean[] isInitial) {
        vis[x] = true;
        size++;

        if (nodeId != -2 && isInitial[x]) {
            nodeId = nodeId == -1 ? x : -2;
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[x][i] == 1 && !vis[i]) {
                dfsToMinMalwareSpread(i, graph, vis, isInitial);
            }
        }
    }


    /**
     * 2007. 从双倍数组中还原原数组
     *
     * @param changed
     * @return
     */
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1 || n == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int x : changed) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        Arrays.sort(changed);

        boolean flag = true;
        int[] ans = new int[n];
        int count = 0;
        for (int x : changed) {
            if (map.containsKey(x)) {
                if (map.containsKey(x * 2)) {
                    map.put(x, map.get(x) - 1);
                    map.put(x * 2, map.get(x * 2) - 1);
                    if (map.get(x) == 0) map.remove(x);
                    if (map.get(x * 2) == 0) map.remove(x * 2);
                    ans[count++] = x;
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            return Arrays.copyOfRange(ans, 0, n / 2);
        }

        return new int[0];
    }


    /**
     * 04.19
     * 1883. 准时抵达会议现场的最小跳过休息次数
     *
     * @param dist
     * @param speed
     * @param hoursBefore
     * @return
     */
    // 可忽略误差
    static final double EPS = 1e-8;
    // 极大值
    static final double INFTY = 1e20;

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double[][] f = new double[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], INFTY);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j != i) {
                    f[i][j] = Math.min(f[i][j], Math.ceil(f[i - 1][j] + (double) dist[i - 1] / speed - EPS));
                }
                if (j != 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + (double) (dist[i - 1] / speed));
                }
            }
        }
        for (int j = 0; j <= n; ++j) {
            if (f[n][j] < hoursBefore + EPS) {
                return j;
            }
        }
        return -1;
    }


    /**
     * 02.23
     * 1052. 爱生气的书店老板
     *
     * @param customers
     * @param grumpy
     * @param minutes
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int[] s = new int[2];
        int ans1 = Integer.MIN_VALUE;

        for (int i = 0; i < customers.length; i++) {
            s[grumpy[i]] += customers[i];
            if (i < minutes - 1) {
                continue;
            }
            ans1 = Math.max(ans1, s[1]);

            s[grumpy[i]] -= grumpy[i - minutes - 1] == 1 ? customers[i - minutes - 1] : 0;
        }

        return s[0] + ans1;
    }


    /**
     * 4.24
     * 2385. 感染二叉树需要的总时间
     *
     * @param root
     * @param start
     * @return
     */
    private TreeNode startNode;
    private final Map<TreeNode, TreeNode> fa = new HashMap<>();

    public int amountOfTime1(TreeNode root, int start) {
        dfs(root, null, start);
        return maxDepth(startNode, startNode);
    }

    private void dfs(TreeNode node, TreeNode from, int start) {
        if (node == null) {
            return;
        }
        fa.put(node, from); // 记录每个节点的父节点
        if (node.val == start) {
            startNode = node; // 找到 start
        }
        dfs(node.left, node, start);
        dfs(node.right, node, start);
    }

    private int maxDepth(TreeNode node, TreeNode from) {
        if (node == null) {
            return -1; // 注意这里是 -1，因为 start 的深度为 0
        }
        int res = -1;
        if (node.left != from) {
            res = Math.max(res, maxDepth(node.left, node));
        }
        if (node.right != from) {
            res = Math.max(res, maxDepth(node.right, node));
        }
        if (fa.get(node) != from) {
            res = Math.max(res, maxDepth(fa.get(node), node));
        }
        return res + 1;
    }

    /**
     * @param root
     * @param start
     * @return
     */
    private int ans;

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, start);
        return ans;
    }

    private int dfs(TreeNode node, int start) {
        if (node == null) {
            return 0;
        }
        int lLen = dfs(node.left, start);
        int rLen = dfs(node.right, start);
        if (node.val == start) {
            ans = -Math.min(lLen, rLen);
            return 1;
        }
        if (lLen > 0 || rLen > 0) {
            ans = Math.max(ans, Math.abs(lLen) + Math.abs(rLen));
            return Math.max(lLen, rLen) + 1;
        }

        return Math.min(lLen, rLen) - 1;
    }


    /**
     * 04.25
     * 2739. 总行驶距离
     *
     * @param mainTank
     * @param additionalTank
     * @return
     */
    public int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0;

        while (mainTank > 0) {
            if (mainTank >= 5) {
                ans += 50;
                mainTank -= 5;
                if (additionalTank > 0) {
                    mainTank++;
                    additionalTank--;
                }
            } else {
                ans += mainTank * 10;
                break;
            }
        }

        return ans;
    }


    /**
     * 04.26
     * 1146. 快照数组
     */
    public class SnapshotArray {
        int curSnapId;
        int[] array;

        Map<Integer, List<int[]>> history;

        public SnapshotArray(int length) {
            curSnapId = 0;
            history = new HashMap<>();
        }

        public void set(int index, int val) {
            history.computeIfAbsent(index, i -> new ArrayList<>()).add(new int[]{curSnapId, val});
        }

        public int snap() {
            curSnapId++;
            return curSnapId - 1;
        }

        public int get(int index, int snap_id) {
            List<int[]> list = history.get(index);
            int i = search(list, snap_id);
            return list.get(i)[1];
        }

        private int search(List<int[]> list, int snapId) {
            int left = -1;
            int right = list.size();

            while (left + 1 < right) {
                int mid = left + (right - left) / 2;

                if (list.get(mid)[0] < snapId) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

    /**
     * 04.28
     * 1017. 负二进制转换
     *
     * @param n
     * @return
     */
    public String baseNeg2(int n) {
        if (n == 0 || n == 1) {
            return String.valueOf(n);
        }
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            int remainder = n & 1;
            res.append(remainder);
            n -= remainder;
            n /= -2;
        }
        return res.reverse().toString();
    }


    /**
     * 4.29
     * 1329. 将矩阵按对角线排序
     *
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] a = new int[Math.min(n, m)];

        int sum = m + n - 1;
        int startX = n - 1;
        int startY = 0;
        for (int i = 0; i < sum; i++) {
            int tempX = startX;
            int tempY = startY;
            int count = 0;
            Arrays.fill(a, Integer.MAX_VALUE);

            while (tempX < n && tempY < m) {
                a[count++] = mat[tempX][tempY];
                tempX++;
                tempY++;
            }
            Arrays.sort(a);
            tempX = startX;
            tempY = startY;
            count = 0;
            while (tempX < n && tempY < m) {
                mat[tempX][tempY] = a[count++];
                tempX++;
                tempY++;
            }

            if (startX == 0) {
                startY++;
            } else {
                startX--;
            }
        }
        return mat;
    }


    /**
     * 05.04
     * 1235. 规划兼职工作
     *
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[] dp = new int[n + 1];
        int[][] job = new int[n][];

        for (int i = 0; i < n; i++) {
            job[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(job, (a, b) -> a[1] - b[1]);

        for (int i = 0; i < n; i++) {
            int j = search(job, i, job[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j + 1] + job[i][2]);
        }
        return n;
    }

    private int search(int[][] jobs, int right, int upper) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (jobs[mid][1] <= upper) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }


    /**
     * 05.05
     * 1652. 拆炸弹
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) return result;
        int[] sum = new int[2 * n];

        for (int i = 1; i <= 2 * n; i++) {
            sum[i] = sum[i - 1] + code[(i - 1) % n];
        }

        for (int i = 1; i <= n; i++) {
            if (k < 0) {
                result[i - 1] = sum[i - 1 + n] - sum[i - 1 + n - k];
            } else {
                result[i - 1] = sum[i + k] - sum[i];
            }
        }
        return result;
    }


    /**
     * 05.06
     * 741. 摘樱桃
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] f = new int[n + 1][n + 1];
        for (int[] temp : f) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        f[1][1] = grid[0][0];
        for (int t = 1; t < n * 2 - 1; t++) {
            for (int j = Math.min(t, n - 1); j >= Math.max(t - n + 1, 0); j--) {
                for (int k = Math.min(t, n - 1); k >= j; k--) {
                    if (grid[t - j][j] < 0 || grid[t - k][k] < 0) {
                        f[j + 1][k + 1] = Integer.MIN_VALUE;
                    } else {
                        f[j + 1][k + 1] = Math.max(Math.max(f[j + 1][k + 1], f[j + 1][k]), Math.max(f[j][k + 1], f[j][k])) +
                                grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
                    }
                }
            }
        }
        return Math.max(f[n][n], 0);
    }


    /**
     * 05.08
     * 2079. 给植物浇水
     *
     * @param plants
     * @param capacity
     * @return
     */
    public int wateringPlants(int[] plants, int capacity) {
        int cur = capacity;
        int ans = 0;
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] < cur) {
                ans += 2 * i + 1;
            } else {
                cur -= plants[i];
                ans += 1;
            }
        }
        return ans;
    }


    /**
     * 05.09
     * 2105. 给植物浇水 II
     *
     * @param plants
     * @param capacityA
     * @param capacityB
     * @return
     */
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        boolean flag = n % 2 == 0;
        int mid = n / 2;
        int ans = 0;
        int curA = capacityA;
        int curB = capacityB;

        for (int i = 0; i < mid; i++) {
            if (plants[i] > curA) {
                curA = capacityA;
                ans++;
            }
            curA -= plants[i];
        }

        if (flag) mid--;
        for (int i = n - 1; i > mid; i--) {
            if (plants[i] > curB) {
                curB = capacityB;
                ans++;
            }
            curB -= plants[i];
        }

        if (curA < plants[mid] && curB < plants[mid]) ans++;

        return ans;
    }


    /**
     * 05.10
     * 2960. 统计已测试设备
     *
     * @param batteryPercentages
     * @return
     */
    public int countTestedDevices(int[] batteryPercentages) {
        int ans = 0;

        for (int i : batteryPercentages) {
            if (i - ans > 0) {
                ans++;
            }
        }

        return ans;
    }


    /**
     * 05.11
     * 2391. 收集垃圾的最少总时间
     *
     * @param garbage
     * @param travel
     * @return
     */
    public int garbageCollection(String[] garbage, int[] travel) {
        char[] cs = new char[]{'M', 'P', 'G'};
        int n = garbage.length;

        int ans = 0;
        for (char c : cs) {
            int temp = 0;
            for (int i = 0; i < n; i++) {
                char[] s = garbage[i].toCharArray();
                for (char cur : s) {
                    if (cur == c) {
                        temp++;
                    }
                }
                if (i != 0) temp += travel[i - 1];
            }
            for (int i = n - 1; i > 0; i--) {
                char[] s = garbage[i].toCharArray();
                boolean flag = false;
                for (char cur : s) {
                    if (cur == c) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    temp -= travel[i - 1];
                } else {
                    break;
                }
            }
            ans += temp;
        }
        return ans;
    }


    /**
     * 05.12
     * 1553. 吃掉 N 个橘子的最少天数
     *
     * @param n
     * @return
     */
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {

        if (n <= 1) {
            return n;
        }
        Integer res = memo.get(n);
        if (res != null) { // 之前计算过
            return res;
        }
        res = Math.min(minDays(n / 2) + n % 2, minDays(n / 3) + n % 3) + 1;
        memo.put(n, res); // 记忆化
        return res;
    }


    /**
     * 05.14
     * 2244. 完成所有任务需要的最少轮数
     *
     * @param tasks
     * @return
     */
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for (int i : tasks) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            int count = entry.getValue();
            if (count == 1) return -1;
            ans += count / 3;
            ans += count % 3 == 0 ? 0 : 1;
        }
        return ans;
    }


    /**
     * 05.15
     * 2589. 完成所有任务的最少时间
     *
     * @param tasks
     * @return
     */
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int n = tasks.length;
        int ans = 0;
        int max = tasks[n - 1][1];
        boolean[] run = new boolean[max + 1];

        for (int[] cur : tasks) {
            int work = cur[2];
            int end = cur[1];
            int start = cur[0];

            for (int i = start; i <= end; i++) {
                if (run[i]) work--;
            }
            for (int i = end; work > 0; i--) {
                if (!run[i]) {
                    work--;
                    run[i] = true;
                    ans++;
                }
            }
        }
        return ans;
    }


    /**
     * 05.16
     * 1953. 你可以工作的最大周数
     */
    class Solution {
        public long numberOfWeeks(int[] milestones) {
            long s = 0;
            int m = 0;
            for (int x : milestones) {
                s += x;
                m = Math.max(m, x);
            }
            return m > s - m + 1 ? (s - m) * 2 + 1 : s;
        }
    }


    /**
     * 05.18
     * 2644. 找出可整除性得分最大的整数
     *
     * @param nums
     * @param divisors
     * @return
     */
    public int maxDivScore(int[] nums, int[] divisors) {
        int ans = Integer.MAX_VALUE;
        int maxS = 0;

        for (int i : divisors) {
            int s = 0;
            for (int j : nums) {
                if (j % i == 0) s++;
            }
            if (s == maxS) ans = Math.min(ans, i);
            if (s > maxS) {
                maxS = s;
                ans = i;
            }
        }
        return ans;
    }


    /**
     * 05.19
     * 1535. 找出数组游戏的赢家
     *
     * @param arr
     * @param k
     * @return
     */
    public int getWinner(int[] arr, int k) {
        int mx = arr[0];
        int win = 0;
        for (int i = 1; i < arr.length && win < k; i++) {
            if (arr[i] > mx) { // 新的最大值
                mx = arr[i];
                win = 0;
            }
            win++; // 获胜回合 +1
        }
        return mx;
    }


    /**
     * 05.20
     * 1542. 找出最长地超赞子字符串
     *
     * @param s
     * @return
     */
    private static final int D = 10; // s 中的字符种类数

    public int longestAwesome(String s) {
        int n = s.length();
        int[] pos = new int[1 << D];
        Arrays.fill(pos, n); // n 表示没有找到异或前缀和
        pos[0] = -1; // pre[-1] = 0
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            pre ^= 1 << (s.charAt(i) - '0');
            for (int d = 0; d < D; d++) {
                ans = Math.max(ans, i - pos[pre ^ (1 << d)]); // 奇数
            }
            ans = Math.max(ans, i - pos[pre]); // 偶数
            if (pos[pre] == n) { // 首次遇到值为 pre 的前缀异或和，记录其下标 i
                pos[pre] = i;
            }
        }
        return ans;
    }

    /**
     * 05.21
     * 2769. 找出最大的可达成数字
     *
     * @param num
     * @param t
     * @return
     */
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }


    /**
     * 05.22
     * 2225. 找出输掉零场或一场比赛的玩家
     *
     * @param matches
     * @return
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ans = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for (int[] i : matches) {
            if (map.containsKey(i[1])) {
                map.put(i[1], map.get(i[1]) + 1);
            } else {
                map.put(i[1], 1);
            }
            if (!map.containsKey(i[0])) map.put(i[0], 0);
        }

        List<Integer> losers = new ArrayList<>();
        List<Integer> wins = new ArrayList<>();
        ans.add(wins);
        ans.add(losers);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) losers.add(entry.getKey());
            if (entry.getValue() == 0) wins.add(entry.getKey());
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));

        return ans;
    }


    /**
     * 05.23
     * 2831. 找出最长等值子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        List<Integer>[] lists = new List[n + 1];
        Arrays.setAll(lists, i -> new ArrayList<>());

        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            lists[x].add(i);
        }
        int ans = Integer.MIN_VALUE;
        for (List<Integer> temp : lists) {
            if (temp.size() <= ans) continue;

            int left = 0;
            for (int right = 0; right < temp.size(); right++) {
                if (temp.get(right) - temp.get(left) - (right - left) > k) {
                    left++;
                }
                ans = Math.max(right - left + 1, ans);
            }
        }
        return ans;
    }


    /**
     * 05.24
     * 1673. 找出最具竞争力的子序列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(nums[i]);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > nums[i] && k - stack.size() <= (n - 1 - i)) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }


    /**
     * 05.25
     * 2903. 找出满足差值条件的下标 I
     *
     * @param nums
     * @param indexDifference
     * @param valueDifference
     * @return
     */
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;

        for (int i = n - 1; i >= indexDifference; i--) {
            for (int j = i - indexDifference; j >= 0; j--) {
                if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * 05.26
     * 1738. 找出第 K 大的异或坐标值
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        List<Integer> list = new ArrayList<>();

        dp[0][0] = matrix[0][0];
        list.add(dp[0][0]);
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] ^ matrix[0][i];
            list.add(dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] ^ matrix[i][0];
            list.add(dp[i][0]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] ^ dp[i - 1][j] ^ dp[i][j - 1];
                list.add(dp[i][j]);
            }
        }

        list.sort((a, b) -> b - a);
        return list.get(k - 1);
    }


    /**
     * 05.27
     * 2028. 找出缺失的观测数据
     *
     * @param rolls
     * @param mean
     * @param n
     * @return
     */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int allSum = (n + m) * mean;
        for (int i : rolls) {
            allSum -= i;
        }
        int[] ans = new int[n];
        if (n * 6 < allSum || n > allSum) return ans;

        Arrays.fill(ans, allSum / n);
        allSum %= n;

        int count = 0;
        while (allSum > 0) {
            ans[count]++;
            allSum--;
            count++;
            count %= n;
        }
        return ans;
    }


    /**
     * 05.28
     * 2951. 找出峰值
     *
     * @param mountain
     * @return
     */
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i < mountain.length - 1; i++) {
            int cur = mountain[i];
            if (mountain[i - 1] < cur && mountain[i + 1] < cur) {
                ans.add(i);
            }
        }
        return ans;
    }


    /**
     * 05.29
     * 2981. 找出出现至少三次的最长特殊子字符串 I
     *
     * @param s
     * @return
     */
    public int maximumLength(String s) {
        List<Integer>[] lists = new List[26];
        Arrays.setAll(lists, i -> new ArrayList<>());

        int i = 1;
        char pre = s.charAt(0);
        int n = s.length();
        int count = 1;

        while (n > i) {
            while (i < n && s.charAt(i) == pre) {
                count++;
                i++;
            }
            lists[pre - 'a'].add(count);
            if (i != n) pre = s.charAt(i);
            i++;
            count = 1;
        }

        int ans = 0;
        for (List<Integer> list : lists) {
            if (list.isEmpty()) continue;
            list.sort((a, b) -> b - a);
            list.add(0);
            list.add(0);

            ans = Math.max(ans,
                    Math.max(list.get(0) - 2,
                            Math.max(list.get(2),
                                    Math.min(list.get(0) - 1, list.get(1)))));
        }
        return ans > 0 ? ans : -1;
    }


    /**
     * 05.31
     * 2965. 找出缺失和重复的数字
     *
     * @param grid
     * @return
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int xorAll = 0;
        for (int[] row : grid) {
            for (int x : row) {
                xorAll ^= x;
            }
        }

        int sum = ((n * n + 1) * (n * n)) / 2;
        int ans1 = 0;

        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                int cur = ints[j];
                if ((xorAll & cur) == 0) {
                    ans1 = cur;
                } else {
                    sum -= cur;
                }

            }
        }

        return new int[]{ans1, sum + ans1};
    }


    /**
     * 06.01
     * 2928. 给小朋友们分糖果 I
     *
     * @param n
     * @param limit
     * @return
     */
    public int distributeCandies(int n, int limit) {
        int ans = 0;
        for (int i = 0; i <= limit; i++) {
            int temp = n - i;
            if (temp > 2 * limit) continue;
            if (temp < 0) break;
            int con = temp / 2 + 1;

            ans += Math.min(temp, limit) * 2;
            if (temp % 2 == 0) ans--;
        }
        return ans;
    }


    /**
     * 06.02
     * 575. 分糖果
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();

        for (int i : candyType) {
            set.add(i);
        }

        return Math.min(set.size(), n / 2);
    }


    /**
     * 06.03
     * 1103. 分糖果 II
     *
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandiesII(int candies, int num_people) {
        int[] ans = new int[num_people];
        int m = (int) ((Math.sqrt(8.0 * candies + 1) - 1) / 2);
        int k = m / num_people;
        int e = m % num_people;

        for (int i = 0; i < num_people; i++) {
            ans[i] = (k * (k - 1) / 2 * num_people + (k) * (i + 1)) +
                    e > i ? k * num_people + i + 1 : 0;
        }

        return ans;
    }


    /**
     * 06.04
     * 3067. 在带权树网络中统计可连接服务器对数目
     *
     * @param edges
     * @param signalSpeed
     * @return
     */
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] tree = new ArrayList[n];
        Arrays.setAll(tree, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            tree[x].add(new int[]{y, w});
            tree[y].add(new int[]{x, w});
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int[] e : tree[i]) {
                int cnt = dfsToCountPairsOfConnectableServers(e[0], i, e[1], tree, signalSpeed);
                ans[i] += cnt * sum;
                sum += cnt;
            }
        }

        return ans;
    }

    private int dfsToCountPairsOfConnectableServers(int cur, int pre, int pathNum, List<int[]>[] tree, int signalSpeed) {
        int cnt = pathNum % signalSpeed == 0 ? 1 : 0;
        for (int[] temp : tree[cur]) {
            int next = temp[0];
            if (next != pre) {
                cnt += dfsToCountPairsOfConnectableServers(next, cur, pathNum + temp[1], tree, signalSpeed);
            }
        }
        return cnt;
    }


    /**
     * 06.06
     * 2938. 区分黑球与白球
     *
     * @param s
     * @return
     */
    public long minimumSteps(String s) {
        char[] cs = s.toCharArray();
        int zero = cs.length - 1;
        int one = 0;
        long ans = 0;

        while (zero > one) {
            while (zero > one && cs[one] != '1') {
                one++;
            }
            while (zero > one && cs[zero] != '0') {
                zero--;
            }
            ans += zero - one;
            zero--;
            one++;
        }

        return ans;
    }


    public int maxOperations(int[] nums) {
        int sum = nums[0] + nums[1];
        int ans = 1;

        int cur = 3;
        while (cur < nums.length) {
            if (nums[cur - 1] + nums[cur] == sum) ans++;
            cur += 2;
        }
        return ans;
    }


    /**
     * 06.10
     * 881. 救生艇
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int right = people.length - 1;
        int left = 0;
        int ans = 0;

        while (right > left) {
            if (people[right] + people[left] <= limit) {
                right--;
                left++;
            } else {
                right--;
            }
            ans++;
        }
        return right == left ? ans + 1 : ans;
    }


    /**
     * 06.11
     * 419. 甲板上的战舰
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 'X') continue;
                if ((i - 1 < 0 || board[i - 1][j] != 'X') && (j - 1 < 0 || board[i][j - 1] != 'X')) {
                    ans++;
                }
            }
        }
        return ans;
    }


    /**
     * 06.12
     * 2806. 取整购买后的账户余额
     *
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        return 100 - (((purchaseAmount / 10) * 10) + ((purchaseAmount % 10) > 4 ? 10 : 0));
    }


    /**
     * 06.17
     * 522. 最长特殊序列 II
     *
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        Arrays.sort(strs, (a, b) -> b.length() - a.length());

        next:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isSubString(strs[i], strs[j])) {
                    continue next;
                }
            }
            return strs[i].length();
        }
        return -1;
    }

    private boolean isSubString(String s, String t) {
        int i = 0;
        for (char c : t.toCharArray()) {
            if (s.charAt(i) == c && ++i == s.length()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 06.18
     * 2288. 价格减免
     *
     * @param sentence
     * @param discount
     * @return
     */
    public String discountPrices(String sentence, int discount) {
        String[] ss = sentence.split(" ");
        double d = 1 - discount / 100.0;

        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            if (s.charAt(0) != '$' || !check(s.substring(1))) continue;
            ss[i] = String.format("$%.2f", Long.parseLong(s.substring(1)) * d);
        }

        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private boolean check(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 06.19
     * 2713. 矩阵中严格递增的单元格数
     *
     * @param mat
     * @return
     */
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] rowMax = new int[m];
        int[] colMax = new int[n];

        Map<Integer, List<int[]>> g = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        int ans = 0;
        for (List<int[]> pos : g.values()) {
            int[] mx = new int[pos.size()];
            for (int k = 0; k < pos.size(); k++) {
                int[] p = pos.get(k);
                int i = p[0];
                int j = p[1];
                mx[k] = Math.max(rowMax[i], colMax[j]) + 1;
                ans = Math.max(ans, mx[k]);
            }
            for (int k = 0; k < pos.size(); k++) {
                int[] p = pos.get(k);
                int i = p[0];
                int j = p[1];
                rowMax[i] = Math.max(rowMax[i], mx[k]);
                colMax[i] = Math.max(colMax[j], mx[k]);
            }
        }
        return ans;
    }


    /**
     * 06.20
     * 2748. 美丽下标对的数目
     *
     * @param nums
     * @return
     */
    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        int[] count = new int[10];

        for (int x : nums) {
            for (int z = 1; z < 10; z++) {
                if (count[z] > 0 && gcd(z, x % 10) == 1) {
                    ans++;
                }
            }
            while (x >= 10) {
                x /= 10;
            }
            count[x]++;
        }

        return ans;
    }


    /**
     * 06.21
     * LCP 61. 气温变化趋势
     *
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0;
        int start = 0;

        for (int i = 1; i < temperatureA.length; i++) {
            if (tab(temperatureA[i - 1], temperatureA[i]) != tab(temperatureB[i - 1], temperatureB[i])) {
                ans = Math.max(ans, i - start - 1);
                start = i;
            }
        }
        if (start != temperatureA.length - 1) {
            ans = Math.max(ans, temperatureA.length - start - 1);
        }
        return ans;
    }

    private int tab(int pre, int cur) {
        if (cur - pre == 0) return 0;
        if (cur - pre > 0) return 1;
        return -1;
    }


    /**
     * 07.15
     * 721. 账户合并
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailToIdx = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                emailToIdx.computeIfAbsent(accounts.get(i).get(j), x -> new ArrayList<>()).add(i);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        boolean[] vis = new boolean[accounts.size()];
        Set<String> emailSet = new HashSet<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (vis[i]) {
                continue;
            }
            emailSet.clear();
            dfs(i, accounts, emailToIdx, vis, emailSet);

            List<String> list = new ArrayList<>(emailSet);
            Collections.sort(list);
            list.add(0, accounts.get(i).get(0));
            ans.add(list);
        }
        return ans;
    }

    private void dfs(int i,
                     List<List<String>> accounts,
                     Map<String, List<Integer>> emailToIdx,
                     boolean[] vis,
                     Set<String> emailSet) {
        vis[i] = true;

        for (int k = 1; k < accounts.get(i).size(); k++) {
            String email = accounts.get(i).get(k);
            if (emailSet.contains(email)) {
                continue;
            }
            emailSet.add(email);
            for (int j : emailToIdx.get(email)) {
                if (!vis[j]) {
                    dfs(j, accounts, emailToIdx, vis, emailSet);
                }
            }
        }
    }


    /**
     * 07.16
     * 2956. 找到两个数组中的公共元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] ans = new int[2];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int j : nums1) {
            set1.add(j);
        }
        for (int j : nums2) {
            set2.add(j);
        }
        for (int j : nums1) {
            if (set2.contains(j)) {
                ans[0]++;
            }
        }
        for (int j : nums2) {
            if (set1.contains(j)) {
                ans[1]++;
            }
        }

        return ans;
    }


    /**
     * 07.17
     * 2959. 关闭分部的可行集合数目
     *
     * @param n
     * @param maxDistance
     * @param roads
     * @return
     */
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] g = new int[n][n];

        for (int[] row : g) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        for (int[] e : roads) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x][y] = Math.min(g[x][y], wt);
            g[y][x] = Math.min(g[y][x], wt);
        }

        int ans = 1; // s=0 一定满足要求
        int[][][] f = new int[1 << n][n][n];
        for (int[][] matrix : f) {
            for (int[] row : matrix) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }
        f[0] = g;

        for (int s = 1; s < (1 << n); s++) {
            int k = Integer.numberOfTrailingZeros(s);
            int t = s ^ (1 << k);

            boolean ok = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[s][i][j] = Math.min(f[t][i][j], f[t][i][k] + f[t][k][j]);
                    if (ok && j < i && (s >> i & 1) != 0 && (s >> j & 1) != 0 && f[s][i][j] > maxDistance) {
                        ok = false;
                    }
                }
            }
            ans += ok ? 1 : 0;
        }

        return ans;
    }


    /**
     * 07.29
     * 682. 棒球比赛
     *
     * @param operations
     * @return
     */
    public int calPoints(String[] operations) {
        List<Integer> list = new ArrayList<>();

        for (String s : operations) {
            if (Objects.equals(s, "C")) {
                list.remove(list.size() - 1);
                continue;
            }
            if (Objects.equals(s, "D")) {
                list.add(list.get(list.size() - 1) * 2);
                continue;
            }
            if (Objects.equals(s, "+")) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
                continue;
            }
            list.add(Integer.parseInt(s));
        }

        int ans = 0;
        for (Integer i : list) {
            ans += i;
        }
        return ans;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 2024.09.09
     * 2181.合并零之间的节点
     *
     * @param head
     * @return
     */
    public ListNode mergeNodes(ListNode head) {
        ListNode result = head;
        ListNode current = head.next;
        while (current != null) {
            if (current.val != 0) {
                head.val += current.val;
            } else {
                if (current.next != null) {
                    head.next = current;

                }
            }
            current = current.next;
        }
        head.next = null;
        return result;
    }

    /**
     * 2024.09.10
     * 2552.统计上升四元组
     *
     * @param nums
     * @return
     */
    public long countQuadruplets(int[] nums) {
        long cnt4 = 0;

        long[] cnt3 = new long[nums.length];
        for (int l = 2; l < nums.length; l++) {
            int cnt2 = 0;
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    //记录 j < l && nums[j] < nums[l]
                    cnt4 += cnt3[j];

                    //记录 i < k && nums[i] < nums[k]
                    cnt2++;
                } else {
                    //记录  j < k && nums[j] > nums[k]
                    cnt3[j] += cnt2;
                }
            }
        }
        return cnt4;
    }


    /**
     * 2024.09.11
     * 2555.两个线段获得的最多奖品
     *
     * @param prizePositions
     * @param k
     * @return
     */
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;

        if (k * 2 + 1 >= prizePositions[n - 1] - prizePositions[0]) {
            return n;
        }

        int ans = 0;
        int left = 0;
        int[] mx = new int[n + 1];

        for (int right = 0; right < n; right++) {
            while (prizePositions[right] - prizePositions[left] > k) {
                //缩小第二个线段的滑动窗口
                left++;
            }

            ans = Math.max(ans, mx[left] + right - left + 1);
            mx[right + 1] = Math.max(mx[right], right - left + 1);
        }

        return ans;
    }

    /**
     * 2024.09.12
     * 2576.求出最多标记下标
     *
     * @param nums
     * @return
     */
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;

        for (int j = (n + 1) / 2; j < n; j++) {
            if (nums[i] * 2 <= nums[j]) {
                i++;
            }
        }
        return i * 2;
    }


    /**
     * 2024.09.14
     * 2390.从字符串中移除星号
     *
     * @param s
     * @return
     */
    public String removeStars(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c == '*') {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            } else {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 2024.09.19
     * 2414. 最长的字母序连续子字符串的长度
     *
     * @param s
     * @return
     */
    public int longestContinuousSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        if (n == 1) {
            return 1;
        }
        int left = 0;
        int right = 1;
        int result = 0;

        while (right < n && left < n) {
            if (chars[right] - chars[right - 1] != 1) {
                result = Math.max(result, right - left);
                left = right;
            }
            right++;
        }
        result = Math.max(result, right - left);
        return result;
    }

    /**
     * 2024/9/14晚上七点，美团第六场笔试第二题
     * 链接：https://leetcode.cn/circle/discuss/2nbtCZ/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param a
     * @param b
     * @param c
     * @param x
     * @param y
     * @return
     */
    public int maxTao(int a, int b, int c, int x, int y) {
        int left = 0;
        int right = Math.max(a, Math.max(b, c));

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(a, b, c, x, y, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private boolean check(int a, int b, int c, int x, int y, int mid) {
        if (a < mid) {
            return false;
        }
        int p1 = a - mid;
        if (b + p1 / x < mid) {
            return false;
        }
        int p2 = b + p1 / x - mid;
        if (c + p2 / y < mid) {
            return false;
        }
        return true;
    }


    /**
     * 2024.09.20
     * 2376. 统计特殊整数
     *
     * @param n
     * @return
     */
    public int countSpecialNumbers(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int[][] memo = new int[s.length][1 << 10];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfsToCountSpecialNumbers(0, 0, true, false, s, memo);
    }

    private int dfsToCountSpecialNumbers(int i, int mask, boolean isLimit, boolean isNum, char[] s, int[][] memo) {
        if (i == s.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;

        //跳过当前数位
        if (!isNum) {
            res = dfsToCountSpecialNumbers(i + 1, mask, false, false, s, memo);
        }

        //不跳过
        int up = isLimit ? s[i] - '0' : 9;

        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if (((mask >> d & 1) == 0)) {
                res += dfsToCountSpecialNumbers(i + 1, mask | (1 << d), isLimit && d == up, true, s, memo);
            }
        }

        if (!isLimit && isNum) {
            memo[i][mask] = res;
        }

        return res;
    }

    /**
     * 357. 统计各位数字都不同的数字个数
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        int[][] memo = new int[n + 1][1 << 10];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfsToCountNumbersWithUniqueDigits(0, 0, n, false, memo);
    }

    private int dfsToCountNumbersWithUniqueDigits(int i, int mask, int n, boolean isNum, int[][] memo) {
        if (i == n) {
            return isNum ? 1 : 0;
        }
        if (isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }

        int res = 0;
        if (!isNum) {
            res = dfsToCountNumbersWithUniqueDigits(i + 1, mask, n, false, memo);
        }

        for (int d = isNum || i == n - 1 ? 0 : 1; d <= 9; d++) {
            if ((mask >> d & 1) == 0) {
                res +=
                        dfsToCountNumbersWithUniqueDigits(
                                i + 1, mask | (d << 1), n, true, memo);
            }
        }

        if (isNum) {
            memo[i][mask] = res;
        }

        return res;
    }

    /**
     * 2024.09.21
     * 2374. 边积分最高的节点
     *
     * @param edges
     * @return
     */
    public int edgeScore(int[] edges) {
        int ans = 0;
        long[] score = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            int to = edges[i];
            score[to] += i;

            if (score[to] > score[ans] || (score[to] == score[ans] && to > ans)) {
                ans = to;
            }
        }

        return ans;
    }

    /**
     * 1884. 鸡蛋掉落-两枚鸡蛋
     *
     * @param n
     * @return
     */
    private static final int[] f1 = new int[1001];

    static {
        for (int i = 1; i < 1001; i++) {
            f1[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                f1[i] = Math.min(f1[i], Math.max(j, f1[i - j] + 1));
            }
        }
    }

    public int twoEggDrop(int n) {
        return f1[n];
    }

    /**
     * 2024.10.14
     * 887. 鸡蛋掉落
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; ; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + 1;
            }
            if (dp[i][k] >= n) {
                return i;
            }
        }
    }

    /**
     * 2024.10.20
     * 908. 最小差值 I
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        int max = -1;
        int min = 10_001;

        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        return Math.max(max - min - 2 * k, 0);
    }

    public int smallestRangeII(int[] nums, int k) {
        int max = -1;
        int min = 10_001;

        for (int x : nums) {
            max = Math.max(max, x);
        }

        for (int x : nums) {
            if (x < max) {
                max = Math.max(max, x + k);
                min = Math.min(min, x + k);
            } else {
                max = Math.max(max, x - k);
                min = Math.min(min, x - k);
            }
        }

        return Math.max(max - min, 0);
    }


    /**
     * 2024.12.10 每日一题
     * 935. 骑士拨号器
     *
     * @param n
     * @return
     */
    public int knightDialer(int n) {
        int MOD = 1_000_000_007;
        Map<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{4, 6});
        map.put(1, new int[]{6, 8});
        map.put(2, new int[]{7, 9});
        map.put(3, new int[]{4, 8});
        map.put(4, new int[]{0, 3, 9});
        map.put(6, new int[]{0, 1, 7});
        map.put(7, new int[]{2, 6});
        map.put(8, new int[]{1, 3});
        map.put(9, new int[]{2, 4});

        long[][] dp = new long[n][10];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0);
        }
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) continue;
                int[] temp = map.get(j);
                for (int x : temp) {
                    dp[i][x] += dp[i - 1][j];
                    dp[i][x] %= MOD;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[n - 1][i];
            ans %= MOD;
        }
        return (int) ans;
    }

    /**
     * 2024.12.12
     * 2931. 购买物品的最大开销
     *
     * @param values
     * @return
     */
    public long maxSpending(int[][] values) {
        int m = values.length;
        int n = values[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> values[a[0]][a[1]] - values[b[0]][b[1]]);

        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, n - 1});
        }

        long ans = 0;
        for (int d = 1; d <= m * n; d++) {
            int[] p = pq.poll();
            int i = p[0];
            int j = p[1];

            ans += (long) values[i][j] * d;

            if (j > 0) pq.offer(new int[]{i, j - 1});
        }

        return ans;
    }


    /**
     * 2024.12.13
     * 3264. K 次乘运算后的最终数组 I
     *
     * @param nums
     * @param k
     * @param multiplier
     * @return
     */
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    index = j;
                }
            }
            nums[index] *= multiplier;
            min = Integer.MAX_VALUE;
        }

        return nums;
    }


    /**
     * 2024.12.17
     *
     * @param words
     * @param target
     * @return
     */
    public int minValidStrings(String[] words, String target) {
        char[] t = target.toCharArray();
        int n = target.length();

        // 多项式字符串哈希（方便计算子串哈希值）
        // 哈希函数 hash(s) = s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-2] * base + s[n-1]
        final int BASE = (int) 8e8 + new Random().nextInt((int) 1e8); // 随机 base，防止 hack
        int[] powBase = new int[n + 1]; // powBase[i] = base^i
        int[] preHash = new int[n + 1]; // 前缀哈希值 preHash[i] = hash(target[0] 到 target[i-1])
        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * BASE % MOD);
            preHash[i + 1] = (int) (((long) preHash[i] * BASE + t[i]) % MOD); // 秦九韶算法计算多项式哈希
        }

        int maxLen = 0;
        for (String w : words) {
            maxLen = Math.max(maxLen, w.length());
        }
        Set<Integer>[] sets = new HashSet[maxLen];
        Arrays.setAll(sets, i -> new HashSet<>());
        for (String w : words) {
            long h = 0;
            for (int j = 0; j < w.length(); j++) {
                h = (h * BASE + w.charAt(j)) % MOD;
                sets[j].add((int) h); // 注意 j 从 0 开始
            }
        }

        int ans = 0;
        int curR = 0; // 已建造的桥的右端点
        int nxtR = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < n; i++) {
            while (nxtR < n && nxtR - i < maxLen && sets[nxtR - i].contains(subHash(i, nxtR + 1, powBase, preHash))) {
                nxtR++;
            }
            if (i == curR) { // 到达已建造的桥的右端点
                if (i == nxtR) { // 无论怎么造桥，都无法从 i 到 i+1
                    return -1;
                }
                curR = nxtR; // 造一座桥
                ans++;
            }
        }
        return ans;
    }

    private int subHash(int l, int r, int[] powBase, int[] preHash) {
        return (int) ((((long) preHash[r] - (long) preHash[l] * powBase[r - l]) % MOD + MOD) % MOD);
    }


    /**
     * 3218. 切蛋糕的最小总开销 I
     *
     * @param m
     * @param n
     * @param horizontalCut
     * @param verticalCut
     * @return
     */
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int ans = 0;

        PriorityQueue<Integer> horizontalCutQueue = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> verticalCutQueue = new PriorityQueue<>((a, b) -> b - a);
        int horizontalSum = 0;
        int verticalSum = 0;
        for (int x : horizontalCut) {
            horizontalCutQueue.offer(x);
            horizontalSum += x;
        }

        for (int x : verticalCut) {
            verticalCutQueue.offer(x);
            verticalSum += x;
        }

        while (!(horizontalCutQueue.isEmpty() && verticalCutQueue.isEmpty())) {
            if (horizontalCutQueue.isEmpty()) {
                while (!verticalCutQueue.isEmpty()) {
                    ans += verticalCutQueue.poll();
                }
                break;
            }
            if (verticalCutQueue.isEmpty()) {
                while (!horizontalCutQueue.isEmpty()) {
                    ans += horizontalCutQueue.poll();
                }
                break;
            }
            if (horizontalSum > verticalSum) {
                ans += horizontalCutQueue.poll();
                PriorityQueue<Integer> temp = new PriorityQueue<>((a, b) -> b - a);
                horizontalSum = 0;
                while (!verticalCutQueue.isEmpty()) {
                    int x = verticalCutQueue.poll();
                    temp.offer(x * 2);
                    horizontalSum += x * 2;
                }
                verticalCutQueue = temp;
            } else {
                ans += verticalCutQueue.poll();
                PriorityQueue<Integer> temp = new PriorityQueue<>((a, b) -> b - a);
                verticalSum = 0;
                while (!horizontalCutQueue.isEmpty()) {
                    int x = horizontalCutQueue.poll();
                    temp.offer(x * 2);
                    verticalSum += x * 2;
                }
                horizontalCutQueue = temp;
            }
        }
        return ans;
    }


    /**
     * 2024.12.29
     * 1366. 通过投票对团队排名
     *
     * @param votes
     * @return
     */
    public String rankTeams(String[] votes) {
        int m = votes[0].length();
        int[][] cnts = new int[26][m];
        for (String vote : votes) {
            for (int i = 0; i < m; i++) {
                cnts[vote.charAt(i) - 'A'][i]++;
            }
        }

        return votes[0].chars()
                .mapToObj(c -> (char) c)
                .sorted((a, b) -> {
                    int c = Arrays.compare(cnts[b - 'A'], cnts[a - 'A']);
                    return c != 0 ? c : a - b;
                })
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


    /**
     * 2024.12.30
     * 1367. 二叉树中的链表
     *
     * @param head
     * @param root
     * @return
     */
    static ListNode tempHead;

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        tempHead = head;
        return dfs(head, root);
    }

    public boolean dfs(ListNode listNode, TreeNode root) {
        if (listNode == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        return dfs(root.val != listNode.val ? tempHead : listNode.next, root.left) ||
                dfs(root.val != listNode.val ? tempHead : listNode.next, root.right);
    }


    /**
     * 2025.01.05
     */
    class ATM {
        private static final int[] DENOMINATIONS = {20, 50, 100, 200, 500};
        private static final int KINDS = DENOMINATIONS.length;
        private final int[] banknotes = new int[KINDS];

        public ATM() {

        }

        public void deposit(int[] banknotesCount) {
            for (int i = 0; i < KINDS; i++) {
                banknotes[i] += banknotesCount[i]++;
            }
        }

        public int[] withdraw(int amount) {
            int[] ans = new int[KINDS];

            for (int i = KINDS - 1; i >= 0; i--) {
                ans[i] = Math.min(amount / DENOMINATIONS[i], banknotes[i]);
                amount -= ans[i] * DENOMINATIONS[i];
            }

            if (amount != 0) return new int[]{-1};

            for (int i = 0; i < KINDS; i++) {
                banknotes[i] -= ans[i];
            }

            return ans;
        }
    }

    /**
     * 2025.01.06
     * 2274. 不含特殊楼层的最大连续楼层数
     *
     * @param bottom
     * @param top
     * @param special
     * @return
     */
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int result = 0;
        result = Math.max(special[0] - bottom, top - special[special.length - 1]);

        for (int i = 1; i < special.length; i++) {
            result = Math.max(special[i] - special[i - 1] - 1, result);
        }
        return result;
    }


    /**
     * 2025.01.07
     * 3019. 按键变更的次数
     *
     * @param s
     * @return
     */
    public int countKeyChanges(String s) {
        int ans = 0;
        char[] cs = s.toCharArray();
        for (int i = 1; i < cs.length; i++) {
            if (Character.toLowerCase(cs[i]) != Character.toLowerCase(cs[i - 1])) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 2025.01.08
     * 2264. 字符串中最大的 3 位相同数字
     *
     * @param num
     * @return
     */
    public String largestGoodInteger(String num) {
        String result = "";
        int max = -1;
        for (int i = 0; i <= num.length() - 3; i++) {
            String temp = num.substring(i, i + 3);
            if (Objects.equals(temp.charAt(0), temp.charAt(1)) && Objects.equals(temp.charAt(0), temp.charAt(2)) && Objects.equals(temp.charAt(2), temp.charAt(1))) {
                int x = (int) temp.charAt(0);
                if (x > max) {
                    result = temp;
                    max = x;
                }
            }
        }

        return result;
    }

    /**
     * 2025.01.09
     * 3297. 统计重新排列后包含另一个字符串的子字符串数目 I
     *
     * @param word1
     * @param word2
     * @return
     */
    public long validSubstringCount(String word1, String word2) {
        if (word1.length() < word2.length()) {
            return 0;
        }

        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int[] diff = new int[26]; // t 的字母出现次数与 s 的字母出现次数之差
        for (char c : t) {
            diff[c - 'a']++;
        }

        // 统计窗口内有多少个字母的出现次数比 t 的少
        int less = 0;
        for (int d : diff) {
            if (d > 0) {
                less++;
            }
        }

        long ans = 0;
        int left = 0;
        for (char c : s) {
            diff[c - 'a']--;
            if (diff[c - 'a'] == 0) {
                // c 移入窗口后，窗口内 c 的出现次数和 t 的一样
                less--;
            }
            while (less == 0) { // 窗口符合要求
                char outChar = s[left++]; // 准备移出窗口的字母
                if (diff[outChar - 'a'] == 0) {
                    // outChar 移出窗口之前检查出现次数，
                    // 如果窗口内 outChar 的出现次数和 t 的一样，
                    // 那么 outChar 移出窗口后，窗口内 outChar 的出现次数比 t 的少
                    less++;
                }
                diff[outChar - 'a']++;
            }
            ans += left;
        }
        return ans;
    }

    /**
     * 2025.01.14
     * 3065. 超过阈值的最少操作数 I
     *
     * @param nums
     * @param k
     * @return
     */
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= k) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    /**
     * 2025.01.15
     * 3066. 超过阈值的最少操作数 II
     *
     * @param nums
     * @param k
     * @return
     */
    public int minOperationsII(int[] nums, int k) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for (int x : nums) {
            priorityQueue.offer((long) x);
        }

        int ans = 0;
        while (priorityQueue.peek() < k) {
            long x = priorityQueue.poll();
            long y = priorityQueue.poll();

            priorityQueue.offer(Math.min(x, y) * 2 + y);
            ans++;
        }
        return ans;
    }

    /**
     * 2025.01.22
     * 1561. 你可以获得的最大硬币数目
     *
     * @param piles
     * @return
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;
        int count = piles.length / 3;
        int j = piles.length - 2;
        for (int i = 0; i < count; i++) {
            ans += piles[j];
            j -= 2;
        }
        return ans;
    }

    /**
     * 2025.03.06
     * 2588. 统计美丽子数组数目
     *
     * @param nums
     * @return
     */
    public long beautifulSubarrays(int[] nums) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>(nums.length + 1);
        map.put(0, 1);
        int cur = 0;
        for (int num : nums) {
            cur ^= num;
            result += map.getOrDefault(cur, 0);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return result;
    }
}






























