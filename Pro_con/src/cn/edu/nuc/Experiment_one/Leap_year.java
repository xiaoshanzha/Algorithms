package cn.edu.nuc.Experiment_one;

import java.util.Scanner;

public class Leap_year {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int num = sc.nextInt();
            if(num%4==0 && num%100!=0){
                System.out.println(num+"��������");
            }else if(num%400==0){
                System.out.println(num+"��������"); 
            }else {
                System.out.println(num+"�겻������");
            }
        }
    }
}
