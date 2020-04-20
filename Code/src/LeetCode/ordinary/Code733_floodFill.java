package LeetCode.ordinary;

public class Code733_floodFill {
    public int newColor = 0;
    public int oldColor = 0;
    //设置访问的标志位
    public int[][] img = null;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.newColor = newColor;
        this.oldColor = image[sr][sc];
        img = new int[image.length][image[0].length];
        infect(image,sr,sc);
        return image;
    }
    public void infect(int[][] image,int i,int j) {
        if(i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oldColor||img[i][j]==1){
            return;
        }
        //将该位置设为已被访问的状态(以免出现最后死循环的状态 eg: 相连两个位置互相调用导致栈溢出)
        img[i][j] = 1;
        image[i][j] = newColor;
        infect(image,i-1,j);
        infect(image,i+1,j);
        infect(image,i,j-1);
        infect(image,i,j+1);
    }

    public static void main(String[] args) {
        Code733_floodFill c = new Code733_floodFill();
        int[][] arr = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        int[][] res = c.floodFill(arr,1,1,2);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
