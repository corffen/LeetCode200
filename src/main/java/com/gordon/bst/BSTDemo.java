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

    /**
     * 删除目标节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        root = tDeleteNode(root, key);
        return root;
    }
    private TreeNode tDeleteNode(TreeNode root,int val){
        if (root == null) {
            return null;
        }
        if (root.val>val){
            root.left = tDeleteNode(root.left,val);
        }else if (root.val<val){
            root.right = tDeleteNode(root.right,val);
        }else{
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.val = temp.val;
            root.right = tDeleteNode(root.right, temp.val);
        }
        return root;
    }

    /**
     * 修剪二叉树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val>high){
            return trimBST(root.left, low, high);
        } else if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTreeNode(nums, 0, nums.length);
    }
    private TreeNode buildTreeNode(int[] nums,int l,int r){
        if (l >= r) {
            return null;
        }
        int mid = l +(r-l)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTreeNode(nums, l, mid);
        root.right = buildTreeNode(nums, mid+1,r);
        return root;
    }

    /**
     * 二叉树转换成累加树
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        traverseBST(root);
        return root;
    }
    private int preTreeNode =0;
    private void traverseBST(TreeNode root){
        if (root == null) {
            return;
        }
        traverseBST(root.right);
        preTreeNode+= root.val;
        root.val=preTreeNode;
        traverseBST(root.left);
    }
}
