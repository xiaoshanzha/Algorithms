package cn.edu.nuc;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Producter producter = new Producter();
        Consumer consumer = new Consumer();
        producter.start();
        Thread.sleep(1000);
        consumer.start();
    }
}
