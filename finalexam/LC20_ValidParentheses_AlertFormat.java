package finalexam;
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');
        
        for (char c : s.toCharArray()) {
            if (mapping.containsKey(c)) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if (topElement != mapping.get(c)) {
                    System.out.println("false");
                    sc.close();
                    return;
                }
            } else {
                stack.push(c);
            }
        }
        
        System.out.println(stack.isEmpty() ? "true" : "false");
        sc.close();
    }
}
