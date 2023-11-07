package org.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chequer {

    /**
     * 51. N皇后
     *
     * @param n
     * @return
     */
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backtrackingToSolveNQueens(n, 0, chessboard);
        return result;
    }

    public void backtrackingToSolveNQueens(int n, int row, char[][] chessboard) {
        if (row == n) {
            result.add(ArraysToList(chessboard));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValidSolveNQueens(n, row, i, chessboard)) {
                chessboard[row][i] = 'Q';
                backtrackingToSolveNQueens(n, row + 1, chessboard);
                chessboard[row][i] = '.';
            }
        }
    }

    //验证合法性
    public boolean isValidSolveNQueens(int n, int row, int col, char[][] chessboard) {
        //检查列
        for (int i = 0; i < n; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        //检查45°斜线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        //检查135°斜线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    //数组转为List
    public List<String> ArraysToList(char[][] chessboard) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < chessboard[0].length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < chessboard.length; j++) {
                stringBuilder.append(chessboard[i][j]);
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }


    /**
     * 37. 解数独
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtrackingToSolveSudoku(board);
    }

    public boolean backtrackingToSolveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValidSolveSudoku(k, i, j, board)) {
                            board[i][j] = k;
                            if (backtrackingToSolveSudoku(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSolveSudoku(char number, int row, int col, char[][] board) {
        //检查列
        for (int i = 0; i < 9; i++) {
            if (number == board[i][col]) {
                return false;
            }
        }
        //检查行
        for (int i = 0; i < 9; i++) {
            if (number == board[row][i]) {
                return false;
            }
        }
        //检查小的九方格
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }


}
