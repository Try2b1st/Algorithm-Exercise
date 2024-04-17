package Hot100;

import java.util.*;

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
            return quickSort(small, k - big.size() - equal.size());
        }
        return pivot;
    }


    /**
     * 295. 数据流的中位数
     */
    public class MedianFinder {
        PriorityQueue<Integer> l = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> r = new PriorityQueue<>((a, b) -> a - b);

        public MedianFinder() {
        }

        public void addNum(int num) {
            if (l.size() == r.size()) {
                r.offer(num);
                l.offer(r.poll());
            } else {
                l.offer(num);
                r.offer(l.poll());
            }
        }

        public double findMedian() {
            if (l.size() != r.size()) {
                return l.peek();
            } else {
                return (l.peek() + r.peek()) / 2.0D;
            }
        }
    }
}
