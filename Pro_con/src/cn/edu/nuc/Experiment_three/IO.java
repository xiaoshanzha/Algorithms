package cn.edu.nuc.Experiment_three;

import java.io.*;

public class IO {
    public void Copy_ByteStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Ex3_2.HTML");
            fos = new FileOutputStream("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Ex3_2(byte).txt");
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = fis.read(bytes))!=-1){
                System.out.println(new String(bytes));
                fos.write(bytes,0,len);
            }
        } catch (IOException io) {
            System.out.println(io);
            throw new RuntimeException("复制失败");
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException("释放资源失败");
                }
            }
        }
    }


    public void Copy_CharStream() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Ex3_2.HTML");
            fw = new FileWriter("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Ex3_2(char).txt");
            char[] c = new char[1024];
            int len = 0;
            while((len=fr.read(c))!=-1){
                System.out.println(c);
                fw.write(c,0,len);
                fw.flush();
            }
        }  catch (IOException e) {
            throw new RuntimeException("复制失败");
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException("释放资源失败");
                }
            }
        }
    }
    public static void main(String[] args) {
        IO io = new IO();
        io.Copy_ByteStream();
        io.Copy_CharStream();
    }
}
