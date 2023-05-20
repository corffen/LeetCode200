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
}
