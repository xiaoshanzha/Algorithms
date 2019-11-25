import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class banker {
    public static int Sum[] = {10,5,7};
    public static int Allocation[][] = {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
    public static int Max[][] = {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
    public static int Need[][] = {{7,4,3},{1,2,2},{6,0,0},{0,1,1},{4,3,1}};
    public static int Available[] = {3,3,2};
    public static int Work[] = {3,3,2};
    public static boolean Finish[] = {true,true,true,true,true};

    public void Updata(){
        for(int i = 0;i<5;i++){
            for (int j = 0; j < 3; j++) {
                Need[i][j] = Max[i][j] - Allocation[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            Available[i] = Sum[i]-Allocation[0][i]-Allocation[1][i]-Allocation[2][i]-Allocation[3][i]-Allocation[4][i];
        }
    }
    public void Request(int i,int a,int b,int c){
        Allocation[i][0] = Allocation[i][0]+a;
        Allocation[i][1] = Allocation[i][1]+b;
        Allocation[i][2] = Allocation[i][2]+c;
        Updata();
    }

    public void Release(int i,int a,int b,int c){
        Allocation[i][0] = Allocation[i][0]-a;
        Allocation[i][1] = Allocation[i][1]-b;
        Allocation[i][2] = Allocation[i][2]-c;
        Updata();
    }

    public void Display(){
        System.out.println("进程\\资源情况       Max        Allocation      Need        Available");
        System.out.println("                 A  B  C       A  B  C       A  B  C       A  B  C");
        for (int i = 0; i < 5; i++) {
            System.out.print("     "+"p"+ i +"          " + Max[i][0]+"  "+Max[i][1]+"  "+Max[i][2]+
                    "       " + Allocation[i][0]+"  "+Allocation[i][1]+"  "+Allocation[i][2]+
                    "       " + Need[i][0]+"  "+Need[i][1]+"  "+Need[i][2]);
            if(i==0){
                System.out.print("       " + Available[0]+"  "+Available[1]+"  "+Available[2]);
            }
            System.out.println();
        }
    }
    public void Display_Check(int []arr){
        System.out.println("进程\\资源情况       Work        Need        Allocation       Work+Allocation        Finish");
        System.out.println("                 A  B  C      A  B  C       A  B  C            A  B  C");
        for (int i:arr) {
            int W_A[] = {Work[0]+Allocation[i][0],Work[1]+Allocation[i][1],Work[2]+Allocation[i][2]};
            System.out.println("     "+"p"+ i +"          " + Work[0]+"  "+Work[1]+"  "+Work[2]+
                    "      " + Need[i][0]+"  "+Need[i][1]+"  "+Need[i][2]+
                    "       " + Allocation[i][0]+"  "+Allocation[i][1]+"  "+Allocation[i][2]+
                    "            " + W_A[0]+"  "+W_A[1]+"  "+W_A[2]+
                    "              "+Finish[i]);
            Work[0] = W_A[0];
            Work[1] = W_A[1];
            Work[2] = W_A[2];
        }
    }

    public void Check(){
        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < 5; i++) {
            Node one = new Node(i);
            list.add(one);
        }
        Stack<Integer> stack = new Stack<Integer>();
        int Position = 1;
        int zero = 0;
        boolean final_flag = true;
        while (final_flag){
            for (int i = 0; i < 5; i++) {
                Node temp_Node = list.get(i);
                int temp_index = list.get(i).index;
                boolean Node_Satisfy = Work[0]>=Need[temp_index][0]&&Work[1]>=Need[temp_index][1]&&Work[2]>=Need[temp_index][2];
                //进栈
                if(temp_Node.flag&&Node_Satisfy &&!temp_Node.not.contains(Position)){
                    stack.push(temp_index);
                    list.get(temp_index).flag = false;
                    Work[0] = Work[0]+Allocation[temp_index][0];
                    Work[1] = Work[1]+Allocation[temp_index][1];
                    Work[2] = Work[2]+Allocation[temp_index][2];
                    Position++;
                    zero++;
                }
                /*//出栈
                if(){
                    int out_index = stack.pop();
                    list.get(out_index).flag = true;
                    list.get(out_index).not.add(Position--);
                }*/
                //终止
                if(Position==1&&(list.get(0).not.contains(1)&&list.get(1).not.contains(1)&&list.get(2).not.contains(1)&&list.get(3).not.contains(1)&&list.get(4).not.contains(1))){
                    final_flag = false;
                    break;
                }
                if(i==4&&zero==0){
                    final_flag = false;
                    break;
                }
                if(stack.size()==5)
                {
                    final_flag = false;
                    break;
                }
            }
        }
        if(stack.size()==5){
            final_flag = true;
            int a = stack.pop();
            int b = stack.pop();
            int c = stack.pop();
            int d = stack.pop();
            int e = stack.pop();
            int []safe_arr =  new int[]{e,d,c,b,a};
            Work[0] = 3;
            Work[1] = 3;
            Work[2] = 2;
            Display_Check(safe_arr);
        }
        if(!final_flag){
            System.out.println("不安全");
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入");
        System.out.println("1.查看目前系统资源表  2.申请资源  3.释放资源  4.安全性检查");
        Scanner scanner = new Scanner(System.in);
        banker banker = new banker();
        while (true){
            int i = scanner.nextInt();
            if(i==1){
                banker.Display();
            }else if(i==2){
                System.out.println("输入要申请资源的进程和分别需要申请A,B,C资源的数量");
                int j = scanner.nextInt();
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                banker.Request(j,a,b,c);
            }else if(i==3){
                System.out.println("输入要释放资源的进程和分别需要释放A,B,C资源的数量");
                int j = scanner.nextInt();
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                banker.Release(j,a,b,c);
            }else if(i==4){
                banker.Check();
            }
        }
    }
}
