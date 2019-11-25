package cn.edu.nuc.Experiment_three;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Object {
    public static void main(String[] args) {
        Student student1 = new Student("ÍôÄ³","Å®");
        Student student2 = new Student("º«Ä³","Å®");
        Student student3 = new Student("ºúÄ³","Å®");
        Student student4 = new Student("ÁõÄ³","Å®");
        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Student.txt");
            for(int i = 0;i<list.size();i++){
                fw.write(list.get(i).toString());
            }
            fw.close();
            fr = new FileReader("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\Student.txt");
            char[] c = new char[1024];
            int len = 0;
            while((len=fr.read(c))!=-1){
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
