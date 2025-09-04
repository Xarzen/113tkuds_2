package finalexam;
import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        
        List<List<Integer>> result = combinationSum(candidates, target);
        for (List<Integer> combination : result) {
            for (int i = 0; i < combination.size(); i++) {
                System.out.print(combination.get(i));
                if (i < combination.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
        sc.close();
    }
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] candidates, int target, int start, 
                                List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
