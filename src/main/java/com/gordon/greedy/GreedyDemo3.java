package com.gordon.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class GreedyDemo3 {

    /**
     * https://leetcode.cn/problems/queue-reconstruction-by-height/
     * 406. 根据身高重建队列
     *
     * 思路:
     * 1. 按身高h从大到小排序,如果身高相同,就按照第二个元素的升序排列
     * 2. 用链表不断的插入元素
     * 3. 插入的规则是,位置为排完序的元素的k值,插入的值是当前元素
     * 4. 元素插入完,就是最后的答案
     *
     * tips: 二维数组的排序,用lambda
     * linkedList转为数组,调用toArray方法就好,传入一个数组
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            if (a[0]==b[0]){
                return Integer.compare(a[1],b[1]);
            }
            return Integer.compare(b[0],a[0]);
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
     *
     * 思路: 1. 首先需要对气球排序,规则是按照区间排序,也就是比较第一个值
     * 2. 如果当前气球的第一个值,大于上一个气球的第二个值. 说明需要多加一支箭才能引爆
     * 3. 否则就更新当前气球的结束位置为 和上一个气球结束位置的较小值.
     *
     * count默认值是1,因为需要从第二个气球开始遍历
     * 最后返回count
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0]>points[i-1][1]){
                count++;
            }else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }
}
