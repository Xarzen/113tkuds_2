package Leetcode;

class Solution_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0); // 創建虛擬頭節點
        dummy.next = head;
        ListNode prev = dummy; // 前驅節點指針
        
        // 當存在至少兩個節點可以交換時
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next; // 第一個節點
            ListNode second = prev.next.next; // 第二個節點
            
            // 執行交換操作
            prev.next = second; // 前驅節點指向第二個節點
            first.next = second.next; // 第一個節點指向第二個節點的下一個
            second.next = first; // 第二個節點指向第一個節點
            
            prev = first; // 更新前驅節點為交換後的第二個節點（原第一個節點）
        }
        
        return dummy.next; // 返回新的頭節點
    }
}
