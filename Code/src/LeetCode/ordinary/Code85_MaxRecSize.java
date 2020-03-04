package LeetCode.ordinary;

import java.util.Stack;

public class Code85_MaxRecSize {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix[0].length == 0 || matrix.length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(largestRectangleArea(height),maxArea);
        }
        return maxArea;
    }
    public int largestRectangleArea(int[] heights) {
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
}
