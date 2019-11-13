package cn.edu.nuc.Experiment_one;

import java.util.Scanner;

public class Prize {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       while(true){
           int num = sc.nextInt();
           if(num==1){
               System.out.println("奖励一个巴掌！");
           }else if(num == 2){
               System.out.println("奖励两个巴掌！");
           }else if(num == 3){
               System.out.println("奖励三个巴掌！");
           }else {
               System.out.println("没有奖品给你！");
           }
       }
    }
}
