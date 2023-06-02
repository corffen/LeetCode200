package com.gordon.interview;

import java.util.*;

public class InterViewSolution {

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    /**
     * 求数组的所有子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper(nums, 0);
        return result;
    }

    private void subsetsHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }


    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (n > 1) {
            idx = (idx+m-1)%n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    public int lastRemaining2(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans+m)%i;
        }
        return ans;
    }
}
