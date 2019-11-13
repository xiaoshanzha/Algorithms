package cn.edu.nuc.Design_Patterns.behavior.Impl;

import cn.edu.nuc.Design_Patterns.behavior.flyBehavior;

public class flyNo implements flyBehavior {
    @Override
    public void fly() {
        System.out.println("我真的不会飞");
    }
}
