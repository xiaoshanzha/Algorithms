package cn.edu.nuc.Experiment_one;

import java.util.Scanner;

public class Prize {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       while(true){
           int num = sc.nextInt();
           if(num==1){
               System.out.println("����һ�����ƣ�");
           }else if(num == 2){
               System.out.println("�����������ƣ�");
           }else if(num == 3){
               System.out.println("�����������ƣ�");
           }else {
               System.out.println("û�н�Ʒ���㣡");
           }
       }
    }
}
