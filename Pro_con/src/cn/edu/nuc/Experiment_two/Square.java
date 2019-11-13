package cn.edu.nuc.Experiment_two;

import cn.edu.nuc.Experiment_two.Shape;

public class Square implements Shape {
    private  String name = "Square";
    @Override
    public Double Area(Double x) {
        return x * x;
    }
    public String getName() {
        return name;
    }
}