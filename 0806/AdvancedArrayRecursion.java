import java.util.Arrays;

public class AdvancedArrayRecursion {
    
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        
        swap(array, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        return mergeHelper(arr1, arr2, 0, 0);
    }
    
    private static int[] mergeHelper(int[] arr1, int[] arr2, int i1, int i2) {
        if (i1 >= arr1.length && i2 >= arr2.length) {
            return new int[0];
        }
        
        if (i1 >= arr1.length) {
            int[] remaining = new int[arr2.length - i2];
            System.arraycopy(arr2, i2, remaining, 0, arr2.length - i2);
            return remaining;
        }
        
        if (i2 >= arr2.length) {
            int[] remaining = new int[arr1.length - i1];
            System.arraycopy(arr1, i1, remaining, 0, arr1.length - i1);
            return remaining;
        }
        
        int[] result;
        if (arr1[i1] <= arr2[i2]) {
            result = new int[1 + mergeHelper(arr1, arr2, i1 + 1, i2).length];
            result[0] = arr1[i1];
            int[] rest = mergeHelper(arr1, arr2, i1 + 1, i2);
            System.arraycopy(rest, 0, result, 1, rest.length);
        } else {
            result = new int[1 + mergeHelper(arr1, arr2, i1, i2 + 1).length];
            result[0] = arr2[i2];
            int[] rest = mergeHelper(arr1, arr2, i1, i2 + 1);
            System.arraycopy(rest, 0, result, 1, rest.length);
        }
        
        return result;
    }
    
    public static int findKthSmallest(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException("k 超出範圍");
        }
        
        int[] sortedArray = array.clone();
        quickSort(sortedArray, 0, sortedArray.length - 1);
        return sortedArray[k - 1];
    }
    
    public static int quickSelect(int[] array, int low, int high, int k) {
        if (low == high) {
            return array[low];
        }
        
        int pivotIndex = partition(array, low, high);
        
        if (k == pivotIndex) {
            return array[k];
        } else if (k < pivotIndex) {
            return quickSelect(array, low, pivotIndex - 1, k);
        } else {
            return quickSelect(array, pivotIndex + 1, high, k);
        }
    }
    
    public static boolean hasSubsetSum(int[] array, int targetSum) {
        return hasSubsetSumHelper(array, 0, targetSum, 0);
    }
    
    private static boolean hasSubsetSumHelper(int[] array, int index, int targetSum, int currentSum) {
        if (currentSum == targetSum) {
            return true;
        }
        
        if (index >= array.length || currentSum > targetSum) {
            return false;
        }
        
        return hasSubsetSumHelper(array, index + 1, targetSum, currentSum + array[index]) ||
               hasSubsetSumHelper(array, index + 1, targetSum, currentSum);
    }
    
    public static int arraySum(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }
        
        return array[index] + arraySum(array, index + 1);
    }
    
    public static int arrayMax(int[] array, int index) {
        if (index >= array.length) {
            return Integer.MIN_VALUE;
        }
        
        if (index == array.length - 1) {
            return array[index];
        }
        
        return Math.max(array[index], arrayMax(array, index + 1));
    }
    
    public static boolean isSorted(int[] array, int index) {
        if (index >= array.length - 1) {
            return true;
        }
        
        return array[index] <= array[index + 1] && isSorted(array, index + 1);
    }
    
    public static void printSubsetsWithSum(int[] array, int targetSum) {
        boolean[] used = new boolean[array.length];
        System.out.println("目標總和 " + targetSum + " 的所有子集合:");
        findSubsetsWithSum(array, 0, targetSum, 0, used);
    }
    
    private static void findSubsetsWithSum(int[] array, int index, int targetSum, int currentSum, boolean[] used) {
        if (currentSum == targetSum) {
            System.out.print("{ ");
            for (int i = 0; i < array.length; i++) {
                if (used[i]) {
                    System.out.print(array[i] + " ");
                }
            }
            System.out.println("}");
            return;
        }
        
        if (index >= array.length || currentSum > targetSum) {
            return;
        }
        
        used[index] = true;
        findSubsetsWithSum(array, index + 1, targetSum, currentSum + array[index], used);
        
        used[index] = false;
        findSubsetsWithSum(array, index + 1, targetSum, currentSum, used);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 進階陣列遞迴操作 ===\n");
        
        System.out.println("1. 快速排序測試:");
        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};
        System.out.println("排序前: " + Arrays.toString(testArray));
        quickSort(testArray, 0, testArray.length - 1);
        System.out.println("排序後: " + Arrays.toString(testArray));
        
        System.out.println("\n2. 合併已排序陣列:");
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10, 12};
        System.out.println("陣列1: " + Arrays.toString(arr1));
        System.out.println("陣列2: " + Arrays.toString(arr2));
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("合併結果: " + Arrays.toString(merged));
        
        System.out.println("\n3. 尋找第 k 小元素:");
        int[] kthArray = {7, 10, 4, 3, 20, 15};
        System.out.println("陣列: " + Arrays.toString(kthArray));
        for (int k = 1; k <= 3; k++) {
            System.out.printf("第 %d 小的元素: %d\n", k, findKthSmallest(kthArray, k));
        }
        
        System.out.println("\n4. 子集合總和檢查:");
        int[] subsetArray = {3, 34, 4, 12, 5, 2};
        int[] targets = {9, 11, 30, 50};
        System.out.println("陣列: " + Arrays.toString(subsetArray));
        
        for (int target : targets) {
            boolean hasSum = hasSubsetSum(subsetArray, target);
            System.out.printf("存在子集合總和為 %d: %b\n", target, hasSum);
        }
        
        System.out.println("\n5. 其他遞迴陣列操作:");
        int[] miscArray = {5, 2, 8, 1, 9};
        System.out.println("陣列: " + Arrays.toString(miscArray));
        System.out.println("陣列總和: " + arraySum(miscArray, 0));
        System.out.println("陣列最大值: " + arrayMax(miscArray, 0));
        System.out.println("陣列是否已排序: " + isSorted(miscArray, 0));
        
        System.out.println("\n6. 尋找所有子集合 (總和為9):");
        int[] subsetDemo = {3, 4, 5, 2, 1};
        System.out.println("陣列: " + Arrays.toString(subsetDemo));
        printSubsetsWithSum(subsetDemo, 9);
    }
}
