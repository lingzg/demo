package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution3 {

	/**
	 * 101. 对称二叉树
	 * 给定一个二叉树，检查它是否是镜像对称的。
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
        if(root==null){
        	return true;
        }
        if(root.left==null&&root.right==null){
        	return true;
        }
        if(root.left==null&&root.right!=null || root.left!=null&&root.right==null){
        	return false;
        }
        return root.left.val == root.right.val && isSymmetric(root.left) && isSymmetric(root.right);
    }
	
	/**
	 * 104. 二叉树的最大深度
	 * 给定一个二叉树，找出其最大深度。
	 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		if(root.left==null&&root.right==null){
			return 1;
		}
		int m1 = maxDepth(root.left);
		int m2 = maxDepth(root.right);
		if(root.left==null||root.right==null){
			return m1+m2+1;
		}
		return Math.max(m1, m2)+1;
    }

	/**
	 * 111. 二叉树的最小深度
	 * 给定一个二叉树，找出其最小深度。
	 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		if(root.left==null&&root.right==null){
			return 1;
		}
		/*Stack<Integer> output = new Stack<Integer>();
		List<Integer> res = new ArrayList<Integer>();
		backtrack(root, output, res);
		return res.get(0);*/
		int m1 = minDepth(root.left);
		int m2 = minDepth(root.right);
		if(root.left==null||root.right==null){
			return m1+m2+1;
		}
		return Math.min(m1, m2)+1;
    }
	
	/**
	 * 112. 路径总和
	 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root==null){
			return false;
		}
		/*Stack<Integer> output = new Stack<Integer>();
		backtrack(root, output, sum);
		return flag;*/
		if(root.left==null&&root.right==null){
			return root.val==sum;
		}
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
	private void backtrack(TreeNode node, Stack<Integer> output, List<Integer> res){
		output.push(node.val);
		if(node.left==null&&node.right==null){
			int size = output.size();
			if(res.isEmpty()||res.get(0)< size){
				res.add(size);
			}else{
				res.add(0,size);
			}
			return;
		}
		if(node.left!=null){
			backtrack(node.left, output, res);
			output.pop();
		}
		if(node.right!=null){
			backtrack(node.right, output, res);
			output.pop();
		}
	}
	
	/**
	 * 118. 杨辉三角
	 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Integer[] curr = {};
		for(int i=0;i<numRows;i++){
			List<Integer> next = new ArrayList<Integer>(curr.length+1);
			next.add(0, 1);
			for(int j=0;j<curr.length-1;j++){
				next.add(j+1, curr[j]+curr[j+1]);
			}
			if(curr.length>0){
				next.add(curr.length, 1);
			}
			res.add(next);
			curr = next.toArray(curr);
		}
		return res;
    }
	
	/**
	 * 119. 杨辉三角 II
	 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
	 * @param rowIndex
	 * @return
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<Integer>(rowIndex+1);
		/*Integer[] curr = {1};
		res.add(1);
		for(int i=0;i<rowIndex;i++){
			res.add(0, 1);
			for(int j=0;j<curr.length-1;j++){
				res.set(j+1, curr[j]+curr[j+1]);
			}
			curr = res.toArray(curr);
		}*/
		long pre = 1;
		res.add(1);
		for(int k=1;k<=rowIndex;k++){
			long curr = pre*(rowIndex-k+1)/k;
			res.add((int) curr);
			pre = curr;
		}
		return res;
    }
	
	/**
	 * 125. 验证回文串
	 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
	 * 说明：本题中，我们将空字符串定义为有效的回文串。
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s) {
		int i=0,j=s.length()-1;
		while(i<j){
			while(i<s.length()-1 && !Character.isLetterOrDigit(s.charAt(i))){
				i++;
			}
			while(j>0 && !Character.isLetterOrDigit(s.charAt(j))){
				j--;
			}
			if(i>=j){
				break;
			}
			if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
				return false;
			}
			i++;
			j--;
		}
		/*s = s.replaceAll("[^a-z|A-Z|0-9]", "").toLowerCase();
		int len = s.length();
		for(int i=0;i<len/2;i++){
			if(s.charAt(i)!=s.charAt(len-1-i)){
				return false;
			}
		}*/
		return true;
    }
	
	/**
	 * 136. 只出现一次的数字
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		int res  = 0;
		for(int n : nums){
			res ^= n;
		}
		return res;
    }
	
	/**
	 * 137. 只出现一次的数字 II
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现三次。找出那个只出现了一次的元素
	 * @param nums
	 * @return
	 */
	public int singleNumber2(int[] nums) {
		/*int seenOnce = 0, seenTwice = 0;

	    for (int num : nums) {
	      // first appearence: 
	      // add num to seen_once 
	      // don't add to seen_twice because of presence in seen_once

	      // second appearance: 
	      // remove num from seen_once 
	      // add num to seen_twice

	      // third appearance: 
	      // don't add to seen_once because of presence in seen_twice
	      // remove num from seen_twice
	      seenOnce = ~seenTwice & (seenOnce ^ num);
	      seenTwice = ~seenOnce & (seenTwice ^ num);
	    }

	    return seenOnce;*/
	    
	    int[] counts = new int[32];
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }
	
	public static void main(String[] args) {
		Solution3 s = new Solution3();
//		System.out.println(s.isPalindrome(""));
//		System.out.println(s.generate(5));
//		System.out.println(s.getRow(4));
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2,new TreeNode(3),new TreeNode(4));
		root.right = new TreeNode(2,new TreeNode(4),new TreeNode(3));
		root.left.left.left = new TreeNode(1);
//		System.out.println(s.hasPathSum(root, 22));
//		System.out.println(s.minDepth(root));
//		System.out.println(s.isSymmetric(root));
//		System.out.println(s.maxDepth(root));
//		System.out.println(s.singleNumber(new int[]{1,2,4,3,2,3,1}));
		System.out.println(s.singleNumber2(new int[]{1,2,1,4,2,3,2,3,1,3}));
	}
}
