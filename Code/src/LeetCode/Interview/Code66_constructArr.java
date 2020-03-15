package LeetCode.Interview;

public class Code66_constructArr {
    public int[] constructArr(int[] a) {
        if(a == null || a.length <= 1){
            return a;
        }

        //从首元素 乘到该位置的积
        int[] pre = new int[a.length];
        int[] pos = new int[a.length];
        int[] b = new int[a.length];
        int temp = 1;
        for (int i = 0; i < pre.length; i++) {
            temp *= a[i];
            pre[i] = temp;
        }
        temp = 1;
        for (int i = pre.length - 1; i >= 0; i--) {
            temp *= a[i];
            pos[i] = temp;
        }
        b[0] = pos[1];
        b[b.length - 1] = pre[b.length - 2];
        for (int i = 1; i < b.length - 1; i++) {
            b[i] = pre[i - 1] * pos[i + 1];
        }
        return b;
    }
}
