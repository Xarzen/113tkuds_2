package finalexam;
import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        ListNode dummy1 = new ListNode(0);
        ListNode current1 = dummy1;
        for (int i = 0; i < n; i++) {
            current1.next = new ListNode(sc.nextInt());
            current1 = current1.next;
        }
        
        ListNode dummy2 = new ListNode(0);
        ListNode current2 = dummy2;
        for (int i = 0; i < m; i++) {
            current2.next = new ListNode(sc.nextInt());
            current2 = current2.next;
        }
        
        ListNode merged = mergeTwoLists(dummy1.next, dummy2.next);
        
        while (merged != null) {
            System.out.print(merged.val);
            merged = merged.next;
            if (merged != null) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
