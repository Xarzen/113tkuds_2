package midterm;
import java.util.*;

public class M07_BinaryTreeLeftView {
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        if (n == 0) {
            System.out.println("LeftView:");
            sc.close();
            return;
        }
        
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            if (val != -1) {
                nodes[i] = new TreeNode(val);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (nodes[i] != null) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                
                if (left < n) {
                    nodes[i].left = nodes[left];
                }
                if (right < n) {
                    nodes[i].right = nodes[right];
                }
            }
        }
        
        List<Integer> leftView = getLeftView(nodes[0]);
        System.out.print("LeftView:");
        for (int val : leftView) {
            System.out.print(" " + val);
        }
        System.out.println();
        
        sc.close();
    }
    
    private static List<Integer> getLeftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                if (i == 0) {
                    result.add(node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
    }
}
