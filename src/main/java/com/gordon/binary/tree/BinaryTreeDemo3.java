package com.gordon.binary.tree;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeDemo3 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        queue.add(root2);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            node1.val += node2.val;
            if (node1.left != null && node2.left != null) {
                queue.add(node1.left);
                queue.add(node2.left);
            } else {
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
            if (node1.right != null && node2.right != null) {
                queue.add(node1.right);
                queue.add(node2.right);
            } else {
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
        }
        return root1;
    }

    /**
     * 二叉搜索树
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                root = searchBST2(root.right, val);
            } else {
                root = searchBST2(root.left, val);
            }
        }
        return null;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBst(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min) {
            return false;
        }
        if (root.val >= max) {
            return false;
        }
        return isValidBst(root.left, min, root.val) && isValidBst(root.right, root.val, max);
    }

    /**
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traversal(root);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private TreeNode pre;
    private int result = Integer.MAX_VALUE;

    /**
     * 中序遍历,和上一个节点进行比较,求绝对差值,记录最小的值
     * @param root 根节点
     */
    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        traversal(root.left);
        if (pre != null) {
            result = Math.min(result, Math.abs(root.val - pre.val));
        }
        pre = root;
        traversal(root.right);
    }

    private Map<Integer, Integer> map = new HashMap<>();

    /**
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * 假定 BST 有如下定义：
     *
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        searchBstFrequency(root);
        List<Integer> result = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> mapToList =
                map.entrySet()
                        .stream()
                        .sorted((c1, c2) -> c2.getValue() - c1.getValue())
                        .collect(Collectors.toList());
        result.add(mapToList.get(0).getKey());
        for (int i = 1; i < mapToList.size(); i++) {
            if (Objects.equals(mapToList.get(i).getValue(), mapToList.get(i - 1).getValue())) {
                result.add(mapToList.get(i).getKey());
            } else {
                break;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void searchBstFrequency(TreeNode root) {
        if (root == null) {
            return;
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        searchBstFrequency(root.left);
        searchBstFrequency(root.right);
    }

    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        findMode1(root);
        return countList.stream().mapToInt(Integer::intValue).toArray();
    }

    private TreeNode preNode;
    private int count = 0;
    private int maxCount = 0;
    private List<Integer> countList = new ArrayList<Integer>();

    private void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);
        if (preNode == null || preNode.val != root.val) {
            count = 1;
        } else {
            count++;
        }
        if (count > maxCount) {
            countList.clear();
            countList.add(root.val);
            maxCount = count;
        } else if (count == maxCount) {
            countList.add(root.val);
        }
        findMode1(root.right);
    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 最小公共节点
     * 1. 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。
     * <p>
     * 2. 在回溯的过程中，必然要遍历整棵二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
     * <p>
     * 3. 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        }
        return left;
    }
}
