package cn.edu.nuc;

public class Consumer extends Thread{
    @Override
    public void run() {
        while(true){
            Resource r = Buffer.Get();
        }
    }
}
