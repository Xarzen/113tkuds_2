import java.util.*;

public class RecursiveTreePreview {
    
    static class FileSystemNode {
        String name;
        boolean isDirectory;
        List<FileSystemNode> children;
        int fileSize;
        
        public FileSystemNode(String name, boolean isDirectory, int fileSize) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.fileSize = fileSize;
            this.children = new ArrayList<>();
        }
        
        public void addChild(FileSystemNode child) {
            if (isDirectory) {
                children.add(child);
            }
        }
    }
    
    public static int countTotalFiles(FileSystemNode root) {
        if (root == null) {
            return 0;
        }
        
        if (!root.isDirectory) {
            return 1;
        }
        
        int totalFiles = 0;
        for (FileSystemNode child : root.children) {
            totalFiles += countTotalFiles(child);
        }
        
        return totalFiles;
    }
    
    public static int calculateTotalSize(FileSystemNode root) {
        if (root == null) {
            return 0;
        }
        
        if (!root.isDirectory) {
            return root.fileSize;
        }
        
        int totalSize = 0;
        for (FileSystemNode child : root.children) {
            totalSize += calculateTotalSize(child);
        }
        
        return totalSize;
    }
    
    public static void printFileSystem(FileSystemNode root, int depth) {
        if (root == null) {
            return;
        }
        
        String indent = "  ".repeat(depth);
        String type = root.isDirectory ? "[DIR]" : "[FILE]";
        String sizeInfo = root.isDirectory ? "" : " (" + root.fileSize + " KB)";
        
        System.out.println(indent + type + " " + root.name + sizeInfo);
        
        if (root.isDirectory) {
            for (FileSystemNode child : root.children) {
                printFileSystem(child, depth + 1);
            }
        }
    }
    
    static class MenuItem {
        String title;
        List<MenuItem> subItems;
        boolean isLeaf;
        
        public MenuItem(String title, boolean isLeaf) {
            this.title = title;
            this.isLeaf = isLeaf;
            this.subItems = new ArrayList<>();
        }
        
        public void addSubItem(MenuItem item) {
            if (!isLeaf) {
                subItems.add(item);
            }
        }
    }
    
    public static void printMenu(MenuItem root, int level) {
        if (root == null) {
            return;
        }
        
        String prefix = "|-- ".repeat(level);
        String marker = root.isLeaf ? "• " : "▼ ";
        
        System.out.println(prefix + marker + root.title);
        
        if (!root.isLeaf) {
            for (MenuItem subItem : root.subItems) {
                printMenu(subItem, level + 1);
            }
        }
    }
    
    public static int getMenuDepth(MenuItem root) {
        if (root == null || root.isLeaf) {
            return 1;
        }
        
        int maxDepth = 0;
        for (MenuItem subItem : root.subItems) {
            maxDepth = Math.max(maxDepth, getMenuDepth(subItem));
        }
        
        return 1 + maxDepth;
    }
    
    public static List<Integer> flattenNestedArray(Object[] array) {
        List<Integer> result = new ArrayList<>();
        flattenHelper(array, result);
        return result;
    }
    
    private static void flattenHelper(Object[] array, List<Integer> result) {
        for (Object element : array) {
            if (element instanceof Integer) {
                result.add((Integer) element);
            } else if (element instanceof Object[]) {
                flattenHelper((Object[]) element, result);
            }
        }
    }
    
    public static int getNestedArrayDepth(Object[] array) {
        int maxDepth = 1;
        
        for (Object element : array) {
            if (element instanceof Object[]) {
                maxDepth = Math.max(maxDepth, 1 + getNestedArrayDepth((Object[]) element));
            }
        }
        
        return maxDepth;
    }
    
    public static int countNestedElements(Object[] array) {
        int count = 0;
        
        for (Object element : array) {
            if (element instanceof Integer) {
                count++;
            } else if (element instanceof Object[]) {
                count += countNestedElements((Object[]) element);
            }
        }
        
        return count;
    }
    
    static class NestedList {
        private Object value;
        private List<NestedList> children;
        private boolean isInteger;
        
        public NestedList(int value) {
            this.value = value;
            this.isInteger = true;
            this.children = null;
        }
        
        public NestedList() {
            this.value = null;
            this.isInteger = false;
            this.children = new ArrayList<>();
        }
        
        public void add(NestedList item) {
            if (!isInteger && children != null) {
                children.add(item);
            }
        }
        
        public boolean isInteger() {
            return isInteger;
        }
        
        public int getInteger() {
            return isInteger ? (Integer) value : 0;
        }
        
        public List<NestedList> getList() {
            return isInteger ? null : children;
        }
    }
    
    public static int calculateNestedSum(NestedList nestedList, int depth) {
        if (nestedList.isInteger()) {
            return nestedList.getInteger() * depth;
        }
        
        int sum = 0;
        for (NestedList item : nestedList.getList()) {
            sum += calculateNestedSum(item, depth + 1);
        }
        
        return sum;
    }
    
    public static int getMaxNestedDepth(NestedList nestedList) {
        if (nestedList.isInteger()) {
            return 1;
        }
        
        int maxDepth = 1;
        for (NestedList item : nestedList.getList()) {
            maxDepth = Math.max(maxDepth, 1 + getMaxNestedDepth(item));
        }
        
        return maxDepth;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 遞迴樹狀結構預習 ===\n");
        
        System.out.println("1. 檔案系統模擬:");
        FileSystemNode root = new FileSystemNode("根目錄", true, 0);
        
        FileSystemNode documents = new FileSystemNode("Documents", true, 0);
        FileSystemNode pictures = new FileSystemNode("Pictures", true, 0);
        FileSystemNode music = new FileSystemNode("Music", true, 0);
        
        documents.addChild(new FileSystemNode("report.txt", false, 150));
        documents.addChild(new FileSystemNode("presentation.ppt", false, 2000));
        
        pictures.addChild(new FileSystemNode("photo1.jpg", false, 800));
        pictures.addChild(new FileSystemNode("photo2.jpg", false, 950));
        
        music.addChild(new FileSystemNode("song1.mp3", false, 4000));
        music.addChild(new FileSystemNode("song2.mp3", false, 3500));
        
        root.addChild(documents);
        root.addChild(pictures);
        root.addChild(music);
        
        printFileSystem(root, 0);
        System.out.println("總檔案數: " + countTotalFiles(root));
        System.out.println("總檔案大小: " + calculateTotalSize(root) + " KB");
        
        System.out.println("\n2. 多層選單結構:");
        MenuItem mainMenu = new MenuItem("主選單", false);
        
        MenuItem fileMenu = new MenuItem("檔案", false);
        fileMenu.addSubItem(new MenuItem("新增", true));
        fileMenu.addSubItem(new MenuItem("開啟", true));
        fileMenu.addSubItem(new MenuItem("儲存", true));
        
        MenuItem editMenu = new MenuItem("編輯", false);
        editMenu.addSubItem(new MenuItem("複製", true));
        editMenu.addSubItem(new MenuItem("貼上", true));
        
        MenuItem viewMenu = new MenuItem("檢視", false);
        MenuItem zoomMenu = new MenuItem("縮放", false);
        zoomMenu.addSubItem(new MenuItem("放大", true));
        zoomMenu.addSubItem(new MenuItem("縮小", true));
        viewMenu.addSubItem(zoomMenu);
        
        mainMenu.addSubItem(fileMenu);
        mainMenu.addSubItem(editMenu);
        mainMenu.addSubItem(viewMenu);
        
        printMenu(mainMenu, 0);
        System.out.println("選單最大深度: " + getMenuDepth(mainMenu));
        
        System.out.println("\n3. 巢狀陣列展平:");
        Object[] nestedArray = {
            1, 2, 
            new Object[]{3, 4, new Object[]{5, 6}}, 
            7, 
            new Object[]{8, new Object[]{9, 10}}
        };
        
        List<Integer> flattened = flattenNestedArray(nestedArray);
        System.out.println("巢狀陣列結構: [1, 2, [3, 4, [5, 6]], 7, [8, [9, 10]]]");
        System.out.println("展平結果: " + flattened);
        System.out.println("陣列深度: " + getNestedArrayDepth(nestedArray));
        System.out.println("元素總數: " + countNestedElements(nestedArray));
        
        System.out.println("\n4. 巢狀清單深度加權計算:");
        NestedList nestedList = new NestedList();
        nestedList.add(new NestedList(1));
        nestedList.add(new NestedList(2));
        
        NestedList subList1 = new NestedList();
        subList1.add(new NestedList(3));
        subList1.add(new NestedList(4));
        
        NestedList subSubList = new NestedList();
        subSubList.add(new NestedList(5));
        subList1.add(subSubList);
        
        nestedList.add(subList1);
        
        System.out.println("巢狀清單結構: [1, 2, [3, 4, [5]]]");
        System.out.println("深度加權總和: " + calculateNestedSum(nestedList, 1));
        System.out.println("最大深度: " + getMaxNestedDepth(nestedList));
        
        System.out.println("\n=== 樹狀結構概念預習 ===");
        System.out.println("在這些練習中，我們學習了:");
        System.out.println("• 樹的遍歷 (深度優先搜尋)");
        System.out.println("• 樹的高度/深度計算");
        System.out.println("• 樹節點的計數");
        System.out.println("• 遞迴處理階層式資料結構");
        System.out.println("\n這些概念是理解二元樹、AVL樹等資料結構的基礎！");
    }
}
