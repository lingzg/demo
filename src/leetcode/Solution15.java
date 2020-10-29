package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Solution15 {

    /**
     * 739. 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<T.length;i++){
            while(!stack.isEmpty() && T[i]>T[stack.peek()]){
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
    
    public static void main(String[] args) {
        Solution15 s = new Solution15();
      int[] res = s.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
      System.out.println(Arrays.toString(res));
    }
}
