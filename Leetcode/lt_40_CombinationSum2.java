package Leetcode;

import java.util.*;

class Solution_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); // 存儲所有組合
        List<Integer> current = new ArrayList<>(); // 當前組合
        
        // 排序數組以便去重和剪枝
        Arrays.sort(candidates);
        
        // 開始回溯搜索
        backtrack(candidates, target, 0, current, result);
        
        return result; // 返回所有不重複的組合
    }
    
    private void backtrack(int[] candidates, int target, int start, 
                          List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // 找到有效組合，添加到結果中
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // 剪枝：如果當前數字大於目標值，後面的數字也會更大
            if (candidates[i] > target) {
                break;
            }
            
            // 去重：跳過重複的數字，但保留第一個
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // 選擇當前數字
            current.add(candidates[i]);
            
            // 遞歸搜索，每個數字只能使用一次，所以下一層從i+1開始
            backtrack(candidates, target - candidates[i], i + 1, current, result);
            
            // 回溯，撤銷選擇
            current.remove(current.size() - 1);
        }
    }
}
