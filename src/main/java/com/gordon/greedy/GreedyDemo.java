package com.gordon.greedy;

import java.util.Arrays;

public class GreedyDemo {

    public static void main(String[] args) {
        int[] g = new int[]{1, 8, 6, 7};
        int[] s = new int[]{1, 3, 9, 7};
        GreedyDemo demo = new GreedyDemo();
        int children = demo.findContentChildren(g, s);
        System.out.println(children);
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        return result;
    }
}
