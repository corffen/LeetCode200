package com.gordon.stack;

import java.util.ArrayDeque;

public class MyStack {
    private ArrayDeque<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        int size = queue.size();
        size--;
        while (size-- > 0) {
            queue.add(queue.pollFirst());
        }
        return queue.pollFirst();
    }

    public int top() {
        return queue.peekLast();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
