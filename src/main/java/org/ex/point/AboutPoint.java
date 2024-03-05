package org.ex.point;

/**
 * @author 下水道的小老鼠
 */
public class AboutPoint {

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;

        while (fast < nums.length) {
            if (nums[fast] == val) {
                fast++;
            } else {
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }

}
