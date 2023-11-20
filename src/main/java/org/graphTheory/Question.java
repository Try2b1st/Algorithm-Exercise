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


    /**
     * 130. 被围绕的区域
     *
     * @param board
     */
    public void solve(char[][] board) {
        int rowSize = board.length;
        int colSize = board[0].length;

        for (int i = 0; i < rowSize; i++) {
            if (board[i][colSize - 1] == 'O') {
                bfsToSolve(board, i, colSize - 1);
            }
            if (board[i][0] == 'O') {
                bfsToSolve(board, i, 0);
            }
        }

        for (int i = 0; i < colSize; i++) {
            if (board[0][i] == 'O') {
                bfsToSolve(board, 0, i);
            }
            if (board[rowSize - 1][i] == 'O') {
                bfsToSolve(board, rowSize - 1, i);
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void bfsToSolve(char[][] board, int x, int y) {
        board[x][y] = 'A';

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{x, y});

        while (!deque.isEmpty()) {
            int m = deque.peek()[0];
            int n = deque.peek()[1];
            deque.pop();

            for (int i = 0; i < 4; i++) {
                int nextX = m + dir[i][0];
                int nextY = n + dir[i][1];

                //越界
                if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length) {
                    continue;
                }

                if (board[nextX][nextY] == 'O') {
                    deque.add(new int[]{nextX, nextY});
                    board[nextX][nextY] = 'A';
                }
            }
        }
    }

    /**
     * 417. 太平洋大西洋水流问题
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][][] visited = new boolean[heights.length][heights[0].length][2];
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            dfsToPacificAtlantic(heights, visited, i, 0, 0);
            dfsToPacificAtlantic(heights, visited, i, heights[0].length - 1, 1);
        }
        for (int i = 0; i < heights[0].length; i++) {
            dfsToPacificAtlantic(heights, visited, 0, i, 0);
            dfsToPacificAtlantic(heights, visited, heights.length - 1, i, 1);
        }


        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (visited[i][j][0] && visited[i][j][1]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(new ArrayList<>(list));
                }
            }
        }

        return result;
    }

    public void dfsToPacificAtlantic(int[][] heights, boolean[][][] visited, int x, int y, int sign) {
        visited[x][y][sign] = true;
        for (int[] temp : dir) {
            int nextX = x + temp[0];
            int nextY = y + temp[1];

            if (nextX < 0 || nextX >= heights.length || nextY < 0 || nextY >= heights[0].length) {
                continue;
            }
            if (heights[x][y] > heights[nextX][nextY] || visited[nextX][nextY][sign]) {
                continue;
            }
            dfsToPacificAtlantic(heights, visited, nextX, nextY, sign);
        }

    }


    /**
     * 827. 最大人工岛
     *
     * @param grid
     * @return
     */
    public int dfs(int[][] grid, int row, int col, int mark) {
        int ans = 0;
        grid[row][col] = mark;
        for (int[] current : dir) {
            int curRow = row + current[0], curCol = col + current[1];
            if (curRow < 0 || curRow >= grid.length || curCol < 0 || curCol >= grid.length) continue;  // 越界
            if (grid[curRow][curCol] == 1)
                ans += 1 + dfs(grid, curRow, curCol, mark);
        }
        return ans;
    }

    public int largestIsland(int[][] grid) {
        int ans = Integer.MIN_VALUE, size = grid.length, mark = 2;
        Map<Integer, Integer> getSize = new HashMap<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == 1) {
                    int areaSize = 1 + dfs(grid, row, col, mark);
                    getSize.put(mark++, areaSize);
                }
            }
        }
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // 当前位置如果不是 0 那么直接跳过，因为我们只能把 0 变成 1
                if (grid[row][col] != 0) continue;
                Set<Integer> hashSet = new HashSet<>();     // 防止同一个区域被重复计算
                // 计算从当前位置开始获取的 1 的数量，初始化 1 是因为把当前位置的 0 转换成了 1
                int curSize = 1;
                for (int[] current : dir) {
                    int curRow = row + current[0], curCol = col + current[1];
                    if (curRow < 0 || curRow >= grid.length || curCol < 0 || curCol >= grid.length) continue;
                    int curMark = grid[curRow][curCol];     // 获取对应位置的标记
                    // 如果标记存在 hashSet 中说明该标记被记录过一次，如果不存在 getSize 中说明该标记是无效标记(此时 curMark = 0)
                    if (hashSet.contains(curMark) || !getSize.containsKey(curMark)) continue;
                    hashSet.add(curMark);
                    curSize += getSize.get(curMark);
                }
                ans = Math.max(ans, curSize);
            }
        }
        // 当 ans == Integer.MIN_VALUE 说明矩阵数组中不存在 0，全都是有效区域，返回数组大小即可
        return ans == Integer.MIN_VALUE ? size * size : ans;
    }


    /**
     * 127. 单词接龙
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (set.isEmpty() || set.contains(endWord)) {
            return 0;
        }
        Deque<String> deque = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();

        deque.offer(beginWord);
        map.put(beginWord, 1);

        while (!deque.isEmpty()) {
            String current = deque.pop();
            int path = map.get(current);

            for (int i = 0; i < current.length(); i++) {
                char[] chars = current.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String s = String.valueOf(chars);
                    if (s.equals(endWord)) {
                        return path + 1;
                    }

                    if (set.contains(s) && !map.containsKey(s)) {
                        deque.offer(s);
                        map.put(s, path + 1);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 841. 钥匙和房间
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] in = new boolean[rooms.size()];
        Deque<Integer> deque = new ArrayDeque<>();

        in[0] = true;
        deque.offer(0);

        while (!deque.isEmpty()) {
            int roomNumber = deque.pop();
            List<Integer> keys = rooms.get(roomNumber);

            for (int i = 0; i < keys.size(); i++) {
                int room = keys.get(i);
                if (!in[room]) {
                    in[room] = true;
                    deque.offer(room);
                }
            }
        }

        for (boolean b : in) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    /**
     * 463. 岛屿的周长
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nextI = i + dir[k][0];
                        int nextJ = j + dir[k][1];
                        if(nextI < 0 || nextJ < 0 || nextI >= grid.length || nextJ >= grid[0].length || grid[nextI][nextJ] == 0 ){
                            sum++;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
