package midterm;

import java.util.Scanner;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        buildHeap(arr, type.equals("max"));
        
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();
        
        sc.close();
    }
    
    private static void buildHeap(int[] arr, boolean isMaxHeap) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, isMaxHeap);
        }
    }
    
    private static void heapifyDown(int[] arr, int n, int i, boolean isMaxHeap) {
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && compare(arr[left], arr[target], isMaxHeap)) {
            target = left;
        }
        
        if (right < n && compare(arr[right], arr[target], isMaxHeap)) {
            target = right;
        }
        
        if (target != i) {
            swap(arr, i, target);
            heapifyDown(arr, n, target, isMaxHeap);
        }
    }
    
    private static boolean compare(int a, int b, boolean isMaxHeap) {
        return isMaxHeap ? a > b : a < b;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：從最後一個非葉節點開始向上進行heapifyDown操作，每個節點最多下沉log h層（h為該節點的高度）
 * 由於大部分節點在較低層，整體複雜度為O(n)而非O(n log n)
 */
