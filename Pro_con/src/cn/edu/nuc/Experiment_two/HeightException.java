package cn.edu.nuc.Experiment_two;

public class HeightException extends Exception{
    String message;

    public HeightException(float m) {
        this.message = "身高" + m + "是负数，不符合要求.";
    }
    public String warnMess(){
        return message;
    }
}
