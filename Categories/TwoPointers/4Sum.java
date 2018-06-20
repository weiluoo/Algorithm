/**
* Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
* 
* Note: The solution set must not contain duplicate quadruplets.
* 
* For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
* 
* A solution set is:
* [
*   [-1,  0, 0, 1],
*   [-2, -1, 1, 2],
*   [-2,  0, 0, 2]
* ]
*/

/*
solution 1
复杂度
时间O(n^3) 空间O(1)

思路：N指针 N=4 
3sum的扩展 外层再加一个循环->做N次3Sum 
*/

public class 4Sum {
	public List<List<Integer>> fourSum(int[] num, int target) {
	  List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (num == null || num.length <= 3) return res;
		Arrays.sort(num);
		for (int i = num.length - 1; i > 2; i--) {
			if (i == num.length - 1 || num[i] != num[i+1]) {
				List<List<Integer>> subRes = threeSum(num, i-1, target-num[i]);
				for (int j = 0; j < subRes.size(); j++) {
					subRes.get(j).add(num[i]);
				}
				res.addAll(subRes);
			}
		}
		return res;
	}
	
	private List<List<Integer>> threeSum(int[] num, int idx, int target) {
	  List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (num == null || num.length <= 2) return res;
		Arrays.sort(num);
		for (int i = idx; i > 1; i--) {
			if (i == idx || num[i] != num[i+1]) {
				List<List<Integer>> subRes = twoSum(num, i-1, target-num[i]);
				for (int j = 0; j < subRes.size(); j++) {
					subRes.get(j).add(num[i]);
				}
				res.addAll(subRes);
			}
		}
		return res;
	}
	
	private List<List<Integer>> twoSum(int[] num, int idx, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int left = 0;
		int right = idx;
		while (left < right) {
			if (num[left] + num[right] == target) {
				List<Integer> subRes = new ArrayList<Integer>();
				subRes.add(num[left]);
				subRes.add(num[right]);
				res.add(subRes);
				left++;
				right--;
				while (left < right && num[left] == num[left-1]) {
				  left++;
				}
				while (left < right && num[right] == num[right+1]) {
				  right--;
				}
			} else if (num[left] + num[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}

/*
solution 2
复杂度
时间O(n^2 * logn) 空间O(1)

思路：二分 +　twoSum
把所有的两两pair都求出来，然后再进行一次Two Sum的匹配，已知Two Sum是一个排序加上一个线性的操作，并且把所有pair的数量是O((n-1)+(n-2)+...+1)=O(n(n-1)/2)=O(n^2)
所以对O(n^2)的排序如果不用特殊线性排序算法是O(n^2*log(n^2))=O(n^2*2logn)=O(n^2*logn)->复杂度比解法1好一点
实现细节上很多地方需要处理：
首先，我们要对每一个pair建一个数据结构来存储元素的值和对应的index，这样做是为了后面当找到合适的两对pair相加能得到target值时看看他们是否有重叠的index，
如果有说明它们不是合法的一个结果，因为不是四个不同的元素。接下来我们还得对这些pair进行排序，所以要给pair定义comparable的函数
最后，当进行Two Sum的匹配时因为pair不再是一个值，所以不能像Two Sum中那样直接跳过相同的，每一组都得进行查看，这样就会出现重复的情况，所以我们还得给每一个四个元素组成的tuple定义hashcode和相等函数，
以便可以把当前求得的结果放在一个HashSet里面，这样得到新结果如果是重复的就不加入结果集了

推广：K-sum
利用第二种方法的思想 进行二分处理 然后两两结合->细节处理相当复杂 
*/

