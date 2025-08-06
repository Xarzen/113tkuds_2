public class RecursionVsIteration {
    
    public static long binomialCoefficientRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        
        if (k < 0 || k > n) {
            return 0;
        }
        
        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }
    
    public static long binomialCoefficientIterative(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        
        if (k == 0 || k == n) {
            return 1;
        }
        
        k = Math.min(k, n - k);
        
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        
        return result;
    }
    
    public static long arrayProductRecursive(int[] array, int index) {
        if (index >= array.length) {
            return 1;
        }
        
        return array[index] * arrayProductRecursive(array, index + 1);
    }
    
    public static long arrayProductIterative(int[] array) {
        long product = 1;
        for (int num : array) {
            product *= num;
        }
        return product;
    }
    
    public static int countVowelsRecursive(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        
        char ch = Character.toLowerCase(str.charAt(index));
        int count = 0;
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            count = 1;
        }
        
        return count + countVowelsRecursive(str, index + 1);
    }
    
    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char ch : str.toLowerCase().toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
    
    public static boolean isValidParenthesesRecursive(String str) {
        return isValidParenthesesHelper(str, 0, 0);
    }
    
    private static boolean isValidParenthesesHelper(String str, int index, int balance) {
        if (balance < 0) {
            return false;
        }
        
        if (index >= str.length()) {
            return balance == 0;
        }
        
        char ch = str.charAt(index);
        if (ch == '(') {
            return isValidParenthesesHelper(str, index + 1, balance + 1);
        } else if (ch == ')') {
            return isValidParenthesesHelper(str, index + 1, balance - 1);
        } else {
            return isValidParenthesesHelper(str, index + 1, balance);
        }
    }
    
    public static boolean isValidParenthesesIterative(String str) {
        int balance = 0;
        
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        
        return balance == 0;
    }
    
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        
        return b;
    }
    
    public static long factorialRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        
        return n * factorialRecursive(n - 1);
    }
    
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void performanceTest(String testName, Runnable recursiveMethod, Runnable iterativeMethod) {
        System.out.println("\n=== " + testName + " 效能測試 ===");
        
        long startTime = System.currentTimeMillis();
        recursiveMethod.run();
        long recursiveTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        iterativeMethod.run();
        long iterativeTime = System.currentTimeMillis() - startTime;
        
        System.out.println("遞迴版本時間: " + recursiveTime + " 毫秒");
        System.out.println("迭代版本時間: " + iterativeTime + " 毫秒");
        
        if (recursiveTime < iterativeTime) {
            System.out.println("遞迴版本較快");
        } else if (iterativeTime < recursiveTime) {
            System.out.println("迭代版本較快");
        } else {
            System.out.println("兩者速度相同");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 遞迴與迭代比較 ===\n");
        
        System.out.println("1. 二項式係數 C(10,5):");
        long recursiveResult = binomialCoefficientRecursive(10, 5);
        long iterativeResult = binomialCoefficientIterative(10, 5);
        System.out.println("遞迴結果: " + recursiveResult);
        System.out.println("迭代結果: " + iterativeResult);
        System.out.println("結果相同: " + (recursiveResult == iterativeResult));
        
        System.out.println("\n2. 陣列元素乘積:");
        int[] testArray = {1, 2, 3, 4, 5};
        long productRec = arrayProductRecursive(testArray, 0);
        long productIter = arrayProductIterative(testArray);
        System.out.println("陣列: [1, 2, 3, 4, 5]");
        System.out.println("遞迴結果: " + productRec);
        System.out.println("迭代結果: " + productIter);
        System.out.println("結果相同: " + (productRec == productIter));
        
        System.out.println("\n3. 計算元音字母數量:");
        String testString = "programming";
        int vowelsRec = countVowelsRecursive(testString, 0);
        int vowelsIter = countVowelsIterative(testString);
        System.out.println("字串: " + testString);
        System.out.println("遞迴結果: " + vowelsRec);
        System.out.println("迭代結果: " + vowelsIter);
        System.out.println("結果相同: " + (vowelsRec == vowelsIter));
        
        System.out.println("\n4. 括號配對檢查:");
        String[] parenthesesTests = {"(())()", "(()", "))(", "()()()"};
        for (String test : parenthesesTests) {
            boolean recResult = isValidParenthesesRecursive(test);
            boolean iterResult = isValidParenthesesIterative(test);
            System.out.printf("'%s': 遞迴=%b, 迭代=%b, 相同=%b\n", 
                            test, recResult, iterResult, recResult == iterResult);
        }
        
        System.out.println("\n5. 費波那契數列 F(30):");
        System.out.println("注意: 遞迴版本會很慢，請耐心等待...");
        
        long startTime = System.currentTimeMillis();
        int fibRec = fibonacciRecursive(30);
        long recTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int fibIter = fibonacciIterative(30);
        long iterTime = System.currentTimeMillis() - startTime;
        
        System.out.println("遞迴結果: " + fibRec + " (時間: " + recTime + "ms)");
        System.out.println("迭代結果: " + fibIter + " (時間: " + iterTime + "ms)");
        System.out.println("結果相同: " + (fibRec == fibIter));
        
        System.out.println("\n6. 階乘計算 10!:");
        long factRec = factorialRecursive(10);
        long factIter = factorialIterative(10);
        System.out.println("遞迴結果: " + factRec);
        System.out.println("迭代結果: " + factIter);
        System.out.println("結果相同: " + (factRec == factIter));
        
        System.out.println("\n=== 總結 ===");
        System.out.println("遞迴優點:");
        System.out.println("- 程式碼簡潔易懂");
        System.out.println("- 適合解決分治問題");
        System.out.println("- 自然地表達數學定義");
        
        System.out.println("\n迭代優點:");
        System.out.println("- 通常效能更好");
        System.out.println("- 不會有堆疊溢位問題");
        System.out.println("- 記憶體使用較少");
        
        System.out.println("\n建議:");
        System.out.println("- 簡單問題使用迭代");
        System.out.println("- 複雜分治問題使用遞迴");
        System.out.println("- 注意遞迴深度限制");
    }
}
