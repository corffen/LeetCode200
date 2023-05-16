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
    private void bt2(int[] candidates, int target,int index,int sum){
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            //注意这里是大于index,而不是大于0
            if (i>index&&candidates[i]==candidates[i-1]){
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            bt2(candidates,target,i+1,sum);
            sum -= candidates[i];
            path.pollLast();
        }
    }

    /**
     * 分割回文字符串
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        bt(s,0);
        return lists;
    }

    List<List<String>> lists = new ArrayList<List<String>>();
    Deque<String> deque = new ArrayDeque<String>();

    private void bt(String s,int index){
        if (index>=s.length()){
            lists.add(new ArrayList<>(deque));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isP(s,index,i)) {
                deque.add(s.substring(index,i+1));
            }else {
                continue;
            }
            bt(s,i+1);
            deque.pollLast();
        }
    }
    private boolean isP(String s,int l,int r){
        while(l<r){
            if (s.charAt(l)!=s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
