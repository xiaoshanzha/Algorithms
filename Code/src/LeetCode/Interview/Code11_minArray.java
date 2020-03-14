package LeetCode.Interview;

import java.util.Arrays;

public class Code11_minArray {
    public static int minArray(int[] numbers) {
        if(numbers.length == 0){
            return 0;
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            int mid = (left + right) / 2;
  /*          System.out.println("left : " + left + " : "+ numbers[left] );
            System.out.println("right" + right + " : "+ numbers[right] );
            System.out.println(" mid " + mid + " : "+ numbers[mid]);
            System.out.println("------------------------------");*/
            if(numbers[mid] > numbers[right]){
                left = mid + 1;
            }else if(numbers[mid] < numbers[right]){
                right = mid ;
            }else if(numbers[mid] == numbers[right]){ //此时无法判断在哪个数组，right--来缩小范围
               right--;
            }
        }
        return numbers[right];
    }
    public static int[] generate(int maxvalue,int maxNums){
        int nums = (int) (Math.random() * maxNums);

        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = (int) (Math.random() * maxvalue);
        }
        Arrays.sort(arr);
        int flag = (int) (Math.random() * (nums - 1));
        int[] res = new int[nums];
        int cur = 0;
        for (int i = flag; i < nums ;i++) {
            res[cur++] = arr[i];
        }
        for (int i = 0; i < flag; i++) {
            res[cur++] = arr[i];
        }
        return res;
    }
    public static int compator(int[] arr){
        if(arr.length == 0){
            return 0;
        }
        Arrays.sort(arr);
        return arr[0];
    }

    public static void printArray(int[] arr){
        System.out.println("原数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] copyArray(int[] arr){
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testtimes = 3000000;
        for (int i = 0; i < testtimes; i++) {
            int[] arr = generate(10,10);
            int[] arr1 = copyArray(arr);
            if(minArray(arr) != compator(arr1)){
                printArray(arr);
                System.out.println(minArray(arr));
                System.out.println(compator(arr1));
                break;
            }
        }
    }
}
