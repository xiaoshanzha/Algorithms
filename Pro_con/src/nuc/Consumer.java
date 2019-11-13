package nuc;

public class Consumer extends Thread{
    @Override
    public void run() {
        while(true){
            Resource r = Buffer.Get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
