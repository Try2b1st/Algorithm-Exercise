package Hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dui {


    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return quickSort(list, k);
    }

    private int quickSort(List<Integer> nums, int k) {
        Random random = new Random();
        int pivot = nums.get(random.nextInt(nums.size()));

        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        for (int num : nums) {
            if (num > pivot) {
                big.add(num);
            } else if (num == pivot) {
                equal.add(num);
            } else {
                small.add(num);
            }
        }

        if (k <= big.size()) {
            return quickSort(big, k);
        } else if (k > big.size() + equal.size()) {
            return quickSort(small, k -big.size() - equal.size());
        }
        return pivot;
    }
}
