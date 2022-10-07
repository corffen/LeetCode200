package com.gordon.listnode;

public class ListDemo {
    /**
     * 移除链表中的元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummy.next;
    }

    /**
     * 翻转链表,创建当前节点和前一个节点,指向关系,逐步拼接
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode temp = null;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 做的第二遍,只是高频考点,联系一下
     * 遍历当前节点,不断的指向下一个节点为pre
     * 最终返回pre即可.
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 两两交换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode temp = null;
        while (pre.next != null && pre.next.next != null) {
            temp = head.next.next;
            pre.next = head.next; //第一步
            head.next.next = head;//第二步
            head.next = temp;//第三步
            pre = head;  //移动虚拟头结点,注意此时的指向应该是head
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode fast = headA;
        ListNode slow = headB;
        int aSize = 0;
        int bSize = 0;
        while (fast != null) {
            fast = fast.next;
            aSize++;
        }
        while (slow != null) {
            slow = slow.next;
            bSize++;
        }
        if (aSize < bSize) {
            return getIntersectionNode(headB, headA);
        }
        int distance = aSize - bSize;
        fast = headA;
        slow = headB;
        while (distance-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            if (fast == slow) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode a = fast;
                ListNode b = head;
                while (a != b) {
                    a = a.next;
                    b = b.next;
                }
                return a;
            }
        }
        return null;
    }
}
