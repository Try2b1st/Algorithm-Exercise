package org.ex.graphTheory;

import java.util.ArrayList;
import java.util.List;

public class Connection {

    /**
     * 684. 冗余连接
     *
     * @param edges
     * @return
     */
    int[] father;

    public int[] findRedundantConnection(int[][] edges) {
        father = new int[edges.length + 1];
        init();
        int[] result = new int[2];

        for (int[] arrays : edges) {
            if (isSame(arrays[0], arrays[1])) {
                result = arrays;
            }
            join(arrays[0], arrays[1]);
        }
        return result;
    }


    public void init() {
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
    }

    public int find(int u) {
        if (u == father[u]) {
            return u;
        }
        return find(father[u]);
    }

    public boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

    public void join(int f, int s) {
        f = find(f);
        s = find(s);

        if (f == s) {
            return;
        }
        father[s] = f;
    }

    /**
     * 685. 冗余连接 II
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] tempIn = new int[n + 1];

        for (int[] temp : edges) {
            tempIn[temp[1]]++;
        }

        List<Integer> inIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (tempIn[edges[i][1]] == 2) {
                inIndex.add(i);
            }
        }

        //有节点有两个入度
        if (inIndex.size() > 0) {
            if (isTreeAfterRemove(edges, inIndex.get(1))) {
                return edges[inIndex.get(1)];
            } else {
                return edges[inIndex.get(0)];
            }
        }

        //成环，用并查集
        return getRemove(edges);
    }


    public boolean isTreeAfterRemove(int[][] edges, int deleteEdge) {
        father = new int[edges.length + 1];
        init();

        for (int i = 0; i < edges.length; i++) {
            if (i == deleteEdge) {
                continue;
            }
            if (isSame(edges[i][0], edges[i][1])) {
                return false;
            }
            join(edges[i][0], edges[i][1]);
        }
        return true;
    }

    public int[] getRemove(int[][] edges) {
        father = new int[edges.length + 1];
        init();

        for (int[] temp : edges) {
            if (isSame(temp[0], temp[1])) {
                return temp;
            }
            join(temp[0], temp[1]);
        }
        return null;
    }


}
