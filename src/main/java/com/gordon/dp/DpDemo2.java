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

    /**
     * <a href="https://leetcode.cn/problems/last-stone-weight-ii/">1049. 最后一块石头的重量 II</a>
     * 思路: 本题可以转换成0,1背包问题.
     * 首先物品是stones,重量为stones[i],价值也是stones[i]
     * dp[j] 表示,背包容量为j所能装的物品的最大价值.对于本题来说就是最大重量.
     * 本题的背包容量大小是所有石头重量和的一半. 因为要抵消石头,最好是能够装到所有石头和的一半,剩下的石头与之相碰,必然消的也最多.
     *
     * dp的五个步骤
     * 1. 确定dp含义
     * 2. 确定状态转移方程
     * 3. 初始化初始值
     * 4. 确定遍历顺序
     * 5. 打印日志,观察数据是否与推导的一样
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = IntStream.of(stones).sum();
        int target = sum/2;
        int[] dp = new int[target+1];
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum-dp[target]-dp[target];
    }

    /**
     * <a href="https://leetcode.cn/problems/target-sum/">494. 目标和</a>
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = IntStream.of(nums).sum();
        int left = (sum+target)>>1;
        //注意这里必须是绝对值比较,因为target可能是负数
        if (sum < Math.abs(target)) {
            return 0;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        if(left<0) left=-left;
        int[] dp = new int[left + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >=nums[i] ; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }
}
