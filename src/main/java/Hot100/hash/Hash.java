package Hot100.hash;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Hash {
    public int[] twoSum(@NotNull int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (hashMap.containsKey(j) && hashMap.get(j) != i) {
                return new int[]{i, hashMap.get(j)};
            }
            hashMap.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 最长连续序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(@NotNull int[] nums) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;

        for(int num : set){
            if(!set.contains(num-1)){
                int currentLen = 1;
                int currentNum = num;
                while(set.contains(currentNum+1)){
                    currentLen++;
                    currentNum++;
                }
                result = Math.max(result,currentLen);
            }
        }

        return result;
    }
}
