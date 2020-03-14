package LeetCode.Interview;

import java.util.Stack;

public class Code31_validateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0 && popped.length == 0){
            return true;
        }
        if(pushed.length != popped.length){
            return false;
        }
        int cur1 = 0;
        int cur2 = 0;
        Stack<Integer> stack = new Stack<>();
        while (cur2 < popped.length){
            if(stack.isEmpty()){
                stack.add(pushed[cur1++]);
            }
            if(stack.peek() == popped[cur2]){
                cur2++;
                stack.pop();
            }else {
                stack.push(pushed[cur1++]);
            }
            if(cur1 == pushed.length &&
                    (cur2 != popped.length && stack.peek() != popped[cur2])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {11};
        int[] arr2 = {4,5,3,2,1};
        int[] arr3 = {4,3,5,1,2};
        System.out.println(validateStackSequences(arr1,arr2));
        System.out.println(validateStackSequences(arr3,arr3));
    }
}
