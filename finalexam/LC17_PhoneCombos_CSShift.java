package finalexam;
import java.util.*;

public class LC17_PhoneCombos_CSShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        
        if (digits.isEmpty()) {
            sc.close();
            return;
        }
        
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        backtrack(result, digits, 0, new StringBuilder(), mapping);
        
        for (String combo : result) {
            System.out.println(combo);
        }
        sc.close();
    }
    
    private static void backtrack(List<String> result, String digits, int index, 
                                StringBuilder current, String[] mapping) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        String letters = mapping[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(result, digits, index + 1, current, mapping);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
