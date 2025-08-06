public class LinkedListRecursionDemo {
    
    static class ListNode {
        int data;
        ListNode next;
        
        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        
        System.out.print(head.data + " ");
        printList(head.next);
    }
    
    public static void printListReverse(ListNode head) {
        if (head == null) {
            return;
        }
        
        printListReverse(head.next);
        System.out.print(head.data + " ");
    }
    
    public static int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        
        return 1 + getLength(head.next);
    }
    
    public static boolean search(ListNode head, int target) {
        if (head == null) {
            return false;
        }
        
        if (head.data == target) {
            return true;
        }
        
        return search(head.next, target);
    }
    
    public static int sumList(ListNode head) {
        if (head == null) {
            return 0;
        }
        
        return head.data + sumList(head.next);
    }
    
    public static int findMax(ListNode head) {
        if (head == null) {
            throw new IllegalArgumentException("空串列沒有最大值");
        }
        
        if (head.next == null) {
            return head.data;
        }
        
        int maxOfRest = findMax(head.next);
        return Math.max(head.data, maxOfRest);
    }
    
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = createList(values);
        
        System.out.println("=== 鏈結串列遞迴操作 ===");
        
        System.out.print("正向列印串列：");
        printList(head);
        System.out.println();
        
        System.out.print("反向列印串列：");
        printListReverse(head);
        System.out.println();
        
        System.out.println("串列長度：" + getLength(head));
        System.out.println("串列總和：" + sumList(head));
        System.out.println("最大值：" + findMax(head));
        
        System.out.println("搜尋 3：" + search(head, 3));
        System.out.println("搜尋 6：" + search(head, 6));
        
        System.out.println("\n=== 空串列測試 ===");
        ListNode emptyList = null;
        System.out.print("空串列列印：");
        printList(emptyList);
        System.out.println("(無輸出)");
        System.out.println("空串列長度：" + getLength(emptyList));
        System.out.println("空串列搜尋：" + search(emptyList, 1));
    }
}
