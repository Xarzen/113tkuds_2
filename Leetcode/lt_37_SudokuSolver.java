package Leetcode;

class Solution_37 {
    public void solveSudoku(char[][] board) {
        solve(board); // 調用遞歸求解函數
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') { // 找到空位
                    // 嘗試填入1-9的數字
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) { // 檢查是否可以填入該數字
                            board[i][j] = c; // 填入數字
                            
                            if (solve(board)) { // 遞歸求解剩餘部分
                                return true; // 如果成功，返回true
                            }
                            
                            board[i][j] = '.'; // 回溯，撤銷選擇
                        }
                    }
                    return false; // 所有數字都嘗試過，無解
                }
            }
        }
        return true; // 所有位置都填完，求解成功
    }
    
    // 檢查在位置(row, col)填入數字c是否有效
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查行是否有重複
            if (board[row][i] == c) {
                return false;
            }
            
            // 檢查列是否有重複
            if (board[i][col] == c) {
                return false;
            }
            
            // 檢查3x3九宮格是否有重複
            int boxRow = 3 * (row / 3) + i / 3; // 計算九宮格內的行位置
            int boxCol = 3 * (col / 3) + i % 3; // 計算九宮格內的列位置
            if (board[boxRow][boxCol] == c) {
                return false;
            }
        }
        
        return true; // 填入該數字有效
    }
}
