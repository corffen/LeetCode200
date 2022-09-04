package com.gordon.twopoints;

public class TwoPointDemo {

    public static void main(String[] args) {

    }

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[slow++] = nums[i];
            }
        }
        return slow;
    }
}
