package Hot100;

public class Search {

    /**
     * 74. 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int x = n - 1;
        int y = 0;

        while (x > 0 && y < m) {
            if(matrix[x][y] == target){
                return true;
            }
            if(matrix[x][y] > target){
                x--;
            }else{
                y++;
            }
        }
        return false;
    }
}
