import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) return true;
        
        if (root.val <= min || root.val >= max) return false;
        
        return isValidBSTHelper(root.left, min, root.val) &&
               isValidBSTHelper(root.right, root.val, max);
    }
    
    public static List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        List<Integer> invalidNodes = new ArrayList<>();
        for (int i = 0; i < inorderList.size() - 1; i++) {
            if (inorderList.get(i) >= inorderList.get(i + 1)) {
                invalidNodes.add(inorderList.get(i));
                invalidNodes.add(inorderList.get(i + 1));
            }
        }
        
        return new ArrayList<>(new LinkedHashSet<>(invalidNodes));
    }
    
    private static TreeNode first = null;
    private static TreeNode second = null;
    private static TreeNode prev = null;
    
    public static void recoverBST(TreeNode root) {
        first = null;
        second = null;
        prev = null;
        
        inorderRecovery(root);
        
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
    
    private static void inorderRecovery(TreeNode root) {
        if (root == null) return;
        
        inorderRecovery(root.left);
        
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        
        inorderRecovery(root.right);
    }
    
    public static int minNodesToRemove(TreeNode root) {
        if (root == null) return 0;
        
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        return inorderList.size() - longestIncreasingSubsequence(inorderList);
    }
    
    private static int longestIncreasingSubsequence(List<Integer> nums) {
        if (nums.isEmpty()) return 0;
        
        int[] dp = new int[nums.size()];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < nums.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(i) > nums.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }
        
        return maxLength;
    }
    
    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }
    
    public static TreeNode createValidBST() {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(35);
        return root;
    }
    
    public static TreeNode createInvalidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static TreeNode createSwappedBST() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        return root;
    }
    
    public static TreeNode createComplexInvalidBST() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        return root;
    }
    
    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        
        return newNode;
    }
    
    public static void main(String[] args) {
        System.out.println("=== BST驗證與修復 ===");
        
        TreeNode validBST = createValidBST();
        System.out.println("有效BST驗證：" + isValidBST(validBST));
        List<Integer> validInorder = new ArrayList<>();
        inorderTraversal(validBST, validInorder);
        System.out.println("有效BST中序走訪：" + validInorder);
        
        TreeNode invalidBST = createInvalidBST();
        System.out.println("\n無效BST驗證：" + isValidBST(invalidBST));
        List<Integer> invalidInorder = new ArrayList<>();
        inorderTraversal(invalidBST, invalidInorder);
        System.out.println("無效BST中序走訪：" + invalidInorder);
        System.out.println("無效節點：" + findInvalidNodes(invalidBST));
        
        TreeNode swappedBST = createSwappedBST();
        TreeNode swappedCopy = copyTree(swappedBST);
        System.out.println("\n交換錯誤BST修復前：");
        List<Integer> beforeRepair = new ArrayList<>();
        inorderTraversal(swappedCopy, beforeRepair);
        System.out.println("中序走訪：" + beforeRepair);
        System.out.println("是否有效：" + isValidBST(swappedCopy));
        
        recoverBST(swappedCopy);
        System.out.println("修復後：");
        List<Integer> afterRepair = new ArrayList<>();
        inorderTraversal(swappedCopy, afterRepair);
        System.out.println("中序走訪：" + afterRepair);
        System.out.println("是否有效：" + isValidBST(swappedCopy));
        
        TreeNode complexInvalid = createComplexInvalidBST();
        System.out.println("\n複雜無效BST分析：");
        List<Integer> complexInorder = new ArrayList<>();
        inorderTraversal(complexInvalid, complexInorder);
        System.out.println("中序走訪：" + complexInorder);
        System.out.println("需要移除的節點數：" + minNodesToRemove(complexInvalid));
        
        System.out.println("\n各種測試案例：");
        TreeNode singleNode = new TreeNode(1);
        System.out.println("單節點BST有效性：" + isValidBST(singleNode));
        
        System.out.println("空樹BST有效性：" + isValidBST(null));
    }
}
