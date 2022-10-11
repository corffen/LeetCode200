package com.gordon.binary.tree;

public class BinaryTreeDemo4 {
    /**
     * 二叉搜索树的公共祖先节点
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 二叉搜索树的公共最小祖先,只需要使用前序遍历,满足当前节点在[p,q]之间,就直接返回这个节点就好了.
     * 同时利用二叉树的有序性,进行方向的判断就好.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (right != null) {
                return right;
            }
        }
        if (root.val > p.val && root.val > q.val) {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            if (left != null) {
                return left;
            }
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key); //注意这里不是直接递归返回,而是用root来接收
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);//注意这里不是直接递归返回,而是用root来接收
        } else {
            if (root.left == null) { //左节点为空,返回右节点
                return root.right;
            }
            if (root.right == null) { //右节点为空,返回左节点
                return root.left;
            }
            TreeNode temp = root.right; //临时节点
            while (temp.left != null) { //右节点的最左节点
                temp = temp.left;
            }
            temp.left = root.left; //右节点的最左节点赋给待删除节点的左节点
            root = root.right; //然后删除当前节点
            return root;
        }
        return root;
    }

    /**
     * 修剪二叉搜索树
     * 1. 确定入参和返回值. 根据题目中的值就好
     * 2. 确定单层逻辑
     *      2.1 如果遇到空节点,返回null,就好
     *      2.2 如果当前节点的值小于low,那么就递归调用右边的节点,并返回
     *      2.3 如果当前节点的值大于high,那么就递归调用,返回左边节点
     *      2.4 如果当前节点在low-high中间,那么用当前节点的left接收左边的递归返回值
     *           用当前节点的right接收右边的递归返回值
     * 3. 最后返回root
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
