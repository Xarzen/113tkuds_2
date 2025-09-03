package Leetcode;

import java.util.*;

class Solution_20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // 使用堆疊來匹配括號
        
        for (char c : s.toCharArray()) { // 遍歷字符串中的每個字符
            if (c == '(' || c == '[' || c == '{') { // 如果是左括號
                stack.push(c); // 推入堆疊
            } else { // 如果是右括號
                if (stack.isEmpty()) { // 如果堆疊為空，無法匹配
                    return false;
                }
                
                char top = stack.pop(); // 彈出堆疊頂部元素
                
                // 檢查是否匹配對應的左括號
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false; // 不匹配，返回false
                }
            }
        }
        
        return stack.isEmpty(); // 如果堆疊為空，說明所有括號都匹配
    }
}
