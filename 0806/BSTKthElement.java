import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static Integer findKthSmallest(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        if (k <= 0 || k > inorderList.size()) {
            return null;
        }
        
        return inorderList.get(k - 1);
    }
    
    public static Integer findKthLargest(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        if (k <= 0 || k > inorderList.size()) {
            return null;
        }
        
        return inorderList.get(inorderList.size() - k);
    }
    
    public static List<Integer> findKthToJthSmallest(TreeNode root, int k, int j) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        
        List<Integer> result = new ArrayList<>();
        
        if (k <= 0 || j <= 0 || k > j || j > inorderList.size()) {
            return result;
        }
        
        for (int i = k - 1; i < j; i++) {
            result.add(inorderList.get(i));
        }
        
        return result;
    }
    
    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }
    
    static class DynamicBST {
        private TreeNode root;
        
        public void insert(int val) {
            root = insertHelper(root, val);
        }
        
        private TreeNode insertHelper(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            
            if (val < root.val) {
                root.left = insertHelper(root.left, val);
            } else if (val > root.val) {
                root.right = insertHelper(root.right, val);
            }
            
            return root;
        }
        
        public boolean delete(int val) {
            if (search(val)) {
                root = deleteHelper(root, val);
                return true;
            }
            return false;
        }
        
        private TreeNode deleteHelper(TreeNode root, int val) {
            if (root == null) return null;
            
            if (val < root.val) {
                root.left = deleteHelper(root.left, val);
            } else if (val > root.val) {
                root.right = deleteHelper(root.right, val);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteHelper(root.right, minNode.val);
            }
            
            return root;
        }
        
        private TreeNode findMin(TreeNode root) {
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
        
        public boolean search(int val) {
            return searchHelper(root, val);
        }
        
        private boolean searchHelper(TreeNode root, int val) {
            if (root == null) return false;
            if (root.val == val) return true;
            
            return val < root.val ? 
                searchHelper(root.left, val) : 
                searchHelper(root.right, val);
        }
        
        public Integer findKthSmallest(int k) {
            return BSTKthElement.findKthSmallest(root, k);
        }
        
        public List<Integer> getAllElements() {
            List<Integer> result = new ArrayList<>();
            inorderTraversal(root, result);
            return result;
        }
    }
    
    public static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(35);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("=== BST第k小/大元素 ===");
        
        TreeNode bst = createSampleBST();
        List<Integer> allElements = new ArrayList<>();
        inorderTraversal(bst, allElements);
        System.out.println("BST中所有元素：" + allElements);
        
        System.out.println("\n第k小元素測試：");
        for (int k = 1; k <= 7; k++) {
            Integer kthSmallest = findKthSmallest(bst, k);
            System.out.println("第 " + k + " 小的元素：" + kthSmallest);
        }
        
        System.out.println("\n第k大元素測試：");
        for (int k = 1; k <= 7; k++) {
            Integer kthLargest = findKthLargest(bst, k);
            System.out.println("第 " + k + " 大的元素：" + kthLargest);
        }
        
        System.out.println("\n範圍查詢測試：");
        List<Integer> range = findKthToJthSmallest(bst, 2, 5);
        System.out.println("第2小到第5小的元素：" + range);
        
        System.out.println("\n動態BST測試：");
        DynamicBST dynamicBST = new DynamicBST();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        System.out.println("插入元素：" + Arrays.toString(values));
        for (int val : values) {
            dynamicBST.insert(val);
        }
        System.out.println("當前BST：" + dynamicBST.getAllElements());
        
        System.out.println("第3小的元素：" + dynamicBST.findKthSmallest(3));
        
        System.out.println("刪除元素30");
        dynamicBST.delete(30);
        System.out.println("刪除後BST：" + dynamicBST.getAllElements());
        System.out.println("第3小的元素：" + dynamicBST.findKthSmallest(3));
    }
}
