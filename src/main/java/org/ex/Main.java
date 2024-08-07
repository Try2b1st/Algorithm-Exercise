package org.ex;


import Hot100.Dp;
import Hot100.Skill;
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
        int n = 3;
        for (int s = 1; s < (1 << n); s++) {
            int k = Integer.numberOfTrailingZeros(s);
            int t = s ^ (1 << k);
            System.out.println(s + ","+k + "," + t);
        }
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




