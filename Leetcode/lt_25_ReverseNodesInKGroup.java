package Leetcode;

class Solution_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 首先檢查是否有足夠的節點進行反轉
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        if (count == k) { // 如果有k個節點
            curr = reverseKGroup(curr, k); // 遞歸處理剩餘部分
            
            // 反轉當前k個節點
            while (count > 0) {
                ListNode tmp = head.next; // 保存下一個節點
                head.next = curr; // 當前節點指向已處理的部分
                curr = head; // 移動curr指針
                head = tmp; // 移動head指針
                count--; // 減少計數
            }
            head = curr; // 更新頭節點
        }
        
        return head; // 返回新的頭節點
    }
}
