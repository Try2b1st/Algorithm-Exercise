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
        n = n/2;
        while (n>0) {
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
}
