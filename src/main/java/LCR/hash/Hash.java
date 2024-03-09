package LCR.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
}


















