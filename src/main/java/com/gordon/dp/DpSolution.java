package com.gordon.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DpSolution {

    /**
     * 确定dp状态转移方程
     * 每个元素，只能从上方和左方到达
     * 1.定义dp的含义，dp[i][j]表示从（0，0）--》（i，j)的路径的个数
     * 2.那么状态转移方程就是dp[i][j] = dp[i-1][j]+dp[i][j-1];
     * 3.初始化。  dp[i][0]  第一行和第一列必然只有一种路径，所以他们都是1
     * 4.dp必须用上一次的结果，利用状态转移方程，得到当前的结果。 有了初始值，和方程式，有终结点
     * 最后就可以得到最终的答案。
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 遇到障碍物了，他的dp值就是0
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        //如果在起点或终点出现了障碍，直接返回0
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        //注意这里，如果条件不满足，就不会往后++了
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 拆分整数,使用dp实现
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];
    }

    /**
     * 需要累加j作为根节点的个数,所以j的值是[1-i];
     * i是从[2-n]
     * 初始化时,需要初始化dp[0]和dp[1],这里需要注意dp[0]的值必须是1
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //当j为根节点时，左子树的个数是dp[j-1]而右子树是dp[i-j];
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 分割等和子集
     * 转化为0-1背包问题（物品只能选择一次）
     * 数字的大小既为价值，又为其重量
     * 背包的大小是数组和的一半，设为target。
     * 最后dp【target】所能容纳的最大价值，等于target，说明满足结果
     */
    public boolean canPartition(int[] nums) {
        int sum = IntStream.of(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        //先遍历物品
        for (int num : nums) {
            //再遍历背包
            for (int j = target; j >= num; j--) {
                //0,1背包递推公式
                dp[j] = Math.max(dp[j], dp[j - num] + num);
                System.out.println("dp[" + j + "]=" + dp[j]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target] == target;
    }

    /**
     * 最后一块石头的重量
     * 用容量为总重量一半的背包尽可能多的装石头，跟上一道题是一样的
     * 最后sum-dp【target】 是剩余石头的重量，因为target是向下取整，必然有sum-dp[target] >=dp[target]
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = IntStream.of(stones).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int num : stones) {
            for (int j = target; j >= num; j--) {
                //容量为j的背包,所能容下的最多的石头重量
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        //向下取整,sum-dp[target] 就是背包中剩下的重量
        return sum-dp[target]-dp[target];
    }

    /**
     * ❎❎❎❎❎❎❎❎❎❎
     * 排序用大的相撞，结果是不对的。
     * 不能从大到小，依次消除
     * ❎❎❎❎❎❎❎❎❎❎
     */
    public int lastStoneWeightII2(int[] stones) {
        Arrays.sort(stones);
        int ans = stones[stones.length - 1];
        for (int i = stones.length - 2; i >= 0; i--) {
            ans = Math.abs(stones[i] - ans);
        }
        return ans;
    }

    /**
     * 组合总和,
     * 这道题是求全排列的(3,1)和(1,3)是两组答案
     * 对于完全背包来说,就需要先遍历背包
     * 然后遍历物品
     * 判断条件是背包的大小大于 物品的大小
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始化dp数组为最大值
        Arrays.fill(dp, max);
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int coin : coins) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coin; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coin] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }
}
