package com.gordon.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BackTrackSolution {

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<List<Integer>>();
    private List<Integer> path = new ArrayList<Integer>();

    private void backtrack(int n, int k, int startIndex) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        //剪枝操作 n - (k - path.size()) + 1
        //当前已经有的个数是path.size(),还需要k-path.size()
        //当前的位置是i,那么i+(k-path.size())  必须小于n+1,
        //为什么要加1呢?带入一个数看一下就知道了.
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtrack(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 找出k个数,使得他们的值相加之合为n
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        backCombine(n, k, 1, 0);
        return combineAns;
    }

    private List<Integer> combinePath = new ArrayList<Integer>();
    private List<List<Integer>> combineAns = new ArrayList<List<Integer>>();

    /**
     * 确认入参和出参
     *
     * @param targetSum
     * @param k
     * @param startIndex 索引决定
     * @param sum
     */
    private void backCombine(int targetSum, int k, int startIndex, int sum) {
        //回溯的出口
        if (combinePath.size() == k) {
            if (targetSum == sum) {
                combineAns.add(new ArrayList<>(combinePath));
            }
            return;
        }
        //遍历条件,递归时纵向遍历,i是横向遍历,
        for (int i = startIndex; i <= 9; i++) {
            sum += i;
            combinePath.add(i);
            backCombine(targetSum, k, i + 1, sum);
            sum -= i;
            combinePath.remove(combinePath.size() - 1);
        }
    }

    //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
    String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 电话号码中的字母组合中
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return letters;
        }
        backtrackLetter(digits,0);
        return letters;
    }

    StringBuilder sb = new StringBuilder();
    List<String> letters = new ArrayList<String>();

    private void backtrackLetter(String digits, int index) {
        if (index == digits.length()) {
            letters.add(sb.toString());
            return;
        }
        String num = numString[digits.charAt(index) - '0'];
        for (int i = 0; i < num.length(); i++) {
            sb.append(num.charAt(i));
            //注意这里是index+1,而不是i+1,i是每次都需要从0开始的
            //index是同一组数据中的递归获取
            backtrackLetter(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
