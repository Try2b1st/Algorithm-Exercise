package org.monotonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Question {


    /**
     * 739. 每日温度
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int temp = stack.peek();
                result[temp] = i - temp;
                stack.pop();
            }
            stack.push(i);
        }

        return result;
    }

    /**
     * 496. 下一个更大元素 I
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int temp = stack.pop();
                if (map.containsKey(nums2[temp])) {
                    result[map.get(nums2[temp])] = nums2[i];
                }
            }
            stack.push(i);
        }

        return result;
    }


    /**
     * 503. 下一个更大元素 II
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < nums.length * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                int temp = stack.pop();
                result[temp] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }

        return result;
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            if (height[i] > 0) {
                stack.push(i);
                break;
            }
        }

        for (int i = stack.peek() + 1; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int tempIndex = stack.pop();
                if (!stack.isEmpty()) {
                    result += (Math.min(height[stack.peek()], height[i]) - height[tempIndex]) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }

        return result;
    }

    /**
     * 84. 柱状图中最大的矩形
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        int[] newHeight = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeight, 1, heights.length);
        newHeight[heights.length + 1] = 0;
        newHeight[0] = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < newHeight.length; i++) {
            while (newHeight[stack.peek()] > newHeight[i]) {
                int hIndex = stack.pop();
                int h = newHeight[hIndex];
                int w = i - stack.peek() - 1;
                result = Math.max(result, h * w);
            }
        }

        return result;
    }


}
