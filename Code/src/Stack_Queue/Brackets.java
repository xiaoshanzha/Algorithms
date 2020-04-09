package Stack_Queue;

import java.util.Stack;

public class Brackets {
    /*
    * 处理一种括号：假设字符串中只有圆括号
    * 括号合法原则：每个右括号 ) 的左边必须有⼀个左括号 ( 和它匹配。
    * */
    boolean isValid(String str){
        //待匹配的左括号数量
        int left = 0;
        for (char c : str.toCharArray()) {
            if(c == '('){
                left++;
            }else{
                left--;
            }

            if(left < 0) {
                return false;
            }
        }
        return left == 0;
    }

    /*
    * 处理多种括号：
    * 遇到左括号就入栈，遇到右括号就去栈中寻找最近的左括号，看是否匹配
    * */
    boolean isValid1(String str){
        Stack<Character> left = new Stack<>();
        for (char c : str.toCharArray()) {
            if(c == '(' || c == '[' || c == '{'){
                left.push(c);
            }else {
                if(!left.isEmpty() && leftOf(c) == left.peek()){
                    left.pop();
                }else {
                    return false;
                }
            }
        }
        //检查是否所有的左括号都被匹配了
        return left.isEmpty();
    }

     char leftOf(char c) {
        if(c == '('){
            return ')';
        }
        if(c == '['){
            return ']';
        }
        return '{';
    }
}
