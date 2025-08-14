import java.util.*;

class AVLLeaderboardSystem {
    static class Player {
        String name;
        int score;
        
        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return name + "(" + score + ")";
        }
    }
    
    static class Node {
        Player player;
        Node left, right;
        int height;
        int size;
        
        Node(Player player) {
            this.player = player;
            this.height = 1;
            this.size = 1;
        }
    }
    
    private Node root;
    private Map<String, Player> playerMap = new HashMap<>();
    
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }
    
    private void updateNode(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            node.size = size(node.left) + size(node.right) + 1;
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
        
        updateNode(y);
        updateNode(x);
        
        return x;
    }
    
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        updateNode(x);
        updateNode(y);
        
        return y;
    }
    
    public void addPlayer(String name, int score) {
        if (playerMap.containsKey(name)) {
            updatePlayerScore(name, score);
        } else {
            Player player = new Player(name, score);
            playerMap.put(name, player);
            root = insert(root, player);
        }
    }
    
    public void updatePlayerScore(String name, int newScore) {
        if (playerMap.containsKey(name)) {
            Player oldPlayer = playerMap.get(name);
            root = delete(root, oldPlayer);
            oldPlayer.score = newScore;
            root = insert(root, oldPlayer);
        }
    }
    
    private Node insert(Node node, Player player) {
        if (node == null) {
            return new Node(player);
        }
        
        if (player.score > node.player.score || 
            (player.score == node.player.score && player.name.compareTo(node.player.name) < 0)) {
            node.left = insert(node.left, player);
        } else {
            node.right = insert(node.right, player);
        }
        
        updateNode(node);
        
        return balance(node);
    }
    
    private Node delete(Node node, Player player) {
        if (node == null) return node;
        
        int cmp = comparePlayers(player, node.player);
        
        if (cmp < 0) {
            node.left = delete(node.left, player);
        } else if (cmp > 0) {
            node.right = delete(node.right, player);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            Node successor = findMin(node.right);
            node.player = successor.player;
            node.right = delete(node.right, successor.player);
        }
        
        updateNode(node);
        return balance(node);
    }
    
    private int comparePlayers(Player p1, Player p2) {
        if (p1.score != p2.score) {
            return Integer.compare(p2.score, p1.score);
        }
        return p1.name.compareTo(p2.name);
    }
    
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private Node balance(Node node) {
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
    
    public int getPlayerRank(String name) {
        if (!playerMap.containsKey(name)) return -1;
        Player player = playerMap.get(name);
        return getRank(root, player) + 1;
    }
    
    private int getRank(Node node, Player player) {
        if (node == null) return 0;
        
        int cmp = comparePlayers(player, node.player);
        
        if (cmp == 0) {
            return size(node.left);
        } else if (cmp < 0) {
            return getRank(node.left, player);
        } else {
            return size(node.left) + 1 + getRank(node.right, player);
        }
    }
    
    public List<Player> getTopK(int k) {
        List<Player> result = new ArrayList<>();
        getTopKHelper(root, k, result);
        return result;
    }
    
    private void getTopKHelper(Node node, int k, List<Player> result) {
        if (node == null || result.size() >= k) return;
        
        getTopKHelper(node.left, k, result);
        if (result.size() < k) {
            result.add(node.player);
        }
        getTopKHelper(node.right, k, result);
    }
    
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();
        
        leaderboard.addPlayer("Alice", 100);
        leaderboard.addPlayer("Bob", 150);
        leaderboard.addPlayer("Charlie", 120);
        leaderboard.addPlayer("Diana", 180);
        leaderboard.addPlayer("Eve", 90);
        
        System.out.println("Top 3 players: " + leaderboard.getTopK(3));
        System.out.println("Bob's rank: " + leaderboard.getPlayerRank("Bob"));
        
        leaderboard.updatePlayerScore("Bob", 200);
        System.out.println("After updating Bob's score to 200:");
        System.out.println("Top 3 players: " + leaderboard.getTopK(3));
        System.out.println("Bob's rank: " + leaderboard.getPlayerRank("Bob"));
    }
}
