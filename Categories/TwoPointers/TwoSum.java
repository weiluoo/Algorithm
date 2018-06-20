/**
* Given an array of integers, return indices of the two numbers such that they add up to a specific target.
* 
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* 
* Example:
* Given nums = [2, 7, 11, 15], target = 9,
* 
* Because nums[0] + nums[1] = 2 + 7 = 9,
* return [0, 1].
*/

/* solution 1: hash map + one pointer  
   maintain a hashMap to keep track of index of nums[i] by <nums[i], index[i]> 	
   a pointer iterate through the array to find if -nums[i] exists in hash map
   T(n) = O(n), S(n) = O(n)   
*/ 
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int valToFind = target - nums[i];
			if (map.containsKey(valToFind)) {
				int index = map.get(valToFind);
				return new int[] {i, index};
		}
			map.put(nums[i], i);
		}
		return null;
	}
}

/*
solution 2
复杂度
时间O(nlogn) 空间(n)

思路：排序+双指针
*/
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
    int[] copyNums = new int[nums.length];
    System.arraycopy(nums, 0, copyNums, 0, nums.length);
		Arrays.sort(copyNums);
		int left = 0;
		int right = copyNums.length - 1;
		int nums1 = 0;
		int nums2 = 0;
		while (left < right) {
		  if (copyNums[left] + copyNums[right] == target) {
			  nums1 = copyNums[left];
				nums2 = copyNums[right];
				break;
			}	else if (copyNums[left] + copyNums[right] < target) {
			  left++;
			} else {
			  right--;
			}
		}
		left = -1;
		right = -1;
		for (int i = 0; i < nums.length; i++) {
		  if (left == -1 && nums[i] == nums1) {
			  left = i;
				continue;
			} 
			if (right == -1 && nums[i] == nums2) {
				right = i;
			}
		}
		return new int[] {left, right};
	}
}
