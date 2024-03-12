package LCR.hash;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    Queue<Integer> min, max;

    public MedianFinder() {
        //小顶堆 堆顶小 存大的那一部分
        min = new PriorityQueue<>();

        //大顶堆 堆顶大 存小的那一部分
        max = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if (min.size() != max.size()) {
            max.add(num);
            min.add(max.poll());
        } else {
            min.add(num);
            max.add(min.poll());
        }
    }

    public double findMedian() {
        int result = 0;
        if (min.size() != max.size()) {
            result = max.peek();
        } else {
            result = (max.peek() + min.peek()) / 2;
        }
        return result;
    }
}
