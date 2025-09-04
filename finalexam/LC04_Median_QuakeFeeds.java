package finalexam;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        double[] nums1 = new double[n];
        double[] nums2 = new double[m];
        
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextDouble();
        }
        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextDouble();
        }
        
        System.out.println(findMedianSortedArrays(nums1, nums2));
        sc.close();
    }
    
    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int x = nums1.length;
        int y = nums2.length;
        
        int low = 0;
        int high = x;
        
        while (low <= high) {
            int cutX = (low + high) / 2;
            int cutY = (x + y + 1) / 2 - cutX;
            
            double maxLeftX = (cutX == 0) ? Double.NEGATIVE_INFINITY : nums1[cutX - 1];
            double maxLeftY = (cutY == 0) ? Double.NEGATIVE_INFINITY : nums2[cutY - 1];
            
            double minRightX = (cutX == x) ? Double.POSITIVE_INFINITY : nums1[cutX];
            double minRightY = (cutY == y) ? Double.POSITIVE_INFINITY : nums2[cutY];
            
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = cutX - 1;
            } else {
                low = cutX + 1;
            }
        }
        return 1.0;
    }
}
