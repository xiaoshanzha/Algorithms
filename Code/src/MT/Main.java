package MT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

}
/*
 3. 现在有n名选手进行轮流报数，选手按顺序编号为1~n，另外我们会给出一个序列A，游戏会进行n轮，

         每轮会出局一名选手，第i轮淘汰的选手最后的排名是n-i+1,即第一轮出局的是倒数第一。
         出局的选手不会参与下一轮报数。
        每轮游戏都是从第一个选手开始报数，即如果1号选手仍在，则从1号选手开始，
         否则从2号选手开始，以此类推，但是注意，每轮报数是从0开始的，
         第i轮时，第一个报到A[i]的选手会出局，且当前轮游戏结束。A[i]有可能大于当前的剩余人数，
         则最后一个人报完以后，会由第一个人接着报，直到报出A[i]。
8
4.目前，你和你的家里人一共k个人一起去买生活用品。由于打折活动力度很大，每个人只能去付款一次，但是这一次买的东西价格是不做限制的。

         熊爷爷的超市物品分为两类：A类和B类物品，活动是如果一个人买的商品中含有A类物品，那么他买的所有物品中最便宜的一件物品半价。如果一个人买的商品中只有B类物品，那么他买的物品不打折。

         你们计划要买n个物品，现在将这n个物品的信息给你，请你计算如何分配k位家人比较合算。
5.两个数是相似的，当且仅当他们位与起来不为0。例如，3和5是相似的，因为3的二进制为011,5的二进制为101,他们位与起来为001不为0。

         现在，给出序列a1,a2…an我们希望你找出，对于任意的i∈[1,n]，是否存在j∈[1,n]，使得ai,aj不相似。
*/


    /* 2.货币数值的规范化是金融公司的一个问题，现在你需要写一个程序来解决这一问题：

1.货币数值的整数部分要求每3位加一个英文逗号','（不含引号）。例如12345678应该规范化为12,345,678

2.货币数值最多只有两位小数，如果有多余的小数位数应当舍去。注意，不是四舍五入。

3.负数代表欠款，在规范化后应当在数值两端加上括号 '(' 和 ')' ，然后省略掉负号。

4.应当在数值前面，前括号后面（如果有括号的话）加上金钱符号'$'（不含引号）

现在给你一个数字，请你规范化这一数字

    public static void main(String[] args) {
        //不知道多少组测试数据，读取输入，直到没有整型数据可读
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()){
            String s = cin.next();
            char[] chars = s.toCharArray();
            int index = -1;
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] == '.'){
                    index = i;
                    break;
                }
            }
            int num = 0; //小数点前的位数
            //没小数点
            if(index == -1){
                if(chars[0] != '-'){ //正
                    num = chars.length;
                }else { //负
                    num = chars.length - 1;
                }
            }else { //有小数点
                if(chars[0] != '-'){ //正
                    num = index;
                }else { //负
                    num = index - 1;
                }
            }
            StringBuilder sb = new StringBuilder();
            if(chars[0] != '-'){ //正数
                sb.append('$');
                int t = 0;
                for (int i = 0; i < num; i++) {
                    sb.append(chars[i]);
                    t++;
                    if((num- t) % 3 == 0 && (num- t)/3 > 0){
                        sb.append(',');
                    }
                }
                sb.append(".");
                int sheng = 0;
                if(index != -1){
                    sheng = chars.length - 1 - num;
                }
                if(sheng == 0){
                    sb.append('0');
                    sb.append('0');
                }else if(sheng == 1){
                    sb.append(chars[index + 1]);
                    sb.append('0');
                }else{
                    sb.append(chars[index + 1]);
                    sb.append(chars[index + 2]);
                }
            }else {
                sb.append("($");
                int t = 0;
                for (int i = 1; i <= num; i++) {
                    sb.append(chars[i]);
                    t++;
                    if((num- t) % 3 == 0 && (num- t)/3 > 0){
                        sb.append(',');
                    }
                }
                sb.append('.');
                int sheng = 0;
                if(index != -1){
                    sheng = chars.length - 2 - num;
                }
                if(sheng == 0){
                    sb.append('0');
                    sb.append('0');
                    sb.append(')');
                }else if(sheng == 1){
                    sb.append(chars[index + 1]);
                    sb.append('0');
                    sb.append(')');
                }else{
                    sb.append(chars[index + 1]);
                    sb.append(chars[index + 2]);
                    sb.append(')');
                }
            }
            System.out.println(sb.toString());
        }
    }*/

   /* 1.在观星的时候，一种常用的方式是划出类似于正方形的区域内，确定其中所有星星的坐标。

现在我们在星空（一个无限大的二维平面）上建立坐标系。由于星星很小，我们忽略它的面积，认为每一个星星是一个点，且所有星星的坐标都是整数。

幸运星的定义是这一颗星星在这个平面内，正上，正下，正左，正右都有其他的星星(不一定相邻)。

现在，我们已经将这个正方形的区域取出，并且将他们所在的坐标给你。现在希望你能计算，这个平面内有多少颗幸运星？

   public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int[][] points = new int[n][2];
        for (int j = 0; j < n; j++) {
            strings = bf.readLine().split(" ");
            points[j][0] = Integer.parseInt(strings[0]);
            points[j][1] = Integer.parseInt(strings[1]);
        }
        int res = 0;
        Arrays.sort(points,(o1,o2)->{
            int num = o1[0] - o2[0];
            if(num == 0){
                num = o1[1] - o2[1];
            }
            return num;
        });
        for (int[] point : points) {
            boolean left = false;
            boolean right = false;
            boolean top = false;
            boolean bottom = false;
            for (int[] p : points) {
                *//*if(point[0] == 0 && point[1] == 2){
                    System.out.println(p[0] + " ::: " + p[1]);
                    System.out.print(left);
                    System.out.print(bottom);
                    System.out.print(top);
                    System.out.print(right);
                    System.out.println();
                }*//*
                if(p[0] < point[0] && p[1] == point[1]){
                    left = true;
                }
                if(p[0] == point[0] && p[1] < point[1]){
                    bottom = true;
                }
                if(p[0] == point[0] && p[1] > point[1]){
                    top = true;
                }
                if(p[0] > point[0] && p[1] == point[1]){
                    right = true;
                }
                if(left && top && bottom && right){
                    res++;
                    break;
                }
            }
        }
        System.out.println(res);
    }*/
