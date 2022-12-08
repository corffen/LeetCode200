package com.gordon.greedy;

public class GreedyDemo2 {

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
     *  买卖股票的最佳时机 II
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            //从第二天开始,每天的利润为今天的值减去前一天的值
            int profit = prices[i]-prices[i-1];
            //如果利润大于0,就累加这个利润
            if (profit > 0) {
                result+=profit;
            }
        }
        return result;
    }
}
