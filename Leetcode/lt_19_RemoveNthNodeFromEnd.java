package Leetcode;

class Solution_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 創建虛擬頭節點，簡化邊界處理
        dummy.next = head;
        
        ListNode fast = dummy; // 快指針
        ListNode slow = dummy; // 慢指針
        
        // 快指針先移動 n+1 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 快慢指針同時移動，直到快指針到達末尾
        while (fast != null) {
            fast = fast.next; // 快指針移動
            slow = slow.next; // 慢指針移動
        }
        
        // 此時慢指針指向要刪除節點的前一個節點
        slow.next = slow.next.next; // 刪除目標節點
        
        return dummy.next; // 返回新的頭節點
    }
}
