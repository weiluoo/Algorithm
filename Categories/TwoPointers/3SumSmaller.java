/**
* Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
* 
* For example, given nums = [-2, 0, 1, 3], and target = 2.
* 
* Return 2. Because there are two triplets which sums are less than 2:
* 
* [-2, 0, 1]
* [-2, 0, 3]
* Follow up:
* Could you solve it in O(n2) runtime?
*/

/*
复杂度
时间：O(n^2) 空间：O(1)

思路：三指针
维护三个指针i, j, k,先对输入数组排序,方便跳过重复元素
对k -> [0, len-3]:
  i = k + 1， j = len - 1; 
  i,j 双指针相对而行, 如果i，j，k指向的数相加小于target,则j取[i+1, j]之间的所有组合都满足要求,
  因为j最大,最大的可以,比它小的也肯定满足可以.更新cnt后直接把i指针右移即可.   
*/ 

public class ThreeSumSmaller {
  public int threeSumSmaller(int[] nums, int target) {
    if (nums == null || nums.length == 0) return 0;
	  Arrays.sort(nums); 
	  int len = nums.length;
	  int cnt = 0;
	
	  for (int k = 0; k < len-2; k++) {
	    int i = k + 1;
      int j = len - 1;
      while (i < j) {
	  	  int sum = nums[k] + nums[i] + nums[j];
	  	  if (sum >= target) {
	  	    j--;
	  	  } else {
	  	    cnt += j - i;
	  		  i++;
	  	  }
	    }	  
	  }
	  return cnt;
  }
}
