package com.gordon.greedy;

public class GreedyDemo4 {
    /**
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">...</a>
     * 714. 买卖股票的最佳时机含手续费
     *
     * 思路:
     *  1. 用min用来记录最低的历史价格
     *  2. 当当前的价格-min-fee >0时,说明有利润,就累加起来
     *  3. 同时修改当前卖出后的最低历史价格为当前的价格-fee.
     *  4. 3步骤确定了,最低历史价格从卖出的那一刻开始重新记录
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i]<min) min = prices[i];
            if (prices[i]>min+fee){
                ans+=prices[i]-min-fee;
                min = prices[i]-fee;
            }
        }
        return ans;
    }
}
