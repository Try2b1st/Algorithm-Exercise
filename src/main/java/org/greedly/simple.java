package org.greedly;

import java.util.Arrays;

public class simple {

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int sCount = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if(sCount >= 0 && g[i]<s[sCount]){
                sCount--;
                count++;
            }
        }
        return count;
    }
}
