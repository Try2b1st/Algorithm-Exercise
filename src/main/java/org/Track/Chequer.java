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
            if(isValid(n,row,i,chessboard)){
                chessboard[row][i] = 'Q';
                backtrackingToSolveNQueens(n, row + 1, chessboard);
                chessboard[row][i] = '.';
            }
        }
    }

    //验证合法性
    public boolean isValid(int n, int row, int col, char[][] chessboard) {
        //检查列
        for (int i = 0; i < n; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        //检查45°斜线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chessboard[i][j]=='Q'){
                return false;
            }
        }

        //检查135°斜线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if(chessboard[i][j]=='Q'){
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

}
