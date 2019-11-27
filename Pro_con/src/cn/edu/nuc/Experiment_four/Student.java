package cn.edu.nuc.Experiment_four;

import java.io.Serializable;

public class Student{
    private String Name;
    private String Sex;
    private String Stu_num;
    private String classes;
    private String Age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getStu_num() {
        return Stu_num;
    }

    public void setStu_num(String stu_num) {
        Stu_num = stu_num;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
