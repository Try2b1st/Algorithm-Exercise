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
        if (nums[n - 1] > target) {
            return findTarget(nums, -1, rightStart, target);
        }
        return findTarget(nums, rightStart - 1, n, target);
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

    private int findTarget(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }


    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public int findMin1(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right];
    }


    /**
     * 4. 寻找两个正序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int aStart = 0;
        int bStart = 0;
        int sum = nums1.length + nums2.length;
        double midNumber = 0.0;
        double number1 = 0.0;
        double number2 = 0.0;

        if (sum != 0) {
            if (sum % 2 != 0) {
                for (int i = 0; i <= sum / 2; i++) {
                    if (aStart < nums1.length && bStart < nums2.length) {
                        if (nums1[aStart] == nums2[bStart]) {
                            midNumber = nums1[aStart];
                            aStart++;
                            bStart++;
                            i++;
                        } else if (nums1[aStart] < nums2[bStart]) {
                            midNumber = nums1[aStart];
                            aStart++;
                        } else {
                            midNumber = nums2[bStart];
                            bStart++;
                        }
                    } else if (aStart < nums1.length) {
                        midNumber = nums1[aStart];
                        aStart++;
                    } else {
                        midNumber = nums2[bStart];
                        bStart++;
                    }
                }
                return midNumber;
            } else {
                for (int i = 0; i <= sum / 2; i++) {
                    if (aStart < nums1.length && bStart < nums2.length) {
                        if (nums1[aStart] == nums2[bStart]) {
                            number1 = nums1[aStart];
                            aStart++;
                            bStart++;
                            i++;
                        } else if (nums1[aStart] < nums2[bStart]) {
                            number1 = nums1[aStart];
                            aStart++;
                        } else {
                            number1 = nums2[bStart];
                            bStart++;
                        }
                    } else if (aStart < nums1.length) {
                        number1 = nums1[aStart];
                        aStart++;
                    } else {
                        number1 = nums2[bStart];
                        bStart++;
                    }
                }
                aStart = 0;
                bStart = 0;
                for (int i = 0; i < sum / 2; i++) {
                    if (aStart < nums1.length && bStart < nums2.length) {
                        if (nums1[aStart] == nums2[bStart]) {
                            number2 = nums1[aStart];
                            aStart++;
                            bStart++;
                            i++;
                        } else if (nums1[aStart] < nums2[bStart]) {
                            number2 = nums1[aStart];
                            aStart++;
                        } else {
                            number2 = nums2[bStart];
                            bStart++;
                        }
                    } else if (aStart < nums1.length) {
                        number2 = nums1[aStart];
                        aStart++;
                    } else {
                        number2 = nums2[bStart];
                        bStart++;
                    }
                }
                midNumber = (number1 + number2) / 2.0;
                return midNumber;
            }
        } else {
            return 0;
        }
    }
}
