package org.array;

/**
 * 双指针法
 * @author 下水道的小老鼠
 */
public class DoublePointer {
    public DoublePointer() {
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[slow + 1] = nums[fast];
                slow++;
            }
        }
        return slow + 1;
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[i] = 0;
            }
        }
    }

    /**
     * 844.比较含退格的字符串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int scSlow = 0;
        for (int fast = 0; fast < sc.length; fast++) {
            if (sc[fast] != '#') {
                sc[scSlow] = sc[fast];
                scSlow++;
            } else {
                if (scSlow != 0) {
                    scSlow--;
                }
            }
        }
        int tcSlow = 0;
        for (int fast = 0; fast < tc.length; fast++) {
            if (tc[fast] != '#') {
                tc[tcSlow] = tc[fast];
                tcSlow++;
            } else {
                if (tcSlow != 0) {
                    tcSlow--;
                }
            }
        }

        if (scSlow != tcSlow) {
            return false;
        } else {
            for (int i = 0; i < scSlow; i++) {
                if (sc[i] != tc[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 977. 有序数组的平方
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int start = 0;
        int count = nums.length;
        int end = count - 1;
        int[] result = new int[count];
        while (start != end) {
            if (nums[start] * nums[start] <= nums[end] * nums[end]) {
                result[count - 1] = nums[end] * nums[end];
                end--;
            } else {
                result[count - 1] = nums[start] * nums[start];
                start++;
            }
            count--;
        }
        result[count - 1] = nums[start] * nums[start];
        return result;
    }
}
