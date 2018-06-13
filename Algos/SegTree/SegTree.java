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
    int lazyTag; //懒惰标签
    Node leftChild, rightChild;

    public Node(int left, int right, int val) {
      this.left = left;
      this.right = right;
      this.val = val;
      this.lazyTag = 0;
    }
  }

  private Node root;

  //根据输入数组建立线段树
  public void buildMin(int[] nums) {
    root = buildMin(nums, 0, nums.length - 1);
  }

  public void buildSum(int[] nums) {
    root = buildSum(nums, 0, nums.length -1 );
  }

  private Node buildSum(int[] nums, int lo, int hi) {
    if (hi < lo) return null;
    if (hi == lo) {
      Node root = new Node(lo, hi, nums[lo]);
      return root;
    }
    int mid = lo + (hi - lo) / 2;
    Node leftNode = buildSum(nums, lo, mid);
    Node rightNode = buildSum(nums, mid+1, hi);
    root.leftChild = leftNode;
    root.rightChild = rightNode;
    return root;
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

  //[lo,hi]区间查询最小值
  public int queryMin(Node root, int lo, int hi) {
    if (lo > root.right || hi < root.left) return Integer.MAX_VALUE;
    if (lo <= root.left && hi >= root.right) {
      return root.val;
    }
    if (root.lazyTag != 0) {
      root.leftChild.lazyTag += root.lazyTag;
      root.rightChild.lazyTag += root.lazyTag;
      root.leftChild.val += root.lazyTag;
      root.rightChild.val += root.lazyTag;
      root.lazyTag = 0;
    }
    int leftMin = queryMin(root.leftChild, lo, hi);
    int rightMin = queryMin(root.rightChild, lo, hi);
    return Math.min(leftMin, rightMin);
  }

  public int querySum(Node root, int lo, int hi) {
    if (lo > root.right || hi < root.left) return 0;
    if (lo <= root.left && hi >= root.right) {
      return root.val;
    }
    if (root.lazyTag != 0) {
      passLazyTag(root.leftChild, root.lazyTag);
      passLazyTag(root.rightChild, root.lazyTag);
      root.lazyTag = 0;
    }
    int leftSum = querySum(root.leftChild, lo, hi);
    int rightSum = querySum(root.rightChild, lo, hi);
    return leftSum + rightSum;
  }

  //单点更新 分治思想 回溯时调整父节点的值
  public void updateMin(Node root, int idx, int val) {
    if (idx < root.left || idx >= root.right) return;
    if (idx == root.left || idx == root.right) {
      root.val = val;
      return;
    }
    int mid = root.left + (root.right - root.left) / 2;
    if (idx >= root.left && idx <= mid) {
      updateMin(root.leftChild, idx, val);
    } else {
      updateMin(root.rightChild, idx, val);
    }
    root.val = Math.min(root.leftChild.val, root.rightChild.val);
  }

  //区间更新 [lo,hi]区间的值都加上val
  public void updateMin(Node root, int lo, int hi, int val) {
    if (lo > root.right || hi < root.left) return;
    if (lo <= root.left && hi >= root.right) {
      root.val += val;
      root.lazyTag += val;
      return;
    }
    //pass down lazy tag
    if (root.lazyTag != 0) {
      root.leftChild.val += root.lazyTag;
      root.rightChild.val += root.lazyTag;
      root.leftChild.lazyTag += root.lazyTag;
      root.rightChild.lazyTag += root.lazyTag;
      root.lazyTag = 0;
    }
    updateMin(root.leftChild, lo, hi, val);
    updateMin(root.rightChild, lo, hi, val);
    root.val = Math.min(root.leftChild.val, root.rightChild.val);
  }

  //[lo, hi]区间更新 所有值加上val
  public void updateSum(Node root, int lo, int hi, int val) {
    if (lo > root.right || hi < root.left) return;
    if (lo <= root.left && hi >= root.right) {
      int len = root.right - root.left + 1;
      root.val += len * val;
      root.lazyTag += val;
      return;
    }
    if (root.lazyTag != 0) {
      passLazyTag(root.leftChild, root.lazyTag);
      passLazyTag(root.rightChild, root.lazyTag);
      root.lazyTag = 0;
    }
    updateSum(root.leftChild, lo, hi, val);
    updateSum(root.rightChild, lo, hi, val);
    root.val = root.leftChild.val + root.rightChild.val;
  }

  private void passLazyTag(Node root, int lazyTag) {
    int len = root.right - root.left + 1;
    root.val += len * lazyTag;
    root.lazyTag += lazyTag;
  }


  public static void main(String[] args) {
    int[] nums = {5,3,6,1,-7,3,2};
    int[] nums1 = {0,0,0,0,0,0};
    SegTree st = new SegTree();
    st.buildMin(nums1);
    // System.out.println(st.queryMin(st.root, 1, 5));
    st.updateSum(st.root, 0, 4, 2);
    System.out.println(st.querySum(st.root, 1, 4));
  }
}