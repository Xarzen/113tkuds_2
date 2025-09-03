package Leetcode;

import java.util.*;

class Solution_17 {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>(); // 存儲結果的列表
        if (digits == null || digits.length() == 0) { // 處理邊界情況
            return result;
        }
        
        // 映射數字到字母的對應關係
        String[] mapping = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };
        
        backtrack(result, mapping, digits, 0, new StringBuilder()); // 開始回溯
        return result; // 返回所有組合
    }
    
    private void backtrack(List<String> result, String[] mapping, String digits, 
                          int index, StringBuilder current) {
        if (index == digits.length()) { // 如果已處理完所有數字
            result.add(current.toString()); // 添加當前組合到結果
            return;
        }
        
        int digit = digits.charAt(index) - '0'; // 獲取當前數字
        String letters = mapping[digit]; // 獲取對應的字母
        
        for (char letter : letters.toCharArray()) { // 遍歷所有可能的字母
            current.append(letter); // 選擇當前字母
            backtrack(result, mapping, digits, index + 1, current); // 遞歸處理下一個數字
            current.deleteCharAt(current.length() - 1); // 回溯，移除當前字母
        }
    }
}
