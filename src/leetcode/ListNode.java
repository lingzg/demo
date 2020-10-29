package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    int val;
    ListNode next;
    ListNode() {}
    ListNode(int x) { val = x; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    public static ListNode createNode(int[] arr){
        int i=0;
        ListNode node = new ListNode(arr[i++]);
        ListNode n = node;
        while(i<arr.length){
            n.next = new ListNode(arr[i++]);
            n = n.next;
        }
        return node;
    }
    
    public List<Integer> expand(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(val);
        ListNode node = next;
        while(node!=null){
            list.add(node.val);
            node = node.next;
        }
        return list;
    }
}
