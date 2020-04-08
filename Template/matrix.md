[TOC]
## 矩阵问题
### 旋转矩阵
![](https://upload-images.jianshu.io/upload_images/10460153-ea42a8bbd3e7c706.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
//进行分圈处理，左上角和右下角两个点确定一个圈。
public void rotate(int[][] matrix) {
    int tR = 0;
    int tC = 0;
    int dR = matrix.length - 1;
    int dC = matrix[0].length - 1;
    while (tR < dR){
        rotateEdge(matrix,tR++,tC++,dR--,dC--);
    }
}

private void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
    // times表示总的组数
    int times = dR - tR;
    int tmp = 0;
    for (int i = 0; i < times; i++) {
        tmp = matrix[tR][tC + i];
        matrix[tR][tC + i] = matrix[dR - i][tC];
        matrix[dR - i][tC] = matrix[dR][dC - i];
        matrix[dR][dC - i] = matrix[tR + i][dC];
        matrix[tR + i][dC] = tmp;
    }
}
```