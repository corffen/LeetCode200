package com.gordon.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InterViewSolution {
    public static void main(String[] args) {
        InterViewSolution solution = new InterViewSolution();
//        testSplit(solution);
//        testConverted(solution);

//        testDuplicate(solution);
        int i = solution.lastRemaining3(5, 3);
        System.out.println("i="+i);
    }

    private static void testDuplicate(InterViewSolution solution) {
        int duplicates = solution.findDuplicates(new int[]{1, 3, 2, 1, 3});
        System.out.println("duplicate=" + duplicates);
    }

    private static void testConverted(InterViewSolution solution) {
        String converted = solution.convert(new String[]{"aa", "bbb", "cc"});
        System.out.println(converted);
    }

    private static void testSplit(InterViewSolution solution) {
        String[] s1 = solution.split("aa&&bbb&&c");
        String[] s2 = solution.split("aa&&bb&&");
        String[] s3 = solution.split("&&b&a&&");

        System.out.println("s1=" + Arrays.toString(s1));
        System.out.println("s2=" + Arrays.toString(s2));
        System.out.println("s3=" + Arrays.toString(s3));
    }

    //1.对字符串按照&&进行切割成数组
    public String[] split(String str) {
        String s = str;
        List<String> ans = new ArrayList<>();
        int pos;
        while ((pos = s.indexOf("&&")) != -1) {
            if (pos != 0) {
                String sub = s.substring(0, pos);
                ans.add(sub);
            }
            s = s.substring(pos + "&&".length());
        }
        if (!s.isBlank()) {
            ans.add(s);
        }
        return ans.toArray(new String[0]);
    }

    public String convert(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
            sb.append("&&");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    /**
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找数组中重复的元素
     *
     * @param arr
     */
    public int findDuplicates(int[] arr) {
        int[] hash = new int[1000];
        for (int num : arr) {
            hash[num]++;
            if (hash[num] == 2) {
                return num;
            }
        }
        return -1;
    }

    public int lastRemaining3(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }


    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    /**
     * 求数组的所有子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper(nums, 0);
        return result;
    }

    private void subsetsHelper(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length) { //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }


    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    public int lastRemaining2(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }


    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = part(nums, l, r);
        quickSort(nums, l, p - 1);
        quickSort(nums, p + 1, r);
    }

    private int part(int[] nums, int l, int r) {
        int v = nums[l];
        int i = l;
        int j = r + 1;
        while (true) {
            while (nums[++i] < v) {
                if (i >= r) {
                    break;
                }
            }
            while (nums[--j] > v) {
                if (j <= l) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        int t = nums[l];
        nums[l] = nums[j];
        nums[j] = t;
        return j;
    }
}
