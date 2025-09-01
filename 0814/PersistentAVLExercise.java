import java.util.*;

class PersistentAVLExercise {
    static class Node {
        int data;
        Node left, right;
        int height;
        
        Node(int data) {
            this.data = data;
            this.height = 1;
        }
        
        Node(int data, Node left, Node right, int height) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }
    
    static class Version {
        Node root;
        int versionNumber;
        
        Version(Node root, int versionNumber) {
            this.root = root;
            this.versionNumber = versionNumber;
        }
    }
    
    private List<Version> versions;
    private int currentVersion;
    
    public PersistentAVLExercise() {
        versions = new ArrayList<>();
        versions.add(new Version(null, 0));
        currentVersion = 0;
    }
    
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
        
        Node newX = new Node(x.data, x.left, 
            new Node(y.data, T2, y.right, Math.max(height(T2), height(y.right)) + 1),
            0);
        newX.height = Math.max(height(newX.left), height(newX.right)) + 1;
        
        return newX;
    }
    
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        Node newY = new Node(y.data, 
            new Node(x.data, x.left, T2, Math.max(height(x.left), height(T2)) + 1),
            y.right, 0);
        newY.height = Math.max(height(newY.left), height(newY.right)) + 1;
        
        return newY;
    }
    
    public int insert(int data) {
        Node currentRoot = versions.get(currentVersion).root;
        Node newRoot = insertNode(currentRoot, data);
        
        Version newVersion = new Version(newRoot, versions.size());
        versions.add(newVersion);
        currentVersion = versions.size() - 1;
        
        return currentVersion;
    }
    
    private Node insertNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        
        Node newNode;
        if (data < node.data) {
            Node newLeft = insertNode(node.left, data);
            newNode = new Node(node.data, newLeft, node.right, 
                Math.max(height(newLeft), height(node.right)) + 1);
        } else if (data > node.data) {
            Node newRight = insertNode(node.right, data);
            newNode = new Node(node.data, node.left, newRight,
                Math.max(height(node.left), height(newRight)) + 1);
        } else {
            return node;
        }
        
        int balance = getBalance(newNode);
        
        if (balance > 1 && data < newNode.left.data) {
            return rotateRight(newNode);
        }
        
        if (balance < -1 && data > newNode.right.data) {
            return rotateLeft(newNode);
        }
        
        if (balance > 1 && data > newNode.left.data) {
            newNode = new Node(newNode.data, rotateLeft(newNode.left), newNode.right, 0);
            newNode.height = Math.max(height(newNode.left), height(newNode.right)) + 1;
            return rotateRight(newNode);
        }
        
        if (balance < -1 && data < newNode.right.data) {
            newNode = new Node(newNode.data, newNode.left, rotateRight(newNode.right), 0);
            newNode.height = Math.max(height(newNode.left), height(newNode.right)) + 1;
            return rotateLeft(newNode);
        }
        
        return newNode;
    }
    
    public boolean search(int data, int version) {
        if (version < 0 || version >= versions.size()) {
            return false;
        }
        
        Node root = versions.get(version).root;
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
    
    public List<Integer> getVersionHistory(int version) {
        if (version < 0 || version >= versions.size()) {
            return new ArrayList<>();
        }
        
        List<Integer> result = new ArrayList<>();
        inorderTraversal(versions.get(version).root, result);
        return result;
    }
    
    private void inorderTraversal(Node node, List<Integer> result) {
        if (node != null) {
            inorderTraversal(node.left, result);
            result.add(node.data);
            inorderTraversal(node.right, result);
        }
    }
    
    public int getCurrentVersion() {
        return currentVersion;
    }
    
    public int getTotalVersions() {
        return versions.size();
    }
    
    public static void main(String[] args) {
        PersistentAVLExercise persistentAVL = new PersistentAVLExercise();
        
        System.out.println("Version 0: " + persistentAVL.getVersionHistory(0));
        
        int v1 = persistentAVL.insert(10);
        System.out.println("Version " + v1 + ": " + persistentAVL.getVersionHistory(v1));
        
        int v2 = persistentAVL.insert(20);
        System.out.println("Version " + v2 + ": " + persistentAVL.getVersionHistory(v2));
        
        int v3 = persistentAVL.insert(30);
        System.out.println("Version " + v3 + ": " + persistentAVL.getVersionHistory(v3));
        
        System.out.println("Search 20 in version 1: " + persistentAVL.search(20, 1));
        System.out.println("Search 20 in version 2: " + persistentAVL.search(20, 2));
        System.out.println("Search 30 in version 2: " + persistentAVL.search(30, 2));
        
        System.out.println("Total versions: " + persistentAVL.getTotalVersions());
    }
}
