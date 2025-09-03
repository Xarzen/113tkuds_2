package Leetcode;

class Solution_26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) { // 處理空數組情況
            return 0;
        }
        
        int slow = 0; // 慢指針，指向去重後數組的末尾
        
        for (int fast = 1; fast < nums.length; fast++) { // 快指針遍歷數組
            if (nums[fast] != nums[slow]) { // 如果發現不同元素
                slow++; // 慢指針前移
                nums[slow] = nums[fast]; // 將不同元素移到慢指針位置
            }
        }
        
        return slow + 1; // 返回去重後的數組長度
    }
}
