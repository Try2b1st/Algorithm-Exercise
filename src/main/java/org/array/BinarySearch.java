package org.array;

/**
 * 二分搜索法
 */
public class BinarySearch {
    public BinarySearch() {
    }

    /**
     * 35. 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        if(left == right && nums[0] == target){
            return new int[]{0,0};
        }
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                int leftMid = mid-1;
                int rightMid = mid+1;
                while(leftMid >=0 && nums[leftMid] == target){
                    leftMid--;
                }
                while(rightMid <= nums.length-1 && nums[rightMid] == target){
                    rightMid++;
                }

                return new int[]{leftMid+1,rightMid-1};
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 69. x 的平方根
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int mid;
        int ans = -1;
        while(left<=right){
            mid = (left + right) / 2;
            if((long)mid * mid <= x){
                ans = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    /**
     * 367. 有效的完全平方数
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int l = 0;
        int r = num;
        while(l <= r){
            int mid = (r + l) / 2;
            long q = (long) mid * mid;
            if(q < num){
                l = mid + 1;
            }else if(q > num){
                r = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
