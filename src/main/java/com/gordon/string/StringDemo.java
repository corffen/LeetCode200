package com.gordon.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringDemo {

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

    public String reverseWords2(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split(" "));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverse(sb, 0, sb.length() - 1);
        reverseEach(sb);
        return sb.toString();
    }

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

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

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

    //�滻�ַ����еĿո�
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

    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, n - 1);
        reverse(sb, n, len - 1);
        return sb.reverse().toString();
    }

    /**
     * kmp算法求 子字符串所在的位置
     *
     * @param haystack
     * @param needle
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
}
