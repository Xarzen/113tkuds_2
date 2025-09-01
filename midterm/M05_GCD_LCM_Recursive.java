package midterm;
import java.util.Scanner;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        
        long gcd = gcd(a, b);
        long lcm = a / gcd * b;
        
        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
        
        sc.close();
    }
    
    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

/*
 * Time Complexity: O(log(min(a,b)))
 * 說明：歐幾里得算法每次遞迴將較大數縮減為原數對較小數的餘數
 * 根據拉梅定理，遞迴深度最多為較小數的對數級別，因此時間複雜度為O(log(min(a,b)))
 */
