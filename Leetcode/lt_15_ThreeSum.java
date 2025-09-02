package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lt_15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // 存儲結果的列表
        
        // 邊界條件：陣列長度小於3無法組成三元組
        if (nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums); // 先排序，便於使用雙指標和跳過重複值
        
        // 遍歷陣列，固定第一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的第一個數字，避免重複結果
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 使用雙指標尋找剩餘兩個數字
            int left = i + 1; // 左指標從當前位置的下一個開始
            int right = nums.length - 1; // 右指標從陣列末尾開始
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; // 計算三數之和
                
                if (sum == 0) {
                    // 找到一組解，加入結果列表
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳過重複的左指標值
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳過重複的右指標值
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移動雙指標繼續尋找
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 和太小，需要增大，移動左指標
                    left++;
                } else {
                    // 和太大，需要減小，移動右指標
                    right--;
                }
            }
        }
        
        return result; // 返回所有不重複的三元組
    }
    
    public static void main(String[] args) {
        lt_15_ThreeSum solution = new lt_15_ThreeSum();
        
        // 測試案例 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("測試案例 1: " + solution.threeSum(nums1)); 
        // 預期輸出: [[-1, -1, 2], [-1, 0, 1]]
        
        // 測試案例 2
        int[] nums2 = {0, 1, 1};
        System.out.println("測試案例 2: " + solution.threeSum(nums2)); 
        // 預期輸出: []
        
        // 測試案例 3
        int[] nums3 = {0, 0, 0};
        System.out.println("測試案例 3: " + solution.threeSum(nums3)); 
        // 預期輸出: [[0, 0, 0]]
        
        // 測試案例 4
        int[] nums4 = {-2, 0, 1, 1, 2};
        System.out.println("測試案例 4: " + solution.threeSum(nums4)); 
        // 預期輸出: [[-2, 0, 2], [-2, 1, 1]]
    }
}
