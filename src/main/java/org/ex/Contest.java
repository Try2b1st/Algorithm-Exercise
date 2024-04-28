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
}




















