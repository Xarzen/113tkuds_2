import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MovingAverageStream {
    private Queue<Integer> window;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private int size;
    private double sum;
    
    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.sum = 0;
    }
    
    public double next(int val) {
        if (window.size() == size) {
            int removed = window.poll();
            sum -= removed;
            maxHeap.remove(removed);
            minHeap.remove(removed);
        }
        
        window.offer(val);
        sum += val;
        
        addToHeaps(val);
        balanceHeaps();
        
        return sum / window.size();
    }
    
    private void addToHeaps(int val) {
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
    }
    
    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }
    
    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int val : window) {
            min = Math.min(min, val);
        }
        return min;
    }
    
    public int getMax() {
        int max = Integer.MIN_VALUE;
        for (int val : window) {
            max = Math.max(max, val);
        }
        return max;
    }
    
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}
