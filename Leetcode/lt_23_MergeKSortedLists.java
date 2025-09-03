package Leetcode;

import java.util.*;

class Solution_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) { // 處理邊界情況
            return null;
        }
        
        // 使用優先隊列（最小堆）來維護各鏈表的頭節點
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // 將所有非空鏈表的頭節點加入優先隊列
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0); // 虛擬頭節點
        ListNode current = dummy; // 當前指針
        
        while (!pq.isEmpty()) { // 當優先隊列不為空時
            ListNode node = pq.poll(); // 取出值最小的節點
            current.next = node; // 連接到結果鏈表
            current = current.next; // 移動當前指針
            
            if (node.next != null) { // 如果該節點有下一個節點
                pq.offer(node.next); // 將下一個節點加入優先隊列
            }
        }
        
        return dummy.next; // 返回合併後的鏈表
    }
}
