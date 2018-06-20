/**
* Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
* 
* For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
* 
* Note:
* You must do this in-place without making a copy of the array.
* Minimize the total number of operations.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路：
two pointers, one point to first zero element, 
second point to following first non-zero element.

注意指针移动时边界条件的判断,每次移动之后都检查是否到达边界
*/

public class MoveZeroes {
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length <= 1) return;
  	int idxZero = 0;
  	int nonZero = 0;
  	while (nonZero < nums.length) {
  	  // find first zero element
  	  while (idxZero < nums.length && nums[idxZero] != 0) {
  		  idxZero++;
  	  }
  	  if (idxZero == nums.length) return; // no zero elements found
  	  nonZero = idxZero + 1;
  	  // find following first non-zero element
  	  while (nonZero < nums.length && nums[nonZero] == 0) {
  		  nonZero++;
  	  }
  	  if (nonZero == nums.length) return;
  	  swap(nums, idxZero, nonZero);
  	}
  }
	
	private void swap(int[] nums, int i, int j) {
	  int tmp = nums[i];
	  nums[i] = nums[j];
	  nums[j] = tmp;
	}
} 
