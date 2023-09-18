package org.array;

import java.util.HashMap;

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

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

//        HashMap<Character, Integer> tMap = new HashMap<>(t.length());
        HashMap<Character, Integer> sMap = new HashMap<>(t.length());

        for (int i = 0; i < t.length(); i++) {
//            tMap.put(t.charAt(i), 1);
            sMap.put(t.charAt(i), 0);
        }
        int start = 0;
        int minSize= s.length();
        for (int end = 0; end < s.length(); end++) {
            if (sMap.containsKey(s.charAt(end))) {
                sMap.put(s.charAt(end), (sMap.get(s.charAt(end))) + 1);
            }
        }
        return "";
    }
}
