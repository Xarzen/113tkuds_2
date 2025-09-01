class AVLBasicExercise {
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
    
    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
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
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        int balance = getBalance(node);
        
        if (balance > 1 && data < node.left.data) {
            return rotateRight(node);
        }
        
        if (balance < -1 && data > node.right.data) {
            return rotateLeft(node);
        }
        
        if (balance > 1 && data > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        
        if (balance < -1 && data < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        return node;
    }
    
    public boolean search(int data) {
        return searchNode(root, data);
    }
    
    private boolean searchNode(Node node, int data) {
        if (node == null) {
            return false;
        }
        
        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return searchNode(node.left, data);
        } else {
            return searchNode(node.right, data);
        }
    }
    
    public int getTreeHeight() {
        return height(root);
    }
    
    public boolean isValidAVL() {
        return checkAVL(root);
    }
    
    private boolean checkAVL(Node node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }
        
        return checkAVL(node.left) && checkAVL(node.right);
    }
    
    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();
        
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);
        
        System.out.println("Search 30: " + avl.search(30));
        System.out.println("Search 15: " + avl.search(15));
        System.out.println("Tree height: " + avl.getTreeHeight());
        System.out.println("Is valid AVL: " + avl.isValidAVL());
    }
}
