import java.util.ArrayList;

public class BasicMinHeapPractice {
    private ArrayList<Integer> heap;
    
    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }
    
    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }
    
    public int extractMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        
        int min = heap.get(0);
        int last = heap.get(heap.size() - 1);
        heap.set(0, last);
        heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heapifyDown(0);
        }
        
        return min;
    }
    
    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.get(0);
    }
    
    public int size() {
        return heap.size();
    }
    
    public boolean isEmpty() {
        return heap.size() == 0;
    }
    
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parent)) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }
    
    private void heapifyDown(int index) {
        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            
            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            
            if (smallest == index) {
                break;
            }
            
            swap(index, smallest);
            index = smallest;
        }
    }
    
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    public static void main(String[] args) {
        BasicMinHeapPractice heap = new BasicMinHeapPractice();
        
        int[] values = {15, 10, 20, 8, 25, 5};
        for (int val : values) {
            heap.insert(val);
        }
        
        while (!heap.isEmpty()) {
            System.out.println(heap.extractMin());
        }
    }
}
