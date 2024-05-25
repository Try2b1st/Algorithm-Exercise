package org.ex;

import LCR.graph.Question;

import java.util.*;


/**
 * 4.21
 * 4.28
 */
public class Contest {

    /**
     * 100294. 统计特殊字母的数量 I
     *
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        int[] array = new int[26];
        Arrays.fill(array, 9);
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);

            if (c >= 'a' && c <= 'z') {
                if (array[c - 'a'] == 9) array[c - 'a'] = 1;
                if (array[c - 'a'] == -1) array[c - 'a'] = 0;
            } else {
                if (array[c - 'A'] == 9) array[c - 'A'] = -1;
                if (array[c - 'A'] == 1) array[c - 'A'] = 0;
            }
        }

        int ans = 0;
        for (int x : array) {
            if (x == 0) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 100291. 统计特殊字母的数量 II
     *
     * @param word
     * @return
     */
    public int numberOfSpecialCharsII(String word) {
        int[] array = new int[26];
        Arrays.fill(array, 9);
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);

            if (c >= 'a' && c <= 'z') {
                if (array[c - 'a'] == 9) array[c - 'a'] = 1;
                if (array[c - 'a'] == 0) array[c - 'a'] = 10;
            } else {
                if (array[c - 'A'] == 9) array[c - 'A'] = -1;
                if (array[c - 'A'] == 1) array[c - 'A'] = 0;
            }
        }

        int ans = 0;
        for (int x : array) {
            if (x == 0) {
                ans++;
            }
        }

        return ans;
    }


    /**
     * 743. 网络延迟时间
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        for (int[] temp : times) {
            g[temp[0] - 1].add(new int[]{temp[1] - 1, temp[2]});
        }

        int maxDis = 0;
        int left = n;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[k - 1] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        priorityQueue.offer(new int[]{0, k - 1});

        while (!priorityQueue.isEmpty()) {
            int[] temp = priorityQueue.poll();
            int curStart = temp[1];
            int dx = temp[0];
            if (dx > dis[curStart]) {
                continue;
            }

            List<int[]> list = g[curStart];
            maxDis = dx;
            left--;

            for (int[] array : list) {
                int goX = array[0];
                int newDx = array[1] + array[1];
                if (newDx < dis[goX]) {
                    dis[goX] = newDx;
                    priorityQueue.offer(new int[]{newDx, goX});
                }
            }
        }

        return left == 0 ? maxDis : -1;
    }

    /**
     * 100290. 使矩阵满足条件的最少操作次数
     *
     * @param grid
     * @return
     */
    public int minimumOperations(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int f0 = 0;
        int f1 = 0;
        int pre = -1;

        for (int i = 0; i < m; i++) {

            int[] temp = new int[10];
            for (int[] ints : grid) {
                temp[ints[i]] += 1;
            }

            int mx = -1, mx2 = 0, x = -1;
            for (int v = 0; v < 10; v++) {
                int res = (v != pre ? f0 : f1) + temp[v];
                if (res > mx) {
                    mx2 = mx;
                    mx = res;
                    x = v;
                } else if (res > mx2) {
                    mx2 = res;
                }
            }
            f0 = mx;
            f1 = mx2;
            pre = x;
        }
        return m * n - f0;
    }


    /**
     * 100276. 最短路径中的边
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean[] findAnswer(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            g[x].add(new int[]{y, w, i});
            g[y].add(new int[]{x, w, i});
        }

        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] dxPair = pq.poll();
            long dx = dxPair[0];
            int x = (int) dxPair[1];
            if (dx > dis[x]) {
                continue;
            }
            for (int[] temp : g[x]) {
                int y = temp[0];
                int w = temp[1];
                if (w + dx < dis[y]) {
                    dis[y] = w + dx;
                    pq.offer(new long[]{w + dx, y});
                }
            }
        }

        boolean[] ans = new boolean[edges.length];
        if (dis[n - 1] == Long.MAX_VALUE) {
            return ans;
        }

        boolean[] vis = new boolean[n];
        vis[n - 1] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n - 1);

        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int[] t : g[y]) {
                int x = t[0];
                int w = t[1];
                int i = t[2];

                if (dis[y] - w != dis[x]) {
                    continue;
                }
                ans[i] = true;
                if (!vis[x]) {
                    vis[x] = true;
                    queue.add(x);
                }
            }
        }
        return ans;
    }


    /**
     * 100285. 找出与数组相加的整数 I
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int addedInteger(int[] nums1, int[] nums2) {
        int sum1 = 0;
        for (int i : nums1) {
            sum1 += i;
        }

        int sum2 = 0;
        for (int i : nums2) {
            sum2 += i;
        }
        return (sum2 - sum1) / nums2.length;
    }


    /**
     * 100287. 找出与数组相加的整数 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 2; i >= 0; i--) {
            int diff = nums2[0] - nums1[i];

            int j = 0;
            for (int k = i; k < nums1.length; k++) {
                if (j < nums2.length && nums2[j] - nums1[k] == diff && ++j == nums2.length) {
                    // nums2 是 {nums1[i] + diff} 的子序列
                    return diff;
                }
            }
        }
        return 0;
    }


    /**
     * 100282. 数组最后一个元素的最小值
     *
     * @param n
     * @param x
     * @return
     */
    public long minEnd(int n, int x) {
        n--;
        int i = 0;
        int j = 0;
        long ans = x;
        while ((n >> j) > 0) {
            if (((ans >> i) & 1) == 0) {
                ans |= (long) ((n >> j) & 1) << i;
                j++;
            }
            i++;
        }
        return ans;
    }


    /**
     * 100257. 找出唯一性数组的中位数
     *
     * @param nums
     * @return
     */
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        long k = ((long) n * (n + 1) / 2 + 1) / 2;
        int left = 0;
        int right = n;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int upper, long k) {
        long cnt = 0;
        int l = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int r = 0; r < nums.length; r++) {
            freq.merge(nums[r], 1, Integer::sum);
            while (freq.size() > upper) {
                int out = nums[l++];
                if (freq.merge(out, -1, Integer::sum) == 0) {
                    freq.remove(out);
                }
            }
            cnt += r - l + 1;
            if (cnt >= k) {
                return true;
            }
        }
        return false;
    }


    /*
      05.12 周赛
     */

    /**
     * 100296. 两个字符串的排列差
     *
     * @param s
     * @param t
     * @return
     */
    public int findPermutationDifference(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int n = ss.length;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(ss[i], i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(map.get(ts[i]) - i);
        }
        return ans;
    }


    /**
     * 100274. 从魔法师身上吸取的最大能量
     *
     * @param energy
     * @param k
     * @return
     */
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;

        int ans = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                energy[i] += energy[i + k];
            }
        }

        for (int j : energy) {
            ans = Math.max(ans, j);
        }
        return ans;
    }


    /**
     * 100281. 矩阵中的最大得分
     *
     * @param grid
     * @return
     */
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] array = new int[m][n];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = grid.get(i).get(j);
            }
        }

        int[][] score = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int tempScore = Integer.MIN_VALUE;

                for (int k = j + 1; k < n; k++) {
                    tempScore = Math.max(tempScore,
                            Math.max(array[i][k] - array[i][j] + score[i][k],
                                    array[i][k] - array[i][j]));
                }

                for (int k = i + 1; k < m; k++) {
                    tempScore = Math.max(tempScore,
                            Math.max(array[k][j] - array[i][j] + score[k][j],
                                    array[k][j] - array[i][j]));
                }

                score[i][j] = tempScore == Integer.MIN_VALUE ? 0 : tempScore;
                if (!(i == m - 1 && j == n - 1)) ans = Math.max(score[i][j], ans);
            }
        }

        return ans;
    }

    /**
     * 100312. 找出分数最低的排列
     *
     * @param nums
     * @return
     */
    public int[] findPermutation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        return ans;
    }

    /*
      05.19 周赛
     */


    /**
     * 100310. 特殊数组 I
     *
     * @param nums
     * @return
     */
    public boolean isArraySpecial(int[] nums) {
        int i = -1;

        for (int x : nums) {
            int y = x % 2;
            if (i == y) return false;
            i = y;
        }
        return true;
    }

    /**
     * 100308. 特殊数组 II
     *
     * @param nums
     * @param queries
     * @return
     */
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = queries.length;
        int m = nums.length;
        int[] temp = new int[m];
        boolean[] ans = new boolean[n];

        int h = nums[m - 1] % 2;
        int maxL = m;
        for (int i = m - 2; i >= 0; i--) {
            int x = nums[i] % 2;
            if (x == h) {
                Arrays.fill(temp, i + 1, maxL, i + 1);
                maxL = i + 1;
            }
            h = x;
        }

        for (int i = 0; i < n; i++) {
            ans[i] = temp[queries[i][1]] <= queries[i][0];
        }
        return ans;
    }


    /**
     * 100300. 所有数对中数位不同之和
     *
     * @param nums
     * @return
     */
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        long ans = 0;
        while (nums[0] != 0) {
            long[] temp = new long[10];
            for (int i = 0; i < n; i++) {
                temp[nums[i] % 10]++;
                nums[i] /= 10;
            }

            long tempN = n;
            for (int j = 0; j < 10; j++) {
                long i = temp[j];
                if (i == 0) continue;
                ans += i * (tempN - i);
                tempN -= i;
            }
        }

        return ans;
    }


    /**
     * 100298. 到达第 K 级台阶的方案数
     *
     * @param k
     * @return
     */
    public int waysToReachStair(int k) {
        int[] dp = new int[k + 1];
        return dp[k];
    }


    //05.25 双周赛

    /**
     * 100309. 求出出现两次数字的 XOR 值
     *
     * @param nums
     * @return
     */
    public int duplicateNumbersXOR(int[] nums) {
        int[] count = new int[51];

        for (int i : nums) {
            count[i] += 1;
        }

        int ans = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 2) {
                ans ^= i;
            }
        }
        return ans;
    }


    /**
     * 100303. 查询数组中元素的出现位置
     *
     * @param nums
     * @param queries
     * @param x
     * @return
     */
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                list.add(i);
            }
        }

        int n = queries.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int m = list.size();
        for (int i = 0; i < n; i++) {
            if (queries[i] <= m) {
                ans[i] = list.get(queries[i] - 1);
            }
        }
        return ans;
    }


    /**
     * 100313. 所有球里面不同颜色的数目
     *
     * @param limit
     * @param queries
     * @return
     */
    public int[] queryResults(int limit, int[][] queries) {
        int[] color = new int[limit + 1];
        int[] ans = new int[queries.length];
        Map<Integer, Integer> map = new HashMap<>();

        ans[0] = 1;
        color[queries[0][0]] = queries[0][1];
        map.put(queries[0][1], 1);

        for (int i = 1; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];

            int xy = color[x];
            if (xy != 0) {
                map.put(xy, map.get(xy) - 1);
                if (map.get(xy) == 0) map.remove(xy);
            }
            map.put(y,map.getOrDefault(y,0) + 1);
            color[x] = y;
            ans[i] = map.keySet().size();
        }

        return ans;
    }

}




















