package Leetcode;

import java.util.Arrays;

class Solution_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序數組，方便使用雙指針
        int closestSum = nums[0] + nums[1] + nums[2]; // 初始化最接近的和
        
        for (int i = 0; i < nums.length - 2; i++) { // 固定第一個數字
            int left = i + 1; // 左指針
            int right = nums.length - 1; // 右指針
            
            while (left < right) { // 雙指針遍歷
                int currentSum = nums[i] + nums[left] + nums[right]; // 計算當前三數之和
                
                // 如果當前和更接近目標值，更新最接近的和
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                if (currentSum < target) { // 如果和小於目標，移動左指針
                    left++;
                } else { // 如果和大於等於目標，移動右指針
                    right--;
                }
            }
        }
        
        return closestSum; // 返回最接近的三數之和
    }
}
