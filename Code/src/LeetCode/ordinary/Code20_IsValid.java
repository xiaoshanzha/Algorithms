package LeetCode.ordinary;

import java.util.Stack;

public class Code20_IsValid {
    /*
    * 遇到开括号就入栈，如果是闭括号则拿出栈顶元素，看是否匹配，
    * 最后看 栈内是否还有元素
    * */
    public boolean isValid(String s) {
        char[] ss = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < ss.length; i++) {
            char t = ss[i];
            if(t == '('||t == '{'||t == '['){
                stack.push(t);
            }else {

                //注意符号不为开括号时，应先看栈内是否是空
                if(stack.isEmpty()){
                    return false;
                }else {
                    char t2 = stack.peek();
                    if((t == ')'&& t2 == '(')||(t == '}'&& t2 == '{')||
                            (t == ']'&& t2 == '[')){
                        stack.pop();
                    }else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
