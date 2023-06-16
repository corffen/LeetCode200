package com.gordon.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrackSolution {
    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<List<Integer>>();
    private List<Integer> path = new ArrayList<Integer>();

    /**
     * 从1-n中选出k个数
     */
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
     * 多了一个条件就是和为n
     * 变化的是从1-9这9个数中选择，是固定的
     * 所以递归条件，需要多加上sum，用于统计路径上的和
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
            //满足条件才将路径添加到结果中去
            if (targetSum == sum) {
                combineAns.add(new ArrayList<>(combinePath));
            }
            return;
        }
        //遍历条件,递归时纵向遍历,i是横向遍历,
        for (int i = startIndex; i <= 9; i++) {
            //统计路径上的和
            sum += i;
            combinePath.add(i);//记录路径上的值
            backCombine(targetSum, k, i + 1, sum);
            sum -= i; //回溯的条件
            combinePath.remove(combinePath.size() - 1);
        }
    }

    //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
    String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 电话号码中的字母组合中
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return letters;
        }
        backtrackLetter(digits, 0);
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

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为： [ [7], [2,2,3] ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为： [ [2,2,2,2], [2,3,3], [3,5] ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrackComSum(candidates, target,0,0);
        return ans2;
    }

    private List<List<Integer>> ans2 = new ArrayList<List<Integer>>();
    private List<Integer> path2 = new ArrayList<>();

    private void backtrackComSum(int[] candidates, int target, int sum, int index) {
        if (sum == target) {
            ans2.add(new ArrayList<>(path2));
            return;
        }
        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            path2.add(candidates[i]);
            sum += candidates[i];
            backtrackComSum(candidates, target, sum, i);
            path2.remove(path2.size() - 1);
            sum -= candidates[i];
        }
    }
}
