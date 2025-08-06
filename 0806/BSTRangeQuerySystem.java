import java.util.*;

public class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }
    
    private static void rangeQueryHelper(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        
        if (root.val >= min && root.val <= max) {
            result.add(root.val);
        }
        
        if (root.val > min) {
            rangeQueryHelper(root.left, min, max, result);
        }
        if (root.val < max) {
            rangeQueryHelper(root.right, min, max, result);
        }
    }
    
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        
        int count = 0;
        if (root.val >= min && root.val <= max) {
            count = 1;
        }
        
        if (root.val > min) {
            count += rangeCount(root.left, min, max);
        }
        if (root.val < max) {
            count += rangeCount(root.right, min, max);
        }
        
        return count;
    }
    
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        
        int sum = 0;
        if (root.val >= min && root.val <= max) {
            sum = root.val;
        }
        
        if (root.val > min) {
            sum += rangeSum(root.left, min, max);
        }
        if (root.val < max) {
            sum += rangeSum(root.right, min, max);
        }
        
        return sum;
    }
    
    public static TreeNode findClosest(TreeNode root, int target) {
        if (root == null) return null;
        
        TreeNode closest = root;
        int minDiff = Math.abs(root.val - target);
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                int diff = Math.abs(current.val - target);
                if (diff < minDiff) {
                    minDiff = diff;
                    closest = current;
                }
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            current = current.right;
        }
        
        return closest;
    }
    
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    
    public static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(50);
        int[] values = {30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        
        for (int val : values) {
            root = insert(root, val);
        }
        
        return root;
    }
    
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== BST範圍查詢系統 ===");
        
        TreeNode bst = createSampleBST();
        System.out.println("BST內容 (中序走訪)：");
        inorderTraversal(bst);
        System.out.println();
        
        int minRange = 25;
        int maxRange = 65;
        System.out.println("\n範圍查詢 [" + minRange + ", " + maxRange + "]：");
        List<Integer> rangeResult = rangeQuery(bst, minRange, maxRange);
        System.out.println("範圍內的節點：" + rangeResult);
        
        System.out.println("範圍內節點數量：" + rangeCount(bst, minRange, maxRange));
        System.out.println("範圍內節點總和：" + rangeSum(bst, minRange, maxRange));
        
        int target = 33;
        TreeNode closest = findClosest(bst, target);
        System.out.println("\n最接近 " + target + " 的節點：" + closest.val);
        
        target = 100;
        closest = findClosest(bst, target);
        System.out.println("最接近 " + target + " 的節點：" + closest.val);
        
        System.out.println("\n不同範圍的查詢測試：");
        System.out.println("範圍 [15, 45]：" + rangeQuery(bst, 15, 45));
        System.out.println("範圍 [55, 85]：" + rangeQuery(bst, 55, 85));
    }
}
