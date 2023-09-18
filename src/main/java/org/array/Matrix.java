package org.array;

/**
 * @author 下水道的小老鼠
 */
public class Matrix {
    /**
     * 59. 螺旋矩阵 II
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int startX = 0;
        int i;
        int startY = 0;
        int j;
        int count = 1;
        int offSize = 1;
        boolean flag = false;
        int size = n;
        if (n % 2 == 1) {
            flag = true;
            n -= 1;
        }
        n = n / 2;
        while (n > 0) {
            j = startY;
            i = startX;
            for (; j < size - offSize; j++) {
                result[i][j] = count++;
            }
            for (; i < size - offSize; i++) {
                result[i][j] = count++;
            }
            for (; j > startY; j--) {
                result[i][j] = count++;
            }
            for (; i > startX; i--) {
                result[i][j] = count++;
            }
            startX++;
            startY++;
            offSize++;
            n--;
        }
        if (flag) {
            result[startX][startY] = count;
        }
        return result;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int maxI = matrix.length;
        int maxJ = matrix[0].length;
        int up = 0;
        int down = maxI - 1;
        int right = maxJ - 1;
        int left = 0;

        int[] result = new int[maxI * maxJ];
        int count = 0;
        while (true) {
            for (int i = left; i <= right; i++) {
                result[count++] = matrix[up][i];
            }
            if (++up > down) {
                break;
            }
            for (int i = up; i <= down; i++) {
                result[count++] = matrix[i][right];
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                result[count++] = matrix[down][i];
            }
            if (--down < up) {
                break;
            }
            for (int i = down; i >= up; i--) {
                result[count++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return result;
    }
}
