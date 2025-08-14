class AVLRotationExercise {
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
    
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    public Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }
    
    public Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }
    
    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
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
        
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        
        if (balance > 1 && data > node.left.data) {
            return leftRightRotate(node);
        }
        
        if (balance < -1 && data < node.right.data) {
            return rightLeftRotate(node);
        }
        
        return node;
    }
    
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }
    
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }
    
    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();
        
        System.out.println("Testing left rotation scenario:");
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.inorderTraversal();
        
        avl = new AVLRotationExercise();
        System.out.println("Testing right rotation scenario:");
        avl.insert(30);
        avl.insert(20);
        avl.insert(10);
        avl.inorderTraversal();
        
        avl = new AVLRotationExercise();
        System.out.println("Testing left-right rotation scenario:");
        avl.insert(30);
        avl.insert(10);
        avl.insert(20);
        avl.inorderTraversal();
        
        avl = new AVLRotationExercise();
        System.out.println("Testing right-left rotation scenario:");
        avl.insert(10);
        avl.insert(30);
        avl.insert(20);
        avl.inorderTraversal();
    }
}
