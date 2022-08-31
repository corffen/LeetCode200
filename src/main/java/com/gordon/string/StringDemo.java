package com.gordon.string;

public class StringDemo {

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            swap(s,l,r);
            l++;
            r--;
        }
    }
    private void swap(char[] s,int p,int q){
        char temp = s[p];
        s[p] = s[q];
        s[q] = temp;
    }
}
