package Leetcode;

class Solution_29 {
    public int divide(int dividend, int divisor) {
        // 處理溢出情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // 確定結果的符號
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // 轉換為正數進行計算（使用long避免溢出）
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        long result = 0; // 存儲結果
        
        // 使用位移操作加速除法運算
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor; // 臨時除數
            long multiple = 1; // 倍數
            
            // 找到最大的tempDivisor，使得tempDivisor <= absDividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1; // 除數左移一位（乘以2）
                multiple <<= 1; // 倍數也左移一位
            }
            
            absDividend -= tempDivisor; // 減去找到的最大除數
            result += multiple; // 累加倍數到結果
        }
        
        // 根據符號返回結果
        return negative ? (int) -result : (int) result;
    }
}
