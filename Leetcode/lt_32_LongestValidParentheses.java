package Leetcode;

class Solution_32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) return 0; // 空字符串返回0
        
        // dp[i]表示以位置i結尾的最長有效括號長度
        int[] dp = new int[n];
        int maxLen = 0; // 記錄最長有效括號長度
        
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') { // 只有右括號才可能形成有效括號
                if (s.charAt(i - 1) == '(') {
                    // 情況1：...()，直接匹配
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (dp[i - 1] > 0) {
                    // 情況2：...))，需要檢查是否能與前面的左括號匹配
                    int matchIndex = i - dp[i - 1] - 1; // 找到可能匹配的左括號位置
                    if (matchIndex >= 0 && s.charAt(matchIndex) == '(') {
                        // 如果找到匹配的左括號
                        dp[i] = dp[i - 1] + 2 + (matchIndex > 0 ? dp[matchIndex - 1] : 0);
                    }
                }
                maxLen = Math.max(maxLen, dp[i]); // 更新最大長度
            }
        }
        
        return maxLen; // 返回最長有效括號長度
    }
}
