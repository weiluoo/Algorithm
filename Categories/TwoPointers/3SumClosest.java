/**
* Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
* 
*     For example, given array S = {-1 2 1 -4}, and target = 1.
* 
*     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

/*
复杂度
时间O(n^2) 空间O(1)

思路：三指针->双向夹逼
与3Sum套路一样 这里换成维护一个最小距离 当找到三个数能使与目标的距离更近时 更新最小距离
*/

public class 3SumClosest {
  public int threeSumClosest(int[] num, int target) {
    if (num == null || num.length <= 2) return Integer.MIN_VALUE;
		Arrays.sort(num);
		int minDiff = num[0] + num[1] + num[2] - target;
		for (int i = 0; i < num.length - 2; i++) {
			int subDiff = twoSum(num, target - num[i], i+1);
			if (Math.abs(subDiff) < Math.abs(minDiff)) {
				minDiff = subDiff;
			}
		}
		int closest = minDiff + target;
		return closest;
  }
	
  private int twoSum(int[] num, int target, int idx) {
    int minDiff = num[idx] + num[idx+1] - target;
		int left = idx;
		int right = num.length - 1;
		while (left < right) {
			if (num[left] + num[right] == target) {
				return 0;
			}
			int subDiff = num[left] + num[right] - target;
			if (Math.abs(subDiff) < Math.abs(minDiff)) {
				minDiff = subDiff;
			}
			if (subDiff > 0) {
				right--;
			} else {
				left++;
			}
		}
		return minDiff;
  }
} 	