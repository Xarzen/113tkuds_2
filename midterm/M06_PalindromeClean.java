package midterm;
import java.util.Scanner;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        StringBuilder clean = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                clean.append(Character.toLowerCase(c));
            }
        }
        
        boolean isPalindrome = isPalindrome(clean.toString(), 0, clean.length() - 1);
        System.out.println(isPalindrome ? "Yes" : "No");
        
        sc.close();
    }
    
    private static boolean isPalindrome(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        
        return isPalindrome(s, left + 1, right - 1);
    }
}
