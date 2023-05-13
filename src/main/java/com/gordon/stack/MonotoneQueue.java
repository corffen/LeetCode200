package com.gordon.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotoneQueue {

    Deque<Integer> queue;

    public MonotoneQueue() {
        queue = new ArrayDeque<>();
    }

    public void pop(int value){
        if(!queue.isEmpty()&&queue.peek()==value){
            queue.pop();
        }
    }

    public void push(int value){
        while(!queue.isEmpty()&&queue.getLast()<value){
            queue.removeLast();
        }
        queue.add(value);
    }
    public int front(){
        return queue.peek();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] answer = new int[n-k+1];
        MonotoneQueue queue = new MonotoneQueue();
        for (int i = 0; i < k; i++) {
            queue.push(nums[i]);
        }
        int index = 0;
        answer[index++] = queue.front();
        for (int i = k; i < n; i++) {
            queue.pop(nums[i-k]);
            queue.push(nums[i]);
            answer[index++] = queue.front();
        }
        return answer;
    }
}
