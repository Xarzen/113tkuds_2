import java.util.*;

public class MultiWayTreeAndDecisionTree {
    
    static class MultiWayTreeNode {
        int val;
        List<MultiWayTreeNode> children;
        
        MultiWayTreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
        
        public void addChild(MultiWayTreeNode child) {
            children.add(child);
        }
        
        public int getDegree() {
            return children.size();
        }
    }
    
    static class DecisionTreeNode {
        String question;
        String answer;
        Map<String, DecisionTreeNode> branches;
        boolean isLeaf;
        
        DecisionTreeNode(String question) {
            this.question = question;
            this.branches = new HashMap<>();
            this.isLeaf = false;
        }
        
        DecisionTreeNode(String answer, boolean isLeaf) {
            this.answer = answer;
            this.isLeaf = isLeaf;
            this.branches = new HashMap<>();
        }
        
        public void addBranch(String condition, DecisionTreeNode node) {
            branches.put(condition, node);
        }
    }
    
    public static void depthFirstTraversal(MultiWayTreeNode root) {
        if (root == null) return;
        
        System.out.print(root.val + " ");
        
        for (MultiWayTreeNode child : root.children) {
            depthFirstTraversal(child);
        }
    }
    
    public static void breadthFirstTraversal(MultiWayTreeNode root) {
        if (root == null) return;
        
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.val + " ");
            
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }
    
    public static int calculateHeight(MultiWayTreeNode root) {
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;
        
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, calculateHeight(child));
        }
        
        return maxChildHeight + 1;
    }
    
    public static Map<MultiWayTreeNode, Integer> calculateDegrees(MultiWayTreeNode root) {
        Map<MultiWayTreeNode, Integer> degrees = new HashMap<>();
        if (root == null) return degrees;
        
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            degrees.put(node, node.getDegree());
            
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
        
        return degrees;
    }
    
    public static DecisionTreeNode createGuessNumberGame() {
        DecisionTreeNode root = new DecisionTreeNode("你想的數字是否大於50？");
        
        DecisionTreeNode greater50 = new DecisionTreeNode("你想的數字是否大於75？");
        DecisionTreeNode less50 = new DecisionTreeNode("你想的數字是否大於25？");
        
        root.addBranch("是", greater50);
        root.addBranch("否", less50);
        
        DecisionTreeNode greater75 = new DecisionTreeNode("你想的數字是否大於87？");
        DecisionTreeNode between5075 = new DecisionTreeNode("你想的數字是否大於62？");
        greater50.addBranch("是", greater75);
        greater50.addBranch("否", between5075);
        
        DecisionTreeNode between2550 = new DecisionTreeNode("你想的數字是否大於37？");
        DecisionTreeNode less25 = new DecisionTreeNode("你想的數字是否大於12？");
        less50.addBranch("是", between2550);
        less50.addBranch("否", less25);
        
        greater75.addBranch("是", new DecisionTreeNode("是93嗎？", true));
        greater75.addBranch("否", new DecisionTreeNode("是81嗎？", true));
        
        between5075.addBranch("是", new DecisionTreeNode("是68嗎？", true));
        between5075.addBranch("否", new DecisionTreeNode("是56嗎？", true));
        
        between2550.addBranch("是", new DecisionTreeNode("是43嗎？", true));
        between2550.addBranch("否", new DecisionTreeNode("是31嗎？", true));
        
        less25.addBranch("是", new DecisionTreeNode("是18嗎？", true));
        less25.addBranch("否", new DecisionTreeNode("是6嗎？", true));
        
        return root;
    }
    
    public static void playGuessNumberGame(DecisionTreeNode root, Scanner scanner) {
        DecisionTreeNode current = root;
        
        while (!current.isLeaf) {
            System.out.println(current.question);
            System.out.print("請回答 (是/否)：");
            String answer = scanner.nextLine().trim();
            
            if (current.branches.containsKey(answer)) {
                current = current.branches.get(answer);
            } else {
                System.out.println("請回答 '是' 或 '否'");
            }
        }
        
        System.out.println(current.answer);
    }
    
    public static MultiWayTreeNode createSampleMultiWayTree() {
        MultiWayTreeNode root = new MultiWayTreeNode(1);
        
        MultiWayTreeNode child1 = new MultiWayTreeNode(2);
        MultiWayTreeNode child2 = new MultiWayTreeNode(3);
        MultiWayTreeNode child3 = new MultiWayTreeNode(4);
        
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        
        child1.addChild(new MultiWayTreeNode(5));
        child1.addChild(new MultiWayTreeNode(6));
        
        child2.addChild(new MultiWayTreeNode(7));
        child2.addChild(new MultiWayTreeNode(8));
        child2.addChild(new MultiWayTreeNode(9));
        
        child3.addChild(new MultiWayTreeNode(10));
        
        return root;
    }
    
    public static DecisionTreeNode createAnimalGame() {
        DecisionTreeNode root = new DecisionTreeNode("它是哺乳動物嗎？");
        
        DecisionTreeNode mammal = new DecisionTreeNode("它生活在陸地上嗎？");
        DecisionTreeNode nonMammal = new DecisionTreeNode("它會飛嗎？");
        
        root.addBranch("是", mammal);
        root.addBranch("否", nonMammal);
        
        mammal.addBranch("是", new DecisionTreeNode("是獅子嗎？", true));
        mammal.addBranch("否", new DecisionTreeNode("是鯨魚嗎？", true));
        
        nonMammal.addBranch("是", new DecisionTreeNode("是老鷹嗎？", true));
        nonMammal.addBranch("否", new DecisionTreeNode("是魚嗎？", true));
        
        return root;
    }
    
    public static int countNodes(MultiWayTreeNode root) {
        if (root == null) return 0;
        
        int count = 1;
        for (MultiWayTreeNode child : root.children) {
            count += countNodes(child);
        }
        
        return count;
    }
    
    public static List<Integer> getAllValues(MultiWayTreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) return values;
        
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            values.add(node.val);
            
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
        
        return values;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 多路樹與決策樹 ===");
        
        MultiWayTreeNode multiTree = createSampleMultiWayTree();
        
        System.out.println("多路樹深度優先走訪：");
        depthFirstTraversal(multiTree);
        System.out.println();
        
        System.out.println("多路樹廣度優先走訪：");
        breadthFirstTraversal(multiTree);
        System.out.println();
        
        System.out.println("\n多路樹統計：");
        System.out.println("樹的高度：" + calculateHeight(multiTree));
        System.out.println("節點總數：" + countNodes(multiTree));
        System.out.println("所有節點值：" + getAllValues(multiTree));
        
        System.out.println("\n各節點的度數：");
        Map<MultiWayTreeNode, Integer> degrees = calculateDegrees(multiTree);
        List<Integer> allValues = getAllValues(multiTree);
        for (int i = 0; i < allValues.size(); i++) {
            System.out.println("節點 " + allValues.get(i) + " 的度數：" + 
                degrees.values().toArray()[i]);
        }
        
        System.out.println("\n決策樹示例 - 動物猜謎遊戲：");
        DecisionTreeNode animalGame = createAnimalGame();
        
        Scanner scanner = new Scanner("是\n是\n");
        System.out.println("模擬遊戲過程（輸入：是, 是）：");
        
        DecisionTreeNode current = animalGame;
        String[] simulatedInputs = {"是", "是"};
        int inputIndex = 0;
        
        while (!current.isLeaf && inputIndex < simulatedInputs.length) {
            System.out.println(current.question);
            String answer = simulatedInputs[inputIndex++];
            System.out.println("回答：" + answer);
            
            if (current.branches.containsKey(answer)) {
                current = current.branches.get(answer);
            }
        }
        
        if (current.isLeaf) {
            System.out.println("結果：" + current.answer);
        }
        
        System.out.println("\n猜數字遊戲示例：");
        DecisionTreeNode guessGame = createGuessNumberGame();
        
        String[] guessInputs = {"是", "否", "是"};
        current = guessGame;
        inputIndex = 0;
        
        System.out.println("模擬猜數字過程（目標數字68，輸入：是, 否, 是）：");
        while (!current.isLeaf && inputIndex < guessInputs.length) {
            System.out.println(current.question);
            String answer = guessInputs[inputIndex++];
            System.out.println("回答：" + answer);
            
            if (current.branches.containsKey(answer)) {
                current = current.branches.get(answer);
            }
        }
        
        if (current.isLeaf) {
            System.out.println("結果：" + current.answer);
        }
    }
}
