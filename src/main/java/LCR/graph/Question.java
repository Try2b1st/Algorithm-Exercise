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

}
