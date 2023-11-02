package org.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        backtrackingToCombinationSum(candidates,target,0);
        return result;
    }

    public void backtrackingToCombinationSum(int[] nums,int target,int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        if(sum > target){
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            sum += nums[i];
            backtrackingToCombinationSum(nums,target,i);
            list.remove(list.size() - 1);
            sum -= nums[i];
        }
    }


}
