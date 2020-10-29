package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Solution5 {

	/**
	 * 202. 快乐数
	 * 编写一个算法来判断一个数 n 是不是快乐数。
	 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
	 * 如果 可以变为  1，那么这个数就是快乐数。
	 * @param n
	 * @return
	 */
	public boolean isHappy(int n) {
        if(n<=0){
        	return false;
        }
        Set<Integer> nums = new HashSet<Integer>();
        nums.add(n);
        while(true){
        	int sum=0;
        	while(n>0){
        		int d = n%10;
        		sum+=d*d;
        		n/=10;
        	}
        	n=sum;
        	if(n==1){
        		return true;
        	}
        	if(nums.contains(n)){
        		return false;
        	}
        	nums.add(n);
        }
    }
	
	/**
	 * 204. 计数质数
	 * 统计所有小于非负整数 n 的质数的数量。
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		for(int i=2;i*i<n;i++){
			if(!notPrime[i]){
				for(int j=i*i;j<n;j+=i){
					notPrime[j]=true;
				}
			}
		}
		int count=0;
		for(int i=2;i<n;i++){
			if(!notPrime[i]){
				count++;
			}
		}
		return count;
    }
	private boolean isPrime(int n){
		for(int i=2,max = (int) Math.sqrt(n);i<=max;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 205. 同构字符串
	 * 给定两个字符串 s 和 t，判断它们是否是同构的。
	 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
	 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isIsomorphic(String s, String t) {
        return mapping(s).equals(mapping(t));
    }
	private String mapping(String s){
		StringBuilder sb = new StringBuilder();
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		for(int i=0,len = s.length();i<len;i++){
			char c = s.charAt(i);
			if(!map.containsKey(c)){
				map.put(c, i+1);
			}
			sb.append(map.get(c));
		}
		return sb.toString();
	}
	
	/**
	 * 216. 组合总和 III
	 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
	 * 说明:所有数字都是正整数。解集不能包含重复的组合。
	 * @param k
	 * @param n
	 * @return
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(k<1 || k>9 || n>(19-k)*k/2 || n<(1+k)*k/2){
			return res;
		}
		Stack<Integer> nums = new Stack<Integer>();
		backtrack(k,n,1,nums, res);
		return res;
    }
	private void backtrack(int k, int sum,int r,Stack<Integer> nums,List<List<Integer>> res){
		if(k==0){
			if(sum==0){
				res.add(new ArrayList<Integer>(nums));
			}
			return;
		}
		for(int i=r;i<=9;i++){
			nums.push(i);
			backtrack(k-1,sum-i,i+1,nums, res);
			nums.pop();
		}
	}
	
	/**
	 * 217. 存在重复元素
	 * 给定一个整数数组，判断是否存在重复元素。
	 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for(int num : nums){
			if(set.contains(num)){
				return true;
			}
			set.add(num);
		}
		return false;
    }
	
	/**
	 * 218. 天际线问题
	 * @param buildings
	 * @return
	 */
	public List<List<Integer>> getSkyline(int[][] buildings) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		return res;
    }
	
	/**
	 * 219. 存在重复元素 II
	 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		/*Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<nums.length;i++){
			Integer j = map.get(nums[i]);
			if(j!=null&&i-j<=k){
				return true;
			}
			map.put(nums[i], i);
		}*/
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<nums.length;i++){
			if(set.contains(nums[i])){
				return true;
			}
			set.add(nums[i]);
			if(set.size()>k){
				set.remove(nums[i-k]);
			}
		}
		return false;
    }
	
	/**
	 * 220. 存在重复元素 III
	 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
	 * 如果存在则返回 true，不存在返回 false。
	 * @param nums
	 * @param k
	 * @param t
	 * @return
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if(t<0){
			return false;
		}
		TreeSet<Long> set = new TreeSet<Long>();
		for(int i=0;i<nums.length;i++){
			Long ceiling = set.ceiling((long)nums[i]-(long)t);
			if(ceiling!=null && ceiling <= (long)nums[i]+(long)t){
				return true;
			}
			set.add((long) nums[i]);
			if(set.size()>k){
				set.remove((long)nums[i-k]);
			}
		}
		return false;
    }
	
	/**
	 * 221. 最大正方形
	 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
	 * @param matrix
	 * @return
	 */
	public int maximalSquare(char[][] matrix) {
		if(matrix == null || matrix.length==0 || matrix[0].length==0){
			return 0;
		}
		int maxSide = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] dq = new int[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(matrix[i][j]=='1'){
					if(i==0||j==0){
						dq[i][j]=1;
					}else{
						dq[i][j]=Math.min(dq[i-1][j], Math.min(dq[i-1][j-1], dq[i][j-1]))+1;
					}
					maxSide = Math.max(maxSide, dq[i][j]);
				}
			}
		}
		return maxSide*maxSide;
    }
	
	/**
	 * 231. 2的幂
	 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
	 * @param n
	 * @return
	 */
	public boolean isPowerOfTwo(int n) {
		if(n<=0){
			return false;
		}
		int res = n&(n-1);
		return res == 0;
    }
	
	/**
	 * 233. 数字 1 的个数
	 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
	 * @param n
	 * @return
	 */
	public int countDigitOne(int n) {
		int count = 0;

	    for (long k = 1; k <= n; k *= 10) {
	        long r = n / k, m = n % k;
	        // sum up the count of ones on every place k
	        count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
	    }

	    return count;
    }
	private int combination(int n, int k){ // Cnk
		int res = 1;
		if(n==k || k==0){
			return res;
		}
		for(int i=n;i>=n-k+1;i--){
			res*=i;
		}
		for(int i=k;i>=2;i--){
			res/=i;
		}
		return res;
	}
	
	/**
	 * 238. 除自身以外数组的乘积
	 * @param nums
	 * @return
	 */
	public int[] productExceptSelf(int[] nums) {
		int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }
	
	/**
	 * 334. 反转字符串
	 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
	 * @param s
	 */
	public void reverseString(char[] s) {
		char t;
		for(int i=0;i<s.length/2;i++){
			t = s[i];
			s[i]=s[s.length-1-i];
			s[s.length-1-i]=t;
		}
    }
	
	/**
	 * 557. 反转字符串中的单词 III
	 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		StringBuilder res = new StringBuilder();
		int start=0;
		while(start<s.length()){
			int end = s.indexOf(" ", start);
			if(end==-1){
				end = s.length();
			}
			if(start!=0){
				res.append(" ");
			}
			for(int i=start;i<end;i++){
				res.append(s.charAt(end-1+start-i));
			}
			start = end+1;
		}
		return res.toString();
    }

	public static void main(String[] args) {
		Solution5 s = new Solution5();
//		System.out.println(s.isHappy(999999999));
//		System.out.println(s.countPrimes(100));
//		System.out.println(s.isIsomorphic("abab", "egge"));
//		System.out.println(s.isPowerOfTwo(Integer.MAX_VALUE/2+1));
//		System.out.println(s.countDigitOne(3184191));//100 180 20 8 1
//		System.out.println(s.combinationSum3(3, 9));
		/*
		 * 1 0 1 0 0
		 * 1 0 1 1 1
		 * 1 1 1 1 1
		 * 1 0 0 1 0
		 */
//		char[][] matrix={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//		System.out.println(s.maximalSquare(matrix));
//		int[] res = s.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
//		System.out.println(Arrays.toString(res));
//		System.out.println(s.reverseWords("Let's take LeetCode contest"));
		System.out.println(0^3);
		System.out.println(Arrays.toString(s.productExceptSelf(new int[]{1,2,3,4})));
	}
}
