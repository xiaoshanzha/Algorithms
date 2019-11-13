package cn.edu.nuc.Experiment_two;

import cn.edu.nuc.Experiment_two.Shape;

public class Triangle implements Shape {
    private  String name = "Triangle";
    @Override
    public Double Area(Double x) {
        return   x * x / 2;
    }
    public String getName() {
        return name;
    }
}