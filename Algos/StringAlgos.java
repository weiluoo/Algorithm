/*
有关String相关的算法
1. KMP
2. Manacher's Algo (江湖人称马拉车算法)
3. AC automation machine (AC自动机)
4. extended KMP (扩展KMP 应用较少 此处不涉及)
*/

import java.util.*;

public class StringAlgos {

/*
AC自动机
解决多模式匹配问题 本质上是KMP+Trie树

reference link:
B站ACM大佬视频详解
https://www.bilibili.com/video/av6295004/

多模字符串匹配算法之AC自动机—原理与Java实现
https://www.cnblogs.com/nullzx/p/7499397.html

AC自动机-算法详解
https://blog.csdn.net/u013371163/article/details/60469534


*/

  //处理英文类型的字符串,所以数组长度128
  private static final int ASCII = 128;
  //AC自动机的根节点,不储存任何信息
  private Node root;
  //待查找字符串集合
  private List<String> target;
  //文本字符串查找的结果 key:目标字符串 value:目标串在文本字符串出现的位置
  private Map<String, List<Integer>> result;
  //内部静态类 ac自动机节点
  private static class Node {
    //从起点到该点如果表示一个目标字符串,则str!=null && str==target
    String str;
    //128叉树
    Node[] table = new Node[ASCII];
    //fail指针
    Node fail;

    public boolean isWord() {
      return str != null;
    }
  }

  public void buildACAutomation(List<String> target) {
    root = new Node();
    this.target = target;
  }

  private void buildTrieTree() {
    for (String targetStr : target) {
      Node cur = root;
      for (int i = 0; i < targetStr.length(); i++) {
        char ch = targetStr.charAt(i);
        int idx = ch - '0';
        if (cur.table[idx] == null) {
          cur.table[idx] = new Node();
        }
        cur = cur.table[idx];
      }
      //将目标字符串最后一个字符对应节点变成终点
      cur.str = targetStr;
    }
  }





}























