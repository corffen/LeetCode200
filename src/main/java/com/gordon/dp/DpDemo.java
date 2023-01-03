package com.gordon.dp;

import com.gordon.utils.LogUtils;

public class DpDemo {

    public static void main(String[] args) {
        int[] costs = {1, 10, 1, 1, 1, 100, 1, 1, 100, 1};
//        DpDemo demo = new DpDemo();
//        demo.minCostClimbingStairs(costs);
        for (int i = 0; i < costs.length && costs[i]!=100; i++) {
            LogUtils.log("i="+i+" cost="+costs[i]);
        }
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

    /**
     * <a href="https://leetcode.cn/problems/integer-break/description/">343. 整数拆分</a>
     *
     * 思路:
     * 1. 明确dp含义, 假定dp[i] 表示第i个数字,所能拆分的最大值
     * 2. 确认dp状态转移方程. dp[i]所表达的就是 (i-j)*j,与dp[i-j]*j 中的较大值
     *      其中的j表示的是拆分的数字,它的最大值为i的一半.
     *      (i-j)*j可以认为是拆分成两个数字,所表示的乘积值. 而dp[i-j]表示的是(i-j)拆分的最大值,然后再乘以j
     * 3. 明确初始值. dp[2] = 1
     * 4. 确定遍历过程  从第三个数开始遍历. 用不到dp[0]和dp[1]
     * 5. 检查dp数组,可以打印出来,看看和推导出来的是不是一样的.
     *
     * tips:j遍历时,采取的最大值是 i/2. 注意这里可以等于
     * i是从3开始遍历的.
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[i - j] * j));
            }
        }
        return dp[n];
    }

    /**
     * 贪心算法,分成n个3相乘,再乘以剩下的数字
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int result = 1;
        while (n > 4) {
            result*=3;
            n-=3;
        }
        result*=n;
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/unique-binary-search-trees/">96. 不同的二叉搜索树</a>
     *  思路:
     *   1. 定义dp[i]为 从1到i,可以组成的二叉树种数
     *   2. 很明显dp[1] =1 ,dp[2] = 2.
     *   3. 对于dp[3]而言,如果以1位头结点. 那么有2种.
     *   以2为头结点,结果是左子树是1,右子树是3. 结果有1种
     *   以3为头结点,结果是2种.
     *   dp[3] = dp[0]*dp[2]+dp[1]*dp[1]+dp[2]*dp[0]
     *          以1为头结点的左节点数*右节点数+以2为头节点的左节点数*右节点数+以3为头结点的左节点数*右
     *   dp[i] += dp[j-1]*dp[i-j] j的遍历为内循环,从1到i.
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i]+= dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
