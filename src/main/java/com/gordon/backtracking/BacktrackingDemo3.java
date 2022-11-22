package com.gordon.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BacktrackingDemo3 {

    private final List<List<Integer>> result = new ArrayList<List<Integer>>();
    private final LinkedList<Integer> path = new LinkedList<>();

    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     * 示例:
     *
     * 输入: [1,2,2]
     * 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrackingSubset(nums,0);
        return result;
    }
    private void backtrackingSubset(int[] nums,int startIndex){
        result.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtrackingSubset(nums,i+1);
            path.removeLast();
        }
    }

    /**
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 示例:
     *
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     * 说明:
     *
     * 给定数组的长度不会超过15。
     * 数组中的整数范围是 [-100,100]。
     * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     */
    private List<List<Integer>> subResults = new ArrayList<List<Integer>>();
    private ArrayList<Integer> subPath = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrackingFind(nums,0);
        return subResults;
    }
    private void backtrackingFind(int[] nums,int startIndex) {
        if (subPath.size() > 1) {
            subResults.add(new ArrayList<>(subPath));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (!subPath.isEmpty()&& nums[i]<subPath.get(subPath.size()-1)){
                continue;
            }
            if (used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i]+100] =1;
            subPath.add(nums[i]);
            backtrackingFind(nums,i+1);
            subPath.remove(subPath.size() - 1);
        }
    }

    private List<List<Integer>> permuteResults = new ArrayList<List<Integer>>();
    private ArrayList<Integer> permutePath = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backtrackingPermute(nums, used);
        return permuteResults;
    }
    private void backtrackingPermute(int[] nums,boolean[] used){
        if (permutePath.size() == nums.length) {
            permuteResults.add(new ArrayList<>(permutePath));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permutePath.add(nums[i]);
            backtrackingPermute(nums, used);
            permutePath.remove(permutePath.size() - 1);
            used[i] = false;
        }
    }
}
