import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, currentPath, result);
        
        return result;
    }
    
    private static void findPaths(TreeNode root, List<Integer> currentPath, List<List<Integer>> result) {
        if (root == null) return;
        
        currentPath.add(root.val);
        
        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(currentPath));
        } else {
            findPaths(root.left, currentPath, result);
            findPaths(root.right, currentPath, result);
        }
        
        currentPath.remove(currentPath.size() - 1);
    }
    
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
    
    public static List<Integer> maxSumPath(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        List<Integer> maxPath = new ArrayList<>();
        findMaxSumPath(root, new ArrayList<>(), maxPath);
        
        return maxPath;
    }
    
    private static int findMaxSumPath(TreeNode root, List<Integer> currentPath, List<Integer> maxPath) {
        if (root == null) return 0;
        
        currentPath.add(root.val);
        
        if (root.left == null && root.right == null) {
            int currentSum = currentPath.stream().mapToInt(Integer::intValue).sum();
            int maxSum = maxPath.stream().mapToInt(Integer::intValue).sum();
            
            if (maxPath.isEmpty() || currentSum > maxSum) {
                maxPath.clear();
                maxPath.addAll(new ArrayList<>(currentPath));
            }
            
            currentPath.remove(currentPath.size() - 1);
            return currentSum;
        }
        
        int leftSum = findMaxSumPath(root.left, currentPath, maxPath);
        int rightSum = findMaxSumPath(root.right, currentPath, maxPath);
        
        currentPath.remove(currentPath.size() - 1);
        return Math.max(leftSum, rightSum);
    }
    
    private static int maxDiameter = 0;
    
    public static int treeDiameter(TreeNode root) {
        maxDiameter = 0;
        calculateDiameter(root);
        return maxDiameter;
    }
    
    private static int calculateDiameter(TreeNode root) {
        if (root == null) return 0;
        
        int leftDepth = calculateDiameter(root.left);
        int rightDepth = calculateDiameter(root.right);
        
        int currentDiameter = leftDepth + rightDepth + root.val;
        maxDiameter = Math.max(maxDiameter, currentDiameter);
        
        return Math.max(leftDepth, rightDepth) + root.val;
    }
    
    public static int maxPathSumBetweenAnyNodes(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }
    
    private static int maxPathSumHelper(TreeNode root, int[] maxSum) {
        if (root == null) return 0;
        
        int leftSum = Math.max(0, maxPathSumHelper(root.left, maxSum));
        int rightSum = Math.max(0, maxPathSumHelper(root.right, maxSum));
        
        int currentMaxSum = root.val + leftSum + rightSum;
        maxSum[0] = Math.max(maxSum[0], currentMaxSum);
        
        return root.val + Math.max(leftSum, rightSum);
    }
    
    public static List<List<Integer>> pathSumAll(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> currentPath = new ArrayList<>();
        findAllPathSum(root, targetSum, currentPath, result);
        
        return result;
    }
    
    private static void findAllPathSum(TreeNode root, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (root == null) return;
        
        currentPath.add(root.val);
        
        if (root.left == null && root.right == null && targetSum == root.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            findAllPathSum(root.left, targetSum - root.val, currentPath, result);
            findAllPathSum(root.right, targetSum - root.val, currentPath, result);
        }
        
        currentPath.remove(currentPath.size() - 1);
    }
    
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        return root;
    }
    
    public static TreeNode createMaxSumTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(-25);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        return root;
    }
    
    public static TreeNode createDiameterTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 樹的路徑問題 ===");
        
        TreeNode tree = createSampleTree();
        
        System.out.println("所有根到葉的路徑：");
        List<List<Integer>> allPaths = rootToLeafPaths(tree);
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println("路徑 " + (i + 1) + "：" + allPaths.get(i));
        }
        
        int targetSum = 22;
        System.out.println("\n是否存在和為 " + targetSum + " 的路徑：" + hasPathSum(tree, targetSum));
        
        System.out.println("所有和為 " + targetSum + " 的路徑：");
        List<List<Integer>> targetPaths = pathSumAll(tree, targetSum);
        for (List<Integer> path : targetPaths) {
            System.out.println(path);
        }
        
        System.out.println("\n和最大的根到葉路徑：");
        List<Integer> maxPath = maxSumPath(tree);
        System.out.println(maxPath);
        System.out.println("路徑和：" + maxPath.stream().mapToInt(Integer::intValue).sum());
        
        TreeNode diameterTree = createDiameterTree();
        System.out.println("\n樹的直徑測試：");
        System.out.println("直徑：" + treeDiameter(diameterTree));
        
        TreeNode maxSumTree = createMaxSumTree();
        System.out.println("\n任意兩節點間最大路徑和：");
        System.out.println("最大路徑和：" + maxPathSumBetweenAnyNodes(maxSumTree));
        
        System.out.println("\n簡單樹測試：");
        TreeNode simpleTree = new TreeNode(1);
        simpleTree.left = new TreeNode(2);
        simpleTree.right = new TreeNode(3);
        
        System.out.println("簡單樹的所有路徑：" + rootToLeafPaths(simpleTree));
        System.out.println("是否有和為4的路徑：" + hasPathSum(simpleTree, 4));
    }
}
