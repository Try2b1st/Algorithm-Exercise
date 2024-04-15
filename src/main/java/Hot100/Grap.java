package Hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
                if(grid[i][j] == 1){
                    flag =true;
                }
            }
        }

        if(deque.isEmpty() && !flag) return 0;

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
        return ans -1;
    }
}
