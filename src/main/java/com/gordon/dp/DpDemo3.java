package com.gordon.dp;

import com.gordon.binary.tree.TreeNode;

public class DpDemo3 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    /**
     * <a href="https://leetcode.cn/problems/house-robber-ii/">213. 打家劫舍 II</a>
     * 打家劫舍,收尾相邻的解法
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robMax(nums, 0, len - 1), robMax(nums, 1, len));
    }
    private int robMax(int[] nums,int start,int end) {
        int x = 0,y,z=0;
        for (int i = start; i < end; i++) {
            y=z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] ans = robTree(root);
        return Math.max(ans[0], ans[1]);
    }
    private int[] robTree(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int[] left = robTree(root.left);
        int[] right = robTree(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
