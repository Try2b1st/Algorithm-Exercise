package org.Track;

import java.util.ArrayList;
import java.util.List;

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
        int i = 0;
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
}
