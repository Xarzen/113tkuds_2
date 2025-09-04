package finalexam;
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        int newLength = removeDuplicates(nums);
        
        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i]);
            if (i < newLength - 1) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
    
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        
        return write;
    }
}
