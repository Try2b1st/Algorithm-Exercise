package Hot100;

public class Search {

    /**
     * 74. 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int x = n - 1;
        int y = 0;

        while (x > 0 && y < m) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }
        return false;
    }


    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int rightStart = findMin(nums);
        if(nums[n - 1] > target){
            return findTarget(nums,-1,rightStart,target);
        }
        return findTarget(nums,rightStart - 1,n,target);
    }

    private int findMin(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[n - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private int findTarget(int[] nums,int left,int right,int target){
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}
