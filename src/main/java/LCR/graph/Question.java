package LCR.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Question {


    /**
     * LCR 129. 字母迷宫
     *
     * @param grid
     * @param target
     * @return
     */
    int[][] d = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n;
    int m;
    char[][] wordPuzzleGrid;
    boolean[][] visited;

    public boolean wordPuzzle(char[][] grid, String target) {
        n = grid.length;
        m = grid[0].length;
        wordPuzzleGrid = grid;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == target.charAt(0)) {
                    if (dfsToWordPuzzle(i, j, 0, target)) return true;
                }
            }
        }
        return false;
    }

    public boolean dfsToWordPuzzle(int x, int y, int count, String target) {
        if (count == target.length() - 1) {
            return true;
        }
        boolean result = false;
        visited[x][y] = true;
        for (int[] temp : d) {
            int nextX = x + temp[0];
            int nextY = y + temp[1];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextX][nextY]) {
                if (wordPuzzleGrid[nextX][nextY] != target.charAt(count + 1)) continue;
                result = result || dfsToWordPuzzle(nextX, nextY, count + 1, target);
            }
        }

        visited[x][y] = false;
        return result;
    }


    /**
     * LCR 130. 衣橱整理
     *
     * @param m
     * @param n
     * @param cnt
     * @return
     */
    int sum = 0;

    public int wardrobeFinishing(int m, int n, int cnt) {
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }
        dfsToWardrobeFinishing(m, n, 0, 0, cnt);
        return sum;
    }

    public void dfsToWardrobeFinishing(int m, int n, int x, int y, int cnt) {
        if (x >= m || y >= n || visited[x][y]) return;
        if ((sums(x) + sums(y)) <= cnt) {
            sum++;
            return;
        }

        visited[x][y] = true;
        dfsToWardrobeFinishing(m, n, x + 1, y, cnt);
        dfsToWardrobeFinishing(m, n, x, y + 1, cnt);
    }

    public int sums(int x) {
        int result = 0;
        while (x != 0) {
            result += x % 10;
            x /= 10;
        }
        return result;
    }


    /**
     * LCR 146. 螺旋遍历二维数组
     *
     * @param array
     * @return
     */
    public int[] spiralArray(int[][] array) {
        if (array.length == 0) return new int[0];
        int l = 0, r = array[0].length - 1, t = 0, b = array.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = array[t][i]; // left to right
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = array[i][r]; // top to bottom
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = array[b][i]; // right to left
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = array[i][l]; // bottom to top
            if (++l > r) break;
        }
        return res;
    }


    /**
     * LCR 188. 买卖芯片的最佳时机
     *
     * @param prices
     * @return
     */
    public int bestTiming(int[] prices) {
        int n = prices.length;

        if (n == 0 || n == 1) {
            return 0;
        }
        int[] mix = new int[n];
        mix[1] = prices[0];

        for (int i = 1; i < n - 1; i++) {
            mix[i + 1] = Math.min(mix[i], prices[i]);
        }

        int result = Integer.MIN_VALUE;

        for(int i = 1;i < n;i++){
            result = Math.max(prices[i] - mix[i],result);
        }
        return  result;
    }
}
