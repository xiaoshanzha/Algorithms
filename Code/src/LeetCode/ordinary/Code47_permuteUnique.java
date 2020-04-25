package LeetCode.ordinary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code47_permuteUnique {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> track = new LinkedList<>();
        int[] visited = new int[nums.length];
        //将nums排序是以下剪枝的前提条件(判断下一个访问的位置是否和刚才被撤销选择的数相等)
        Arrays.sort(nums);

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
            if(visited[i] == 1 )
                continue;
            //剪枝 : 之前的该数刚刚被撤销选择，所以剪枝（前提条件 : nums数组已排序）
            if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0){
                continue;
            }
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
