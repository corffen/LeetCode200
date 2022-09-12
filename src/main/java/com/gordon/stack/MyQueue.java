package com.gordon.stack;

import java.util.ArrayDeque;

public class MyQueue {
    ArrayDeque<Integer> in = new ArrayDeque<Integer>();
    ArrayDeque<Integer> out = new ArrayDeque<Integer>();

    public MyQueue() {

    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
