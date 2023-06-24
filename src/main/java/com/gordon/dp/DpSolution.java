package com.gordon.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
     *
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
        return sum - dp[target] - dp[target];
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
     *
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
     *
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

    /**
     * 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = IntStream.of(nums).sum();
        if (Math.abs(target) > sum) {
            return 0;
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        int ans = (sum + target) / 2;
        int[] dp = new int[ans + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = ans; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[ans];
    }

    /**
     * 零钱对换II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 1) {
            return 1;
        }
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 最长连续子序列
     * 强调的是连续
     * 只需要跟前一个值进行比较就可以
     */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 1) {
            return 1;
        }
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 718. 最长重复子数组
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     * <p>
     * 思路:
     * dp[i][j] = 表示以i-1为结尾,nums2以j-1为结尾时,二者的最长的子数组的长度
     * 那么当nums[i-1]==nums[j-1]时,满足的递推公式为dp[i][j] = dp[i-1][j-1]+1;
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int ans = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 思路:
     * 这题和上一个区别是,这道题的子串不需要连续.
     * dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char c2 = text2.charAt(j-1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 279. 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * <p>
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
     * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (set.contains(word) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 打家劫舍，相邻元素不能偷
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    /**
     * 不相交的线
     * 其实就是求最长公共子序列,一模一样的解法
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     */
    public boolean isSubsequence(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2]==len1;
    }

    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     *
     * 每步 可以删除任意一个字符串中的一个字符。
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //最长公共子序列的dp[i][j]表示[0-i-1]的word1 和[0-j-1]的word2 的最大长度
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //当相等时,长度就加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //不相等时,从上方或者左方 得到.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return len1 + len2 - dp[len1][len2] * 2;
    }

    public int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) dp[i][0] = i;
        for (int j = 0; j < word2.length() + 1; j++) dp[0][j] = j;

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2,
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
