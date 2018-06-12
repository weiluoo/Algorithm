/*
线段树是完全二叉树(不一定满) 它在各个节点保存一条线段（数组中的一段子数组）
$%主要用于高效解决连续区间的动态查询问题%$
由于二叉结构的特性 它基本能保持每个操作的复杂度为O(lgN)
性质:父亲的区间是[a,b],(c=(a+b)/2)左儿子的区间是[a,c]
右儿子的区间是[c+1,b]

主要应用
1)：区间最值查询问题
2)：连续区间修改或者单节点更新的动态查询问题
3)：多维空间的动态查询

reference link:
https://blog.csdn.net/whereisherofrom/article/details/78969718
https://blog.csdn.net/u013174702/article/details/44161821
*/

import java.util.*;

public class SegTree {
  public class Node {
    int left, right; //左右区间的值
    int val; //区间上的目标值(最大,最小等)
    Node leftChild, rightChild;

    public Node(int left, int right, int val) {
      this.left = left;
      this.right = right;
      this.val = val;
    }
  }

  private Node root;

  //根据输入数组建立线段树
  public void buildMin(int[] nums) {
    root = buildMin(nums, 0, nums.length - 1);
  }

  private Node buildMin(int[] nums, int lo, int hi) {
    if (hi < lo) return null;
    if (hi == lo) {
      Node root = new Node(lo, hi, nums[lo]);
      return root;
    }

    int mid = lo + (hi - lo) / 2;
    Node leftNode = buildMin(nums, lo, mid);
    Node rightNode = buildMin(nums, mid+1, hi);
    /*
    此处定义区间操作为找最小值.同理可以改成找最大等操作
     */
    int min = Math.min(leftNode.val, rightNode.val);
    Node root = new Node(lo, hi, min);
    root.leftChild = leftNode;
    root.rightChild = rightNode;
    return root;
  }

  public int queryMin(Node root, int lo, int hi) {
    if (lo > root.right || hi < root.left) return Integer.MAX_VALUE;
    if (lo <= root.left && hi >= root.right) return root.val;
    int leftMin = queryMin(root.leftChild, lo, hi);
    int rightMin = queryMin(root.rightChild, lo, hi);
    return Math.min(leftMin, rightMin);
  }

  public void updateMin(int[] nums, int idx, int val) {
    if (idx < 0 || idx >= nums.length) return;

  }


  public static void main(String[] args) {
    int[] nums = {5,3,6,1,-7,3,2};
    SegTree st = new SegTree();
    st.buildMin(nums);
    System.out.println(st.queryMin(st.root, 1, 5));
  }
}
