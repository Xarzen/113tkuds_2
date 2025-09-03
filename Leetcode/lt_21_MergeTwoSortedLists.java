package Leetcode;

class Solution_21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // 創建虛擬頭節點
        ListNode current = dummy; // 當前指針
        
        // 同時遍歷兩個鏈表
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) { // 如果list1的值較小或相等
                current.next = list1; // 連接list1的節點
                list1 = list1.next; // list1指針前移
            } else { // 如果list2的值較小
                current.next = list2; // 連接list2的節點
                list2 = list2.next; // list2指針前移
            }
            current = current.next; // 移動當前指針
        }
        
        // 連接剩餘的節點
        if (list1 != null) {
            current.next = list1; // 如果list1還有剩餘節點
        } else {
            current.next = list2; // 如果list2還有剩餘節點
        }
        
        return dummy.next; // 返回合併後的鏈表頭
    }
}
