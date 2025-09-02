package Leetcode;

public class lt_12_IntegerToRoman {
    public String intToRoman(int num) {
        // 定義羅馬數字的數值和對應符號，按從大到小排列
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder(); // 用於構建結果字串
        
        // 從最大的羅馬數字開始處理
        for (int i = 0; i < values.length; i++) {
            // 計算當前數值可以使用多少次對應的羅馬符號
            int count = num / values[i];
            
            // 將對應數量的羅馬符號加入結果
            for (int j = 0; j < count; j++) {
                result.append(symbols[i]);
            }
            
            // 更新剩餘的數值
            num %= values[i];
        }
        
        return result.toString(); // 返回羅馬數字字串
    }
    
    public static void main(String[] args) {
        lt_12_IntegerToRoman solution = new lt_12_IntegerToRoman();
        
        // 測試案例
        System.out.println("3 -> " + solution.intToRoman(3)); // 預期輸出: III
        System.out.println("58 -> " + solution.intToRoman(58)); // 預期輸出: LVIII
        System.out.println("1994 -> " + solution.intToRoman(1994)); // 預期輸出: MCMXCIV
        System.out.println("27 -> " + solution.intToRoman(27)); // 預期輸出: XXVII
    }
}
