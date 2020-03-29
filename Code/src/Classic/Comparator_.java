package Classic;

import java.util.Arrays;
import java.util.Comparator;

public class Comparator_ {
    public static class Student{
        private String name;
        private int age;
        private int id;

        public Student(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }
    }
    /*
    * return 正数，第二个参数放前面
    * return 负数，第一个参数放前面
    * */
    private static class IdAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    private static class IdDescendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    private static class AgeAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    private static class AgeDescendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }
    public static void main(String[] args) {
        Student student1 = new Student("A", 1, 23);
        Student student2 = new Student("B", 2, 21);
        Student student3 = new Student("C", 3, 22);

        Student[] students = new Student[] { student3, student2, student1 };
        printStudents(students);



        Arrays.sort(students, new IdDescendingComparator());
        printStudents(students);

        Arrays.sort(students, new IdAscendingComparator());
        printStudents(students);
        
        Arrays.sort(students, new AgeAscendingComparator());
        printStudents(students);

        Arrays.sort(students, new AgeDescendingComparator());
        printStudents(students);

    }

    private static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
        }
        System.out.println("===========================");
    }
}
