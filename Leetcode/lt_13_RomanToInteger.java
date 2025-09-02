package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class lt_13_RomanToInteger {
    public int romanToInt(String s) {
        // 建立羅馬數字到整數的映射表
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int result = 0; // 結果累加器
        int prevValue = 0; // 記錄前一個字符的數值
        
        // 從右到左遍歷羅馬數字字串
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(s.charAt(i)); // 獲取當前字符的數值
            
            // 如果當前數值小於前一個數值，說明是減法情況（如IV、IX）
            if (currentValue < prevValue) {
                result -= currentValue; // 執行減法
            } else {
                result += currentValue; // 執行加法
            }
            
            prevValue = currentValue; // 更新前一個數值
        }
        
        return result; // 返回轉換後的整數
    }
    
    public static void main(String[] args) {
        lt_13_RomanToInteger solution = new lt_13_RomanToInteger();
        
        // 測試案例
        System.out.println("III -> " + solution.romanToInt("III")); // 預期輸出: 3
        System.out.println("LVIII -> " + solution.romanToInt("LVIII")); // 預期輸出: 58
        System.out.println("MCMXCIV -> " + solution.romanToInt("MCMXCIV")); // 預期輸出: 1994
        System.out.println("IV -> " + solution.romanToInt("IV")); // 預期輸出: 4
        System.out.println("IX -> " + solution.romanToInt("IX")); // 預期輸出: 9
    }
}
