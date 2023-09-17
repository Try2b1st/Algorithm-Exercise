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
        int windowSize = 0;
        int windowSum = nums[0];
        int start = 0;
        int end = 0;
        while (nums.length >= windowSize) {
            if (windowSum < target) {
                end++;
                windowSum+=nums[end];
            } else {
                windowSize = Math.min(end - start + 1, windowSize);
                windowSum-=nums[start];
                start++;
            }
        }
        return windowSize;
    }
}
