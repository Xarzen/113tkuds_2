package midterm;
import java.util.*;

public class M03_TopKConvenience {
    static class Product {
        String name;
        int qty;
        int index;
        
        Product(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        PriorityQueue<Product> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);
            return Integer.compare(b.index, a.index);
        });
        
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            
            if (minHeap.size() < k) {
                minHeap.offer(new Product(name, qty, i));
            } else if (qty > minHeap.peek().qty || (qty == minHeap.peek().qty && i < minHeap.peek().index)) {
                minHeap.poll();
                minHeap.offer(new Product(name, qty, i));
            }
        }
        
        List<Product> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        
        Collections.reverse(result);
        
        for (Product p : result) {
            System.out.println(p.name + " " + p.qty);
        }
        
        sc.close();
    }
}

/*
 * Time Complexity: O(n log k)
 * 說明：維護大小為k的min heap，每次插入或刪除操作需要O(log k)時間
 * 總共處理n個商品，每個商品最多執行一次插入和一次刪除操作
 * 因此整體時間複雜度為O(n log k)，當k遠小於n時效率很高
 */
