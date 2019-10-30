import java.util.Scanner;
/*
* 选择排序
* */
public class Selection {
    public static void sort(Comparable[] a) {
        //将a[]按升序排列
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //将a[i]和a[i+1...N]中最小的元素交换
            int min = i;  // 记录最小元素的索引
            for (int j = i+1; j < N; j++) {
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }
    //对元素进行比较
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    //将元素交换位置
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])){
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] a = new String[10];
        for (int i = 0; i < 10; i++) {
            a[i] = scanner.nextLine();
        }
        Selection.sort(a);
        show(a);
    }
}
