package Hot100;

import java.util.*;

public class Grap {

    /**
     * 994. 腐烂的橘子
     *
     * @param grid
     * @return
     */
    int[][] deps = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public int orangesRotting(int[][] grid) {
        Deque<int[]> deque = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    deque.addLast(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    flag = true;
                }
            }
        }

        if (deque.isEmpty() && !flag) return 0;

        int ans = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] temp = deque.pollFirst();
                int x = temp[0];
                int y = temp[1];

                for (int[] dep : deps) {
                    int nextX = x + dep[0];
                    int nextY = y + dep[1];
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                        continue;
                    }

                    if (grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;
                        deque.addLast(new int[]{nextX, nextY});
                    }
                }
            }
        }
        for (int[] ints : grid) {
            for (int j = 0; j < m; j++) {
                if (ints[j] == 1) {
                    return -1;
                }
            }
        }
        return ans - 1;
    }


    /**
     * 207. 课程表
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Arrays.fill(result, 0);

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        for (int[] temp : prerequisites) {
            result[temp[1]] += 1;
            adjacency.get(temp[0]).add(temp[1]);
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (result[i] == 0) {
                deque.addLast(i);
            }
        }

        while (!deque.isEmpty()) {
            int x = deque.pollFirst();
            for (int i : adjacency.get(x)) {
                result[i] -= 1;
                if (result[i] == 0) {
                    deque.addLast(i);
                }
            }
        }

        for (int x : result) {
            if (x != 0) {
                return false;
            }
        }

        return true;
    }


    /**
     * 208. 实现 Trie (前缀树)
     */
    public class Trie {
        class TrieNode {
            boolean end;
            TrieNode[] tns = new TrieNode[26];
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.tns[index] == null) cur.tns[index] = new TrieNode();
                cur = cur.tns[index];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if(cur.tns[index] == null) return false;
                cur = cur.tns[index];
            }
            return cur.end;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if(cur.tns[index] == null) return false;
                cur = cur.tns[index];
            }
            return true;
        }
    }
}
