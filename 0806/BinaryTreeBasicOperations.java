import java.util.*;

public class BinaryTreeBasicOperations {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static int calculateSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + calculateSum(root.left) + calculateSum(root.right);
    }
    
    public static double calculateAverage(TreeNode root) {
        if (root == null) return 0;
        int sum = calculateSum(root);
        int count = countNodes(root);
        return (double) sum / count;
    }
    
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    public static TreeNode findMax(TreeNode root) {
        if (root == null) return null;
        TreeNode max = root;
        TreeNode leftMax = findMax(root.left);
        TreeNode rightMax = findMax(root.right);
        
        if (leftMax != null && leftMax.val > max.val) {
            max = leftMax;
        }
        if (rightMax != null && rightMax.val > max.val) {
            max = rightMax;
        }
        return max;
    }
    
    public static TreeNode findMin(TreeNode root) {
        if (root == null) return null;
        TreeNode min = root;
        TreeNode leftMin = findMin(root.left);
        TreeNode rightMin = findMin(root.right);
        
        if (leftMin != null && leftMin.val < min.val) {
            min = leftMin;
        }
        if (rightMin != null && rightMin.val < min.val) {
            min = rightMin;
        }
        return min;
    }
    
    public static int calculateTreeWidth(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }
    
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean foundNull = false;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                foundNull = true;
            } else {
                if (foundNull) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
    
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static TreeNode createIncompleteTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 二元樹基本操作練習 ===");
        
        TreeNode completeTree = createSampleTree();
        System.out.println("完全二元樹測試：");
        System.out.println("節點總和：" + calculateSum(completeTree));
        System.out.println("節點平均值：" + String.format("%.2f", calculateAverage(completeTree)));
        System.out.println("最大值節點：" + findMax(completeTree).val);
        System.out.println("最小值節點：" + findMin(completeTree).val);
        System.out.println("樹的寬度：" + calculateTreeWidth(completeTree));
        System.out.println("是否為完全二元樹：" + isCompleteTree(completeTree));
        
        System.out.println("\n不完全二元樹測試：");
        TreeNode incompleteTree = createIncompleteTree();
        System.out.println("節點總和：" + calculateSum(incompleteTree));
        System.out.println("節點平均值：" + String.format("%.2f", calculateAverage(incompleteTree)));
        System.out.println("是否為完全二元樹：" + isCompleteTree(incompleteTree));
    }
}
