import java.util.*;

public class Problems {
  
  /*
  给一个正整数n, 要求返回长度为2n的list，且list满足如下条件:
  1.每个数字都要用上
  2.两个相同数字之间隔着的数字恰好为i个,i为这个数字大小.
  e.g: input == 3 -> return {3 1 2 1 3 2}
  
  思路：排列组合类型变种,对每个位置枚举所有可能的数字.注意一次放置就要放两个位置.
  */
  public List<Integer> generateTwoNList(int n) {
    if (n == 1) return null;
    //结果长度固定 直接用数组做
    int[] temp = new int[2*n];
    boolean[] used = new boolean[n+1];
    List res = new ArrayList();
    dfs(n, temp, res, 0, used, 0);
    return res;
  }
  
  private void dfs(int n, int[] temp, List res, int start, boolean[] used, int marked) {
    // 用表计量marked来判断是否所有位置都已经放置了数字
    // 因为是数组,所以无法用长度来判断是否所有数字都摆放好了
    if (marked == 2*n) {
      res = Arrays.asList(temp);
      return;
    }
    // 如果没有在上面返回,则此处改回溯了(找不到可行解法)
    if (start == 2*n) return;
    
    // 对当前位置start,枚举[1,n]所有数字
    for (int i = 1; i <= n; i++) {
      // 剪枝 注意这里的剪枝条件和顺序！顺序！顺序！先判断是否数组下表合法 再判断下标位置是否已经填了数字
      if (!used[i] && temp[start] == 0 && start+1+i < 2*n && temp[start+1+i] == 0) {
        temp[start] = i;
        temp[start+1+i] = i;
        used[i] = true;
        marked += 2;
        dfs(n, temp, res, start+1, used, marked);
        // 注意！回溯回来要把所有的变量都重置成之前的状态 包含marked!
        temp[start] = 0;
        temp[start+1+i] = 0;
        used[i] = false;
        marked -= 2;
      }
    }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
