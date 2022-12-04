package com.gordon.backtracking;

import java.util.*;

public class BacktrackingDemo4 {

    public static void main(String[] args) {
        BacktrackingDemo4 demo4 = new BacktrackingDemo4();
        demo4.solveNQueens(3);
    }

    private LinkedList<String> res;
    private LinkedList<String> path = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, Comparator.comparing(a -> a.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        backTracking(new ArrayList<>(tickets), used);
        return res;
    }

    public boolean backTracking(ArrayList<List<String>> tickets, boolean[] used) {
        if (path.size() == tickets.size() + 1) {
            res = new LinkedList<>(path);
            return true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;

                if (backTracking(tickets, used)) {
                    return true;
                }

                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }

    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return ans;
    }

    private void backTrack(int n, int row, char[][] chessboard) {
        //回溯的终止条件,就是每一行都填上数据了,就可以终止了.
        if (row == n) {
            ans.add(Array2List(chessboard));
            return;
        }
        for (int colum = 0; colum < n; colum++) {
            if (isValid(n, row, colum, chessboard)) {
                //满足条件就放上Q
                chessboard[row][colum] = 'Q';
                //然后进行下一行的搜索
                backTrack(n, row + 1, chessboard);
                //搜索后,记得回退标记,标记项是成对出现的
                chessboard[row][colum] = '.';
            }
        }
    }

    private boolean isValid(int n, int row, int colum, char[][] chessboard) {
        //检查每列是否有Q,如果有,就返回false
        //这里为什么只需要检查每列中的元素,而不需要检查每一行中的呢? 因为
        //每一行是调用处横向遍历时,每次取一个元素.
        for (int i = 0; i < row; i++) {
            if (chessboard[i][colum] == 'Q') {
                return false;
            }
        }
        //135度角检查是否同一条线,从上到下从左往右的这条斜线
        for (int i = row - 1, j = colum - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        //45度角的斜线
        for (int i = row - 1, j = colum + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将二维数组转成一个List<String>
     *     这里用了一个String.copyValueOf(char[])-->将数组转成字符串
     * @param chessboard
     * @return
     */
    private List<String> Array2List(char[][] chessboard) {
        ArrayList<String> result = new ArrayList<>();
        for (char[] chars : chessboard) {
            result.add(String.copyValueOf(chars));
        }
        return result;
    }
}
