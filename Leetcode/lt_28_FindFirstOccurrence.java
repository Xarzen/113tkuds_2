package Leetcode;

class Solution_28 {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) { // 如果needle為空，返回0
            return 0;
        }
        
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        
        // 遍歷haystack，尋找needle的第一次出現
        for (int i = 0; i <= haystackLen - needleLen; i++) {
            int j = 0; // needle的指針
            
            // 比較從當前位置開始的子字符串
            while (j < needleLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++; // 字符匹配，繼續比較下一個字符
            }
            
            if (j == needleLen) { // 如果完全匹配
                return i; // 返回起始索引
            }
        }
        
        return -1; // 未找到，返回-1
    }
}
