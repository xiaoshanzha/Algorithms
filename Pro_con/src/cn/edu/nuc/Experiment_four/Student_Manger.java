package cn.edu.nuc.Experiment_four;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student_Manger {
    private static Connection conn;
    private static Statement stmt;
    public static void load() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mydatabase?&serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        conn = DriverManager.getConnection(url,username,password);
        stmt = conn.createStatement();
    }
    public static void show() throws SQLException, ClassNotFoundException {
        load();
        String sql = "select * from students";
        ResultSet rs = stmt.executeQuery(sql);
        List<Student> list = new ArrayList<Student>();
        while(rs.next()){
            Student student = new Student();
            student.setName(rs.getString("Name"));
            student.setSex(rs.getString("Sex"));
            student.setStu_num(rs.getString("Stu_num"));
            student.setClasses(rs.getString("Classes"));
            student.setAge(rs.getString("Age"));
            list.add(student);
        }
        for (Student i:list) {
            System.out.println("Name:"+i.getName());
            System.out.println("Sex:"+i.getSex());
            System.out.println("Stu_num:"+i.getStu_num());
            System.out.println("Classes:"+i.getClasses());
            System.out.println("Age:"+i.getAge());
            System.out.println("---------");
        }
    }
    public static void add() throws SQLException {
        System.out.println("分别输入学生的姓名，性别，学号，班级，年龄");
        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();
        String Sex = scanner.nextLine();
        String Stu_num = scanner.nextLine();
        String Classes = scanner.nextLine();
        String Age = scanner.nextLine();

        String sql = "insert into students(Name,Sex,Stu_num,Classes,Age) values(?,?,?,?,?)";
        // 获取PreparedStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        // 对SQL语句中的第1个参数赋值
        ps.setString(1, Name);
        ps.setString(2, Sex);
        ps.setString(3, Stu_num);
        ps.setString(4, Classes);
        ps.setString(5, Age);
        // 执行更新操作，返回所影响的行数
        int row = ps.executeUpdate();
        // 判断是否更新成功
        if(row > 0){
            System.out.println("添加成功！ " );
        }
        ps.close();
    }
    public static void delete() throws SQLException {
        System.out.println("请输入要删除学生的姓名");
        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();
        String sql_t = "DELETE FROM students where Name='"+Name+"';";
        stmt.executeUpdate(sql_t);
    }
    public static void find() throws SQLException {
        System.out.println("请输入要查询学生的姓名");
        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();
        String sql_t = "select * from students where Name='"+Name+"'";
        ResultSet rs = stmt.executeQuery(sql_t);
        while (rs.next()){
            System.out.println("Name:"+rs.getString("Name"));
            System.out.println("Sex:"+rs.getString("Sex"));
            System.out.println("Stu_num:"+rs.getString("Stu_num"));
            System.out.println("Classes:"+rs.getString("Classes"));
            System.out.println("Age:"+rs.getString("Age"));
        }
    }
    public static void main(String[] args) throws Exception {
        boolean flag = true;
        while(flag){
            System.out.println("\n请输入所需要的操作");
            System.out.println("1.展示学生信息  2.增加学生信息  3.删除学生信息 4.查询学生信息 5.退出系统");
            Scanner scanner = new Scanner(System.in);
            int c = scanner.nextInt();
            if(c==1){
                show();
            }else if(c==2){
                add();
            }else if(c==3){
                delete();
            }else if(c==4){
                find();
            }else if(c==5){
                flag = false;
            }
        }
    }
}
