package finalexam;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] nums = line.split(" ");
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (String numStr : nums) {
            current.next = new ListNode(Integer.parseInt(numStr));
            current = current.next;
        }
        
        ListNode result = swapPairs(dummy.next);
        
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
            if (result != null) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
    
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            prev.next = second;
            first.next = second.next;
            second.next = first;
            
            prev = first;
        }
        
        return dummy.next;
    }
}
