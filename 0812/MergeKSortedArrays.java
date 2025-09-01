import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;
        
        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }
    
    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }
        
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);
            
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][current.elementIndex + 1];
                minHeap.offer(new Element(nextValue, current.arrayIndex, current.elementIndex + 1));
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static void main(String[] args) {
        int[][] test1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] test2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] test3 = {{1}, {0}};
        
        System.out.println(Arrays.toString(mergeKSortedArrays(test1)));
        System.out.println(Arrays.toString(mergeKSortedArrays(test2)));
        System.out.println(Arrays.toString(mergeKSortedArrays(test3)));
    }
}
