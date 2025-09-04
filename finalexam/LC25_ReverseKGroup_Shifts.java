package finalexam;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String[] nums = line.split(" ");
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (String numStr : nums) {
            current.next = new ListNode(Integer.parseInt(numStr));
            current = current.next;
        }
        
        ListNode result = reverseKGroup(dummy.next, k);
        
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
            if (result != null) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
    
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        
        if (count == k) {
            curr = reverseKGroup(curr, k);
            
            while (count-- > 0) {
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        
        return head;
    }
}
