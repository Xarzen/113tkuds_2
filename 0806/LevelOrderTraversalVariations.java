import java.util.*;

public class LevelOrderTraversalVariations {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
    
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    public static Map<Integer, List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> result = new TreeMap<>();
        if (root == null) return result;
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        posQueue.offer(0);
        
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int pos = posQueue.poll();
            
            result.putIfAbsent(pos, new ArrayList<>());
            result.get(pos).add(node.val);
            
            if (node.left != null) {
                nodeQueue.offer(node.left);
                posQueue.offer(pos - 1);
            }
            
            if (node.right != null) {
                nodeQueue.offer(node.right);
                posQueue.offer(pos + 1);
            }
        }
        
        return result;
    }
    
    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (i == 0) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        posQueue.offer(0);
        
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int pos = posQueue.poll();
            
            map.put(pos, node.val);
            
            if (node.left != null) {
                nodeQueue.offer(node.left);
                posQueue.offer(pos - 1);
            }
            
            if (node.right != null) {
                nodeQueue.offer(node.right);
                posQueue.offer(pos + 1);
            }
        }
        
        for (int value : map.values()) {
            result.add(value);
        }
        
        return result;
    }
    
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        return root;
    }
    
    public static TreeNode createComplexTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 層序走訪變形 ===");
        
        TreeNode tree1 = createSampleTree();
        
        System.out.println("標準層序走訪（每層分別儲存）：");
        List<List<Integer>> levelOrderResult = levelOrder(tree1);
        for (int i = 0; i < levelOrderResult.size(); i++) {
            System.out.println("第 " + (i + 1) + " 層：" + levelOrderResult.get(i));
        }
        
        System.out.println("\n之字形層序走訪：");
        List<List<Integer>> zigzagResult = zigzagLevelOrder(tree1);
        for (int i = 0; i < zigzagResult.size(); i++) {
            System.out.println("第 " + (i + 1) + " 層：" + zigzagResult.get(i));
        }
        
        System.out.println("\n右側視圖（每層最後一個節點）：");
        List<Integer> rightView = rightSideView(tree1);
        System.out.println(rightView);
        
        System.out.println("\n左側視圖（每層第一個節點）：");
        List<Integer> leftView = leftSideView(tree1);
        System.out.println(leftView);
        
        TreeNode tree2 = createComplexTree();
        System.out.println("\n垂直層序走訪：");
        Map<Integer, List<Integer>> verticalResult = verticalTraversal(tree2);
        for (Map.Entry<Integer, List<Integer>> entry : verticalResult.entrySet()) {
            System.out.println("位置 " + entry.getKey() + "：" + entry.getValue());
        }
        
        System.out.println("\n底部視圖：");
        List<Integer> bottomResult = bottomView(tree2);
        System.out.println(bottomResult);
        
        System.out.println("\n不同樹的測試：");
        TreeNode simpleTree = new TreeNode(1);
        simpleTree.left = new TreeNode(2);
        simpleTree.right = new TreeNode(3);
        
        System.out.println("簡單樹的右側視圖：" + rightSideView(simpleTree));
        System.out.println("簡單樹的層序走訪：" + levelOrder(simpleTree));
    }
}
