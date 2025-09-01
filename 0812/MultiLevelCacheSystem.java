import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MultiLevelCacheSystem {
    static class CacheEntry {
        String key;
        String value;
        int accessCount;
        long timestamp;
        
        CacheEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.accessCount = 1;
            this.timestamp = System.nanoTime();
        }
    }
    
    static class CacheLevel {
        private Map<String, CacheEntry> data;
        private PriorityQueue<CacheEntry> priorityQueue;
        private int capacity;
        private int accessCost;
        
        CacheLevel(int capacity, int accessCost) {
            this.capacity = capacity;
            this.accessCost = accessCost;
            this.data = new HashMap<>();
            this.priorityQueue = new PriorityQueue<>((a, b) -> {
                if (a.accessCount != b.accessCount) {
                    return a.accessCount - b.accessCount;
                }
                return Long.compare(a.timestamp, b.timestamp);
            });
        }
        
        boolean containsKey(String key) {
            return data.containsKey(key);
        }
        
        String get(String key) {
            CacheEntry entry = data.get(key);
            if (entry != null) {
                entry.accessCount++;
                entry.timestamp = System.nanoTime();
                return entry.value;
            }
            return null;
        }
        
        void put(String key, String value) {
            if (data.size() >= capacity && !data.containsKey(key)) {
                evict();
            }
            
            CacheEntry entry = new CacheEntry(key, value);
            data.put(key, entry);
            priorityQueue.offer(entry);
        }
        
        CacheEntry evict() {
            if (priorityQueue.isEmpty()) {
                return null;
            }
            
            CacheEntry toEvict = null;
            while (!priorityQueue.isEmpty()) {
                CacheEntry candidate = priorityQueue.poll();
                if (data.containsKey(candidate.key)) {
                    toEvict = candidate;
                    break;
                }
            }
            
            if (toEvict != null) {
                data.remove(toEvict.key);
            }
            
            return toEvict;
        }
        
        boolean isFull() {
            return data.size() >= capacity;
        }
        
        int size() {
            return data.size();
        }
    }
    
    private CacheLevel l1, l2, l3;
    
    public MultiLevelCacheSystem() {
        l1 = new CacheLevel(2, 1);
        l2 = new CacheLevel(5, 3);
        l3 = new CacheLevel(10, 10);
    }
    
    public String get(String key) {
        if (l1.containsKey(key)) {
            return l1.get(key);
        }
        
        if (l2.containsKey(key)) {
            String value = l2.get(key);
            promoteToL1(key, value);
            return value;
        }
        
        if (l3.containsKey(key)) {
            String value = l3.get(key);
            promoteToL2(key, value);
            return value;
        }
        
        return null;
    }
    
    public void put(String key, String value) {
        if (l1.containsKey(key) || l2.containsKey(key) || l3.containsKey(key)) {
            return;
        }
        
        if (!l1.isFull()) {
            l1.put(key, value);
        } else if (!l2.isFull()) {
            l2.put(key, value);
        } else {
            l3.put(key, value);
        }
    }
    
    private void promoteToL1(String key, String value) {
        if (l1.isFull()) {
            CacheEntry evicted = l1.evict();
            if (evicted != null) {
                l2.put(evicted.key, evicted.value);
            }
        }
        l1.put(key, value);
    }
    
    private void promoteToL2(String key, String value) {
        if (l2.isFull()) {
            CacheEntry evicted = l2.evict();
            if (evicted != null) {
                l3.put(evicted.key, evicted.value);
            }
        }
        l2.put(key, value);
    }
    
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        
        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("3", "C");
        
        System.out.println(cache.get("1"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        
        cache.put("4", "D");
        cache.put("5", "E");
        cache.put("6", "F");
        
        System.out.println("L1 size: " + cache.l1.size());
        System.out.println("L2 size: " + cache.l2.size());
        System.out.println("L3 size: " + cache.l3.size());
    }
}
