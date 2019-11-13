package cn.edu.nuc.Experiment_two;

import cn.edu.nuc.Experiment_two.Shape;

public class Circle implements Shape {
    private  String name = "Circle";
    @Override
    public Double Area(Double x) {
        return 3.14 * x * x;
    }
    public String getName() {
        return name;
    }
}
