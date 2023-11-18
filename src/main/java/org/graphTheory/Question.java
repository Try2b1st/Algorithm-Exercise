package org.graphTheory;

import java.util.*;

public class Question {

    List<Integer> list = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 797. 所有可能的路径
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        list.add(0);
        dfsToAllPathsSourceTarget(graph, 0);
        return result;
    }

    public void dfsToAllPathsSourceTarget(int[][] graph, int current) {
        if (current == graph.length - 1) {
            result.add(new ArrayList<>(list));
            return;
        }

        int[] tempArray = graph[current];

        for (int j : tempArray) {
            list.add(j);
            dfsToAllPathsSourceTarget(graph, j);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 200. 岛屿数量
     *
     * @param grid
     * @return
     */
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; //四个方向
    boolean[][] visited;

    public int numIslands(char[][] grid) {
        int count = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfsToNumIslands(grid, visited, i, j);
                }
            }
        }
        return count;
    }

    public void dfsToNumIslands(char[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y] || grid[x][y] == '0') {
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            //越界
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }
            dfsToNumIslands(grid, visited, nextX, nextY);
        }
    }

    public void bfsToNumIslands(char[][] grid, boolean[][] visited, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int m = queue.peek()[0];
            int n = queue.peek()[1];
            queue.pop();

            for (int i = 0; i < 4; i++) {
                int nextX = m + dir[i][0];
                int nextY = n + dir[i][1];

                //越界
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                    continue;
                }
                if (!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    /**
     * 695. 岛屿的最大面积
     *
     * @param grid
     * @return
     */
    int maxM = 0;

    public int maxAreaOfIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    bfsToMaxAreaOfIsland(grid, visited, i, j);
                }
            }
        }
        return maxM;
    }

    public void bfsToMaxAreaOfIsland(int[][] grid, boolean[][] visited, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int currentM = 1;

        while (!queue.isEmpty()) {
            int m = queue.peek()[0];
            int n = queue.peek()[1];
            queue.pop();

            for (int i = 0; i < 4; i++) {
                int nextX = m + dir[i][0];
                int nextY = n + dir[i][1];

                //越界
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                    continue;
                }
                if (!visited[nextX][nextY] && grid[nextX][nextY] == 1) {
                    queue.add(new int[]{nextX, nextY});
                    currentM++;
                    visited[nextX][nextY] = true;
                }
            }
        }
        maxM = Math.max(maxM, currentM);
    }

    /**
     * 1020. 飞地的数量
     *
     * @param grid
     * @return
     */
    int numEnclavesCount = 0;
    public int numEnclaves(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid[0].length; i++) {
            if (!visited[0][i] && grid[0][i] == 1) {
                dfsToZero(grid, visited, 0, i);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (!visited[i][grid[0].length - 1] && grid[i][grid[0].length - 1] == 1) {
                dfsToZero(grid, visited, i, grid[0].length - 1);
            }
        }

        for (int i = 0; i < grid[0].length; i++) {
            if (!visited[grid.length - 1][i] && grid[grid.length - 1][i] == 1) {
                dfsToZero(grid, visited, grid.length - 1, i);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (!visited[i][0] && grid[i][0] == 1) {
                dfsToZero(grid, visited, i, 0);
            }
        }

        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfsToNumEnclaves(grid, visited, i, j);
                }
            }
        }
        return numEnclavesCount;
    }

    public void dfsToNumEnclaves(int[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        numEnclavesCount++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }

            dfsToNumEnclaves(grid, visited, nextX, nextY);
        }
    }

    public void dfsToZero(int[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        grid[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;
            }

            dfsToZero(grid, visited, nextX, nextY);
        }
    }
}
