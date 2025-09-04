package finalexam;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        
        List<ListNode> lists = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            String line = sc.nextLine();
            String[] nums = line.split(" ");
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            for (String numStr : nums) {
                int num = Integer.parseInt(numStr);
                if (num == -1) break;
                current.next = new ListNode(num);
                current = current.next;
            }
            lists.add(dummy.next);
        }
        
        ListNode merged = mergeKLists(lists.toArray(new ListNode[0]));
        
        while (merged != null) {
            System.out.print(merged.val);
            merged = merged.next;
            if (merged != null) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
    
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        
        return dummy.next;
    }
}
