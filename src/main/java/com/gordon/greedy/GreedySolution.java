package com.gordon.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class GreedySolution {
    /**
     * 最大子序列和
     *
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
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 可以多次买卖股票
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
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
     * <p>
     * 所以每次遍历时,找到当前跳跃所能覆盖的最大距离
     * 如果还没有到达最后一个的前一步,
     * 当前的i==最大覆盖距离时,就需要把步数+1
     * 然后更新当前的最大覆盖距离
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

    /**
     * 1005. K 次取反后最大化的数组和
     * 1. 按绝对值大小进行排序
     * 2. 遍历数组,先把负数变为正数
     * 3. 如果k>0,就判断是否为奇数,是的话就把最后一位变为负数
     * 4. 求数组的和
     * 每一步都是最优,最后的结果也是最优
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue)
                .toArray();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[nums.length - 1] = -1 * nums[nums.length - 1];
        }
        return IntStream.of(nums).sum();
    }

    /**
     * 134. 加油站
     * 累加剩余油量
     * 如果遍历到i，此时累加的剩余量小于0 ，就start = i+1,也就是i之前的都不行。同时将累加的和置为0
     *
     * 在用一个totalSum，用于累加所有的剩余油量
     *
     * 如果遍历完，totalSum<0说明不存在返回-1
     * 最后返回start 就是要求得的答案
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum =0;
                start = i+1;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }
}
