package com.gordon.binary.tree;

import java.awt.image.renderable.RenderableImage;
import java.util.*;

public class BinaryTreeDemo2 {
    /**
     * 完全二叉树的个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点
            return (1 << leftDepth) + countNodes(root.right);
        } else {// 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }

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
        if (root == null) {
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

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        int mid = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            mid = root.left.val;
        }
        return left + right + mid;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                result += node.left.val;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    /**
     * 求二叉树左下角的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    public int findBottomLeftValue2(TreeNode root) {
        getBottomValue(root, 0);
        return result;
    }

    private int maxDepth = Integer.MIN_VALUE;
    private int result = 0;

    private void getBottomValue(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            if (depth > maxDepth) {
                result = root.val;
                maxDepth = depth;
            }
            return;
        }
        if (root.left != null) {
            depth++;
            getBottomValue(root.left, depth);
            depth--;
        }
        if (root.right != null) {
            depth++;
            getBottomValue(root.right, depth);
            depth--;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return pathSumHelper(root, targetSum);
    }

    private boolean pathSumHelper(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        if (root.left != null) {
            boolean left = pathSumHelper(root.left, targetSum);
            if (left) {
                return true;
            }
        }
        if (root.right != null) {
            boolean right = pathSumHelper(root.right, targetSum);
            return right;
        }
        return false;
    }

    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode buildTreeHelper(int[] inorder, int leftStart, int leftEnd, int[] postorder, int rightStart, int rightEnd) {
        if (leftStart >= leftEnd || rightStart >= rightEnd) {
            return null;
        }
        int rootValue = postorder[rightEnd - 1];
        int rootIndex = inorderMap.get(rootValue);
        TreeNode root = new TreeNode(rootValue);
        int leftLen = rootIndex - leftStart;
        root.left = buildTreeHelper(inorder, leftStart, rootIndex, postorder, rightStart, rightStart + leftLen);
        root.right = buildTreeHelper(inorder, rootIndex + 1, leftEnd, postorder, rightStart + leftLen, rightEnd - 1);
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length);
    }

    private TreeNode buildMaxTree(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
        if (left + 1 == right) {
            return new TreeNode(nums[left]);
        }
        int maxIndex = left;
        int max = nums[maxIndex];
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = buildMaxTree(nums, left, maxIndex);
        root.right = buildMaxTree(nums, maxIndex + 1, right);
        return root;
    }
}
