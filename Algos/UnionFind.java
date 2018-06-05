/*
并查集算法
reference link:
并查集详解
https://blog.csdn.net/nedushy123/article/details/21647519

并查集详解 图文解说
https://blog.csdn.net/liujian20150808/article/details/50848646

并查集路径压缩方法
http://www.cnblogs.com/vongang/archive/2011/07/31/2122763.html
https://blog.csdn.net/kalilili/article/details/43014623

一种树型的数据结构，其保持着用于处理一些不相交集合（Disjoint Sets）的合并及查询问题。
有一个联合-查找算法（union-find algorithm）定义了两个操作用于此数据结构：

> Find：确定元素属于哪一个子集。它可以被用来确定两个元素是否属于同一子集。
> Union：将两个子集合并成同一个集合。

因为它支持这两种操作，一个不相交集也常被称为联合-查找数据结构（union-find data structure）或合并-查找集合（merge-find set）
其他的重要方法，MakeSet，用于建立单元素集合。有了这些方法，许多经典的划分问题可以被解决。
*/

public class UnionFind {

  private int[] father;  // father[x]表示x的父节点
  private int[] rankArr; // rank[x]表示x的秩
  
  /*
  返回x的祖先
  回溯的过程中应用路径压缩 从x往上找祖先的所有点,
  回溯的时候都指向最高级祖先
   */
  public int find(int x) {
    if (father[x] != x) {
      father[x] = find(father[x]);
    }
    return father[x];
  }

  /*
  将x和y合并到一个集合中
   */
  public void union(int x, int y) {
    // x,y祖先不同的时候,将y整个一支挂到x祖先下面
    if (find(x) != find(y)) {
      father[y] = find(x);
    }
  }
  

  public static void main(String[] args) {

  
  }

}








