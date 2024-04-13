package org.ex;


import java.util.*;

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
        for(int i = 0; i< n;i++){
            if(result[i]){
                ans= i;
                count++;
            }
            if(count>=2){
                return -1;
            }
        }
        return ans;
    }
}






























