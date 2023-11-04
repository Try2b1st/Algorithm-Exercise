package org.Track;

import java.util.*;

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

    /**
     * 491. 递增子序列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrackingToFindSubsequences(nums, 0);
        return result;
    }

    public void backtrackingToFindSubsequences(int[] nums, int startIndex) {
        if (list.size() >= 2) {
            result.add(new ArrayList<>(list));
        }

        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if ((!list.isEmpty() && nums[i] < list.get(list.size()-1)) || (!set.isEmpty() && set.contains(nums[i]))) {
                continue;
            }
            set.add(nums[i]);
            list.add(nums[i]);
            backtrackingToFindSubsequences(nums, i+1);
            list.remove(list.size() - 1);
        }
    }

}
