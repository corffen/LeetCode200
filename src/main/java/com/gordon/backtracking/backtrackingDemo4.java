package com.gordon.backtracking;

import java.util.*;

public class backtrackingDemo4 {

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
        if (row == n) {
            ans.add(Array2List(chessboard));
            return;
        }
        for (int colum = 0; colum < n; colum++) {
            if (isValid(n, row, colum, chessboard)) {
                chessboard[row][colum] = 'Q';
                backTrack(n, row + 1, chessboard);
                chessboard[row][colum] = '.';
            }
        }
    }

    private boolean isValid(int n, int row, int colum, char[][] chessboard) {
        for (int i = 0; i < row; i++) {
            if (chessboard[i][colum] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = colum - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = colum + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> Array2List(char[][] chessboard) {
        ArrayList<String> result = new ArrayList<>();
        for (char[] chars : chessboard) {
            result.add(String.copyValueOf(chars));
        }
        return result;
    }
}
