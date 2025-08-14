class AVLDeleteExercise {
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
        
        return balanceNode(node);
    }
    
    public void delete(int data) {
        root = deleteNode(root, data);
    }
    
    private Node deleteNode(Node node, int data) {
        if (node == null) {
            return node;
        }
        
        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteNode(node.right, successor.data);
        }
        
        updateHeight(node);
        
        return balanceNode(node);
    }
    
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private Node balanceNode(Node node) {
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        
        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
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
        AVLDeleteExercise avl = new AVLDeleteExercise();
        
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int value : values) {
            avl.insert(value);
        }
        
        System.out.println("Initial tree:");
        avl.inorderTraversal();
        
        System.out.println("Deleting leaf node (50):");
        avl.delete(50);
        avl.inorderTraversal();
        
        System.out.println("Deleting node with one child (30):");
        avl.delete(30);
        avl.inorderTraversal();
        
        System.out.println("Deleting node with two children (20):");
        avl.delete(20);
        avl.inorderTraversal();
    }
}
