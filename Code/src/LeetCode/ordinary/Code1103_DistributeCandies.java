package LeetCode.ordinary;

public class Code1103_DistributeCandies {
    /*
    * 暴力分糖果
    * */
    public static int[] distributeCandies(int candies, int num_people) {
        int i = 0;
        int[] res = new int[num_people];
        while (candies > 0){
            int index = i % num_people;
            if(candies >= i + 1){
                res[index] += i + 1;
                candies =candies -  ( i + 1 );
            }else {
                res[index] += candies;
                candies = 0;
            }
            i++;
        }
        return res;
    }
/*
* 利用数学等差数列分：
* 假如糖果一共分了(k+1)次，则前k次均是按规则分配，每次得到的糖果组成一个等差数列
*   总糖果数共：(1+k) * k / 2个，可以计算出k值；
* 一共有num_people人，那么按规则进行了k/num_people轮，最后一轮按规则进行了k%num_people个
* 每个同学每次得到的糖果也是一个等差数列。计算和。
* */
    public static int[] distributeCandiesByMath(int candies, int num_people) {
        int k = 1;
        while (k * (k + 1)/2 <= candies){
            k++;
        }
        k--;
        System.out.println(k);
        int[] res = new int[num_people];
        int level = k / num_people;
        int sheng = k % num_people;
        for (int i = 0; i < num_people; i++) {
            res[i] += ((i+1) + (i+1)+num_people*(level-1)) * level/2;
        }
        for (int i = 0; i < sheng; i++) {
            res[i] += (i+1) + num_people * (level);
        }
        res[k % num_people] += candies - (1 + k)*k/2;
        return res;
    }

/*    public static int[] comparator(int candies, int num_people){

    }
    */
    public static void main(String[] args) {
        int testTime  = 50000;
        boolean flag = true;
     /*   for (int i = 0; i < testTime; i++) {
            int candies = (int) (1000000000 * Math.random() + 1);
            int numpeople = (int)(1000 * Math.random() + 1);
        }*/

        int[] res = distributeCandiesByMath(7,4);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

}
