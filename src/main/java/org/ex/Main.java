package org.ex;


import Hot100.Dp;
import Hot100.Skill;
import org.apache.commons.codec.digest.DigestUtils;
import org.ex.graphTheory.Connection;
import org.ex.graphTheory.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5Hex("827ccb0eea8a706c4c34a16891f84e7b"));
        Daily daily = new Daily();
        daily.edgeScore(new int[]{1,0,0,0,0,7,7,5});
    }

    int nodeId;
    int size;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        boolean[] isInitial = new boolean[n];
        int mn = Integer.MAX_VALUE;
        for (int x : initial) {
            isInitial[x] = true;
            mn = Math.min(mn, x);
        }

        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] || isInitial[i]) {
                continue;
            }
            nodeId = -1;
            size = 0;
            dfsToMinMalwareSpread(i, graph, vis, isInitial);

            if (nodeId >= 0) {
                count[nodeId] += size;
            }
        }

        int ans = -1;
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > 0 && (count[i] > maxSize || count[i] == maxSize && i < ans)) {
                maxSize = count[i];
                ans = i;
            }
        }

        return ans < 0 ? mn : ans;
    }

    private void dfsToMinMalwareSpread(int x, int[][] graph, boolean[] vis, boolean[] isInitial) {
        vis[x] = true;
        size++;

        if (isInitial[x]) {
            nodeId = x;
            return;
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[x][i] == 1 && !vis[i]) {
                dfsToMinMalwareSpread(i, graph, vis, isInitial);
            }
        }
    }
}




