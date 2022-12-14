package com.gordon.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class GreedyDemo2 {

    public static void main(String[] args) {
        GreedyDemo2 demo = new GreedyDemo2();
        int[] array = {1, 0, 2};
        demo.candy(array);
    }

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
     * 买卖股票的最佳时机 II
     * 思路: 从第二天开始,每天产生的利润是今天的值-昨天的值
     * 如果利润>0,就累加起来.
     * 一直到数组遍历完毕, 记录所有的累加的利润就是最终解
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            //从第二天开始,每天的利润为今天的值减去前一天的值
            int profit = prices[i] - prices[i - 1];
            //如果利润大于0,就累加这个利润
            if (profit > 0) {
                result += profit;
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
     *
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

    /**
     * https://leetcode.cn/problems/jump-game-ii/
     * 45. 跳跃游戏 II
     * 最小步数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int curDistance = 0;
        int ans = 0;
        int nextDistance = 0;
        for (int i = 0; i < len; i++) {
            nextDistance = Math.max(nextDistance, i + nums[i]);
            if (i == curDistance) {
                if (curDistance != len - 1) {
                    ans++;
                    curDistance = nextDistance;
                    if (nextDistance >= len - 1) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    public int jump2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int curDistance = 0;
        int ans = 0;
        int nextDistance = 0;
        for (int i = 0; i < len-1; i++) {
            nextDistance = Math.max(nextDistance, i + nums[i]);
            if (i == curDistance) {
                curDistance = nextDistance;
                ans++;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
     * 1005. K 次取反后最大化的数组和
     * <p>
     * 思路: 1.对数组按照绝对值的大小,降序排序
     * 2. 如果当前的数是负值,就改变为正的.(如果k还大于0的话)
     * 3. 遍历完数组后,如果k为奇数,说明数组中所有的数据都是正数了,就把最后一个数变成负数
     * 4. 把所有的数据都累加起来,就是最终答案.
     * <p>
     * 贪心思路:
     * 1. 遇到绝对值大的负数就变成正数. 直到k用完
     * 2. 所有的数都是正数的,就把最小的正数变成负数
     * <p>
     * 额外知识点: Arrays.sort(nums,compartor),这里的nums必须是对象类型的数组,才能使用
     * 所以可以用 IntStream.of(nums)
     * .boxed()
     * .sorted(compartor)
     * .mapToInt(Integer::intValue)
     * .toArray();
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue)
                .toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0 && k > 0) {
                k--;
                nums[i] *= -1;
            }
        }
        if (k % 2 == 1) {
            nums[len - 1] *= -1;
        }
        return Arrays.stream(nums).sum();
    }

    /**
     * https://leetcode.cn/problems/gas-station/
     * 134. 加油站
     * <p>
     * 思路:
     * 1. 每个加油站的剩余量rest[i]为gas[i] - cost[i]。
     * <p>
     * i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，
     * 起始位置从i+1算起，再从0计算curSum。等遍历完,start就是最终的起点位置.
     * 2. 如果遍历完之后,totalSum< 0 ,说明从哪里都不能完成 就返回-1.
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0, len = gas.length; i < len; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }

    /**
     * https://leetcode.cn/problems/candy/
     * 135. 分发糖果
     * 1. 首先声明一个数组给每人分配一个糖果
     * 2. 正序遍历,如果后面的比前面的大,就把当前的值加一
     * 3. 然后再逆序遍历,如果前面的值比前一个大,就取当前值与后一个值的较大值
     * 4. 最后累加数组求和
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] array = new int[len];
        Arrays.fill(array, 1);
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                array[i] = array[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                array[i] = Math.max(array[i], array[i + 1] + 1);
            }
        }
        return Arrays.stream(array).sum();
    }

    /**
     * https://leetcode.cn/problems/lemonade-change/
     * 860. 柠檬水找零
     * 思路:金币只有三种 5,10,20
     * 遍历数组,如果碰到5,就把金币5的数量加一
     * 如果碰到10,就把十的数量加一
     * 如果碰到20
     *      如果当前10>0,就把金币10减一,金币5也减一
     *      否则金币5减三
     * 当前层,如果金币5的数量小于0或者10的数量小于0 说明不够找零.返回false
     * 遍历完,说明可以执行完,就返回true
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                five--;
                ten++;
            } else if (bill == 20) {
                if (ten > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0) {
                return false;
            }
        }
        return true;
    }


}
