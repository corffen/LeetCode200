package com.gordon.bst;

import com.gordon.binary.tree.TreeNode;

import java.util.*;

public class BSTDemo {
    /**
     * 获取二叉树中的最小差值
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        traverse(root);
        return ans == Integer.MAX_VALUE?0:ans;
    }
    private TreeNode pre;
    private int ans = Integer.MAX_VALUE;
    private void traverse(TreeNode root){
        if (root == null) {
            return;
        }
        traverse(root.left);
        if (pre != null) {
            ans = Math.min(ans, Math.abs(root.val - pre.val));
        }
        pre = root;
        traverse(root.right);
    }

    public int[] findMode(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        traverse(root,map);
        List<int[]> freq = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            freq.add(new int[]{entry.getKey(), entry.getValue()});
        }
       freq.sort((o1, o2) -> Integer.compare(o2[1], o1[1]));
        List<Integer> result = new ArrayList<>();
        int max = freq.get(0)[1];
        result.add(freq.get(0)[0]);
        for (int i = 1; i < freq.size(); i++) {
            if (freq.get(i)[1] == max) {
                result.add(freq.get(i)[0]);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    private void traverse(TreeNode root,Map<Integer,Integer> map){
        if (root == null) {
            return;
        }
        map.put(root.val,map.getOrDefault(root.val,0)+1);
        traverse(root.left, map);
        traverse(root.right, map);
    }

    public int[] findMode2(TreeNode root) {
        traverseTreeNode(root);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    private int count =0;
    private int maxCount =0;
    private TreeNode preNode;
    List<Integer> result = new ArrayList<Integer>();
    private void traverseTreeNode(TreeNode root){
        if (root == null) {
            return;
        }
        traverseTreeNode(root.left);
        if (preNode == null) {
            count=1;
        } else if (preNode.val == root.val) {
            count++;
        }else {
            count =1;
        }
        if (count == maxCount) {
            result.add(root.val);
        }
        if (count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(root.val);
        }
        preNode = root;
        traverseTreeNode(root.right);
    }

    /**
     * 二叉树中插入元素
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val<val){
            root.right = insertIntoBST(root.right, val);
        }else if (root.val>val){
            root.left = insertIntoBST(root.left,val);
        }
        return root;
    }
}
