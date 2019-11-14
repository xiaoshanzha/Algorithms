package cn.edu.nuc.Design_Patterns;

import cn.edu.nuc.Design_Patterns.behavior.Impl.flyByWings;
import cn.edu.nuc.Design_Patterns.behavior.Impl.singNo;

public class Main {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.swim();
        duck.display();
        duck.performFly();
        duck.performQuack();

        //���ڻ����
        duck.setFlyBehavior(new flyByWings());
        duck.performFly();
        //���ǲ������
        duck.setSingBehavior(new singNo());
        duck.performQuack();
    }
}
