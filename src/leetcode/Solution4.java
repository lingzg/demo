package leetcode;

public class Solution4 {

	/**
	 * 167. 两数之和 II - 输入有序数组
	 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
	 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		int i=0,j=numbers.length-1;
		while(i<j){
			int sum = numbers[i]+numbers[j];
			if(sum==target){
				break;
			}else if(sum>target){
				j--;
			}else{
				i++;
			}
		}
		return new int[]{i+1,j+1};
    }
	
	/**
	 * 168. Excel表列名称
	 * 给定一个正整数，返回它在 Excel 表中相对应的列名称
	 * @param n
	 * @return
	 */
	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		do{
			int a = n%26;
			n /= 26;
			char c = (char) ('A'+a-1);
			if(a==0){
				c = 'Z';
				n -= 1;
			}
			sb.append(c);
		}while(n>0);
		return sb.reverse().toString();
    }
	
	/**
     * 172. 阶乘后的零 
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。 说明: 你算法的时间复杂度应为 O(log n) 。
     * 
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res = 0;
        /*long x = 5;
        while (x <= n) {
            res += n / x;
            x *= 5;
        }*/
        while (n > 0) {
            n /= 5;
            res += n;
        }
        return res;
    }
	
	public static void main(String[] args) {
		Solution4 s = new Solution4();
		System.out.println(s.convertToTitle(801));
	}
}
