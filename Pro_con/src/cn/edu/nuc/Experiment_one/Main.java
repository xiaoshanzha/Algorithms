package cn.edu.nuc.Experiment_one;

import cn.edu.nuc.Experiment_two.Height;
import cn.edu.nuc.Experiment_two.HeightException;

public class Main {
    public static void main(String[] args) {
        Height height = new Height();
        try {
            height.Height(166);
            height.Height(190);
            height.Height(123);
            height.Height(100);
            height.Height(-7);
            height.Height(166);
        } catch (HeightException e) {
            System.out.println(e.warnMess());
        }
        System.out.println("FinalÉí¸ßÎª£º"+height.getM());
    }
}
