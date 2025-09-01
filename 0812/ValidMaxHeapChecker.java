public class ValidMaxHeapChecker {
    
    public static boolean isValidMaxHeap(int[] arr) {
        if (arr.length <= 1) {
            return true;
        }
        
        for (int i = 0; i <= (arr.length - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < arr.length && arr[i] < arr[left]) {
                System.out.println("Invalid at index " + left);
                return false;
            }
            
            if (right < arr.length && arr[i] < arr[right]) {
                System.out.println("Invalid at index " + right);
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        int[] test1 = {100, 90, 80, 70, 60, 75, 65};
        int[] test2 = {100, 90, 80, 95, 60, 75, 65};
        int[] test3 = {50};
        int[] test4 = {};
        
        System.out.println(isValidMaxHeap(test1));
        System.out.println(isValidMaxHeap(test2));
        System.out.println(isValidMaxHeap(test3));
        System.out.println(isValidMaxHeap(test4));
    }
}
