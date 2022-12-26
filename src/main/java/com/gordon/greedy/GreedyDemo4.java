package com.gordon.greedy;

public class GreedyDemo4 {
    /**
     * <a href="https://leetcode.cn/problems/monotone-increasing-digits/">单调递增的数字</a>
     * 738. 单调递增的数字
     * 思路:
     * 1. 首先将数字转成字符串,然后转成字符串组
     * 2. 从后往前遍历,如果当前值比后一个值大,说明不是递增的,就把第i个元素减一,同时更新记录这个位置start
     * 3.遍历完之后,从start到字符串结尾,要把每一位都变成9
     * 4.最后将字符串转成int. 这里现将字符串组转成string,然后调用integer.parse方法.
     * @param n 给定的数字
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = chars.length - 2; i >= 0; i--) {
           if (chars[i]>chars[i+1]){
               chars[i] -=1;
               start = i+1;
           }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
