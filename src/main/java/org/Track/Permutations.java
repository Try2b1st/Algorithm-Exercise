package org.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    boolean[] used;

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrackingToPermute(nums, used);
        return result;
    }

    public void backtrackingToPermute(int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            backtrackingToPermute(nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrackingToPermuteUnique(nums, used);
        return result;
    }

    public void backtrackingToPermuteUnique(int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                backtrackingToPermute(nums, used);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
