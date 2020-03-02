package Classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GetAllNotIncluded {
    /*
    * 一个有序数组A，另一个无序数组B，请打印B中的所有不在A中的数，A数组长度为N，B数组长度为M。
    * 方法一：对于数组B中的每一个数，都在A中通过二分的方式找一下；
    *        时间复杂度O(M*logN)
    * 方法二：先把数组B排序，然后用类似外排的方式打印所有在A中出现的数；
    *        时间复杂度O(M*logM)+O(N+M)
    * 两方法比较：A很短，B很长则方法一好；
    *            B很短，A很长则方法二好
    * */
    private static List<Integer> GetAllByOne(int[] A,int[] B){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            int l = 0;
            int r = A.length - 1;
            boolean contains = false;
            while (l <= r){
                int mid = l + ((r - l) >> 1);
                if(A[mid] == B[i]){
                    contains = true;
                    break;
                }
                if(A[mid] > B[i]){
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }
            if(!contains){
                res.add(B[i]);
            }
        }
        return res;
    }

    private static List<Integer> comparator(int[] A , int[] B){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            boolean contains = false;
            for (int j = 0; j < A.length; j++) {
                if(A[j] == B[i]){
                    contains = true;
                    break;
                }
            }
            if(!contains){
                res.add(B[i]);
            }
        }
        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /*
    * 利用HashMap 比较两个List集合是否相等
    * 先用集合l1初始化map,然后再用集合l2在map上作相应运算比较
    * */
    public static boolean isEqual(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : l1) {
            if (!map.containsKey(i)) {
                map.put(i, 0);
            }
            map.put(i, map.get(i) + 1);
        }
        for (Integer i : l2) {
            if (!map.containsKey(i)) {
                return false;
            }
            map.put(i, map.get(i) - 1);
            if (map.get(i) < 0) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 30000;
        int sortedArrayMaxSize = 300;
        int unsortedArrayMaxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] A = generateRandomArray(sortedArrayMaxSize, maxValue);
            int[] B = generateRandomArray(unsortedArrayMaxSize, maxValue);
            Arrays.sort(A);
            List<Integer> res1 = GetAllByOne(A, B);
            List<Integer> res2 = comparator(A, B);
            if (!isEqual(res1, res2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck!");
    }
}
