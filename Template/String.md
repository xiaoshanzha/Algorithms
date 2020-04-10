## 字符串
### 翻转字符串
![](https://upload-images.jianshu.io/upload_images/10460153-f6a64179b0d059c3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
public String reverseWords(String s) {
    char[] chars = s.toCharArray();
    if(chars == null || chars.length == 0){
        return s;
    }
    reverse(chars,0,chars.length - 1);
    int l = -1;
    int r = -1;
    // 确定每个单词的起始和终止位置
    for (int i = 0; i < chars.length; i++) {
        //确定起始位
        l = i == 0 || chars[i - 1] == ' ' ? i : l;
        //确定终止位
        r = i == chars.length - 1 || chars[i + 1] == ' ' ? i : r;

        if(l != -1 && r != -1){
            reverse(chars,l,r);
            l = -1;
            r = -1;
        }
    }

    int left = 0;
    int right = chars.length - 1;
    //去掉字符串开头的空白字符
    while (left <= right && chars[left] == ' ')
        left++;
    //去掉字符串末尾的空白字符
    while (left <= right && chars[right] == ' ')
        right--;
    //去掉字符串中间多余的空白字符
    StringBuilder sb = new StringBuilder();
    while (left <= right){
        char c = chars[left];
        if(c != ' '){
            sb.append(c);
        }
        else if(chars[left - 1] != ' '){  //c为' '并且前一字符不为' '
            sb.append(c);
        }
        left++;
    }
    return sb.toString();
}

//翻转字符串
public void reverse(char[] chars,int start,int end){
    char temp = 0;
    while (start < end){
        temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
        start++;
        end--;
    }
}
```
补充问题：
给定一个字符类型的数组chas和一个整数size，请把大小为size的左半区整体移动到右半区，右半区整体移动到左边。
eg：ABCDE , size = 3  ; 输出： DEABC
思路：
先把chas[0...size - 1]部分逆序，再把chas[size...N-1]部分逆序，再整体逆序。
ABCDE , size = 3 ；先CBA ED 再DEABC