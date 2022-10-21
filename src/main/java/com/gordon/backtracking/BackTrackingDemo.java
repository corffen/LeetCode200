package com.gordon.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrackingDemo {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        result.clear();
        path.clear();
        backtracking(n, k, 1);
        return result;
    }

    private void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }

    /**
     * 注意题目中的n,k参数 LeetCode中k在前面
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combinationSum3(int n, int k) {
        result.clear();
        path.clear();
        backtracking3(n, k, 0, 1);
        return result;
    }

    private void backtracking3(int targetSum, int k, int sum, int startIndex) {
        if (sum > targetSum) {
            return;
        }
        if (path.size() == k) {
            if (targetSum == sum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backtracking3(targetSum, k, sum, i + 1);
            path.removeLast();
            sum -= i;
        }
    }

    private List<String> letterResult = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();
    //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
    private String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 电话中的字母组合
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return letterResult;
        }
        backtracking(digits, 0);
        return letterResult;
    }

    private void backtracking(String digits, int index) {
        if (index == digits.length()) {
            letterResult.add(sb.toString());
            return;
        }
        String str = numString[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backtracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
