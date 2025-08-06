import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static TreeNode buildTreeFromPreAndIn(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildPreInHelper(preorder, 0, preorder.length - 1, 
                               inorder, 0, inorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildPreInHelper(int[] preorder, int preStart, int preEnd,
                                           int[] inorder, int inStart, int inEnd,
                                           Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inorderMap.get(root.val);
        int leftTreeSize = rootIndex - inStart;
        
        root.left = buildPreInHelper(preorder, preStart + 1, preStart + leftTreeSize,
                                   inorder, inStart, rootIndex - 1, inorderMap);
        
        root.right = buildPreInHelper(preorder, preStart + leftTreeSize + 1, preEnd,
                                    inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
    
    public static TreeNode buildTreeFromPostAndIn(int[] postorder, int[] inorder) {
        if (postorder.length == 0 || inorder.length == 0) return null;
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildPostInHelper(postorder, 0, postorder.length - 1,
                               inorder, 0, inorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildPostInHelper(int[] postorder, int postStart, int postEnd,
                                            int[] inorder, int inStart, int inEnd,
                                            Map<Integer, Integer> inorderMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndex = inorderMap.get(root.val);
        int leftTreeSize = rootIndex - inStart;
        
        root.left = buildPostInHelper(postorder, postStart, postStart + leftTreeSize - 1,
                                    inorder, inStart, rootIndex - 1, inorderMap);
        
        root.right = buildPostInHelper(postorder, postStart + leftTreeSize, postEnd - 1,
                                     inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
    
    public static TreeNode buildCompleteTreeFromLevel(int[] levelorder) {
        if (levelorder.length == 0) return null;
        
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int index = 1;
        while (!queue.isEmpty() && index < levelorder.length) {
            TreeNode node = queue.poll();
            
            if (index < levelorder.length) {
                node.left = new TreeNode(levelorder[index++]);
                queue.offer(node.left);
            }
            
            if (index < levelorder.length) {
                node.right = new TreeNode(levelorder[index++]);
                queue.offer(node.right);
            }
        }
        
        return root;
    }
    
    public static boolean verifyReconstruction(TreeNode original, TreeNode reconstructed) {
        if (original == null && reconstructed == null) return true;
        if (original == null || reconstructed == null) return false;
        
        return original.val == reconstructed.val &&
               verifyReconstruction(original.left, reconstructed.left) &&
               verifyReconstruction(original.right, reconstructed.right);
    }
    
    public static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }
    
    public static void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorderTraversal(root.left, result);
            preorderTraversal(root.right, result);
        }
    }
    
    public static void postorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            postorderTraversal(root.left, result);
            postorderTraversal(root.right, result);
            result.add(root.val);
        }
    }
    
    public static List<Integer> levelorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        return result;
    }
    
    public static TreeNode createOriginalTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static TreeNode createComplexTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 樹的重建 ===");
        
        TreeNode originalTree = createOriginalTree();
        
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        
        preorderTraversal(originalTree, preorder);
        inorderTraversal(originalTree, inorder);
        postorderTraversal(originalTree, postorder);
        
        System.out.println("原始樹的走訪結果：");
        System.out.println("前序：" + preorder);
        System.out.println("中序：" + inorder);
        System.out.println("後序：" + postorder);
        
        System.out.println("\n根據前序和中序重建樹：");
        int[] preArr = preorder.stream().mapToInt(i -> i).toArray();
        int[] inArr = inorder.stream().mapToInt(i -> i).toArray();
        
        TreeNode rebuiltFromPreIn = buildTreeFromPreAndIn(preArr, inArr);
        
        List<Integer> rebuiltPreorder = new ArrayList<>();
        List<Integer> rebuiltInorder = new ArrayList<>();
        preorderTraversal(rebuiltFromPreIn, rebuiltPreorder);
        inorderTraversal(rebuiltFromPreIn, rebuiltInorder);
        
        System.out.println("重建樹的前序：" + rebuiltPreorder);
        System.out.println("重建樹的中序：" + rebuiltInorder);
        System.out.println("重建是否正確：" + verifyReconstruction(originalTree, rebuiltFromPreIn));
        
        System.out.println("\n根據後序和中序重建樹：");
        int[] postArr = postorder.stream().mapToInt(i -> i).toArray();
        
        TreeNode rebuiltFromPostIn = buildTreeFromPostAndIn(postArr, inArr);
        System.out.println("重建是否正確：" + verifyReconstruction(originalTree, rebuiltFromPostIn));
        
        System.out.println("\n根據層序重建完全二元樹：");
        TreeNode complexTree = createComplexTree();
        List<Integer> levelorder = levelorderTraversal(complexTree);
        System.out.println("層序走訪：" + levelorder);
        
        int[] levelArr = levelorder.stream().mapToInt(i -> i).toArray();
        TreeNode rebuiltFromLevel = buildCompleteTreeFromLevel(levelArr);
        
        List<Integer> rebuiltLevel = levelorderTraversal(rebuiltFromLevel);
        System.out.println("重建樹的層序：" + rebuiltLevel);
        System.out.println("重建是否正確：" + verifyReconstruction(complexTree, rebuiltFromLevel));
        
        System.out.println("\n驗證不同重建方法的一致性：");
        TreeNode tree1 = buildTreeFromPreAndIn(new int[]{1,2,4,5,3,6,7}, new int[]{4,2,5,1,6,3,7});
        TreeNode tree2 = buildTreeFromPostAndIn(new int[]{4,5,2,6,7,3,1}, new int[]{4,2,5,1,6,3,7});
        
        System.out.println("前序+中序與後序+中序重建結果一致：" + verifyReconstruction(tree1, tree2));
    }
}
