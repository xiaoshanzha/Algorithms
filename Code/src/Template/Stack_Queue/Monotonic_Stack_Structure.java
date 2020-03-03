package Template.Stack_Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 单调栈结构
* */
public class Monotonic_Stack_Structure {
    /*
    * 问题：给定一个不含重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
    *
    * 思路：找最近且比arr[i]小的位置，栈结构从栈顶到栈底应该严格递减
    *       找最近且比arr[i]大的位置，栈结构从栈顶到栈底应该严格递增
    * 以找最近且比arr[i]小的位置为例：
    *       进栈时：若该值大于栈顶位置所代表的值，则直接放入，
    *              若小于，则弹出栈顶位置，继续进行比较，
    *       i出栈时：因为j值<i值，所以i右边最近且小的值为j
    *               左边最近且小的值为自己在栈中下面相邻的值。
    *       遍历结束清算栈中元素 ：因为是清算才弹出，所有右边没有比自己小的值，为-1
    *               栈中最后一个值没有下面相邻的值所以左边也没有比自己小的值，为-1
    * */
    public int[][] getNearLessNoRepeat(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            //先将栈中比arr[i]大的值弹出
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                int popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        //清算栈中元素
        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = -1;
        }
        return res;
    }

    /*
    * 如果数组有重复值:改变栈结构，直接将值相同的索引压在一起，
    * */
    public int[][] getNearLess(int[] arr){
        Stack<List<Integer>> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> list = stack.pop();
                //此时左边最近且小的索引应为 栈顶list中最后进的值索引
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                        .get(stack.peek().size() - 1);
                for (Integer popi : list) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            // 进栈时应该区分是否有相同的值
            if(!stack.isEmpty() && arr[i] == stack.peek().get(0)){
                stack.peek().add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //清算栈元素
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                    .get(stack.peek().size() - 1);
            for (Integer popi : list) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }
}
