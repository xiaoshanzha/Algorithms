package cn.edu.nuc.Experiment_two;

public class Height {
    private float m ;

    public void Height(float m) throws HeightException {
        if(m<=0){
            throw new HeightException(m);
        }
        this.m = m;
        System.out.println("���Ϊ��" + m);
    }

    public float getM() {
        return m;
    }
}
