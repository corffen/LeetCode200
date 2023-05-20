package com.gordon.greedy;

public class GreedySolution {
    /**
     * 最大子序列和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 可以多次买卖股票
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }

    /**
     * 55. 跳跃游戏
     * 当前所在的位置,是其能跳跃的最大距离
     * 看看能不能从第一个位置,调到最后一个位置
     */
    public boolean canJump(int[] nums) {
        int cover = 0;
        if (nums.length == 1) {
            return true;
        }
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 45. 跳跃游戏 II
     * 注意,本题是一定能到达最后一步,要做的是
     * 找到到达最后一步所需要的最小步数.
     *
     * 所以每次遍历时,找到当前跳跃所能覆盖的最大距离
     * 如果还没有到达最后一个的前一步,
     *  当前的i==最大覆盖距离时,就需要把步数+1
     *  然后更新当前的最大覆盖距离
     */
    public int jump(int[] nums) {
        int step = 0;
        int currDist = 0;
        int nextDist = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextDist = Math.max(nextDist, nums[i] + i);
            if (i == currDist) {
                step++;
                currDist = nextDist;
            }
        }
        return step;
    }
}
