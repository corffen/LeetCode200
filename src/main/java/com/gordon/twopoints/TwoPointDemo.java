package com.gordon.twopoints;

import com.gordon.listnode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoPointDemo {


    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[slow++] = nums[i];
            }
        }
        return slow;
    }

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    /**
     * %20ÓÃÌæ»»×Ö·û´®ÖÐµÄ¿Õ¸ñ
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("  ");
            }
        }
        if (sb.length() == 0) {
            return s;
        }
        int left = s.length() - 1;
        s += sb.toString();
        char[] chars = s.toCharArray();
        int right = s.length() - 1;
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }


    /**
     * 1. È¥³ýÁ½±ßµÄ¿Õ¸ñ,µ¥´ÊÖÐ¼äµÄ¿Õ¸ñ±£³ÖÒ»¸ö
     * 2. ·­×ªÕû¸ö×Ö·û´® ÓÃË«Ö¸Õë
     * 3. ·­×ªÃ¿¸öµ¥´Ê  ÓÃË«Ö¸Õë(start´Ó0¿ªÊ¼,endÖ¸Ïò¿Õ¸ñ È»ºóstart=end+1,end=start+1,È»ºó¼ÆËãendµÄÏÂÒ»´ÎÎ»ÖÃ)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        List<String> stringList = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(stringList);
        return String.join(" ", stringList);
    }

    public void reverseWords2(String s) {
        StringBuilder sb = removeSpace(s);
        StringBuilder reverse = sb.reverse();
        reverseEach(reverse);
    }

    private StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    private void reverseString(StringBuilder sb, int start, int end) {
        while (start <= end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private void reverseEach(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
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
            return getIntersectionNode(headB, headB);
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
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
