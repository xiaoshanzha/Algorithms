package LeetCode.ordinary;

import java.util.Stack;

public class Code84_MaxRecFromBottom {
    /*
    * 利用单调栈 解决柱状图中最大矩形
    *
    * 每一根柱子最大能扩多大，实质就是分别找到该柱子左边和右边离它最近且比他小的柱子位置
    * 每次弹出时，计算矩形高度为该索引的柱子高度 最大面积
    *
    * 栈顶到栈底所表示的值 依次递减，压栈时只有比栈顶值大的才入栈，所以无重复
    * */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int j = stack.pop();
                //l 表示 j柱子左边最近且小的柱子位置  ,此时i表示右边且近
                int l = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - l -1) * heights[j];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - l -1) * heights[j];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int arr1[] = generateArray(10,10);
        int arr2[] = copyArray(arr1);
        int testTime = 50000;
        boolean flag = true;
        for (int i = 0; i < testTime; i++) {
            if(comparator(arr2) != largestRectangleArea(arr1)){
                System.out.println("Fuck!");
                printArray(arr1);
                System.out.println(largestRectangleArea(arr1));
                System.out.println(comparator(arr2));
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println("Nice!");
        }
    }
    private static void printArray(int[] arr) {
        for(int i = 0; i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int comparator(int[] heights){
        int[][] res = new int[heights.length][2];
        for (int i = 0; i < heights.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i-1;
            while(cur >= 0){
                if(heights[cur] < heights[i]){
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while(cur < heights.length){
                if(heights[cur] < heights[i]){
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        int maxArea = 0;
        for (int i = 0; i < res.length; i++) {
            maxArea = Math.max((res[i][1] - res[i][0] - 1)*heights[i],maxArea);
        }
        return maxArea;
    }

    private static int[] copyArray(int[] arr) {
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private static int[] generateArray(int maxsize, int maxvalue) {
        int arr[] = new int[(int)((maxsize+1)*Math.random()) + 6];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxvalue+1)*Math.random());
        }
        return arr;
    }
}
