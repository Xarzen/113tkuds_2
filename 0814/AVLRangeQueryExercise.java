import java.util.*;

class AVLRangeQueryExercise {
    static class Node {
        int data;
        Node left, right;
        int height;
        
        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }
    
    private Node root;
    
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }
    
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    
    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    public void insert(int data) {
        root = insertNode(root, data);
    }
    
    private Node insertNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        
        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node;
        }
        
        updateHeight(node);
        
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (data < node.left.data) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        
        if (balance < -1) {
            if (data > node.right.data) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        
        return node;
    }
    
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }
    
    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }
        
        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }
    
    public static void main(String[] args) {
        AVLRangeQueryExercise avl = new AVLRangeQueryExercise();
        
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int value : values) {
            avl.insert(value);
        }
        
        System.out.println("Range query [10, 22]: " + avl.rangeQuery(10, 22));
        System.out.println("Range query [5, 15]: " + avl.rangeQuery(5, 15));
        System.out.println("Range query [1, 30]: " + avl.rangeQuery(1, 30));
        System.out.println("Range query [15, 25]: " + avl.rangeQuery(15, 25));
    }
}
