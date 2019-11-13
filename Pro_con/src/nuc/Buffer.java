package nuc;

public class Buffer {
    private static int total = 6;
    private static int put = 0;
    private static int get = 0;

    private static Resource[] resources = new Resource[6];
    public static synchronized void Push(Resource r){
        if(total>0){
            resources[put] = r;
            total--;
            System.out.println("生产者在缓存区第"+put+"个位置放入产品:"+r.getLocation()+
                    "，缓存区剩余内存："+total);
            put = (put+1)%6;
        }
    }
    public static synchronized Resource Get(){
        if(total>=0&&total<6){
            Resource r = resources[get];
            total ++;
            System.out.println("消费者从缓冲区第"+get+"个位置拿出产品："+r.getLocation()+
                    "，缓存区剩余内存："+total);
            get = (get+1)%6;
            return r;
        }else
        {
            Resource r = new Resource(-1);
            System.out.println("缓冲区无产品");
            return r;
        }
    }
}
