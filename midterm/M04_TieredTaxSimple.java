package midterm;
import java.util.Scanner;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        long totalTax = 0;
        
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calculateTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }
        
        long average = totalTax / n;
        System.out.println("Average: " + average);
        
        sc.close();
    }
    
    private static long calculateTax(long income) {
        long tax = 0;
        
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        
        if (income > 0) {
            tax += income * 5 / 100;
        }
        
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：對每個收入計算稅額需要常數時間O(1)（最多檢查4個級距），總共處理n筆收入
 * 因此整體時間複雜度為O(n)
 */
