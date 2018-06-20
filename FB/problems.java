import java.util.*;

public class Problems {
  
  /*
  move zeros
  给一个数组 把0都移到后面 要求:
  1.其他非零数字相对位置不能改变
  2.最小化写的次数
  
  two pointers, pointer i to first zero element, 
  pointer j to following first non-zero element.
  注意指针移动时边界条件的判断 -> 是否越界！
  */
  public void moveZeros(int[] nums) {
    if (nums == null || nums.length == 0) return;
    int i = 0, j = 0;
    while (i < nums.length && j < nums.length) {
      if (nums[i] == 0) {
        while (nums[j] == 0 && j < nums.length) j++;
        if (j == nums.length) break;
        nums[i] = nums[j];
        nums[j] = 0;
      }
      i++;
      j++;
    }
  }
  
  
  public void dfs(int[] nums, List<String> res, StringBuilder tmp, int idx, int target) throws ScriptException {
  if (idx == nums.length) {
    System.out.println(" return! " + tmp);
    /*
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    if ((int)engine.eval(tmp.toString()) == target) {
      res.add(tmp.toString());
      return;
    }
    */
    return;
  }

    // no operator, directly append
    tmp.append(nums[idx]);
  System.out.print("  no op before " + tmp);
    dfs(nums, res, tmp, idx+1, target);
    System.out.print(" no op after " + tmp);

    // operator '+'
    tmp.append('+');
    System.out.print(" plus before " + tmp);
    dfs(nums, res, tmp, idx+1, target);
    tmp.deleteCharAt(tmp.length()-1);
    System.out.println("  plus after " + tmp);

    /*
    tmp.append('-');
    dfs(nums, res, tmp, i+1, target);
    tmp.deleteCharAt(tmp.length()-1);
    */
  }
  
  
  
  
  
  
  
  

  
  
  
  public static void main (String[] args) {
    Problems inst = new Problems();
    int[] nums = {0,0,0,0,1,2,3,4};
    inst.moveZeros(nums);
    for(int num : nums) {
      System.out.print(num + " ");
    }

  } 
}
