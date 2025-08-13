import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueWithHeap {
    static class Task {
        String name;
        int priority;
        long timestamp;
        
        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }
    
    private PriorityQueue<Task> pq;
    private long counter = 0;
    
    public PriorityQueueWithHeap() {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return b.priority - a.priority;
            }
            return Long.compare(a.timestamp, b.timestamp);
        });
    }
    
    public void addTask(String name, int priority) {
        pq.offer(new Task(name, priority, counter++));
    }
    
    public String executeNext() {
        if (pq.isEmpty()) {
            return null;
        }
        return pq.poll().name;
    }
    
    public String peek() {
        if (pq.isEmpty()) {
            return null;
        }
        return pq.peek().name;
    }
    
    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        boolean found = false;
        
        while (!pq.isEmpty()) {
            Task task = pq.poll();
            if (task.name.equals(name) && !found) {
                task.priority = newPriority;
                found = true;
            }
            temp.add(task);
        }
        
        for (Task task : temp) {
            pq.offer(task);
        }
    }
    
    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();
        
        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);
        
        System.out.println(queue.executeNext());
        System.out.println(queue.executeNext());
        System.out.println(queue.executeNext());
    }
}
