package com.gordon.binary.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeDemo2 {
    /**
     * 判断一颗二叉树是否为平衡二叉树
     * 只需掌握递归写法即可
     *
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

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (result == null) {
            return result;
        }
        ArrayList<Integer> paths = new ArrayList<>();
        getTreePaths(root, result, paths);
        return result;
    }

    private void getTreePaths(TreeNode currentNode, List<String> result,
                              ArrayList<Integer> paths) {
        paths.add(currentNode.val);
        if (currentNode.left == null && currentNode.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i));
                sb.append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            result.add(sb.toString());
            return;
        }
        if (currentNode.left != null) {
            getTreePaths(currentNode.left, result, paths);
            paths.remove(paths.size() - 1);
        }
        if (currentNode.right != null) {
            getTreePaths(currentNode.right, result, paths);
            paths.remove(paths.size() - 1);
        }
    }
}
