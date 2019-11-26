package cn.edu.nuc.Experiment_three;

import java.io.*;
import java.util.Scanner;

public class temp {
    public static void main(String[] args) throws IOException {
        FileReader fr =  new FileReader("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\temp.txt");
        FileWriter fw =  new FileWriter("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\temp.txt");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        char[] chars = temp.toCharArray();
        for(int i = 0;i<chars.length;i++) {
            char c = chars[i];
            fw.write(c);
            fw.flush();
            System.out.println("正在录入字符："+c);
        }
        char[] read = new char[1024];
        String rea = "";
        int len ;
        while((len = fr.read(read))!=-1){
            rea = rea + String.valueOf(read);
        }
        System.out.println("录入完成，录入信息为：" + rea);
        fw.close();
        fr.close();
    }
}
