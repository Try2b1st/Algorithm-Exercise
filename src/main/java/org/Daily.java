package org;

import java.util.Arrays;

public class Daily {

    /**
     * 11.10 每日一题
     * 2300. 咒语和药水的成功对数
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] result = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int start = 0;
            int end = potions.length - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if ((long) potions[mid] * spells[i] >= success) {
                    result[i] = potions.length - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return result;
    }
}
