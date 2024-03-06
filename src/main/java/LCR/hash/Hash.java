package LCR.hash;

import java.util.HashSet;
import java.util.Set;

public class Hash {

    /**
     * LCR 120. 寻找文件副本
     *
     * @param documents
     * @return
     */
    public int findRepeatDocument(int[] documents) {
//        Set<Integer> set = new HashSet<>();
//        for (int doc : documents) {
//            if(set.contains(doc)){
//                return doc;
//            }
//            set.add(doc);
//        }
//        return -1;
        int i = 0;
        while (i < documents.length) {
            if (documents[i] == i) {
                i++;
                continue;
            }

            if (documents[documents[i]] == documents[i]) {
                return documents[i];
            }
            int temp = documents[i];
            documents[i] = documents[documents[i]];
            documents[documents[i]] = temp;
        }
        return -1;
    }

    /**
     * 121. 寻找目标值 - 二维数组
     * 思路：从数组的右上角上看，可以将其看作二叉搜索树
     *
     * @param plants
     * @param target
     * @return
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        int x = plants.length - 1;
        int y = 0;

        while (x > 0 && y < plants[0].length) {
            if (plants[x][y] == target) {
                return true;
            }
            if (plants[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }
        return false;
    }
}
