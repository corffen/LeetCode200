package com.gordon.array;

public class Sorted2Array {
    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     * <p>
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     */
    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1;
        int[] sorted = new int[nums.length];
        int k = nums.length;
        while (k>0) {
            if (nums[l] * nums[l] < nums[r] * nums[r]) {
                sorted[--k] = nums[r] * nums[r];
                r--;
            } else {
                sorted[--k] = nums[l] * nums[l];
                l++;
            }
        }
        return sorted;
    }
}
