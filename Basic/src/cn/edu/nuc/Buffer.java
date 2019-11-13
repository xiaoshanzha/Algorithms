package cn.edu.nuc;

public class Buffer {
    private static int total = 6;
    private static int location = 1;
    private static int put = 0;
    private static int get = 0;

    private static Resource[] resources = new Resource[6];
    public static synchronized void Push(Resource r){
        resources[put] = r;
        System.out.println("生产者在缓存区第"+put+"个位置放入产品:"+r.getLocation()+
               "，缓存区剩余内存："+total);
        put = (put+1)%6;
        total--;
    }
    public static synchronized Resource Get(){
        Resource r = resources[get];
        total ++;
        System.out.println("消费者从缓冲区第"+get+"个位置拿出产品："+r.getLocation()+
                "，缓存区剩余内存："+total);
        get = (get+1)%6;
        return r;
    }
}
