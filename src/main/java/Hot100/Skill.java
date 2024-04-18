package Hot100;

import java.util.Arrays;

public class Skill {

    /**
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums)  // 1. 遍历 nums 执行异或运算
            x ^= num;
        return x;            // 2. 返回出现一次的数字 x
    }


    /**
     * 169. 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int ans = 0;
        int count = 0;

        for (int x : nums) {
            if (count == 0) ans = x;
            count += x == ans ? 1 : -1;
        }
        return ans;
    }
}




