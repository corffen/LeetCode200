package com.gordon.array;

import com.sun.source.tree.IfTree;

import java.util.HashMap;

public class SlidingWindow {
    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 示例 2：
     * <p>
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                int subLen = j - i + 1;
                result = Math.min(result, subLen);
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 904. 水果成篮
     * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
     * <p>
     * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
     * <p>
     * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
     * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
     * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
     * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：fruits = [1,2,1]
     * 输出：3
     * 解释：可以采摘全部 3 棵树。
     * 示例 2：
     * <p>
     * 输入：fruits = [0,1,2,2]
     * 输出：3
     * 解释：可以采摘 [1,2,2] 这三棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
     * 示例 3：
     * <p>
     * 输入：fruits = [1,2,3,2,2]
     * 输出：4
     * 解释：可以采摘 [2,3,2,2] 这四棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
     * 示例 4：
     * <p>
     * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
     * 输出：5
     * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int i = 0;
        int len = fruits.length;
        int ans = 0;
        Counter counter = new Counter();
        //确认窗口的右边
        for (int j = 0; j < len; j++) {
            counter.increase(fruits[j]);
            //移动左边的窗口
            while (counter.size() >= 3) {
                counter.decrease(fruits[i]);
                if (counter.count(fruits[i]) == 0) {
                    counter.remove(fruits[i]);
                }
                i++;
            }
            //更新窗口的大小
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }


    static class Counter extends HashMap<Integer, Integer> {

        private int count(int key) {
            if (containsKey(key)) {
                return super.get(key);
            } else {
                return 0;
            }
        }

        public void increase(int key) {
            put(key, count(key) + 1);
        }

        public void decrease(int key) {
            put(key, count(key) - 1);
        }
    }
}
