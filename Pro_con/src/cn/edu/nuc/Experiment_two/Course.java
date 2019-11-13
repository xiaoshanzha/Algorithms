package cn.edu.nuc.Experiment_two;

public class Course {
    private String name;
    private int num;
    private String course_num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public void printName(){
        System.out.println("¿Î³ÌÃû£º"+name);
    }
    public void printNum(){
        System.out.println("±àºÅ£º"+num);
    }
    public void printCourse_Num(){
        System.out.println("ÏÈĞŞ¿ÎºÅ£º"+course_num);
    }
}
