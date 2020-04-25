package LeetCode.ordinary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code46_permute {
    /*
    * 回溯：决策树的遍历过程，搞清以下三名词
    *       路径：已经做出的选择
    *       选择列表：当前可以做的选择
    *       结束条件：到达决策树底层，无法再做选择的条件
    * 回溯框架核心： for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
    * */
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> track = new LinkedList<>();
        int[] visited = new int[nums.length];
        // nums 为选择列表，track为路径
        backtrack(nums,track,visited);
        for (List list : res) {
            System.out.println(list.toString());
        }
        return res;
    }

    private void backtrack(int[] nums, List<Integer> track,int[] visited) {
        //触发结束条件
        if(nums.length == track.size()){
            //注意此处不能写 res.add(track)；
            //因为track这个变量所指向的对象在递归的过程中只有一份，深搜完回到根节点，所以这个变量回到根节点以后都为空
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] == 1)
                continue;
            visited[i] = 1;
            track.add(nums[i]);
            backtrack(nums,track,visited);
            visited[i] = 0;
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        Code46_permute c = new Code46_permute();
        c.permute(new int[]{1,1,3});
    }
}
