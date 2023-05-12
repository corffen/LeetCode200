package com.gordon.binary.tree;

import java.util.*;

public class BinaryTreeSolution {
    /**
     * 前序遍历 中-->左-->右
     *
     * @param root 二叉树
     * @return 返回遍历的值
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> st = new LinkedList<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            ans.add(node.val);
            if (node.left != null) {
                st.push(node.left);
            }
            if (node.right != null) {
                st.push(node.right);
            }
        }
        return ans;
    }

    /**
     * 中序遍历 左-->中--->右
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        TreeNode curr = root;
        LinkedList<TreeNode> st = new LinkedList<>();
        while (curr != null || !st.isEmpty()) {
            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            } else {
                TreeNode node = st.pop();
                ans.add(node.val);
                curr = node.right;
            }
        }
        return ans;
    }

    /**
     * 二叉树的后序遍历
     * 左右中
     * 可以由中右左,在翻转得到
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> st = new LinkedList<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            ans.add(node.val);
            if (node.right != null) {
                st.push(node.right);
            }
            if (node.left != null) {
                st.push(node.left);
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    //todo  层级遍历

    /**
     * 翻转二叉树
     * 前序遍历,优先交换当前节点的左右子节点
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /**
     * 用栈去实现
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        LinkedList<TreeNode> st = new LinkedList<>();
        st.add(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            swap(node);
            //这里的添加左右子节点顺序不影响最后的结果
            if (node.left != null) {
                st.push(node.left);
            }
            if (node.right != null) {
                st.push(node.right);
            }
        }
        return root;
    }

    /**
     * 判断二叉树是否对称
     * 注意往队列中添加元素时是对称添加的
     */
    public boolean isSymmetric3(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(left.right);
            queue.add(right.left);
            queue.add(right.right);
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return 0;
        }
        ArrayDeque<TreeNode> st = new ArrayDeque<>();
        st.addLast(root);
        while (!st.isEmpty()) {
            int size = st.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = st.pollFirst();
                if (poll.left != null) {
                    st.add(poll.left);
                }
                if (poll.right != null) {
                    st.add(poll.right);
                }
            }
        }
        return depth;
    }

    /**
     * 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return 0;
        }
        ArrayDeque<TreeNode> st = new ArrayDeque<>();
        st.addLast(root);
        while (!st.isEmpty()) {
            int size = st.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = st.pollFirst();
                if (poll.left != null) {
                    st.add(poll.left);
                }
                if (poll.right != null) {
                    st.add(poll.right);
                }
                if (poll.left == null && poll.right == null) {
                    return depth;
                }
            }
        }
        return depth;
    }

    /**
     * 统计二叉树的个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                result++;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return result;
    }

    /**
     * 平衡二叉树
     * 左右子树的高度不会相差1
     * <p>
     * 注意:这个求高度,用递归解法,用遍历的解法,效率太低了
     * 递归:
     * 1.明确入参和出参信息
     * 2.确定出口
     * 3.确认单层逻辑
     * 4.缩小范围
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<String>();
        List<Integer> path = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }
        traverse(root, ans, path);
        return ans;
    }

    private void traverse(TreeNode root, List<String> ans, List<Integer> path) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(path.size() - 1));
            ans.add(sb.toString());
        }
        if (root.left != null) {
            traverse(root.left, ans, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            traverse(root.right, ans, path);
            path.remove(path.size() - 1);
        }
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> st = new LinkedList<>();
        st.add(root);
        int answer = 0;
        while (!st.isEmpty()) {
            TreeNode node = st.poll();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                answer += node.left.val;
            }
            if (node.left != null) {
                st.add(node.left);
            }
            if (node.right != null) {
                st.add(node.right);
            }
        }
        return answer;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> st = new LinkedList<>();
        st.add(root);
        int answer = 0;
        while (!st.isEmpty()) {
            int size = st.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = st.poll();
                if (i == 0 && node.left == null && node.right == null) {
                    answer = node.val;
                }
                if (node.left != null) {
                    st.add(node.left);
                }
                if (node.right != null) {
                    st.add(node.right);
                }
            }
        }
        return answer;
    }

    /**
     * 从中序和后序遍历中,构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
       return buildTree(inorder, postorder, 0, inorder.length, 0, postorder.length);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int inorderBegin, int inEnd, int postBegin, int postEnd) {
        if (inorderBegin >= inEnd || postBegin >= postEnd) {
            return null;
        }
        Integer rootIndex = map.get(postorder[postEnd - 1]);
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int lenOfLeft = rootIndex - inorderBegin;
        root.left = buildTree(inorder, postorder, inorderBegin, rootIndex, postBegin, postBegin + lenOfLeft);
        root.right = buildTree(inorder, postorder, rootIndex + 1, inEnd, postBegin + lenOfLeft, postEnd-1);
        return root;
    }
}
