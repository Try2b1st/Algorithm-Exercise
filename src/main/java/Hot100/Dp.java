package Hot100;

import java.util.ArrayList;
import java.util.List;

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
}
