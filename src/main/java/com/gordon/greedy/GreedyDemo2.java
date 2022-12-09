package com.gordon.greedy;

public class GreedyDemo2 {

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
     *  买卖股票的最佳时机 II
     *  思路: 从第二天开始,每天产生的利润是今天的值-昨天的值
     *  如果利润>0,就累加起来.
     *  一直到数组遍历完毕, 记录所有的累加的利润就是最终解
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

    /**
     * https://leetcode.cn/problems/jump-game/
     * 跳跃游戏
     * 从0开始记录当前元素可能跳跃的范围,也就是覆盖值.(确定循环的逻辑)
     * 在覆盖值范围内,向前移动元素,然后更新更大的覆盖值.(更新最大值)
     * 如果覆盖值能够大于等于最后一个元素的位置,那么说明是可以达到的.(判断条件,终止条件)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= len - 1) {
                return true;
            }
        }
        return false;
    }
}
