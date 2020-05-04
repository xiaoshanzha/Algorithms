import LeetCode.ordinary.Base.ListNode;

import java.util.List;

public class Double_Pointer {
    /*
    * 快慢指针：
    * 1.判断链表是否有环
    * 2.链表有环，返回环的起始位置
    * 3.找链表的中点
    * 4.找链表倒数k的节点
    * */

    /*
    * 左右指针：
    * 1.二分查找
    * 2.两数之和
    * 3.反转数组
    * 4.滑动窗口
    * */

    boolean hasCycle(ListNode head){
        ListNode fast,slow;
        fast = slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /*
    * 链表有环，返回环的起始位置
    * 解释：第一次相遇时，假设慢指针slow走了k步，则fast一定走了2k步。多走了k步(环的长度)
    *       设相遇点距环的起点距离为m,则环的起点距头结点k - m, 相遇点转一圈再到环的起点距离也为 k - m;
    * */
    ListNode EnterCircle(ListNode head){
        ListNode fast,slow;
        fast = slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        slow = head;
        while(slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
    * 找链表的中点。链表的归并排序
    * 链表的长度为奇数时，slow恰巧在中点位置，长度是偶数，slow为中间偏右
    * */
    ListNode MidNode(ListNode head){
        ListNode fast,slow;
        fast = slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
    * 寻找链表的倒数第k个元素
    * */
    ListNode KthNode(ListNode head , int k){
        ListNode slow,fast;
        slow = fast = head;
        while (k-- > 0){
            fast = fast.next;
        }
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /*
    * 二分查找
    * */

    /*
    * 两数之和
    * */

    /*
    * 反转数组
    * */
    void reverse(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
