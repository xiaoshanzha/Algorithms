public class ClearScreen {

    //ʵ�������ķ����ӿ�
    public native static void clearScreen();

    //����dll�ļ�
    static{
        System.loadLibrary("Clear");//����dll���ļ��� Clear
    }
}
