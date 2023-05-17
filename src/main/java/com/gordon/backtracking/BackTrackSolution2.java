package com.gordon.backtracking;

import java.util.*;

public class BackTrackSolution2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        bt2(candidates, target, 0, 0);
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    private void bt2(int[] candidates, int target, int index, int sum) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
            //注意这里是大于index,而不是大于0
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            bt2(candidates, target, i + 1, sum);
            sum -= candidates[i];
            path.pollLast();
        }
    }

    /**
     * 分割回文字符串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        bt(s, 0);
        return lists;
    }

    List<List<String>> lists = new ArrayList<List<String>>();
    Deque<String> deque = new ArrayDeque<String>();

    private void bt(String s, int index) {
        if (index >= s.length()) {
            lists.add(new ArrayList<>(deque));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isP(s, index, i)) {
                deque.add(s.substring(index, i + 1));
            } else {
                continue;
            }
            bt(s, i + 1);
            deque.pollLast();
        }
    }

    private boolean isP(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return ips;
        }
        btIp(s, 0, 0);
        return ips;
    }

    private List<String> ips = new ArrayList<String>();

    private void btIp(String s, int index, int num) {
        if (num == 3) {
            if (isValid(s, index, s.length() - 1)) {
                ips.add(s);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                num++;
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                btIp(s, i + 2, num);
                s = s.substring(0, i + 1) + s.substring(i + 2);
                num--;
            } else {
                break;
            }
        }
    }

    private boolean isValid(String s, int l, int r) {
        if (l > r) {
            return false;
        }
        if (s.startsWith("0") && l != r) {
            return false;
        }
        int num = 0;
        for (int i = l; i < r; i++) {
            int c = s.charAt(i) - '0';
            if (c > 9 || c < 0) {
                return false;
            }
            num = num * 10 + c;
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        btSub(nums, 0);
        return subs;
    }

    List<List<Integer>> subs = new ArrayList<List<Integer>>();
    Deque<Integer> pathSub = new LinkedList<>();

    private void btSub(int[] nums, int index) {
        subs.add(new ArrayList<>(pathSub));
        for (int i = index; i < nums.length; i++) {
            pathSub.add(nums[i]);
            btSub(nums, i + 1);
            pathSub.pollLast();
        }
    }

    List<List<Integer>> subsDup = new ArrayList<List<Integer>>();
    Deque<Integer> pathSubDup = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        btSubDup(nums, 0);
        return subsDup;
    }

    private void btSubDup(int[] num, int index) {
        subsDup.add(new ArrayList<>(pathSubDup));
        for (int i = index; i < num.length; i++) {
            if (i > index && num[i - 1] == num[i]) {
                continue;
            }
            pathSubDup.add(num[i]);
            btSubDup(num, i + 1);
            pathSubDup.pollLast();
        }
    }

    private List<List<Integer>> subResults = new ArrayList<List<Integer>>();
    private ArrayList<Integer> subPath = new ArrayList<>();

    /**
     * 查找递增子序列
     * 不能排序,因为[4,4,3,1]的递增子序列是[4,4]
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrackingFind(nums, 0);
        return subResults;
    }

    private void backtrackingFind(int[] nums, int startIndex) {
        if (subPath.size() > 1) {
            subResults.add(new ArrayList<>(subPath));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (!subPath.isEmpty()&&nums[i]<subPath.get(subPath.size()-1)||used[nums[i]+100]==1) {
                continue;
            }
            used[nums[i]+100] = 1;//表示递归遍历过
            subPath.add(nums[i]);
            backtrackingFind(nums, i + 1);
            subPath.remove(subPath.size() - 1);
        }
    }

    List<List<Integer>> ansPermute = new ArrayList<List<Integer>>();
    Deque<Integer> pathPermute = new ArrayDeque<Integer>();
    public List<List<Integer>> permute(int[] nums) {
        int[] used = new int[nums.length];
        btPermute(nums,used);
        return ansPermute;
    }

    private void btPermute(int[] num,int[] used){
        if (pathPermute.size() == num.length) {
            ansPermute.add(new ArrayList<>(pathPermute));
        }
        for (int i = 0; i < num.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            used[i] = 1;
            pathPermute.add(num[i]);
            btPermute(num, used);
            used[i] = 0;
            pathPermute.pollLast();
        }
    }

    /**
     * 重新安排行程
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(Comparator.comparing(o -> o.get(1)));
        pathTick.add("JFK");
        btTicket(tickets, new boolean[tickets.size()]);
        return res;
    }

    private Deque<String> pathTick = new LinkedList<>();
    private List<String> res;
    private boolean btTicket(List<List<String>> tickets,boolean[] used){
        if (pathTick.size() == tickets.size() + 1) {
            res = new ArrayList<>(pathTick);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(pathTick.peekLast())) {
                pathTick.add(tickets.get(i).get(1));
                used[i] = true;
                if (btTicket(tickets, used)) {
                    return true;
                }
                used[i] = false;
                pathTick.pollLast();
            }
        }
        return false;
    }

    List<List<String>> resQueen = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return resQueen;
    }


    public void backTrack(int n, int row, char[][] chessboard) {
        if (row == n) {
            resQueen.add(array2List(chessboard));
            return;
        }
        //列每次都是从0到n
        for (int col = 0;col < n; ++col) {
            //判断当前行的,每一列元素,是否符合条件
            if (isValid (row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row+1, chessboard);
                chessboard[row][col] = '.';
            }
        }

    }


    public List<String> array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
