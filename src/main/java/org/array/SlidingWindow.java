package org.array;

public class SlidingWindow {
    public SlidingWindow() {
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = 0;
        int windowSize;
        int windowSum = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            windowSum += nums[end];
            while (target <= windowSum) {
                if (result == 0) {
                    result = nums.length;
                }
                windowSize = end - start + 1;
                result = Math.min(windowSize, result);
                windowSum -= nums[start];
                start++;
            }
        }
        return result;
    }
}
