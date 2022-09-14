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
     *
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

    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int i = stack.pop();
                int j = stack.pop();
                stack.push(j / i);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int idx = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //注意这里的peek对应的索引,必须在[i-k+1,i]之间,否则就移除队首元素
            while (!queue.isEmpty() && queue.peek() < (i - k + 1)) {
                queue.poll();
            }
            //遍历过程中,如果队尾的元素一直大于当前的元素,那么他就是单调的,否则就移除队尾的元素
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k - 1) {
                result[idx++] = nums[queue.peek()];
            }
        }
        return result;
    }

}
