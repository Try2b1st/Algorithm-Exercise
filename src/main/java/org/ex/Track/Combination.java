package org.ex.Track;

import java.util.*;

public class Combination {

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrackingToCombine(n, k, 1);
        return result;
    }

    public void backtrackingToCombine(int n, int k, int startIndex) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            backtrackingToCombine(n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    int sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        int i = 0;
        backtrackingToCombinationSum3(k, n, 1);
        return result;
    }

    public void backtrackingToCombinationSum3(int k, int n, int startIndex) {
        if (list.size() == n && sum == k) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i <= k; i++) {
            list.add(i);
            sum += i;
            backtrackingToCombinationSum3(k, n, i + 1);
            list.remove(list.size() - 1);
            sum -= i;
        }
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    List<String> stringList = new ArrayList<>();
    String[] nums = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    StringBuilder stringBuilder = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (Objects.equals(digits, "")) {
            return null;
        }
        backtrackingToLetterCombinations(digits, 0);
        return stringList;
    }

    public void backtrackingToLetterCombinations(String digits, int startIndex) {
        if (stringBuilder.length() == digits.length()) {
            String s = String.valueOf(stringBuilder);
            stringList.add(s);
            return;
        }
        if (startIndex >= digits.length()) {
            return;
        }
        String numberChar = nums[Integer.parseInt(new String(String.valueOf(digits.charAt(startIndex))))];
        for (int i = 0; i < numberChar.length(); i++) {
            stringBuilder.append(numberChar.charAt(i));
            backtrackingToLetterCombinations(digits, startIndex + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrackingToCombinationSum(candidates, target, 0);
        return result;
    }

    public void backtrackingToCombinationSum(int[] nums, int target, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            sum += nums[i];
            backtrackingToCombinationSum(nums, target, i);
            list.remove(list.size() - 1);
            sum -= nums[i];
        }
    }

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    boolean[] used;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(used, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);

        backtrackingToCombinationSum2(candidates, target, 0);
        return result;
    }

    public void backtrackingToCombinationSum2(int[] nums, int target, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i-1]) {
                continue;
            }
            list.add(nums[i]);
            sum += nums[i];
            used[i] = true;
            backtrackingToCombinationSum2(nums, target, i + 1);
            used[i] = false;
            list.remove(list.size() - 1);
            sum -= nums[i];
        }
    }
}
