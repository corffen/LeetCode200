package com.gordon.stack;

import java.util.LinkedList;

public class MyStack2 {
    LinkedList<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        size--;
        while (size-- > 0) {
            queue.add(queue.pop());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
