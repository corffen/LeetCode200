package com.gordon.dp;

import java.util.stream.IntStream;

public class DpDemo2 {
    /**
     * <a href="https://leetcode.cn/problems/partition-equal-subset-sum/">416. 分割等和子集</a>
     * 思路:
     * 这题可以转换成0,1背包问题.
     * 背包容量就是数组和的一半. target
     * nums[i] 表示物品的容量,和价值
     *
     * 1. 确定dp数组的含义. dp[j] 表示容量为j的背包,所占的最大价值
     * 2. 确定状态转移方程.
     *      dp[j] 应该为以下二者中的较大值
     *      dp[j],不选择物品时的最大价值
     *      dp[j-nums[i]]+nums[i] 选择物品i时的最大价值
     * 3. 初始化dp数组.  对于价值都是正数的就初始化为0.
     *      如果价值有负数,就初始化为负无穷
     * 4. 确定遍历顺序
     *      一维数组,应该先遍历物品,在逆序遍历背包.
     *      遍历物品,从索引0,遍历每个物品
     *      遍历背包,是从大到小,条件是当前的容量大小 j>= nums[i]
     * 5. 打印dp数组,确认推导过程
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = IntStream.of(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum/2;
        int[] dp = new int[target+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }
}
