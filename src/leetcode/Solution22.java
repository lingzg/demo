package leetcode;

import java.util.Arrays;

public class Solution22 {

	/**
     * 1051. 高度检查器 
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列 请你返回能让所有学生以 非递减高度排列的最小必要移动人数。 
     * 注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。
     * 
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int[] arr = new int[heights.length];
        System.arraycopy(heights, 0, arr, 0, heights.length);
        Arrays.sort(arr);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != arr[i]) {
                res++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        Solution22 s = new Solution22();
    }
}
