import java.util.*;

public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    static class DoublyListNode {
        int val;
        DoublyListNode prev;
        DoublyListNode next;
        
        DoublyListNode(int val) {
            this.val = val;
        }
    }
    
    public static DoublyListNode convertBSTToDoublyLinkedList(TreeNode root) {
        if (root == null) return null;
        
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        if (inorderList.isEmpty()) return null;
        
        DoublyListNode head = new DoublyListNode(inorderList.get(0));
        DoublyListNode current = head;
        
        for (int i = 1; i < inorderList.size(); i++) {
            DoublyListNode newNode = new DoublyListNode(inorderList.get(i));
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        
        return head;
    }
    
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        
        return root;
    }
    
    public static boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    private static int getHeight(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static Map<TreeNode, Integer> calculateBalanceFactors(TreeNode root) {
        Map<TreeNode, Integer> balanceFactors = new HashMap<>();
        calculateBalanceFactorsHelper(root, balanceFactors);
        return balanceFactors;
    }
    
    private static int calculateBalanceFactorsHelper(TreeNode root, Map<TreeNode, Integer> balanceFactors) {
        if (root == null) return 0;
        
        int leftHeight = calculateBalanceFactorsHelper(root.left, balanceFactors);
        int rightHeight = calculateBalanceFactorsHelper(root.right, balanceFactors);
        
        int balanceFactor = rightHeight - leftHeight;
        balanceFactors.put(root, balanceFactor);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static TreeNode convertToGreaterTree(TreeNode root) {
        int[] sum = {0};
        convertToGreaterTreeHelper(root, sum);
        return root;
    }
    
    private static void convertToGreaterTreeHelper(TreeNode root, int[] sum) {
        if (root == null) return;
        
        convertToGreaterTreeHelper(root.right, sum);
        
        sum[0] += root.val;
        root.val = sum[0];
        
        convertToGreaterTreeHelper(root.left, sum);
    }
    
    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }
    
    public static TreeNode createUnbalancedBST() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        return root;
    }
    
    public static TreeNode createBalancedBST() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);
        return root;
    }
    
    public static void printDoublyList(DoublyListNode head) {
        if (head == null) return;
        
        List<Integer> forward = new ArrayList<>();
        DoublyListNode current = head;
        
        while (current != null) {
            forward.add(current.val);
            current = current.next;
        }
        
        System.out.println("正向：" + forward);
        
        List<Integer> backward = new ArrayList<>();
        current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        while (current != null) {
            backward.add(current.val);
            current = current.prev;
        }
        
        System.out.println("反向：" + backward);
    }
    
    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        
        return newNode;
    }
    
    public static void main(String[] args) {
        System.out.println("=== BST轉換與平衡 ===");
        
        TreeNode bst = createSampleBST();
        System.out.println("原始BST中序走訪：");
        List<Integer> original = new ArrayList<>();
        inorderTraversal(bst, original);
        System.out.println(original);
        
        System.out.println("\n轉換為雙向鏈結串列：");
        DoublyListNode doublyList = convertBSTToDoublyLinkedList(bst);
        printDoublyList(doublyList);
        
        System.out.println("\n排序陣列轉換為平衡BST：");
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        
        List<Integer> balancedResult = new ArrayList<>();
        inorderTraversal(balancedBST, balancedResult);
        System.out.println("平衡BST中序走訪：" + balancedResult);
        System.out.println("是否平衡：" + isBalanced(balancedBST));
        
        System.out.println("\n不平衡BST測試：");
        TreeNode unbalancedBST = createUnbalancedBST();
        List<Integer> unbalancedResult = new ArrayList<>();
        inorderTraversal(unbalancedBST, unbalancedResult);
        System.out.println("不平衡BST中序走訪：" + unbalancedResult);
        System.out.println("是否平衡：" + isBalanced(unbalancedBST));
        
        System.out.println("\n平衡因子計算：");
        TreeNode testTree = createBalancedBST();
        Map<TreeNode, Integer> balanceFactors = calculateBalanceFactors(testTree);
        System.out.println("各節點平衡因子：");
        List<Integer> testNodes = new ArrayList<>();
        inorderTraversal(testTree, testNodes);
        System.out.println("節點值：" + testNodes);
        
        System.out.println("\n轉換為更大樹：");
        TreeNode greaterTree = copyTree(createBalancedBST());
        System.out.println("轉換前：");
        List<Integer> beforeGreater = new ArrayList<>();
        inorderTraversal(greaterTree, beforeGreater);
        System.out.println(beforeGreater);
        
        convertToGreaterTree(greaterTree);
        System.out.println("轉換後：");
        List<Integer> afterGreater = new ArrayList<>();
        inorderTraversal(greaterTree, afterGreater);
        System.out.println(afterGreater);
        
        System.out.println("\n不同大小陣列的平衡BST：");
        int[] smallArray = {1, 2, 3};
        TreeNode smallBST = sortedArrayToBST(smallArray);
        List<Integer> smallResult = new ArrayList<>();
        inorderTraversal(smallBST, smallResult);
        System.out.println("小陣列BST：" + smallResult);
    }
}
