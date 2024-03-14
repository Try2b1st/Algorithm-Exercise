package LCR.hash;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Hash {

    /**
     * LCR 120. 寻找文件副本
     *
     * @param documents
     * @return
     */
    public int findRepeatDocument(int[] documents) {
//        Set<Integer> set = new HashSet<>();
//        for (int doc : documents) {
//            if(set.contains(doc)){
//                return doc;
//            }
//            set.add(doc);
//        }
//        return -1;
        int i = 0;
        while (i < documents.length) {
            if (documents[i] == i) {
                i++;
                continue;
            }

            if (documents[documents[i]] == documents[i]) {
                return documents[i];
            }
            int temp = documents[i];
            documents[i] = documents[documents[i]];
            documents[documents[i]] = temp;
        }
        return -1;
    }

    /**
     * 121. 寻找目标值 - 二维数组
     * 思路：从数组的右上角上看，可以将其看作二叉搜索树
     *
     * @param plants
     * @param target
     * @return
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        int x = plants.length - 1;
        int y = 0;

        while (x > 0 && y < plants[0].length) {
            if (plants[x][y] == target) {
                return true;
            }
            if (plants[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }
        return false;
    }

    /**
     * LCR 128. 库存管理 I
     *
     * @param stock
     * @return
     */
    public int stockManagement(int[] stock) {
        int l = 0;
        int r = stock.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (stock[m] > stock[r]) {
                l = m;
            } else if (stock[m] < stock[r]) {
                r = m;
            } else if (stock[m] == stock[r]) {
                r -= 1;
            }
        }
        return stock[l];
    }

    /**
     * LCR 131. 砍竹子 I
     *
     * @param bamboo_len
     * @return
     */
    public int cuttingBamboo(int bamboo_len) {
        int result = 1;

        while (bamboo_len > 4) {
            bamboo_len -= 3;
            result *= 3;

        }

        result *= bamboo_len;
        return result;
    }

    /**
     * LCR 132. 砍竹子 II
     *
     * @param bamboo_len
     * @return
     */
    public int cuttingBamboo_2(int bamboo_len) {
        int[] dp = new int[bamboo_len + 1];
        dp[2] = 1;

        for (int i = 3; i < bamboo_len + 1; i++) {
            for (int j = 0; j < i / 2; i++) {
                dp[i] = Math.max(dp[i], Math.max(i * (i - j), i * dp[i - j]));
            }
        }

        return dp[bamboo_len];
    }

    /**
     * LCR 135. 报数
     * 主要考点是大数越界情况下的打印。
     *
     * @param cnt
     * @return
     */
    char[] nums, loop = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    StringBuilder result;
    int count;
    int nine;
    int start;

    public String countNumbers(int cnt) {
        count = cnt;
        result = new StringBuilder();
        nums = new char[cnt];
        start = cnt - 1;
        dfsToCountNumber(0);

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    void dfsToCountNumber(int x) {
        //当确定到最后一位
        if (x == count) {
            String s = String.valueOf(nums).substring(start);
            if (!s.equals("0")) {
                result.append(s).append(",");
            }
            if (count - start == nine) {
                start--;
            }
            return;
        }

        for (char c : loop) {
            if (c == '9') {
                nine++;
            }
            nums[x] = c;
            dfsToCountNumber(x + 1);
        }
        nine--;

    }


    /**
     * LCR 139. 训练计划 I
     *
     * @param actions
     * @return
     */
    public int[] trainingPlan(int[] actions) {
        int l = 0;
        int r = actions.length - 1;

        while (l < r) {
            while (l < r && actions[l] % 2 == 1) {
                l++;
            }
            while (l < r && actions[r] % 2 == 0) {
                r--;
            }
            int temp = actions[l];
            actions[l] = actions[r];
            actions[r] = temp;

        }

        return actions;
    }

    /**
     * LCR 158. 库存管理 II
     *
     * @param stock
     * @return
     */
    public int inventoryManagementByEasy(int[] stock) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x : stock) {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > stock.length / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * 摩尔投票法
     *
     * @param stock
     * @return
     */
    public int inventoryManagement(int[] stock) {
        int x = stock[0];
        int point = 1;

        for (int i = 1; i < stock.length; i++) {
            if (point == 0) {
                x = stock[i];
                point = 1;
            } else {
                if (stock[i] != x) {
                    point--;
                } else {
                    point++;
                }
            }
        }
        return x;
    }


    /**
     * LCR 159. 库存管理 III
     * 快排
     *
     * @param stock
     * @param cnt
     * @return
     */
    public int[] inventoryManagement(int[] stock, int cnt) {
        if (cnt >= stock.length) {
            return stock;
        }
        return quickSort(stock, cnt, 0, stock.length - 1);
    }

    private int[] quickSort(int[] stock, int cnt, int l, int r) {
        int i = l;
        int j = r;
        while (i < j) {
            while (i < j && stock[j] >= stock[l]) {
                j--;
            }
            while (i < j && stock[l] >= stock[i]) {
                i++;
            }
            swap(stock, i, j);
        }
        swap(stock, i, l);

        if (i < cnt) {
            return quickSort(stock, cnt, l, i - 1);
        }
        if (i > cnt) {
            return quickSort(stock, cnt, i + 1, r);
        }
        return Arrays.copyOf(stock, cnt);
    }

    private void swap(int[] stock, int l, int r) {
        int temp = stock[l];
        stock[l] = stock[r];
        stock[r] = temp;
    }


    /**
     * LCR 164. 破解闯关密码
     *
     * @param password
     * @return
     */
    public String crackPassword(int[] password) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = password.length;

        Integer[] arr2 = Arrays.stream(password).boxed().toArray(Integer[]::new);
        Arrays.sort(arr2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = String.valueOf(o1);
                String s2 = String.valueOf(o2);
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        for (int temp : arr2) {
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    /**
     * LCR 168. 丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int a = 0, b = 0, c = 0;

        for (int i = 1; i < n; i++) {
            int n2 = res[a] * 2, n3 = res[b] * 3, n5 = res[c] * 5;
            res[i] = Math.min(n2, Math.min(n3, n5));
            if (res[i] == n2) {
                a++;
            }
            if (res[i] == n3) {
                b++;
            }
            if (res[i] == n5) {
                c++;
            }
        }

        return res[n - 1];
    }

    /**
     * LCR 170. 交易逆序对的总数
     * 归并排序
     *
     * @param record
     * @return
     */
    int countToReverPairs = 0;

    public int reversePairs(int[] record) {
        merge(record, 0, record.length - 1);
        return countToReverPairs;
    }

    public void merge(int[] nums, int l, int r) {
        int mid = l + (r - l) / 2;
        if (l < r) {
            merge(nums, l, mid);
            merge(nums, mid + 1, r);
            mergeSort(nums, l, mid, r);
        }
    }

    public void mergeSort(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int tempCount = 0;
        int leftStart = left;
        int rightStart = mid + 1;
        while (leftStart <= mid && rightStart <= right) {
            if (nums[leftStart] <= nums[rightStart]) {
                temp[tempCount++] = nums[leftStart];
                leftStart++;
            } else {
                temp[tempCount++] = nums[rightStart];
                countToReverPairs++;
                countToReverPairs += (mid - leftStart);
                rightStart++;
            }
        }
        while (leftStart <= mid) {
            temp[tempCount++] = nums[leftStart++];
        }
        while (rightStart <= right) {
            temp[tempCount++] = nums[rightStart++];
        }
        for (int x : temp) {
            nums[left++] = x;
        }
    }


    /**
     * LCR 172. 统计目标成绩的出现次数
     *
     * @param scores
     * @param target
     * @return
     */
    public int countTarget(int[] scores, int target) {
        if (scores.length == 0) {
            return 0;
        }
        int l = 0;
        int r = scores.length - 1;
        int mid = 0;
        int result = 0;

        while (l < r) {
            mid = l + (r - l) / 2;
            if (scores[mid] == target) {
                break;
            } else if (scores[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (scores[mid] != target) {
            return result;
        } else {
            result++;
        }
        int rmid = mid + 1;
        int lmid = mid - 1;
        while (rmid <= r && scores[rmid] == target) {
            rmid++;
            result++;
        }
        while (lmid >= 0 && scores[lmid] == target) {
            lmid--;
            result++;
        }
        return result;
    }


    /**
     * LCR 173.点名
     *
     * @param records
     * @return
     */
    public int takeAttendance(int[] records) {
        int l = 0;
        int r = records.length - 1;
        int mid = l + (r - l) / 2;

        while (l < r) {
            if (records[mid] == mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            mid = l + (r - l) / 2;
        }
        if (records[l] == l) {
            return l + 1;
        } else {
            return l;
        }
    }


    /**
     * LCR 179. 查找总价格为目标值的两个商品
     *
     * @param price
     * @param target
     * @return
     */
    public int[] twoSum(int[] price, int target) {
        int l = 0;
        int r = price.length - 1;

        while (l < r) {
            int tempSum = price[l] + price[r];
            if (tempSum == target) {
                return new int[]{price[l], price[r]};
            } else if (tempSum < target) {
                l++;
            } else {
                r++;
            }
        }

        return null;
    }


    /**
     * LCR 180. 文件组合
     *
     * @param target
     * @return
     */
    public int[][] fileCombination(int target) {
        List<int[]> list = new ArrayList<>();

        int start = 1;
        int end = 2;
        int sum = 3;

        while (start < end) {
            if (sum == target) {
                list.add(getAns(start, end));
                sum -= start;
                start++;
                end++;
                sum += end;
            } else if (sum < target) {
                end++;
                sum += end;
            } else {
                sum -= start;
                start++;
            }
        }
        return list.toArray(new int[0][]);
    }

    @NotNull
    private int[] getAns(int start, int end) {
        int[] ans = new int[end - start + 1];
        int count = 0;
        for (int i = start; i <= end; i++) {
            ans[count++] = i;
        }
        return ans;
    }


    /**
     * LCR 183. 望远镜中最高的海拔
     *
     * @param heights
     * @param limit
     * @return
     */
    public int[] maxAltitude(int[] heights, int limit) {
        int l = 0;
        int r = limit - 1;
        int count = 0;
        if (r <= 0) {
            return new int[]{0};
        }

        MyDeque myDeque = new MyDeque();
        int[] result = new int[heights.length - limit + 1];

        for (int i = l; i < r; i++) {
            myDeque.add(heights[l]);
        }

        for (; r < heights.length; r++, l++) {
            result[count++] = myDeque.peek();
            myDeque.pop(heights[l]);
            if (r + 1 < heights.length) {
                myDeque.add(heights[r + 1]);
            }
        }
        return result;
    }

    public static class MyDeque {
        private Deque<Integer> deque;

        public MyDeque() {
            deque = new LinkedList<>();
        }

        public void add(int x) {
            while (!deque.isEmpty() && x > deque.getLast()) {
                deque.removeLast();
            }
            deque.addLast(x);
        }

        public void pop(int x) {
            if (!deque.isEmpty() && x == deque.getFirst()) {
                deque.removeFirst();
            }
        }

        public int peek() {
            return deque.getFirst();
        }

    }


    /**
     * LCR 186. 文物朝代判断
     *
     * @param places
     * @return
     */
    public boolean checkDynasty(int[] places) {
        Arrays.sort(places);
        boolean result = true;
        int n = places.length;

        int l = 0;
        for (; l < n; l++) {
            if (places[l] != 0) {
                break;
            }
        }

        for (int i = l + 1; i < n; i++) {
            if (places[i] == places[i - 1]) {
                return false;
            }
            if (places[i] - places[i - 1] != 1) {
                l -= places[i] - places[i - 1] - 1;
                if (l < 0) {
                    return false;
                }
            }
        }
        return result;
    }

}


















