package cn.edu.nuc.Design_Patterns.behavior.Impl;

import cn.edu.nuc.Design_Patterns.behavior.flyBehavior;

/*
* ������Ϊʵ����
* */
public class flyByWings implements flyBehavior {
    @Override
    public void fly() {
        System.out.println("�������ɷɷ�");
    }
}
