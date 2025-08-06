import java.util.Arrays;

public class SelectionSortImplementation {
    
    public static void selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        
        int comparisons = 0;
        int swaps = 0;
        
        System.out.println("=== 選擇排序過程 ===");
        System.out.println("初始陣列: " + Arrays.toString(array));
        
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < array.length; j++) {
                comparisons++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                swaps++;
                
                System.out.printf("第 %2d 輪: 交換位置 %d 和 %d -> %s\n", 
                                i + 1, i, minIndex, Arrays.toString(array));
            } else {
                System.out.printf("第 %2d 輪: 無需交換 -> %s\n", 
                                i + 1, Arrays.toString(array));
            }
        }
        
        System.out.println("最終結果: " + Arrays.toString(array));
        System.out.printf("統計: 比較次數 = %d, 交換次數 = %d\n", comparisons, swaps);
    }
    
    public static SortStatistics selectionSortWithStats(int[] array) {
        if (array == null || array.length <= 1) {
            return new SortStatistics(0, 0);
        }
        
        int[] sortedArray = array.clone();
        int comparisons = 0;
        int swaps = 0;
        
        for (int i = 0; i < sortedArray.length - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < sortedArray.length; j++) {
                comparisons++;
                if (sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                int temp = sortedArray[i];
                sortedArray[i] = sortedArray[minIndex];
                sortedArray[minIndex] = temp;
                swaps++;
            }
        }
        
        return new SortStatistics(comparisons, swaps);
    }
    
    public static SortStatistics bubbleSortWithStats(int[] array) {
        if (array == null || array.length <= 1) {
            return new SortStatistics(0, 0);
        }
        
        int[] sortedArray = array.clone();
        int comparisons = 0;
        int swaps = 0;
        
        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = 0; j < sortedArray.length - 1 - i; j++) {
                comparisons++;
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swaps++;
                }
            }
        }
        
        return new SortStatistics(comparisons, swaps);
    }
    
    public static void compareAlgorithms(int[] testArray) {
        System.out.println("\n=== 演算法效能比較 ===");
        System.out.println("測試陣列: " + Arrays.toString(testArray));
        System.out.printf("陣列長度: %d\n", testArray.length);
        System.out.println();
        
        SortStatistics selectionStats = selectionSortWithStats(testArray);
        System.out.println("選擇排序統計:");
        System.out.printf("  比較次數: %d\n", selectionStats.comparisons);
        System.out.printf("  交換次數: %d\n", selectionStats.swaps);
        System.out.printf("  總操作數: %d\n", selectionStats.totalOperations());
        
        SortStatistics bubbleStats = bubbleSortWithStats(testArray);
        System.out.println("\n氣泡排序統計:");
        System.out.printf("  比較次數: %d\n", bubbleStats.comparisons);
        System.out.printf("  交換次數: %d\n", bubbleStats.swaps);
        System.out.printf("  總操作數: %d\n", bubbleStats.totalOperations());
        
        System.out.println("\n效能比較:");
        System.out.printf("  選擇排序 vs 氣泡排序 (比較次數): %d vs %d\n", 
                        selectionStats.comparisons, bubbleStats.comparisons);
        System.out.printf("  選擇排序 vs 氣泡排序 (交換次數): %d vs %d\n", 
                        selectionStats.swaps, bubbleStats.swaps);
        
        if (selectionStats.totalOperations() < bubbleStats.totalOperations()) {
            double improvement = (double) (bubbleStats.totalOperations() - selectionStats.totalOperations()) 
                               / bubbleStats.totalOperations() * 100;
            System.out.printf("  選擇排序比氣泡排序效率高 %.1f%%\n", improvement);
        } else if (selectionStats.totalOperations() > bubbleStats.totalOperations()) {
            double difference = (double) (selectionStats.totalOperations() - bubbleStats.totalOperations()) 
                              / bubbleStats.totalOperations() * 100;
            System.out.printf("  氣泡排序比選擇排序效率高 %.1f%%\n", difference);
        } else {
            System.out.println("  兩個演算法效率相同");
        }
    }
    
    public static int[] generateTestArray(String type, int size) {
        int[] array = new int[size];
        
        switch (type.toLowerCase()) {
            case "random":
                for (int i = 0; i < size; i++) {
                    array[i] = (int) (Math.random() * 100);
                }
                break;
            case "reverse":
                for (int i = 0; i < size; i++) {
                    array[i] = size - i;
                }
                break;
            case "sorted":
                for (int i = 0; i < size; i++) {
                    array[i] = i + 1;
                }
                break;
            case "duplicate":
                for (int i = 0; i < size; i++) {
                    array[i] = i % 3 + 1;  // 只有 1, 2, 3 三個值
                }
                break;
            default:
                for (int i = 0; i < size; i++) {
                    array[i] = (int) (Math.random() * 100);
                }
        }
        
        return array;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 選擇排序實作與比較 ===\n");
        
        int[] testArray1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("1. 基本選擇排序演示:");
        selectionSort(testArray1.clone());
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        String[] testTypes = {"random", "reverse", "sorted", "duplicate"};
        String[] typeNames = {"隨機陣列", "逆序陣列", "已排序陣列", "重複元素陣列"};
        
        for (int i = 0; i < testTypes.length; i++) {
            System.out.printf("2.%d %s測試:\n", i + 1, typeNames[i]);
            int[] testArray = generateTestArray(testTypes[i], 10);
            compareAlgorithms(testArray);
            System.out.println("\n" + "-".repeat(60) + "\n");
        }
        
        System.out.println("3. 不同大小陣列效能測試:");
        int[] sizes = {5, 10, 20, 30};
        
        for (int size : sizes) {
            System.out.printf("陣列大小: %d\n", size);
            int[] randomArray = generateTestArray("random", size);
            
            SortStatistics selectionStats = selectionSortWithStats(randomArray);
            SortStatistics bubbleStats = bubbleSortWithStats(randomArray);
            
            System.out.printf("  選擇排序: %d 次操作\n", selectionStats.totalOperations());
            System.out.printf("  氣泡排序: %d 次操作\n", bubbleStats.totalOperations());
            System.out.println();
        }
    }
}

class SortStatistics {
    public final int comparisons;
    public final int swaps;
    
    public SortStatistics(int comparisons, int swaps) {
        this.comparisons = comparisons;
        this.swaps = swaps;
    }
    
    public int totalOperations() {
        return comparisons + swaps;
    }
}
