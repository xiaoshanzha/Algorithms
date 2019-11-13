package nuc;

public class Producter extends Thread{
    @Override
    public void run() {
        int i = 1;
        while (true){
            Resource r = new Resource(i);
            Buffer.Push(r);
            i++;
        }
    }
}
