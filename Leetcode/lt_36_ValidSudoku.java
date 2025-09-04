package Leetcode;

class Solution_36 {
    public boolean isValidSudoku(char[][] board) {
        // 使用集合記錄已見過的數字
        // 分別記錄行、列、九宮格中出現的數字
        boolean[][] rows = new boolean[9][9]; // rows[i][num]表示第i行是否包含數字num+1
        boolean[][] cols = new boolean[9][9]; // cols[j][num]表示第j列是否包含數字num+1
        boolean[][] boxes = new boolean[9][9]; // boxes[boxIndex][num]表示第boxIndex個九宮格是否包含數字num+1
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') { // 如果當前位置有數字
                    int num = c - '1'; // 將字符轉換為數字索引（0-8）
                    int boxIndex = (i / 3) * 3 + j / 3; // 計算九宮格索引
                    
                    // 檢查當前數字是否已在同一行、列或九宮格中出現過
                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false; // 如果重複，返回false
                    }
                    
                    // 標記當前數字已在對應的行、列、九宮格中出現
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }
        
        return true; // 所有檢查通過，數獨有效
    }
}
