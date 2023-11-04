package org.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrackingToSubsets(nums, 0);
        return result;
    }

    public void backtrackingToSubsets(int[] nums, int startIndex) {
        result.add(new ArrayList<>(list));
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            backtrackingToSubsets(nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 90. 子集 II
     *
     * @param nums
     * @return
     */
    boolean[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrackingToSubsetsWithDup(nums, 0);
        return result;
    }

    public void backtrackingToSubsetsWithDup(int[] nums, int startIndex) {
        result.add(new ArrayList<>(list));
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            backtrackingToSubsetsWithDup(nums, i + 1);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
