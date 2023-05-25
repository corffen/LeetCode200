package com.gordon.dp;

public class DpSolution {

    /**
     * 确定dp状态转移方程
     * 每个元素，只能从上方和左方到达
     * 1.定义dp的含义，dp[i][j]表示从（0，0）--》（i，j)的路径的个数
     * 2.那么状态转移方程就是dp[i][j] = dp[i-1][j]+dp[i][j-1];
     * 3.初始化。  dp[i][0]  第一行和第一列必然只有一种路径，所以他们都是1
     * 4.dp必须用上一次的结果，利用状态转移方程，得到当前的结果。 有了初始值，和方程式，有终结点
     * 最后就可以得到最终的答案。
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
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
