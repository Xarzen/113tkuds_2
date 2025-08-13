import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomScheduler {
    static class Meeting {
        int start;
        int end;
        
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        
        return minHeap.size();
    }
    
    public static int maxMeetingTime(int[][] intervals, int roomCount) {
        if (intervals.length == 0) return 0;
        
        List<Meeting> meetings = new ArrayList<>();
        for (int[] interval : intervals) {
            meetings.add(new Meeting(interval[0], interval[1]));
        }
        
        meetings.sort((a, b) -> a.end - b.end);
        
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        for (int i = 0; i < roomCount; i++) {
            roomEndTimes.offer(0);
        }
        
        int totalTime = 0;
        for (Meeting meeting : meetings) {
            int earliestRoom = roomEndTimes.poll();
            if (earliestRoom <= meeting.start) {
                totalTime += meeting.end - meeting.start;
                roomEndTimes.offer(meeting.end);
            } else {
                roomEndTimes.offer(earliestRoom);
            }
        }
        
        return totalTime;
    }
    
    public static void main(String[] args) {
        int[][] test1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] test2 = {{9, 10}, {4, 9}, {4, 17}};
        int[][] test3 = {{1, 5}, {8, 9}, {8, 9}};
        int[][] test4 = {{1, 4}, {2, 3}, {4, 6}};
        
        System.out.println(minMeetingRooms(test1));
        System.out.println(minMeetingRooms(test2));
        System.out.println(minMeetingRooms(test3));
        System.out.println(maxMeetingTime(test4, 1));
    }
}
