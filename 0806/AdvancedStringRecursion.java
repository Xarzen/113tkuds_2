import java.util.*;

public class AdvancedStringRecursion {
    
    public static List<String> generatePermutations(String str) {
        List<String> result = new ArrayList<>();
        generatePermutationsHelper("", str, result);
        return result;
    }
    
    private static void generatePermutationsHelper(String prefix, String remaining, List<String> result) {
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        
        for (int i = 0; i < remaining.length(); i++) {
            char ch = remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutationsHelper(prefix + ch, newRemaining, result);
        }
    }
    
    public static boolean stringMatch(String text, String pattern) {
        return stringMatchHelper(text, pattern, 0, 0);
    }
    
    private static boolean stringMatchHelper(String text, String pattern, int textIndex, int patternIndex) {
        if (patternIndex == pattern.length()) {
            return textIndex == text.length();
        }
        
        if (textIndex == text.length()) {
            return patternIndex == pattern.length();
        }
        
        if (pattern.charAt(patternIndex) == '*') {
            return stringMatchHelper(text, pattern, textIndex + 1, patternIndex) ||
                   stringMatchHelper(text, pattern, textIndex, patternIndex + 1);
        }
        
        if (pattern.charAt(patternIndex) == '?' || 
            pattern.charAt(patternIndex) == text.charAt(textIndex)) {
            return stringMatchHelper(text, pattern, textIndex + 1, patternIndex + 1);
        }
        
        return false;
    }
    
    public static String removeDuplicates(String str) {
        if (str.length() <= 1) {
            return str;
        }
        
        char firstChar = str.charAt(0);
        String remaining = str.substring(1);
        String result = removeDuplicates(remaining);
        
        if (result.indexOf(firstChar) == -1) {
            return firstChar + result;
        } else {
            return result;
        }
    }
    
    public static List<String> generateSubstrings(String str) {
        List<String> result = new ArrayList<>();
        generateSubstringsHelper(str, 0, result);
        return result;
    }
    
    private static void generateSubstringsHelper(String str, int start, List<String> result) {
        if (start >= str.length()) {
            return;
        }
        
        for (int end = start + 1; end <= str.length(); end++) {
            result.add(str.substring(start, end));
        }
        
        generateSubstringsHelper(str, start + 1, result);
    }
    
    public static boolean isPalindromeString(String str) {
        return isPalindromeHelper(str, 0, str.length() - 1);
    }
    
    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        return isPalindromeHelper(str, left + 1, right - 1);
    }
    
    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str;
        }
        
        return reverseString(str.substring(1)) + str.charAt(0);
    }
    
    public static List<String> generateCombinations(String str) {
        List<String> result = new ArrayList<>();
        generateCombinationsHelper(str, 0, "", result);
        return result;
    }
    
    private static void generateCombinationsHelper(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            result.add(current);
            return;
        }
        
        generateCombinationsHelper(str, index + 1, current, result);
        generateCombinationsHelper(str, index + 1, current + str.charAt(index), result);
    }
    
    public static int countVowels(String str) {
        return countVowelsHelper(str, 0);
    }
    
    private static int countVowelsHelper(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        
        char ch = Character.toLowerCase(str.charAt(index));
        int count = 0;
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            count = 1;
        }
        
        return count + countVowelsHelper(str, index + 1);
    }
    
    public static boolean isValidParentheses(String str) {
        return isValidParenthesesHelper(str, 0, 0);
    }
    
    private static boolean isValidParenthesesHelper(String str, int index, int balance) {
        if (balance < 0) {
            return false;
        }
        
        if (index >= str.length()) {
            return balance == 0;
        }
        
        char ch = str.charAt(index);
        if (ch == '(') {
            return isValidParenthesesHelper(str, index + 1, balance + 1);
        } else if (ch == ')') {
            return isValidParenthesesHelper(str, index + 1, balance - 1);
        } else {
            return isValidParenthesesHelper(str, index + 1, balance);
        }
    }
    
    public static String longestCommonSubsequence(String str1, String str2) {
        return lcsHelper(str1, str2, 0, 0);
    }
    
    private static String lcsHelper(String str1, String str2, int i, int j) {
        if (i >= str1.length() || j >= str2.length()) {
            return "";
        }
        
        if (str1.charAt(i) == str2.charAt(j)) {
            return str1.charAt(i) + lcsHelper(str1, str2, i + 1, j + 1);
        } else {
            String lcs1 = lcsHelper(str1, str2, i + 1, j);
            String lcs2 = lcsHelper(str1, str2, i, j + 1);
            return lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 進階字串遞迴處理 ===\n");
        
        System.out.println("1. 字串排列組合:");
        String permStr = "abc";
        System.out.println("字串: " + permStr);
        List<String> permutations = generatePermutations(permStr);
        System.out.println("所有排列: " + permutations);
        System.out.println("排列數量: " + permutations.size());
        
        System.out.println("\n2. 字串匹配 (支援 * 和 ?):");
        String[] texts = {"hello", "world", "help", "test"};
        String[] patterns = {"h*o", "w?rld", "*l*", "te?t"};
        
        for (int i = 0; i < texts.length; i++) {
            boolean matches = stringMatch(texts[i], patterns[i]);
            System.out.printf("'%s' 匹配 '%s': %b\n", texts[i], patterns[i], matches);
        }
        
        System.out.println("\n3. 移除重複字符:");
        String[] duplicateStrings = {"aabbcc", "hello", "programming", "aaa"};
        for (String str : duplicateStrings) {
            String result = removeDuplicates(str);
            System.out.printf("'%s' → '%s'\n", str, result);
        }
        
        System.out.println("\n4. 生成所有子字串:");
        String subStr = "abc";
        System.out.println("字串: " + subStr);
        List<String> substrings = generateSubstrings(subStr);
        System.out.println("所有子字串: " + substrings);
        System.out.println("子字串數量: " + substrings.size());
        
        System.out.println("\n5. 字串組合 (包含空字串):");
        String combStr = "xyz";
        System.out.println("字串: " + combStr);
        List<String> combinations = generateCombinations(combStr);
        System.out.println("所有組合: " + combinations);
        System.out.println("組合數量: " + combinations.size());
        
        System.out.println("\n6. 其他字串操作:");
        String testStr = "racecar";
        System.out.println("字串: " + testStr);
        System.out.println("是否為回文: " + isPalindromeString(testStr));
        System.out.println("反轉字串: " + reverseString(testStr));
        
        String vowelStr = "programming";
        System.out.println("\n字串: " + vowelStr);
        System.out.println("元音字母數量: " + countVowels(vowelStr));
        
        System.out.println("\n7. 括號配對檢查:");
        String[] parenthesesTests = {"()", "((()))", "(()", "))(", "(())()", ""};
        for (String test : parenthesesTests) {
            boolean valid = isValidParentheses(test);
            System.out.printf("'%s' 配對正確: %b\n", test, valid);
        }
        
        System.out.println("\n8. 最長公共子序列:");
        String lcs1 = "ABCDGH";
        String lcs2 = "AEDFHR";
        System.out.printf("字串1: %s, 字串2: %s\n", lcs1, lcs2);
        String lcs = longestCommonSubsequence(lcs1, lcs2);
        System.out.println("最長公共子序列: " + lcs);
    }
}
