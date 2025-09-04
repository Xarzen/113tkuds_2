package Leetcode;

class Solution_34 {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1}; // 初始化結果數組
        
        if (nums.length == 0) { // 處理空數組情況
            return result;
        }
        
        // 查找目標值的第一個位置
        result[0] = findFirst(nums, target);
        if (result[0] == -1) { // 如果未找到目標值
            return result;
        }
        
        // 查找目標值的最後一個位置
        result[1] = findLast(nums, target);
        
        return result; // 返回範圍
    }
    
    // 查找目標值第一次出現的位置
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1; // 記錄結果
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid; // 記錄當前位置
                right = mid - 1; // 繼續在左半部分查找更小的索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 在右半部分查找
            } else {
                right = mid - 1; // 在左半部分查找
            }
        }
        
        return result;
    }
    
    // 查找目標值最後一次出現的位置
    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1; // 記錄結果
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid; // 記錄當前位置
                left = mid + 1; // 繼續在右半部分查找更大的索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 在右半部分查找
            } else {
                right = mid - 1; // 在左半部分查找
            }
        }
        
        return result;
    }
}
