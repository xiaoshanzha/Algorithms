package cn.edu.nuc.Experiment_two;

public class HeightException extends Exception{
    String message;

    public HeightException(float m) {
        this.message = "���" + m + "�Ǹ�����������Ҫ��.";
    }
    public String warnMess(){
        return message;
    }
}
