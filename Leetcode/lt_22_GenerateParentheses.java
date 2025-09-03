package Leetcode;

import java.util.*;

class Solution_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(); // 存儲結果的列表
        backtrack(result, "", 0, 0, n); // 開始回溯生成
        return result; // 返回所有有效組合
    }
    
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) { // 如果達到目標長度
            result.add(current); // 添加到結果列表
            return;
        }
        
        if (open < max) { // 如果左括號數量未達到上限
            backtrack(result, current + "(", open + 1, close, max); // 添加左括號並遞歸
        }
        
        if (close < open) { // 如果右括號數量少於左括號（保證有效性）
            backtrack(result, current + ")", open, close + 1, max); // 添加右括號並遞歸
        }
    }
}
