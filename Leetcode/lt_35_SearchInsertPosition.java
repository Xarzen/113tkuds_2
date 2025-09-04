package Leetcode;

class Solution_35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 設置左右邊界
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // 計算中點
            
            if (nums[mid] == target) {
                return mid; // 找到目標值，返回索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 目標值在右半部分
            } else {
                right = mid - 1; // 目標值在左半部分
            }
        }
        
        // 如果沒有找到目標值，left就是插入位置
        // 此時left指向第一個大於target的元素位置
        return left;
    }
}
