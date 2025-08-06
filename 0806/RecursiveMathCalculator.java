public class RecursiveMathCalculator {
    
    public static long combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        
        if (k < 0 || k > n) {
            return 0;
        }
        
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }
    
    public static long catalanNumber(int n) {
        if (n <= 1) {
            return 1;
        }
        
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalanNumber(i) * catalanNumber(n - 1 - i);
        }
        
        return result;
    }
    
    public static long hanoiMoves(int n) {
        if (n == 1) {
            return 1;
        }
        
        return 2 * hanoiMoves(n - 1) + 1;
    }
    
    public static boolean isPalindrome(int number) {
        String str = String.valueOf(Math.abs(number));
        return isPalindromeHelper(str, 0, str.length() - 1);
    }
    
    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        return isPalindromeHelper(str, left + 1, right - 1);
    }
    
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        
        return n * factorial(n - 1);
    }
    
    public static long power(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        
        if (exp == 1) {
            return base;
        }
        
        if (exp % 2 == 0) {
            long half = power(base, exp / 2);
            return half * half;
        } else {
            return base * power(base, exp - 1);
        }
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
    
    public static void printHanoiSteps(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("移動盤子 1 從 " + from + " 到 " + to);
            return;
        }
        
        printHanoiSteps(n - 1, from, aux, to);
        System.out.println("移動盤子 " + n + " 從 " + from + " 到 " + to);
        printHanoiSteps(n - 1, aux, to, from);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 遞迴數學計算器 ===\n");
        
        System.out.println("1. 組合數計算:");
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("C(10,3) = " + combination(10, 3));
        System.out.println("C(8,0) = " + combination(8, 0));
        System.out.println("C(6,6) = " + combination(6, 6));
        
        System.out.println("\n2. 卡塔蘭數:");
        for (int i = 0; i <= 8; i++) {
            System.out.printf("C(%d) = %d\n", i, catalanNumber(i));
        }
        
        System.out.println("\n3. 漢諾塔移動步數:");
        for (int i = 1; i <= 8; i++) {
            System.out.printf("漢諾塔 %d 層需要 %d 步\n", i, hanoiMoves(i));
        }
        
        System.out.println("\n4. 回文數檢查:");
        int[] testNumbers = {12321, 12345, 1, 11, 121, 1221, -12321};
        for (int num : testNumbers) {
            System.out.printf("%d 是回文數: %b\n", num, isPalindrome(num));
        }
        
        System.out.println("\n5. 其他數學函數:");
        System.out.println("5! = " + factorial(5));
        System.out.println("2^10 = " + power(2, 10));
        System.out.println("gcd(48, 18) = " + gcd(48, 18));
        
        System.out.println("\n6. 漢諾塔移動步驟 (3層):");
        printHanoiSteps(3, 'A', 'C', 'B');
        
        System.out.println("\n=== 效能測試 ===");
        long startTime = System.currentTimeMillis();
        System.out.println("C(20,10) = " + combination(20, 10));
        long endTime = System.currentTimeMillis();
        System.out.println("計算時間: " + (endTime - startTime) + " 毫秒");
    }
}
