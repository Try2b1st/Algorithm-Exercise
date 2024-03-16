package org.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int[][] tst = new int[][]{{187,167,209,251,152,236,263,128,135}};
        Daily daily = new Daily();
        System.out.println(daily.maxMoves(tst));
    }
}
