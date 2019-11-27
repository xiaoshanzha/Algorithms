package cn.edu.nuc.Experiment_three;
import java.io.*;
import java.util.*;

public class Object{
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Student student1 = new Student("汪某","女");
        Student student2 = new Student("韩某","女");
        Student student3 = new Student("胡某","女");
        Student student4 = new Student("刘某","女");
        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        File file =  new File("F:\\GitHub\\Algorithms\\Pro_con\\src\\cn\\edu\\nuc\\Experiment_three\\student.txt");
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        for (Student s:list) {
            objectOutputStream.writeObject(s);
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        Student t = null;
        try {
            while ( (t = (Student)objectInputStream.readObject())!=null)
            {
                System.out.println(t.toString());
            }
        }catch (EOFException e){ //EOF 即End Of File;当读取结束 抛出此异常
            System.out.println("读取结束");
        }

    }
}
