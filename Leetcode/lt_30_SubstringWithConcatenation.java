package Leetcode;

import java.util.*;

class Solution_30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>(); // 存儲結果索引
        if (s.isEmpty() || words.length == 0) { // 處理邊界情況
            return result;
        }
        
        int wordLen = words[0].length(); // 單個單詞長度
        int totalLen = wordLen * words.length; // 所有單詞連接後的總長度
        
        // 統計words中每個單詞的出現次數
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // 遍歷s中所有可能的起始位置
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>(); // 記錄已見過的單詞
            int j = 0; // 單詞計數器
            
            // 檢查從位置i開始的子字符串
            while (j < words.length) {
                int start = i + j * wordLen; // 當前單詞的起始位置
                String word = s.substring(start, start + wordLen); // 提取單詞
                
                if (!wordCount.containsKey(word)) { // 如果單詞不在words中
                    break; // 跳出內層循環
                }
                
                seen.put(word, seen.getOrDefault(word, 0) + 1); // 記錄見過的單詞
                
                if (seen.get(word) > wordCount.get(word)) { // 如果單詞出現次數超過預期
                    break; // 跳出內層循環
                }
                
                j++; // 處理下一個單詞
            }
            
            if (j == words.length) { // 如果所有單詞都匹配成功
                result.add(i); // 添加起始索引到結果
            }
        }
        
        return result; // 返回所有匹配的起始索引
    }
}
