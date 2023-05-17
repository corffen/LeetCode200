package com.gordon.backtracking;

import java.util.*;

public class BackTrackSolution2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        bt2(candidates, target, 0, 0);
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    private void bt2(int[] candidates, int target, int index, int sum) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            //注意这里是大于index,而不是大于0
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            bt2(candidates, target, i + 1, sum);
            sum -= candidates[i];
            path.pollLast();
        }
    }

    /**
     * 分割回文字符串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        bt(s, 0);
        return lists;
    }

    List<List<String>> lists = new ArrayList<List<String>>();
    Deque<String> deque = new ArrayDeque<String>();

    private void bt(String s, int index) {
        if (index >= s.length()) {
            lists.add(new ArrayList<>(deque));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isP(s, index, i)) {
                deque.add(s.substring(index, i + 1));
            } else {
                continue;
            }
            bt(s, i + 1);
            deque.pollLast();
        }
    }

    private boolean isP(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return ips;
        }
        btIp(s, 0, 0);
        return ips;
    }

    private List<String> ips = new ArrayList<String>();

    private void btIp(String s, int index, int num) {
        if (num == 3) {
            if (isValid(s, index, s.length() - 1)) {
                ips.add(s);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                num++;
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                btIp(s, i + 2, num);
                s = s.substring(0, i + 1) + s.substring(i + 2);
                num--;
            } else {
                break;
            }
        }
    }

    private boolean isValid(String s, int l, int r) {
        if (l > r) {
            return false;
        }
        if (s.startsWith("0") && l != r) {
            return false;
        }
        int num = 0;
        for (int i = l; i < r; i++) {
            int c = s.charAt(i) - '0';
            if (c > 9 || c < 0) {
                return false;
            }
            num = num * 10 + c;
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * 子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        btSub(nums, 0);
        return subs;
    }

    List<List<Integer>> subs = new ArrayList<List<Integer>>();
    Deque<Integer> pathSub = new LinkedList<>();

    private void btSub(int[] nums, int index) {
        subs.add(new ArrayList<>(pathSub));
        for (int i = index; i < nums.length; i++) {
            pathSub.add(nums[i]);
            btSub(nums, i + 1);
            pathSub.pollLast();
        }
    }

    List<List<Integer>> subsDup = new ArrayList<List<Integer>>();
    Deque<Integer> pathSubDup = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        btSubDup(nums,0);
        return subsDup;
    }
    private void btSubDup(int[] num,int index){
        subsDup.add(new ArrayList<>(pathSubDup));
        for (int i = index; i < num.length; i++) {
            if (i>index&&num[i-1]==num[i]){
                continue;
            }
            pathSubDup.add(num[i]);
            btSubDup(num,i+1);
            pathSubDup.pollLast();
        }
    }
}
