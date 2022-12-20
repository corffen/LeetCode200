package com.gordon.greedy;

import com.gordon.utils.LogUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GreedyDemo3 {

    public static void main(String[] args) {
        LogUtils.log("greedy demo3");
    }

    /**
     * https://leetcode.cn/problems/queue-reconstruction-by-height/
     * 406. 根据身高重建队列
     * <p>
     * 思路:
     * 1. 按身高h从大到小排序,如果身高相同,就按照第二个元素的升序排列
     * 2. 用链表不断的插入元素
     * 3. 插入的规则是,位置为排完序的元素的k值,插入的值是当前元素
     * 4. 元素插入完,就是最后的答案
     * <p>
     * tips: 二维数组的排序,用lambda
     * linkedList转为数组,调用toArray方法就好,传入一个数组
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[people.length][]);
    }

    /**
     * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
     * 452. 用最少数量的箭引爆气球
     * <p>
     * 思路: 1. 首先需要对气球排序,规则是按照区间排序,也就是比较第一个值
     * 2. 如果当前气球的第一个值,大于上一个气球的第二个值. 说明需要多加一支箭才能引爆
     * 3. 否则就更新当前气球的结束位置为 和上一个气球结束位置的较小值.
     * <p>
     * count默认值是1,因为需要从第二个气球开始遍历
     * 最后返回count
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }

    /**
     * <a href="https://leetcode.cn/problems/non-overlapping-intervals/">...</a>
     * 435. 无重叠区间
     * <p>
     * 思路:
     * 1. 首先需要对数组进行排序,按照每个元素的右边界进行从小到大排序
     * 2. 然后统计不相交的个数
     * 3. 最后用总的数量减去不相交的个数,就是需要删除的个数
     * <p>
     * 不相交的个数,是如何统计的呢?  如果当前结束的边界<=当前遍历元素的左边界
     * 说明不相交,此时不相交的个数自增,同时更新右边界为当前元素的右边界
     * 注意： 这里是按照右边界进行排序的
     * 统计count记录的是不相交的个数
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len == 1 || len == 0) {
            return 0;
        }
        Arrays.sort(intervals, (Comparator.comparingInt(o -> o[1])));
        int count = 1;
        int end = intervals[0][1];

        for (int i = 1; i < len; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return len - count;
    }

    /**
     * <a href="https://leetcode.cn/problems/partition-labels/">...</a>
     * 763. 划分字母区间
     * 思路:
     * 1. 首先统计每个字符所在的最远位置
     * 2. 用一个right来记录当前遍历过程中的最远位置
     * 3. 如果i等于right,说明已经到达分隔的最远位置了.将分隔的位置添加进来,并更新上次的左边界
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i)-'a'] = i;
        }
        List<Integer> list = new LinkedList<>();
        int left = -1;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']);
            if (i == right) {
                list.add(i-left);
                left = i;
            }
        }
        return list;
    }

    /**
     * <a href="https://leetcode.cn/problems/merge-intervals/">...</a>
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                res.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }else {
                end = Math.max(end,intervals[i][1]);
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][]);
    }
}
