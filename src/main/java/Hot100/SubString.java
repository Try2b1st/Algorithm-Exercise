package Hot100;

import java.util.HashMap;

public class SubString {

    /**
     * 560. 和为 K 的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int length = nums.length;
        int[] pre = new int[length];
        pre[0] = nums[0];
        for (int i = 1; i < length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x : pre) {
            if (!map.containsKey(x)) {
                map.put(x, 1);
            } else {
                map.put(x, map.get(x) + 1);
            }

            if (map.containsKey(x - k)) {
                result += map.get(x - k);
            }else if(x - k == 0){
                result++;
            }
        }
        return result;
    }
}
