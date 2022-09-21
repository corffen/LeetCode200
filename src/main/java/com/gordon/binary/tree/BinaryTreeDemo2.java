package com.gordon.binary.tree;

public class BinaryTreeDemo2 {
    /**
     * 判断一颗二叉树是否为平衡二叉树
     * 只需掌握递归写法即可
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getTreeHeight(root) != -1;
    }

    private int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getTreeHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getTreeHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        int height = Math.abs(leftHeight - rightHeight);
        return height > 1 ? -1 : Math.max(leftHeight, rightHeight) + 1;
    }
}
