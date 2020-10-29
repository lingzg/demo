package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution2 {

	/**
	 * 54. 螺旋矩阵
	 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if(matrix==null || matrix.length==0 || matrix[0].length==0){
			return res;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		
		return res;
    }
	
	/**
	 * 69. x 的平方根
	 * 实现 int sqrt(int x) 函数。
	 * 计算并返回 x 的平方根，其中 x 是非负整数。
	 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if(x==0){
			return 0;
		}
		double k=x,x0=x;
		while(true){
			double xi = 0.5*(x0+k/x0);
			if(Math.abs(xi-x0)<1e-7){
				break;
			}
			x0=xi;
		}
		return (int) x0;
    }
	
	/**
	 * 70. 爬楼梯
	 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
	 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		int a=0,b=1;
		int res=0;
		for(int i=1;i<=n;i++){
			res = a+b;
			a=b;
			b=res;
		}
		return res;
    }
	
	/**
	 * 73. 矩阵置零
	 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		int r = matrix.length;
		int c = matrix[0].length;
		boolean firstCol=false;//标记第一列是否置0
		for(int i=0;i<r;i++){
			if(matrix[i][0]==0){
				firstCol=true;
			}
			for(int j=1;j<c;j++){
				if(matrix[i][j]==0){
					matrix[i][0]=0;
					matrix[0][j]=0;
				}
			}
		}
		for(int i=1;i<r;i++){
			for(int j=1;j<c;j++){
				if(matrix[i][0]==0||matrix[0][j]==0){
					matrix[i][j]=0;
				}
			}
		}
		if(matrix[0][0]==0){//首行置0
			for(int i=1;i<c;i++){
				matrix[0][i]=0;
			}
		}
		if(firstCol){//首列置0
			for(int i=0;i<r;i++){
				matrix[i][0]=0;
			}
		}
    }
	
	/**
	 * 74. 搜索二维矩阵
	 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
	 * 每行中的整数从左到右按升序排列。
	 * 每行的第一个整数大于前一行的最后一个整数。
	 * i = x/n,j=x%n
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {;
		if(matrix==null || matrix.length==0 || matrix[0].length==0){
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int l = 0,r=m*n-1;
		while(l<r){
			int mid = l+(r-l)/2;
			if(matrix[mid/n][mid%n]<target){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		if(l<m*n && matrix[l/n][l%n]==target){
			return true;
		}
		return false;
    }
	
	/**
	 * 75. 颜色分类
	 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
	 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
	 * @param nums
	 */
	public void sortColors(int[] nums) {
		/*int[] buf = new int[3];
		for(int num : nums){
			buf[num]++;
		}
		for(int i=0;i<nums.length;i++){
			if(i<buf[0]){
				nums[i]=0;
			}else if(i<buf[0]+buf[1]){
				nums[i]=1;
			}else{
				nums[i]=2;
			}
		}*/
		int p0=0,p2=nums.length-1,cur=0;
		while(cur<=p2){
			if(nums[cur]==0){
				int t=nums[p0];
				nums[p0]=nums[cur];
				nums[cur]=t;
				cur++;
				p0++;
			}else if(nums[cur]==2){
				int t=nums[p2];
				nums[p2]=nums[cur];
				nums[cur]=t;
				p2--;
			}else{
				cur++;
			}
		}
    }
	
	/**
	 * 77. 组合
	 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(n<1 ||k<1){
			return res;
		}
		Stack<Integer> stk = new Stack<Integer>();
		backtrack(n, k, stk, res);
		return res;
    }
	private void backtrack(int n,int k, Stack<Integer> stk,List<List<Integer>> res){
		if(stk.size()==k){
			res.add(new ArrayList<Integer>(stk));
			return;
		}
		int start = stk.isEmpty()? 1 : stk.peek()+1;
		for(int i=start;i<=n;i++){
			stk.push(i);
			backtrack(n, k, stk, res);
			stk.pop();
		}
	}
	
	/**
	 * 78. 子集
	 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
	 * 说明：解集不能包含重复的子集
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums==null){
			return res;
		}
		res.add(new ArrayList<Integer>());
		if(nums.length==0){
			return res;
		}
		Stack<Integer> stk = new Stack<Integer>();
		backtrack(nums, 0, stk, res);
		return res;
    }
	private void backtrack(int[] nums,int index, Stack<Integer> stk,List<List<Integer>> res){
		if(stk.size()==nums.length){
			return;
		}
		for(int i=index;i<nums.length;i++){
			stk.push(nums[i]);
			res.add(new ArrayList<Integer>(stk));
			backtrack(nums, i+1, stk, res);
			stk.pop();
		}
	}
	
	/**
	 * 79. 单词搜索
	 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
	 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		int m=board.length,n=board[0].length;
		boolean[][] used = new boolean[m][n];
		int index = 0;
		List<int[]> selectList = new ArrayList<int[]>();
		char cur = word.charAt(0);
		Character next = word.length()>1 ? word.charAt(1) : null;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(board[i][j]==cur){
					if(next!=null){
						if(i>0&&board[i-1][j]==next || j<n-1&&board[i][j+1]==next || i<m-1&&board[i+1][j]==next || j>0&&board[i][j-1]==next){
							selectList.add(new int[]{i,j});
						}
					}else{
						selectList.add(new int[]{i,j});
					}
				}
			}
		}
		while(index<word.length()){
			
		}
		return false;
    }
	private void search(char[][] board,boolean[][] used, char cur, Character next){
		
	}
	
	/**
	 * 84. 柱状图中最大的矩形
	 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
	 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
		if(heights==null || heights.length==0){
			return 0;
		}
		int area = 0;
		int[] temp = new int[heights.length+2];
		System.arraycopy(heights, 0, temp, 1, heights.length);
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<temp.length;i++){
			while(!stack.isEmpty()&&temp[i]<temp[stack.peek()]){
				int h = temp[stack.pop()];
				int w = i-stack.peek()-1;
				area = Math.max(area, h*w);
			}
			stack.push(i);
		}
		return area;
    }
	
	/**
	 * 85. 最大矩形
	 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length==0 || matrix[0].length==0){
			return 0;
		}
		int maxArea = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] dq = new int[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(matrix[i][j]=='0'){
					continue;
				}
				dq[i][j] = j==0 ? 1 : dq[i][j-1]+1;
				int width = dq[i][j];
				for(int k=i;k>=0;k--){
					width = Math.min(width, dq[k][j]);
					maxArea = Math.max(maxArea, width*(i-k+1));
				}
			}
		}
		return maxArea;
    }
	
    /**
     * 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * @param p
     * @param q
     * @return
     */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
        	return true;
        }
        if(p==null || q==null){
        	return false;
        }
        return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    
    public static void main(String[] args) {
        Solution2 s = new Solution2();
//		System.out.println(s.isSameTree(new TreeNode(1,new TreeNode(2),new TreeNode(3)), new TreeNode(1,new TreeNode(2),new TreeNode(3))));
//		System.out.println(s.isSameTree(new TreeNode(1,null,new TreeNode(2)), new TreeNode(1,new TreeNode(2),null)));
//		System.out.println(s.isSameTree(new TreeNode(1,new TreeNode(2),new TreeNode(1)), new TreeNode(1,new TreeNode(1),new TreeNode(2))));
//        System.out.println(s.mySqrt(3));
//        System.out.println(s.climbStairs(5));
//        System.out.println(s.largestRectangleArea(new int[]{2,1,5,6,2,3}));
//        char[][] matrix={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        System.out.println(s.maximalRectangle(matrix));
//        System.out.println(s.combine(4, 2));
        System.out.println(s.subsets(new int[]{1,2,3}));
    }
    
}
