public class ClearScreen {

    //实现清屏的方法接口
    public native static void clearScreen();

    //加载dll文件
    static{
        System.loadLibrary("Clear");//生成dll的文件名 Clear
    }
}
