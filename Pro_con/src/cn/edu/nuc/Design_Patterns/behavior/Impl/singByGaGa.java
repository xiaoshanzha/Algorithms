package cn.edu.nuc.Design_Patterns.behavior.Impl;

import cn.edu.nuc.Design_Patterns.behavior.singBehavior;

public class singByGaGa implements singBehavior {
    @Override
    public void sing() {
        System.out.println("GaGaGa~~");
    }
}
