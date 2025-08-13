import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class StockMaximizer {
    static class Transaction {
        int profit;
        int buyDay;
        int sellDay;
        
        Transaction(int profit, int buyDay, int sellDay) {
            this.profit = profit;
            this.buyDay = buyDay;
            this.sellDay = sellDay;
        }
    }
    
    public static int maxProfit(int[] prices, int k) {
        if (prices.length < 2 || k == 0) {
            return 0;
        }
        
        if (k >= prices.length / 2) {
            return maxProfitUnlimited(prices);
        }
        
        PriorityQueue<Transaction> maxHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);
        
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxHeap.offer(new Transaction(prices[j] - prices[i], i, j));
                }
            }
        }
        
        List<Transaction> selected = new ArrayList<>();
        int totalProfit = 0;
        
        while (!maxHeap.isEmpty() && selected.size() < k) {
            Transaction current = maxHeap.poll();
            
            boolean conflict = false;
            for (Transaction trans : selected) {
                if (isOverlap(current, trans)) {
                    conflict = true;
                    break;
                }
            }
            
            if (!conflict) {
                selected.add(current);
                totalProfit += current.profit;
            }
        }
        
        return totalProfit;
    }
    
    private static boolean isOverlap(Transaction a, Transaction b) {
        return !(a.sellDay < b.buyDay || b.sellDay < a.buyDay);
    }
    
    private static int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
    
    public static void main(String[] args) {
        int[] test1 = {2, 4, 1};
        int[] test2 = {3, 2, 6, 5, 0, 3};
        int[] test3 = {1, 2, 3, 4, 5};
        
        System.out.println(maxProfit(test1, 2));
        System.out.println(maxProfit(test2, 2));
        System.out.println(maxProfit(test3, 2));
    }
}
