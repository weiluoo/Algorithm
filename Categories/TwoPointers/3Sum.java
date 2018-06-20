/**
* Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
* 
* Note: The solution set must not contain duplicate triplets.
* 
* For example, given array S = [-1, 0, 1, 2, -1, -4],
* 
* A solution set is:
* [
*   [-1, 0, 1],
*   [-1, -1, 2]
* ]
*/

/*
复杂度
时间 O(n^2) 空间O(n)

思路： 三指针 
利用2Sum为subrutine + 排序后指针相向移动 
因为数组元素可能有重复 所以要先排序O(nlogn)
*/

public class 3Sum {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 2) return res;
		Arrays.sort(nums);
		for (int i = nums.length - 1; i >= 2; i--) {
			if (i < nums.length - 1 && nums[i] == nums[i+1]) continue;
			List<List<Integer>> curRes = twoSum(nums, i-1, -nums[i]);
			for (int j = 0; j < curRes.size(); j++) {
				curRes.get(j).add(nums[i]);
			}
			res.addAll(curRes);
		}
		return res;
  }
	// twoSum as subroutine 
	private List<List<Integer>> twoSum(int[] nums, int end, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 1) return res;
		int left = 0;
		int right = end;
		while (left < right) {
			if (nums[left] + nums[right] == target) {
				List<Integer> curList = new ArrayList<Integer>();
				curList.add(nums[left]);
				curList.add(nums[right]);
				res.add(curList);
				left++;
				right--;
				while (left < right && nums[left] == nums[left-1]) {
					left++;
				}
				while (left < right && nums[right] == nums[right+1]) {
					right--;
				}
			} else if (nums[left] + nums[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
} 