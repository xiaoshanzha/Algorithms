import java.util.Arrays;

public class skill {
    public static void main(String[] args) {
        //1.复制数组并排序
        int[] arr = {1,4,6,3,5};
        int[] newArr = Arrays.copyOf(arr,arr.length+1);  //复制长度为arr.length+1的数组，原数组不够后面补0
        printArr(newArr);
        newArr = Arrays.copyOfRange(arr,2,arr.length-1); //复制索引[from,to)的数组
        printArr(newArr);
    }
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
