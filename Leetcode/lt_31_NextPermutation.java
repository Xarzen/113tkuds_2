package Leetcode;

class Solution_31 {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2; // 從倒數第二個元素開始查找
        
        // 步驟1：從右往左找到第一個升序對 nums[i] < nums[i+1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // 如果當前元素大於等於下一個元素，繼續向左查找
        }
        
        if (i >= 0) { // 如果找到了升序對
            int j = n - 1; // 從最後一個元素開始
            
            // 步驟2：從右往左找到第一個大於nums[i]的元素
            while (nums[j] <= nums[i]) {
                j--; // 找到第一個比nums[i]大的元素
            }
            
            // 步驟3：交換nums[i]和nums[j]
            swap(nums, i, j);
        }
        
        // 步驟4：反轉i+1到末尾的部分，得到下一個排列
        reverse(nums, i + 1);
    }
    
    // 輔助方法：交換數組中兩個位置的元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // 輔助方法：反轉數組指定範圍的元素
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end); // 交換首尾元素
            start++; // 向中間移動
            end--;
        }
    }
}
