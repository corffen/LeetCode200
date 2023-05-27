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
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
                System.out.println("dp[" + j + "]=" + dp[j]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target] == target;
    }

}
