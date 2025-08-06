import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberArrayProcessor {
    
    public static int[] removeDuplicates(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        
        boolean[] visited = new boolean[array.length];
        int uniqueCount = 0;
        
        for (int i = 0; i < array.length; i++) {
            if (!visited[i]) {
                uniqueCount++;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] == array[j]) {
                        visited[j] = true;
                    }
                }
            }
        }
        
        int[] result = new int[uniqueCount];
        int index = 0;
        visited = new boolean[array.length];
        
        for (int i = 0; i < array.length; i++) {
            if (!visited[i]) {
                result[index++] = array[i];
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] == array[j]) {
                        visited[j] = true;
                    }
                }
            }
        }
        
        return result;
    }
    
    public static int[] mergeSortedArrays(int[] array1, int[] array2) {
        if (array1 == null) return array2;
        if (array2 == null) return array1;
        
        int[] merged = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                merged[k++] = array1[i++];
            } else {
                merged[k++] = array2[j++];
            }
        }
        
        while (i < array1.length) {
            merged[k++] = array1[i++];
        }
        
        while (j < array2.length) {
            merged[k++] = array2[j++];
        }
        
        return merged;
    }
    
    public static int findMostFrequent(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("陣列不能為空");
        }
        
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        int mostFrequent = array[0];
        int maxFrequency = 0;
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    public static void printFrequencyAnalysis(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("陣列為空");
            return;
        }
        
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        System.out.println("元素頻率分析：");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            double percentage = (double) entry.getValue() / array.length * 100;
            System.out.printf("  %d: 出現 %d 次 (%.1f%%)\n", 
                            entry.getKey(), entry.getValue(), percentage);
        }
    }
    
    public static int[][] splitArray(int[] array) {
        if (array == null || array.length == 0) {
            return new int[2][0];
        }
        
        int midPoint = (array.length + 1) / 2;
        
        int[] firstHalf = new int[midPoint];
        int[] secondHalf = new int[array.length - midPoint];
        
        System.arraycopy(array, 0, firstHalf, 0, midPoint);
        
        System.arraycopy(array, midPoint, secondHalf, 0, array.length - midPoint);
        
        return new int[][]{firstHalf, secondHalf};
    }
    
    public static boolean isSorted(int[] array) {
        if (array == null || array.length <= 1) {
            return true;
        }
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static int[] simpleSort(int[] array) {
        if (array == null) return null;
        
        int[] sorted = array.clone();
        Arrays.sort(sorted);
        return sorted;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 數字陣列處理器測試 ===\n");
        
        int[] testArray = {5, 2, 8, 2, 9, 1, 5, 5, 3, 8, 1};
        int[] sortedArray1 = {1, 3, 5, 7, 9};
        int[] sortedArray2 = {2, 4, 6, 8, 10, 12};
        
        System.out.println("原始陣列: " + Arrays.toString(testArray));
        System.out.println("已排序陣列1: " + Arrays.toString(sortedArray1));
        System.out.println("已排序陣列2: " + Arrays.toString(sortedArray2));
        System.out.println();
        
        System.out.println("=== 移除重複元素測試 ===");
        int[] unique = removeDuplicates(testArray);
        System.out.println("移除重複後: " + Arrays.toString(unique));
        System.out.println("原長度: " + testArray.length + " → 新長度: " + unique.length);
        System.out.println();
        
        System.out.println("=== 頻率分析測試 ===");
        printFrequencyAnalysis(testArray);
        int mostFrequent = findMostFrequent(testArray);
        System.out.println("出現最頻繁的元素: " + mostFrequent);
        System.out.println();
        
        System.out.println("=== 合併已排序陣列測試 ===");
        int[] merged = mergeSortedArrays(sortedArray1, sortedArray2);
        System.out.println("合併結果: " + Arrays.toString(merged));
        System.out.println("合併後是否仍有序: " + isSorted(merged));
        System.out.println();
        
        System.out.println("=== 陣列分割測試 ===");
        int[][] split = splitArray(testArray);
        System.out.println("原陣列: " + Arrays.toString(testArray));
        System.out.println("第一部分: " + Arrays.toString(split[0]));
        System.out.println("第二部分: " + Arrays.toString(split[1]));
        System.out.println("分割驗證: " + split[0].length + " + " + split[1].length + 
                          " = " + (split[0].length + split[1].length));
        System.out.println();
        
        int[] oddArray = {1, 2, 3, 4, 5};
        int[][] oddSplit = splitArray(oddArray);
        System.out.println("奇數陣列分割測試:");
        System.out.println("原陣列: " + Arrays.toString(oddArray));
        System.out.println("第一部分: " + Arrays.toString(oddSplit[0]));
        System.out.println("第二部分: " + Arrays.toString(oddSplit[1]));
    }
}
