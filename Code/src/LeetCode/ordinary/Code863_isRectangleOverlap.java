package LeetCode.ordinary;

public class Code863_isRectangleOverlap {
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //重叠情况太多，不好考虑， 应考虑不重叠的情况
        /*
        * 检查位置：
        * 将矩形一固定位置放置，不重叠的情况有：
        * 矩形二 在一的 上下左右；
        * */
        if(rec1[2] <= rec2[0] || rec1[0] >= rec2[2]
            || rec1[3] <= rec2[1] || rec1[1] >= rec2[3]){
            return false;
        }
        return true;
    }

    /*
    * 检查区域：
    * 如果两个矩形重叠，那么它们重叠的区域一定也是一个矩形，
    * 那么这代表了两个矩形与 x 轴平行的边（水平边）投影到 x 轴上时会有交集，
    * 与 y 轴平行的边（竖直边）投影到 y 轴上时也会有交集。
    * 因此，我们可以将问题看作一维线段是否有交集的问题。
    *
    * 矩形 rec1 和 rec2 的水平边投影到 x 轴上的线段分别为
    *  (rec1[0], rec1[2]) 和 (rec2[0], rec2[2])。
    * 当 min(rec1[2], rec2[2]) > max(rec1[0], rec2[0]) 时，这两条线段有交集。
    * */
    public static boolean isRectangle(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    public static void main(String[] args) {
        int[] arr1 = {0,0,2,2};
        int[] arr2 = {1,1,3,3};
        System.out.println(isRectangleOverlap(arr1,arr2));
    }
}
