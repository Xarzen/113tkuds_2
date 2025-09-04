package Leetcode;

class Solution_33 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 設置左右邊界
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // 計算中點，避免溢出
            
            if (nums[mid] == target) { // 找到目標值
                return mid;
            }
            
            // 判斷哪一側是有序的
            if (nums[left] <= nums[mid]) {
                // 左半部分有序
                if (nums[left] <= target && target < nums[mid]) {
                    // 目標值在左半部分
                    right = mid - 1;
                } else {
                    // 目標值在右半部分
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                if (nums[mid] < target && target <= nums[right]) {
                    // 目標值在右半部分
                    left = mid + 1;
                } else {
                    // 目標值在左半部分
                    right = mid - 1;
                }
            }
        }
        
        return -1; // 未找到目標值
    }
}
