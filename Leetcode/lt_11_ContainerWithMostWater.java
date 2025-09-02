package Leetcode;

public class lt_11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0; // 左指標從最左邊開始
        int right = height.length - 1; // 右指標從最右邊開始
        int maxArea = 0; // 記錄最大面積
        
        // 使用雙指標法，從兩端向中間收斂
        while (left < right) {
            // 計算當前容器的面積：寬度 × 較短邊的高度
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, area); // 更新最大面積
            
            // 移動較短邊的指標，因為移動較長邊不可能得到更大面積
            if (height[left] < height[right]) {
                left++; // 左邊較短，移動左指標
            } else {
                right--; // 右邊較短或相等，移動右指標
            }
        }
        
        return maxArea; // 返回最大面積
    }
    
    public static void main(String[] args) {
        lt_11_ContainerWithMostWater solution = new lt_11_ContainerWithMostWater();
        
        // 測試案例 1: [1,8,6,2,5,4,8,3,7]
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("測試案例 1: " + solution.maxArea(height1)); // 預期輸出: 49
        
        // 測試案例 2: [1,1]
        int[] height2 = {1, 1};
        System.out.println("測試案例 2: " + solution.maxArea(height2)); // 預期輸出: 1
    }
}
