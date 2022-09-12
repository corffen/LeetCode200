package com.gordon.stack;

import java.util.ArrayDeque;

public class StackDemo {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            if (isLeft(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || !isMatch(stack.pop(),c)) {
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
}
