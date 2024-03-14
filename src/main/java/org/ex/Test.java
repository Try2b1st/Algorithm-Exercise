package org.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();

        int[] s = new int[]{1,2,3};
        int[] i = new int[]{4,5,6,7};

        list.add(s);
        list.add(i);
        int[][] arrays = list.toArray(new int[0][]);
        System.out.println(Arrays.deepToString(arrays));
    }
}
