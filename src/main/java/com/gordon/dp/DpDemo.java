package com.gordon.dp;

public class DpDemo {

    public static void main(String[] args) {
        int[] costs = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        DpDemo demo = new DpDemo();
        demo.minCostClimbingStairs(costs);
    }

    /**
     * <a href="https://leetcode.cn/problems/min-cost-climbing-stairs/">...</a>
     * 746. 使用最小花费爬楼梯
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[len];
    }

    /**
     * <a href="https://leetcode.cn/problems/unique-paths/">不同路径</a>
     * 62. 不同路径
     * <p>
     * 思路:
     * 1. 明确dp的含义.  定义dp[i][j] 为从(0,0)到(i,j)有几条路径
     * 2. 明确状态转移. 很显然dp[i][j]只能从dp[i][j-1]+dp[i-1][j]中来.要么从左边来,要么从右边来
     * 3. 明确初始值   对于dp[i][0],每一行的第一个元素,只有一种路径. 同样的dp[0][j],每一列的第一个元素,也只有一种路径
     * 4. 确定遍历顺序  对于二维数组,从左往右一层一层遍历即可
     * 5. 举例推到dp数组  可以打印出来数组,看看和推出来的结果是否一致.
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
     * <a href="https://leetcode.cn/problems/unique-paths-ii/">...</a>
     * 63. 不同路径 II
     *  思路:
     *   1. 这题跟不同路径I 是一个思路
     *   2. 不同点在于:
     *   a,初始化时,如果当前第一排的某个元素为障碍物,那么后面的都应该为0
     *   b,同一列的也是如此
     *   c,遍历过程中,如果遇到障碍物,那么应该将当前的dp值设置为0.
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
