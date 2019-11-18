public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Lnode p = list.h.next.next;
     //   list.insertElementAfter(p,'a');
     //   list.insertElementAt(3,'s');
        Lnode s = list.get(4);
        System.out.println(s.data);
        list.print();
    }
}
