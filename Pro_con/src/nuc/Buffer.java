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
            System.out.println("�������ڻ�������"+put+"��λ�÷����Ʒ:"+r.getLocation()+
                    "��������ʣ���ڴ棺"+total);
            put = (put+1)%6;
        }
    }
    public static synchronized Resource Get(){
        if(total>=0&&total<6){
            Resource r = resources[get];
            total ++;
            System.out.println("�����ߴӻ�������"+get+"��λ���ó���Ʒ��"+r.getLocation()+
                    "��������ʣ���ڴ棺"+total);
            get = (get+1)%6;
            return r;
        }else
        {
            Resource r = new Resource(-1);
            System.out.println("�������޲�Ʒ");
            return r;
        }
    }
}
