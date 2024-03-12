package org.ex;

import Hot100.SubString;
import LCR.hash.Hash;
import LCR.hash.MedianFinder;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.findMedian();
    }
}
