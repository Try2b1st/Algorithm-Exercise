package org.graphTheory;

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

        for(int[] arrays : edges){
            int flag = join(arrays[0], arrays[1]);
            if(flag == -1){
                result = arrays;
            }
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

    public int join(int f, int s) {
        f = find(f);
        s = find(s);

        if (f == s) {
            return -1;
        }

        father[s] = f;
        return 1;
    }
}
