package com.gordon.stack;

import java.util.ArrayDeque;

public class StackDemo {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            if (isLeft(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || !isMatch(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char c) {
        return c == '[' || c == '{' || c == '(';
    }

    private boolean isMatch(char top, char current) {
        return (top == '[' && current == ']') || (top == '{' && current == '}') || (top == '(' && current == ')');
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.reverse().toString();
    }

}
