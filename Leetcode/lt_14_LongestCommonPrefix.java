package Leetcode;

public class lt_14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // 邊界條件：如果陣列為空，返回空字串
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // 以第一個字串作為基準進行比較
        String prefix = strs[0];
        
        // 依次與其他字串比較，逐步縮短前綴
        for (int i = 1; i < strs.length; i++) {
            // 當當前字串不以prefix開頭時，縮短prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // 移除最後一個字符
                
                // 如果prefix變成空字串，說明沒有共同前綴
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix; // 返回最長共同前綴
    }
    
    // 另一種實作方法：垂直掃描
    public String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // 垂直掃描：逐個字符位置比較
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i); // 取第一個字串的第i個字符作為基準
            
            // 檢查其他所有字串的第i個字符是否與基準相同
            for (int j = 1; j < strs.length; j++) {
                // 如果某個字串長度不夠或字符不匹配，返回前綴
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        
        return strs[0]; // 如果第一個字串完全是前綴
    }
    
    public static void main(String[] args) {
        lt_14_LongestCommonPrefix solution = new lt_14_LongestCommonPrefix();
        
        // 測試案例 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("測試案例 1: " + solution.longestCommonPrefix(strs1)); // 預期輸出: "fl"
        
        // 測試案例 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("測試案例 2: " + solution.longestCommonPrefix(strs2)); // 預期輸出: ""
        
        // 測試案例 3
        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("測試案例 3: " + solution.longestCommonPrefix(strs3)); // 預期輸出: "inters"
        
        // 測試垂直掃描方法
        System.out.println("垂直掃描方法: " + solution.longestCommonPrefixVertical(strs1)); // 預期輸出: "fl"
    }
}
