package com.gordon.backtracking;

import java.util.*;

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
     *
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
        //确定终止逻辑
        if (index == digits.length()) {
            letterResult.add(sb.toString());
            return;
        }
        //单层逻辑怎么写
        String str = numString[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            //递归调用逻辑
            backtracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backCombinationSum(candidates, target, path, result, 0, 0);
        return result;
    }

    private void backCombinationSum(int[] candidates, int target, List<Integer> path, List<List<Integer>> res, int sum, int idx) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backCombinationSum(candidates, target, path, res, sum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    private List<List<String>> resultPartitions = new ArrayList<List<String>>();
    private Deque<String> deque = new ArrayDeque<String>();

    public List<List<String>> partition(String s) {
        backtrackingPartition(s, 0);
        return resultPartitions;
    }

    /**
     * 将字符串切割成所有的回文字符串
     *
     * @param s
     * @param startIndex
     */
    private void backtrackingPartition(String s, int startIndex) {
        if (startIndex >= s.length()) {
            resultPartitions.add(new ArrayList<>(deque));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                deque.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backtrackingPartition(s, i + 1);
            deque.removeLast();
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    List<String> ips = new ArrayList<String>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() > 12) {
            return ips;
        }
        backtrackIps(s, 0, 0);
        return ips;
    }

    private void backtrackIps(String s, int startIndex, int num) {
        if (num == 3) {
            if (isValidIp(s, startIndex, s.length() - 1)) {
                ips.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValidIp(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                num++;
                backtrackIps(s,i+2,num);
                num--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }else {
                break;
            }
        }
    }

    private boolean isValidIp(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.startsWith("0") && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i < end; i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
            num+= 10*num+(s.charAt(i)-'0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> subResults = new ArrayList<>();
    private ArrayDeque<Integer> subPath = new ArrayDeque<>();
    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper(nums,0);
        return subResults;
    }
    private void subsetsHelper(int[] nums,int startIndex){
        subResults.add(new ArrayList<Integer>(subPath));
        for (int i = startIndex; i < nums.length; i++) {
            subPath.add(nums[i]);
            subsetsHelper(nums,i+1);
            subPath.removeLast();
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        BackTrackingDemo demo = new BackTrackingDemo();
        demo.partition(s);
    }
}
