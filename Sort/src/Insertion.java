import java.util.Scanner;

public class Insertion {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
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
        Insertion.sort(a);
        show(a);
    }
}
