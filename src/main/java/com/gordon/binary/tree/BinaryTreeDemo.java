package com.gordon.binary.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeDemo {

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }

    /**
     * 前序遍历: 中-->左-->右
     *
     * @param root
     * @param result
     */
    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        postorder(root, result);
        return result;
    }

    /**
     * 左-->右-->中
     *
     * @param root
     * @param result
     */
    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        inorder(root, result);
        return result;
    }

    /**
     * 左->中-->右
     *
     * @param root
     * @param result
     */
    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 注意前序遍历是 中 -->左-->右
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 中序排序是 左-->中-->右
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.val);
                //注意这里是 赋值操作,而不是添加到栈中
                current = node.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历 应该是左-->右-->中
     * 前序遍历是 中-->左-->右
     * 所以对前序遍历调整顺序为 中-->右-->左
     * 然后再反转上面的顺序,就是后续遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        //调整遍历顺序,再反转
        Collections.reverse(result);
        return result;
    }

    /**
     * 层级遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelTraversal(root, result);
        return result;
    }

    private void levelTraversal(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelTraversal(root, result);
        Collections.reverse(result);
        return result;
    }

//    public List<Integer> rightSideView(TreeNode root) {
//
//    }
//
//    private void levelTraversal(TreeNode root, List<Integer> result){
//        if (root == null) {
//            return;
//        }
//        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//            TreeNode node = queue.poll();
//            result.add(node.val);
//            if (node.right != null) {
//                queue.offer(node.right);
//            }
//        }
//    }
}
