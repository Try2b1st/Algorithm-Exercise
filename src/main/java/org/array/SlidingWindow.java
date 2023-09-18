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


    /**
     * 904. 水果成篮
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        if (fruits.length == 1) {
            return 1;
        }
        if (fruits.length == 2) {
            return 2;
        }
        int r = fruits[0];
        int l = -1;
        int count = 1;
        int windowSize = 1;
        int result = 0;
        int start = 0;
        for (int end = 1; end < fruits.length; end++) {
            if (count == 1) {
                if (fruits[end] != r) {
                    l = r;
                    r = fruits[end];
                    count++;
                }
                windowSize++;
                result = Math.max(result, windowSize);
            } else {
                if (fruits[end] != r && fruits[end] != l) {
                    result = Math.max(result, end - start);
                    r = fruits[end];
                    l = fruits[end - 1];
                    start = end - 2;
                    while (fruits[start] == l) {
                        start--;
                    }
                    start++;
                    windowSize = end - start + 1;
                } else {
                    windowSize++;
                    result = Math.max(result, windowSize);
                }
            }
        }
        return result;
    }
}
