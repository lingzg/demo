package leetcode;

import java.util.Arrays;

public class KthLargest {

    private int k;
    private int[] ans;
    private boolean unfull;
    
    /*public KthLargest(int k, int[] nums) {
        this.k = k;
        this.ans = new int[k];
        int len = nums.length;
        this.unfull = len == k-1;
        if(len==0){
            return;
        }
        int[] temp = new int[Math.min(k, len)];
        for(int i=0;i<temp.length;i++){
            temp[i] = nums[i];
        }
        Arrays.sort(temp);
        System.arraycopy(temp, 0, this.ans, this.unfull?1:0, temp.length);
        for(int i=k;i<len;i++){
            add(nums[i]);
        }
    }*/
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.ans = new int[k];
        int len = nums.length;
        this.unfull = len == k-1;
        if(len==0){
            return;
        }
        Arrays.sort(nums);
        System.arraycopy(nums, Math.max(0, len-k), this.ans, this.unfull?1:0, Math.min(k, len));
    }
    
    public int add(int val) {
        if(this.unfull){
            this.ans[0] = val;
            this.unfull = false;
        }else{
            if(val>this.ans[0]){
                this.ans[0] = val;
            }
        }
        int i=0;
        while(i<this.k-1&&this.ans[i]>this.ans[i+1]){
            int t = this.ans[i];
            this.ans[i] = this.ans[i+1];
            this.ans[i+1] = t;
            i++;
        }
        return this.ans[0];
    }
    
    public static void main(String[] args) {
        KthLargest kl = new KthLargest(3, new int[]{5,-1});
        System.out.println(kl.add(2));
        System.out.println(kl.add(1));
        System.out.println(kl.add(-1));
        System.out.println(kl.add(3));
        System.out.println(kl.add(4));
    }
}
