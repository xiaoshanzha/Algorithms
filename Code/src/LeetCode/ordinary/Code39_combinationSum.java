package LeetCode.ordinary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code39_combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        int cur = 0;
        /*
        * 排序主要的作用是剪枝，让程序在深搜的过程中尽量排除掉不能搜索到结果的分支，以节约时间。
        * 在回溯搜索这种时间复杂度很大的算法中，先排序再剪枝有些时候是有必要的。
        * */
        Arrays.sort(candidates);
        backtrack(res,path,candidates,target,cur,0);

        for (List list : res) {
            System.out.println(list.toString());
        }
        return res;
    }

    /*
    * begin: 本轮搜索的起点下标 , 用来去重
    * */
    private void backtrack(List<List<Integer>> res, List<Integer> path, int[] candidates, int target,int cur,int begin) {
        if(cur == target){
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if(cur > target){
                return;
            }
            path.add(candidates[i]);
            backtrack(res,path,candidates,target,cur + candidates[i],i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Code39_combinationSum c = new Code39_combinationSum();
        c.combinationSum(new int[]{2,3,6,7},7);
    }
}
