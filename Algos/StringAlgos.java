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

time complecity:
假设有N个模式串，平均长度为L；输入的待对比串长度为M
建立Trie树：O(N*L)
建立fail指针：O(N*L)
模式匹配：O(M*L) （注：之所以要乘以一个L，是因为在统计的时候需要顺着链回溯到root结点）
所以总时间复杂度为:O((N+M)*L)
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
    //从起点到该点如果表示一个目标字符串target,则str!=null && str==target
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
    buildTrieTree();
    buildFailPointers();
  }

  //由目标字符串构建Trie树
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

  //给Trie树节点构建fail指针 本质上是KMP的next数组 算法:BFS
  private void buildFailPointers() {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      //根节点的孩子fail指针均指向根节点
      if (cur == root) {
        for (Node x : root.table) {
          if (x != null) {
            x.fail = root;
            //孩子节点入队
            queue.offer(x);
          }
        }
      } else {
        for (int i = 0; i < cur.table.length; i++) {
          if (cur.table[i] != null) {
            //孩子节点入队
            queue.offer(cur.table[i]);
            Node curFail = cur.fail;
            while (curFail != null) {
              //找到最长的公共前缀
              if (curFail.table[i] != null) {
                cur.table[i].fail = curFail.table[i];
                break;
              } else { //接着向上找
                curFail = curFail.fail;
              }
            }
            //到了跟节点也找不到公共前缀
            if (curFail == null) {
              cur.table[i].fail = root;
            }
          }
        }
      }
    }
  }

  //在文本中查找字符串
  public Map<String, List<Integer>> find(String text) {
    result = new HashMap<>();
    for (String str : target) {
      result.put(str, new ArrayList<>());
    }

    Node cur = root;
    int idx = 0;
    while (idx < text.length()) {
      int ch = text.charAt(idx) - '0';
      if (cur.table[ch] != null) {
        //自动机进入下一状态
        cur = cur.table[ch];
        if (cur.isWord()) {
          result.get(cur.str).add(idx-cur.str.length()+1);
        }
        /*
        一个目标字符串的中间某部分字符串可能包含另一个目标字符串 
				e.g: http, ttp, tp均在target数组中.当http找到后,
				ttp,tp也应该找到了.所以应该顺着fail指针一直往上检查.
        */
        Node tmpFail = cur.fail;
        while (tmpFail != null && tmpFail.isWord()) {
          result.get(tmpFail.str).add(idx-tmpFail.str.length()+1);
          tmpFail = tmpFail.fail;
        }
        idx++;
      } else {
        cur = cur.fail;
        if (cur == null) {
          cur = root;
          idx++;
        }
      }
    }
    return result;
  }



  public static void main(String[] args) {
    StringAlgos inst = new StringAlgos();
    List<String> target = new ArrayList<>();
    target.add("http");
    target.add("ttp");
    target.add("tp");
    target.add("abcdef");
    target.add("abhab");
    target.add("bcd");
    target.add("cde");
    target.add("cdfkcdf");

    String text = "httpbcabcdebcedfabcdefababkabhabk";

    inst.buildACAutomation(target);
    Map<String, List<Integer>> res = inst.find(text);
    for (Entry<String, List<Integer>> entry : res.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }




}























