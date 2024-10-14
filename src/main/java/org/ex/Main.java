package org.ex;


import Hot100.Dp;
import Hot100.Skill;
import org.apache.commons.codec.digest.DigestUtils;
import org.ex.graphTheory.Connection;
import org.ex.graphTheory.Question;

import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        int[][] arrays = new int[][]{{1,2},{2,3},{3,4}};
        String s = "ss ss";
        s.split(" ");
        Arrays.sort(arrays, (o1, o2) -> o2[1]-o1[1]);
        System.out.println(Arrays.deepToString(arrays));
    }


}




