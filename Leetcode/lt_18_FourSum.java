package Leetcode;

import java.util.*;

class Solution_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>(); // 存儲結果的列表
        if (nums.length < 4) { // 如果數組長度小於4，直接返回空列表
            return result;
        }
        
        Arrays.sort(nums); // 排序數組
        
        for (int i = 0; i < nums.length - 3; i++) { // 固定第一個數字
            if (i > 0 && nums[i] == nums[i-1]) continue; // 跳過重複的第一個數字
            
            for (int j = i + 1; j < nums.length - 2; j++) { // 固定第二個數字
                if (j > i + 1 && nums[j] == nums[j-1]) continue; // 跳過重複的第二個數字
                
                int left = j + 1; // 左指針
                int right = nums.length - 1; // 右指針
                
                while (left < right) { // 雙指針查找
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right]; // 計算四數之和
                    
                    if (sum == target) { // 找到目標和
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right])); // 添加到結果
                        
                        while (left < right && nums[left] == nums[left+1]) left++; // 跳過重複的左指針元素
                        while (left < right && nums[right] == nums[right-1]) right--; // 跳過重複的右指針元素
                        
                        left++; // 移動左指針
                        right--; // 移動右指針
                    } else if (sum < target) { // 和小於目標，移動左指針
                        left++;
                    } else { // 和大於目標，移動右指針
                        right--;
                    }
                }
            }
        }
        
        return result; // 返回所有四元組
    }
}
