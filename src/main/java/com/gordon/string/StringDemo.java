package com.gordon.string;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class StringDemo {
    /**
     * 利用双指针,收尾交换元素即可
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            swap(s, l, r);
            l++;
            r--;
        }
    }

    private void swap(char[] s, int p, int q) {
        char temp = s[p];
        s[p] = s[q];
        s[q] = temp;
    }

    /**
     * 每2k个元素,交换前k个字符串
     * 思路:每次循环时,步调时2k
     * 然后用双指针收尾交换
     * 左指针指向i
     * 右指针为min(n-1,start+k-1)
     */
    public String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();
        for (int i = 0, n = charArray.length; i < n; i += 2 * k) {
            int start = i;
            int end = Math.min(n - 1, start + k - 1);
            while (start < end) {
                swap(charArray, start, end);
                start++;
                end--;
            }
        }
        return new String(charArray);
    }

    /**
     * 反转字符串中的单词,按空格分隔,然后反转添加上空格
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverse(sb, 0, sb.length() - 1);
        reverseEach(sb);
        return sb.toString();
    }

    /**
     * 去除两边的空格,然后单词中间的空格,只保留一个
     *
     * @param s
     * @return
     */
    private StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    private StringBuilder removeSpace2(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)!=' '){
                if (sb.length()>0){
                    sb.append(' ');
                }
                while(i<s.length()&&s.charAt(i)!=' '){
                    sb.append(s.charAt(i));
                    i++;
                }
            }
        }
        return sb;
    }

    /**
     * 反转stringbuilder
     */
    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * 反转StringBuilder中的每一个单词
     *
     * @param sb
     */
    private void reverseEach(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverse(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }

    /**
     * 用%20来替换空格
     * 用双指针来实现
     * 左指针是原始字符串的末尾
     * 右指针是新字符串(源字符串+空格替换为3个空格)的末尾
     * 从后往前遍历原字符串,遇到空格,就依次填入%20,否则就填入字符
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("  ");
            }
        }
        if (sb.length() == 0) {
            return s;
        }
        int left = s.length() - 1;
        s += sb.toString();
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }

    public String replaceSpace2(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c:s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 翻转前半部分
     * 翻转后半部分
     * 翻转整体
     */
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, n - 1);
        reverse(sb, n, len - 1);
        return sb.reverse().toString();
    }

    public String reverseLeftWords2(String s, int n) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            deque.add(c);
        }
        while (n-- > 0) {
            Character first = deque.pollFirst();
            deque.add(first);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : deque) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * kmp算法求 子字符串所在的位置
     * 1. 利用模式字符串,求出next数组,表示最长的前后缀的公共长度
     * 2. 匹配时,用i指向文本字符串的位置,不会回退该指针
     * 3. 逐个与模式字符串的第j+1个字符进行比较
     *      注意:第j+1个字符,表示的模式字符串中要与文本第i个字符进行比较的元素
     *      如果匹配成功,就移动j
     *      如果匹配不成功,就从next数组中,找到当前字符对应的位置j
     * @param haystack 文本字符串
     * @param needle 模式字符串
     * @return
     */
    public int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j + 1 == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    private void getNext(int[] next, String s) {
        int j = -1;
        next[0] = -1;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    private void getNext2(int[] next, String s) {
        int j = -1;
        next[0] = -1;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    /**
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    public static String getMaxStr(String s, int max)
    {
        StringBuilder sb = new StringBuilder();
        int length = 0;
        int needMax = Math.min(s.getBytes(StandardCharsets.UTF_8).length, max);
        for(int i = 0; i < s.length(); i++)
        {
            String c = s.charAt(i)+"";
            System.out.print(c+"的长度:");
            int cLen= c.getBytes(StandardCharsets.UTF_8).length;
            System.out.println(cLen);
            length+=cLen;
            sb.append(s.charAt(i));
            if (length>=needMax){
                break;
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
//        String s = "abc啊世界!!!";
//        String ans = getMaxStr(s, 10);
//        System.out.println(ans);

        String s = "hello world  ";
        String s1 = "  hello   world  ";
        StringDemo demo = new StringDemo();
        String sr =demo.removeSpace2(s).toString();
        String s1r =demo.removeSpace2(s1).toString();
        System.out.println(s+",remove space ="+sr);
        System.out.println(s1+",remove space ="+s1r);
    }
}
