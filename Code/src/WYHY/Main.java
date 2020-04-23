package WYHY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Main {

}
/*
第一题：字符串相加,字符串为纯数字，或者是小数。 长度很长
        通过 : 100%
    public String add (String num1, String num2) {
        int[][] type = new int[2][2];
        int len1 = num1.length();
        int len2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < len1; i++) {
            if(chars1[i] == '.'){
                type[0][0] = temp;
                temp = 0;
                flag = !flag;
                continue;
            }
            temp++;
            if(!flag && i == len1 - 1){
                type[0][0] = temp;
            }
        }
        if(flag){
            type[0][1] = temp;
        }
        temp = 0;
        flag = false;
        for (int i = 0; i < len2; i++) {
            if(chars2[i] == '.'){
                type[1][0] = temp;
                temp = 0;
                flag = !flag;
                continue;
            }
            temp++;
            if(!flag && i == len2 - 1){
                type[1][0] = temp;
            }
        }
        if(flag){
            type[1][1] = temp;
        }
        String s = "";
        for (int i = 0; i < Math.abs(type[0][0] - type[1][0]); i++) {
            s += "0";
        }
        num1 = Math.max(type[0][0],type[1][0]) == type[0][0] ? num1 : s + num1;
        num2 = Math.max(type[0][0],type[1][0]) == type[1][0] ? num2 : s + num2;
        //小数部分
        if(type[0][1] != 0 || type[1][1] != 0){
            s = "";
            for (int i = 0; i < Math.abs(type[0][1] - type[1][1]); i++) {
                s += "0";
            }
            if(type[0][1] == 0){
                num1 = Math.max(type[0][1],type[1][1]) == type[0][1] ? num1 : num1 + "." + s;
                num2 = Math.max(type[0][1],type[1][1]) == type[1][1] ? num2 : num2 + s;
            }
            if(type[1][1] == 0)
            {
                num1 = Math.max(type[0][1],type[1][1]) == type[0][1] ? num1 : num1 + s;
                num2 = Math.max(type[0][1],type[1][1]) == type[1][1] ? num2 : num2 +"." + s;
            }
            if(type[0][1] != 0 && type[1][1] != 0){
                num1 = Math.max(type[0][1],type[1][1]) == type[0][1] ? num1 : num1 + s;
                num2 = Math.max(type[0][1],type[1][1]) == type[1][1] ? num2 : num2 + s;
            }
        }
        char[] new_1 = num1.toCharArray();
        char[] new_2 = num2.toCharArray();
        int new_len = new_1.length;
        char[] new_3 = new char[new_len];
        boolean jin = false;
        int t = 0;
        for (int i = new_len - 1; i >= 0; i--) {
            if(new_1[i] == '.'){
                new_3[i] = '.';
                continue;
            }
            if(jin){
                t = (new_1[i] - '0') + (new_2[i] - '0') + 1;
            }else {
                t = (new_1[i] - '0') + (new_2[i] - '0');
            }
            if(t >= 9){
                new_3[i] = (char) ((t - 9) + '0');
                jin = true;
            }else {
                new_3[i] = (char) (t + '0');
                jin = false;
            }
        }
        String res = "";
        if(jin){
            res = "1";
        }
        return  res + String.valueOf(new_3);
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.add("25","15"));
    }*/

  /*  第四题： 大盒子套小盒子，最多能套几个，每个盒子有长宽高信息，盒子不能旋转
      通过：80 %
    public int maxBoxes (int[][] boxes) {
        if(boxes == null || boxes.length == 0){
            return 0;
        }
        int nums = boxes.length;
        if(nums == 1){
            return 1;
        }
        Arrays.sort(boxes,(t0, t1) -> {
            int res = Integer.compare(t1[0] , t0[0]);
            if(res == 0){
                res = Integer.compare(t1[1] , t0[1]);
            }
            if(res == 0){
                res = Integer.compare(t1[2] , t0[2]);
            }
            return res;
        });
        int res = 1;
        int a = boxes[0][0];
        int b = boxes[0][1];
        int c = boxes[0][2];
        for (int i = 1; i < nums; i++) {
            if(boxes[i][0] < a && boxes[i][1] < b && boxes[i][2] < c){
                res++;
                a = boxes[i][0];
                b = boxes[i][1];
                c = boxes[i][2];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Main m = new Main();
        int[][] arr = {{5,6,3},{5,4,6},{6,6,6}};
        System.out.println(m.maxBoxes(arr));
    }*/