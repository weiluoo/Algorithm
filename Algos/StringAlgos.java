/*
有关String相关的算法
1. KMP
2. Manacher's Algo (江湖人称马拉车算法)
3. AC automation (AC自动机)
4. extended KMP (扩展KMP 应用较少 此处不涉及)
*/

import java.util.*;
import java.util.Map.Entry;

public class StringAlgos {
  
/*
KMP
解决单模式匹配问题 难点:如何理解next数组

reference link:
详解KMP算法
https://www.cnblogs.com/yjiyjige/p/3263858.html

KMP算法的Next数组详解
http://www.cnblogs.com/tangzhengyue/p/4315393.html
*/
  
  /*
  @param ts 主串
  @param ps 模式串
  @return 如果发现匹配 返回主串中模式串第一个字符出现的位置 否则返回-1
  */
  public int KMP(String ts, String ps) {
    if (ts == null || ps == null || ts.length() == 0 || ps.length() == 0) return -1;
    char[] t = ts.toCharArray();
    char[] p = ps.toCharArray();

    int i = 0; //主串指针
    int j = 0; //模式串指针
    int[] next = getNext(p);

    while (i < t.length && j < p.length) {
      //j == -1,要移动的是i,j也需要归0
      if (j == -1 || t[i] == p[j]) {
        i++;
        j++;
      } else { //KMP精髓:i不回溯,j回溯
        j = next[j];
      }
    }
    if (j == p.length) {
      return i - j;
    } else {
      return -1;
    }
  }

  //由模式串得到next数组 k<-next[j]:在j位置失配时,退回到k位置
  private int[] getNext(char[] p) {
    int[] next = new int[p.length];
    next[0] = -1; //在第一个位置失配,j指针已不能后退.此时主串i指针需要后移
    int j = 0;
    /*
    k = -1,不用继续递归寻找回溯位置.此时next[j+1] <- 0,即把j退回首位
    next[j+1] = 0 也能写成next[++j] = ++k;
    */
    int k = -1;

    //根据已知的前面j位推测第j+1位
    while (j < p.length - 1) {
      if (k == -1 || p[j] == p[k]) {
        /*
        j+1位是失配了的，如果我们回退j后，发现新的j(也就是此时的++k那位)
        跟回退之前的j也相等的话，必然也是失配。所以还得继续往前回退到next[++k]
        */
        if (p[j+1] == p[k+1]) {
          next[++j] = next[++k];
        } else {
          next[++j] = ++k;
        }
      } else {
        k = next[k];
      }
    }
    return next;
  }
  
  
/*
Manacher's 算法
用来查找字符串内部最长回文串 高效算法
时间复杂度:O(n) 每个字符最多访问两次

reference link:
B站ACM大佬视频详解
https://www.bilibili.com/video/av4829276/index_1.html#page=1

马拉车算法C++实现
http://www.cnblogs.com/grandyang/p/4475985.html

马拉车算法Java实现
https://blog.csdn.net/u014771464/article/details/79120964
*/    
  
  public String Manacher(String str) {
    if (str == null || str.length() == 0) return "";
    /*
    由于第一个和最后一个字符都是#号,且都要搜索回文
    为了防止数组越界,在首位各插入一个非#的特殊符号
    仅作为标记.此处头部插入$,尾部插入@(不一样即可)
     */
    StringBuilder sb = new StringBuilder("$#");
    for (int i = 0; i < str.length(); i ++) {
      sb.append(str.charAt(i));
      sb.append("#");
    }
    sb.append("@");

    char[] s = sb.toString().toCharArray();
    int mx = 0;
    int id = 0;
    int resCenter = 0;
    int resLen = 0;
    int[] len = new int[s.length];
    for (int j = 1; j < s.length; j++) {
      //在最后一个标记字符处一定要break,否则s[j+len[j]]会越界
      if (j == s.length - 1) break;
      /*
      此处len[j]的计算是整个算法的精髓 mx > j时,需要进行分类讨论
      mx < j时,由于对mx之后的情况未知,所以只能一位位去比较:len[j] <- 1
       */
      len[j] = (mx > j) ? Math.min(len[2*id-j], mx-j) : 1;
      //一位位去比较 如果匹配,则len[j]自增
      while (s[j+len[j]] == s[j - len[j]]) {
        len[j]++;
      }
      //发现更长的回文字符串,更新各个变量
      if (mx < j + len[j]) {
        mx = j + len[j];
        id = j;
      }
      if (resLen < len[j]) {
        resLen = len[j];
        resCenter = j;
      }
    }
    /*
    注意这里起始位置的坐标计算 最好画个图搞个简单例子
    马拉车串:  _$_  _#_  _c_  __#__  __b__ __#__  __b__   _#_  _a_  _#_   _@_
    原串：     c  b  b  a
    id == 5, resLen == 3
    */
    return str.substring( (resCenter-resLen)/2, (resCenter-resLen)/2 + resLen - 1 );
  }

  
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
    
    String str = "cbba";
    System.out.println(inst.Manacher(str));
  }
}
