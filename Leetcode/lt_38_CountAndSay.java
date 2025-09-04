package Leetcode;

class Solution_38 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1"; // 基礎情況：第1項是"1"
        }
        
        String current = "1"; // 從第1項開始
        
        // 迭代生成第2項到第n項
        for (int i = 2; i <= n; i++) {
            current = getNext(current); // 生成下一項
        }
        
        return current; // 返回第n項
    }
    
    // 根據當前字符串生成下一項
    private String getNext(String s) {
        StringBuilder result = new StringBuilder(); // 用於構建結果字符串
        int i = 0;
        
        while (i < s.length()) {
            char currentChar = s.charAt(i); // 當前字符
            int count = 1; // 計數器，初始為1
            
            // 統計相同字符的連續出現次數
            while (i + 1 < s.length() && s.charAt(i + 1) == currentChar) {
                count++; // 增加計數
                i++; // 移動到下一個字符
            }
            
            // 將計數和字符添加到結果中
            result.append(count).append(currentChar);
            i++; // 移動到下一個不同的字符
        }
        
        return result.toString(); // 返回生成的字符串
    }
}
