/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first=-1;
        int prevCritical=-1;
        int min=Integer.MAX_VALUE;
        int max=-1;
        ListNode prev=head;
        ListNode curr=head.next;
        int index=1;
        while(curr.next!=null){
            ListNode next=curr.next;
            if((curr.val>prev.val&&curr.val>next.val)||(curr.val<prev.val&&curr.val<next.val)){
                if(first==-1){
                    first=index;
                }else{
                    min=Math.min(min,index-prevCritical);
                    max=index-first;
                }
                prevCritical=index;
            }
            prev=curr;
            curr=next;
            index++;
        }
        if(max==-1){
            return new int[]{-1,-1};
        }
        return new int[]{min,max};
    }
}