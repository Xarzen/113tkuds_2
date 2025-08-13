import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {
    
    public static int findKthSmallestWithMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : arr) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        
        return maxHeap.peek();
    }
    
    public static int findKthSmallestWithMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : arr) {
            minHeap.offer(num);
        }
        
        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }
        
        return minHeap.peek();
    }
    
    public static void main(String[] args) {
        int[] test1 = {7, 10, 4, 3, 20, 15};
        int[] test2 = {1};
        int[] test3 = {3, 1, 4, 1, 5, 9, 2, 6};
        
        System.out.println(findKthSmallestWithMaxHeap(test1, 3));
        System.out.println(findKthSmallestWithMinHeap(test1, 3));
        
        System.out.println(findKthSmallestWithMaxHeap(test2, 1));
        System.out.println(findKthSmallestWithMinHeap(test2, 1));
        
        System.out.println(findKthSmallestWithMaxHeap(test3, 4));
        System.out.println(findKthSmallestWithMinHeap(test3, 4));
    }
}
