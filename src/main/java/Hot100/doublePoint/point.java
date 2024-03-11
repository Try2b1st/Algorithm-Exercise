package Hot100.doublePoint;

public class point {

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int result = 0;

        while (l < r) {
            if (height[l] <= height[r]){
                result = Math.max(result,(r - l) * height[l]);
                l++;
            }else{
                result = Math.max(result,(l - r) * height[r]);
                r--;
            }
        }

        return result;
    }
}
