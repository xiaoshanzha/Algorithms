package cn.edu.nuc.Design_Patterns;

import cn.edu.nuc.Design_Patterns.behavior.flyBehavior;
import cn.edu.nuc.Design_Patterns.behavior.singBehavior;

public abstract class Duck {

    flyBehavior flyBehavior;
    singBehavior singBehavior;

    /**
     * 外观
     */
    public abstract void display();

    /**
     * 游泳
     */
    public void swim() {
        System.out.println("冲冲冲......");
    }

    /**
     * 飞行
     */
    public void performFly() {
        //将飞行这件小事委托给行为类
        if (flyBehavior != null)
            flyBehavior.fly();
    }

    /**
     * 唱歌
     */
    public void performQuack() {
        //将唱歌这件小事委托给行为类
        if (singBehavior != null)
            singBehavior.sing();
    }
}
