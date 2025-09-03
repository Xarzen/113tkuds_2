package Leetcode;

class Solution_27 {
    public int removeElement(int[] nums, int val) {
        int slow = 0; // 慢指針，指向下一個非val元素的位置
        
        for (int fast = 0; fast < nums.length; fast++) { // 快指針遍歷數組
            if (nums[fast] != val) { // 如果當前元素不等於目標值
                nums[slow] = nums[fast]; // 將元素移到慢指針位置
                slow++; // 慢指針前移
            }
            // 如果等於val，快指針繼續移動，慢指針不動（跳過該元素）
        }
        
        return slow; // 返回移除目標元素後的數組長度
    }
}
