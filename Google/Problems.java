import java.util.*;

public class Problems {

  /*
    given preorder and postorder traversal of a binary tree, reconstruct this tree.
    assume there's always left child for each subtree except for leaf nodes.
  */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }  
  
  public static int rootIdx = 0;
  
  public TreeNode constructBinaryTreeWithPostAndPre(int[] pre, int[] post, int preIdx, int postL, 
                                                  int postR, Map<Integer, Integer> idxMap) {
    if (rootIdx >= pre.length || postL > postR) return null;
    TreeNode root = new TreeNode(pre[rootIdx]);
    rootIdx++;
    
    if (rootIdx >= pre.length || postL == postR) return root;
    int idx = idxMap.get(pre[rootIdx]);

    root.left = constructBinaryTreeWithPostAndPre(pre, post, preIdx+1, postL, idx, idxMap);
    root.right = constructBinaryTreeWithPostAndPre(pre, post, idx+1, idx+1, postR-1 , idxMap);
    return root;
  }
  
  /*
  given a group of people. Divide them into two groups in even. Each
  assignment has a cost. Find the min cost of arrangement.

  思路: 先假设都放进A组。对person i: costB[i] - costA[i] == i去B不去A的多余开销
  转化为选取n/2个多余开销,使得多余开销和最小 use priority queue to facilitate.
  */
  public int calcGroupCost(int[] costA, int[] costB) {
    if (costA == null || costA.length == 0 || costB == null || costB.length ==0) return 0;
    int delta = 0;
    int sum = 0;
    int len = costA.length;
    //int[i, delta]: i记录第几个人 delta记录costB[i]-costA[i] 
    //优先队列从小到大排序,每次选delta最小的去B(去B比去Acost小)
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      public int compare(int[] arr1, int[] arr2) {
        return arr1[1] - arr2[1];
      }
    });

    for (int i = 0; i < len; i++) {
      delta = costB[i] - costA[i];
      int[] pair = new int[] {i, delta};
      pq.offer(pair);
    }

    // 前len/2个人去B 
    for (int j = 0; j < len/2; j++) {
      sum += pq.peek()[1];
      sum += costA[pq.peek()[0]];
      pq.poll();
    }
    //后len/2个人留在A
    for (int k = len/2; k < len; k++) {
      sum += costA[pq.poll()[0]];
    }
    return sum;
  }
  
  /*
  一些人坐在椅子上，新来的人要坐在离两侧人尽量远的位置，要求在线算法.假设两面都是墙,也要尽可能远离墙
  思路:建立01数组:0代表空位,1代表有人坐下.每次进来新人,取目前最长连续0的中点返回即可.要求在线算法,
  动态维护最长0的区间->利用优先队列
  */
  public PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
    public int compare(int[] intervalA, int[] intervalB) {
      int lenB = intervalB[1] - intervalB[0] + 1;
      int lenA = intervalA[1] - intervalA[0] + 1;
      return lenB - lenA;
    }
  });
  public int[] seats;

  public void initSeats(int num) {
    seats = new int[] {0, num-1};
    pq.offer(seats);
  }

  public int getSeat() {
    if (seats == null || seats.length == 0) return -1;
    if (pq.size() == 0) return -1;

    int start = pq.peek()[0];
    int end = pq.peek()[1];
    int idx = (start + end) / 2;
    if (idx > start) {
      int[] left = new int[] {start, idx-1};
      pq.offer(left);
    }
    if (idx < end) {
      int[] right = new int[] {idx+1, end};
      pq.offer(right);
    }
    pq.poll();
    return idx;
  }

  /*
  给一个char stream,  有next(), 和hasNext(), 两个API。 给一些字符串作为目标字符串。
  要求每当char stream里出现目标字符串任何一个词，就打印这个词。
  比如目标 'abc, att, bba, bc, abce', 然后我们对char stream call next， 
  出来的一些char 是 t, a, b, c, e, t.... 我们需要打印 abc, bc, abce
  
	思路:AC自动机 Trie+KMP 代码见Algos路径下的StringAlgos 构造处出自动机即可 没别的…
  */  

  







  
  public static void main(String[] args) {
    Problems inst = new Problems();

    int[] pre = { 1, 2, 4, 8, 9, 5, 3, 6, 7 };
    int[] post = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < pre.length; i++) {
      for (int j = 0; j < post.length; j++) {
        if (pre[i] == post[j]) {
          map.put(pre[i], j);
        }
      }
    }

    TreeNode root = inst.constructBinaryTreeWithPostAndPre(pre, post, 0, 0, post.length-1, map);

    System.out.println(root.val);
  }
}