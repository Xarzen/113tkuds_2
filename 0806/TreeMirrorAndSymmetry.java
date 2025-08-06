import java.util.*;

public class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }
    
    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return left.val == right.val &&
               isSymmetricHelper(left.left, right.right) &&
               isSymmetricHelper(left.right, right.left);
    }
    
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        mirrorTree(root.left);
        mirrorTree(root.right);
        
        return root;
    }
    
    public static boolean areMirrors(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) return true;
        if (tree1 == null || tree2 == null) return false;
        
        return tree1.val == tree2.val &&
               areMirrors(tree1.left, tree2.right) &&
               areMirrors(tree1.right, tree2.left);
    }
    
    public static boolean isSubtree(TreeNode mainTree, TreeNode subTree) {
        if (mainTree == null) return false;
        if (subTree == null) return true;
        
        if (isSameTree(mainTree, subTree)) return true;
        
        return isSubtree(mainTree.left, subTree) || isSubtree(mainTree.right, subTree);
    }
    
    private static boolean isSameTree(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) return true;
        if (tree1 == null || tree2 == null) return false;
        
        return tree1.val == tree2.val &&
               isSameTree(tree1.left, tree2.left) &&
               isSameTree(tree1.right, tree2.right);
    }
    
    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        
        return newNode;
    }
    
    public static TreeNode createSymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        return root;
    }
    
    public static TreeNode createAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        return root;
    }
    
    public static TreeNode createSubTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        return root;
    }
    
    public static TreeNode createMainTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        return root;
    }
    
    public static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 樹的鏡像與對稱 ===");
        
        TreeNode symmetricTree = createSymmetricTree();
        System.out.println("對稱樹測試：" + isSymmetric(symmetricTree));
        
        TreeNode asymmetricTree = createAsymmetricTree();
        System.out.println("非對稱樹測試：" + isSymmetric(asymmetricTree));
        
        System.out.println("\n原始樹的中序走訪：");
        List<Integer> original = new ArrayList<>();
        inorderTraversal(asymmetricTree, original);
        System.out.println(original);
        
        TreeNode mirroredTree = copyTree(asymmetricTree);
        mirrorTree(mirroredTree);
        System.out.println("鏡像樹的中序走訪：");
        List<Integer> mirrored = new ArrayList<>();
        inorderTraversal(mirroredTree, mirrored);
        System.out.println(mirrored);
        
        System.out.println("兩樹是否互為鏡像：" + areMirrors(asymmetricTree, mirroredTree));
        
        TreeNode mainTree = createMainTree();
        TreeNode subTree = createSubTree();
        
        System.out.println("\n主樹的中序走訪：");
        List<Integer> mainResult = new ArrayList<>();
        inorderTraversal(mainTree, mainResult);
        System.out.println(mainResult);
        
        System.out.println("子樹的中序走訪：");
        List<Integer> subResult = new ArrayList<>();
        inorderTraversal(subTree, subResult);
        System.out.println(subResult);
        
        System.out.println("子樹是否為主樹的子樹：" + isSubtree(mainTree, subTree));
    }
}
