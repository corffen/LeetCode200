package com.gordon.greedy;

import java.util.Arrays;

public class GreedyDemo {

    public static void main(String[] args) {
        int[] g = new int[]{1, 8, 6, 7};
        int[] s = new int[]{1, 3, 9, 7};
        GreedyDemo demo = new GreedyDemo();
        int children = demo.findContentChildren(g, s);
        System.out.println(children);
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        return result;
    }

    /**
     * 摆动序列
     * 获取一个数组中的摆动序列的最长子序列的长度
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int result = 1;
        int currentDiff = 0;
        int preDiff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currentDiff = nums[i + 1] - nums[i];
            if ((currentDiff > 0 && preDiff <= 0) || (currentDiff < 0 && preDiff >= 0)) {
                result++;
                preDiff = currentDiff;
            }
        }
        return result;
    }
}
