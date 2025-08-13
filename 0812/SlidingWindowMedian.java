import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        double[] result = new double[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            addNumber(maxHeap, minHeap, nums[i]);
            
            if (i >= k) {
                removeNumber(maxHeap, minHeap, nums[i - k]);
            }
            
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(maxHeap, minHeap, k);
            }
        }
        
        return result;
    }
    
    private static void addNumber(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        balanceHeaps(maxHeap, minHeap);
    }
    
    private static void removeNumber(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num) {
        if (maxHeap.contains(num)) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        
        balanceHeaps(maxHeap, minHeap);
    }
    
    private static void balanceHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    private static double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int k) {
        if (k % 2 == 1) {
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
    
    public static void main(String[] args) {
        int[] test1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] test2 = {1, 2, 3, 4};
        
        System.out.println(Arrays.toString(medianSlidingWindow(test1, 3)));
        System.out.println(Arrays.toString(medianSlidingWindow(test2, 2)));
    }
}
