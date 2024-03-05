package org.ex.array;

import java.util.HashMap;
import java.util.Map;

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

        HashMap<Character, Integer> tMap = new HashMap<>();
        HashMap<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.containsKey(t.charAt(i)) ? tMap.get(t.charAt(i)) + 1 : 1);
            sMap.put(t.charAt(i), 0);
        }

        int start = 0;
        String result = "";

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
                if (sMap.get(c) > tMap.get(c) && s.charAt(start) == c) {
                    start++;
                    sMap.put(c, sMap.get(c) - 1);
                    while (sMap.containsKey(s.charAt(start))) {
                        if (sMap.get(s.charAt(start)) > tMap.get(s.charAt(start))) {
                            sMap.put(s.charAt(start), sMap.get(s.charAt(start)) - 1);
                            start++;
                        } else {
                            break;
                        }
                    }
                }
            }
            while (!sMap.containsKey(s.charAt(start)) && start < j) {
                start++;
                while (sMap.containsKey(s.charAt(start))) {
                    if (sMap.get(s.charAt(start)) > tMap.get(s.charAt(start))) {
                        sMap.put(s.charAt(start), sMap.get(s.charAt(start)) - 1);
                        start++;
                    } else {
                        break;
                    }
                }
            }

            if (compareHashMaps(sMap, tMap)) {
                if ("".equals(result) || result.length() > j - start + 1) {
                    result = s.substring(start, j + 1);
                }
            }
        }
        return result;
    }

    public boolean compareHashMaps(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (!map2.containsKey(key) || value < map2.get(key)) {
                return false;
            }
        }
        return true;
    }
}
