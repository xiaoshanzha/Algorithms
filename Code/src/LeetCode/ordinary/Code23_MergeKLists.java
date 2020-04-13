package LeetCode.ordinary;

import LeetCode.ordinary.Base.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code23_MergeKLists {
    /*
    * 先将每个首结点组成大小为k的小根堆，每次取出堆头，再放入该节点的next节点
    * */
    public ListNode mergeKLists_1(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1,o2) -> o1.val - o2.val);
        for (ListNode listnode : lists) {
            if(listnode == null){
                continue;
            }
            pq.add(listnode);
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (!pq.isEmpty()){
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next != null){
                pq.add(node.next);
            }
        }
        return head.next;
    }

    /*
    * 分治，将lists分成两部分，进行递归，
    * 终止条件： 如果剩一个，直接返回，生两个，调函数进行排序。
    * */
    public ListNode mergeKLists_2(ListNode[] lists) {
        if(lists == null || lists.length == 0 ){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        if(lists.length == 2){
            return merge(lists[0],lists[1]);
        }

        int mid = lists.length / 2;
        ListNode[] l1 = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length - mid];
        for (int i = mid,j = 0; i < lists.length; i++) {
            l2[j++] = lists[i];
        }
        return merge(mergeKLists_2(l1),mergeKLists_2(l2));
    }
    public ListNode merge(ListNode node1, ListNode node2){
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        ListNode head = null;
        if(node1.val <= node2.val){
            head = node1;
            head.next = merge(node1.next,node2);
        }else {
            head = node2;
            head.next = merge(node1,node2.next);
        }
        return head;
    }
}
