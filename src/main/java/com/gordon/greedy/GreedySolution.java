package com.gordon.greedy;

import java.util.*;
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
     * <p>
     * 在用一个totalSum，用于累加所有的剩余油量
     * <p>
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
                curSum = 0;
                start = i + 1;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }

    /**
     * 135. 分发糖果
     * <p>
     * 每个孩子至少一个糖果
     * 相邻孩子，得分更高的，获取更多的糖果
     * <p>
     * 如果相邻孩子评分一样，他的糖果不一定和评分相等的孩子一致，有可能会比他低
     * 比如 【1，3，2，2，1】 ---》 【1，2，1，2，1】
     */
    public int candy(int[] ratings) {

        int[] ans = new int[ratings.length];
        Arrays.fill(ans, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                ans[i] = ans[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                ans[i] = Math.max(ans[i], ans[i + 1] + 1);
                System.out.println("i=" + i + ",ans[i]=" + ans[i]);
            }
        }
        System.out.println("ans[]=" + Arrays.toString(ans));
        return IntStream.of(ans).sum();
    }

    /**
     * 860. 柠檬水找零
     * <p>
     * 只需要维护 5，10币种的个数
     * 遇到10，就把5减一，10加一  不满足就return false
     * 遇到20 把10和5各减一，或者单独减去3张5元的   不满足就return false
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                five--;
                ten++;
            } else if (bill == 20) {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 406. 根据身高重建队列
     * <p>
     * 1.对身高进行降序，相等再按位置升序
     * 2.对队列按照位置进行插入元素
     * 3.将队列转成数组
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o2[0], o1[0]);
        });
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] person : people) {
            queue.add(person[1], person);
        }
        return queue.toArray(new int[people.length][]);
    }

    /**
     * 452. 用最少数量的箭引爆气球
     * 1. 对数组进行左边界升序排序
     * 2.从1开始遍历数组，如果当前的左边界大于上一个气球的有边界， 累加结果
     * 否则，就更新当前气球的右边界为 上一个气球的右边界和当前值的较小值
     * <p>
     * 3.返回统计结果
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int ans = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                ans++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return ans;
    }

    /**
     * 435. 无重叠区间
     * 1. 按照右边界排序，升序
     * 2. 从1开始遍历，end记录最小的右边界，如果end小于等于当前节点的左边界
     * 说明有重叠，那么更新end为当前节点的右边界，并将交叉的个数+1
     * 3. 用所有的节点个数，减去交叉的个数，就是需要移除的节点个数
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int across = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                end = intervals[i][1];
                across++;
            }
        }
        return intervals.length - across;
    }

    /**
     * 763. 划分字母区间
     * 1. 用hash表记录每个字母出现的最晚的位置
     * 2.遍历数组，更新最远距离
     * 如果当前的i等于最远距离，就更新left，同时将分割的距离放进结果集中
     */
    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            hash[charArray[i] - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < charArray.length; i++) {
            right = Math.max(right, hash[charArray[i] - 'a']);
            if (i == right) {
                ans.add(right - left + 1);
                left = i + 1;
            }
        }
        return ans;
    }

    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> queue = new LinkedList<>();
        Arrays.sort(intervals, (Comparator.comparingInt(o -> o[0])));
        queue.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = queue.peekLast();//注意这里是取出最后一个，不要用peek方法
            if (intervals[i][0] > last[1]) {
                queue.add(intervals[i]);
            } else {
                int start = last[0];
                int end = Math.max(last[1], intervals[i][1]);
                queue.pollLast();
                queue.add(new int[]{start, end});
            }
        }
        return queue.toArray(new int[queue.size()][]);
    }

    public static void main(String[] args) {
      int[][] intervals = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        GreedySolution solution = new GreedySolution();
        solution.merge(intervals);
    }
}
