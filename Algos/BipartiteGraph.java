/*
二分图最大匹配 -> Hungarian Algorithm
算法利用网络流的思想，其核心就是寻找增广路

带权二分图的最大匹配 -> KM算法

步骤：
（1）初始化可行标杆
（2）用匈牙利算法寻找完备匹配
（3）若未找到完备匹配则修改可行标杆
（4）重复（2）（3）直到找到相等子图的完备匹配

reference: 
https://blog.csdn.net/thunderMrbird/article/details/52231639
https://blog.csdn.net/x_y_q_/article/details/51927054
http://www.cnblogs.com/liuzhen1995/p/6740967.html
*/

public class BipartiteGraph {
  
  private int[][] map;          /* stores connectivity and edge weight info. map[i][j] != 0 means there's an edge 
                                   between object i in group A and j in group B 
                                   map.length: number of object in group A
                                   map[0].length: number of object in group B
                                   in this case, length of i and j should be same */                                
  private int[] nodes;          // stores objects A matched by group B
  private boolean[] used;       // keeps track of whether a group B object was matched in one matching iteration of group A object  
  private int sum;              // total number of matched pairs
  private int[] lx;             // labeling of group A 
  private int[] ly;             // labeling of group B
  private boolean[] visitedX;   // same meaning as used[] array. utilized in weighted matching 
  private boolean[] visitedY;   // same meaning as used[] arrau. utilized in weighted matching  
  
  
  public BipartiteGraph(int[][] map) {
    this.map = map;
    int numA = map.length;
    int numB = map[0].length;
    nodes = new int[numB]; 
    sum = 0;
    lx = new int[numA];
    ly = new int[numB];
  }
  
  public int find() {
	sum = 0;  // clean history sum since sum is a member vairable
    for (int i = 0; i < map.length; i++) {
      // clear the used array for a new matching iteration 
      used = new boolean[map[0].length];
      if (canFildMatch(i, used)) sum++;
    }
    return sum;
  }

  public int getSum() {
    return sum;
  }
  
  /*
  for given object x in group A, we try to match it with one object in 
  group B. If successed, nodes[j] = x means j in group B is matched with x. 
  */
  private boolean canFindMatch(int x) {
    for (int j = 0; j < map[0].length; j++) {
      // if x and j have an edge connected, and 
      // j hasn't been examed yet. 
      if (map[x][j] != 0 && !used[j]) {
        used[j] = true;
        if (nodes[j] == 0 || canFildMatch(nodes[j]) {
          nodes[j] = x;
          return true;
        }
      }      
    }
    return false;
  }

  private boolean canFindWeightedMatch(int x) {
    visitedX[x] = true;
    for (int y = 0; y < map[0].length; y++) {
      /*
      lx(x)+ly(y)=weight(x,y) -> a feasible edge in generated graph  
      */
      if (!visitedY[y] && lx[x] + ly[y] == map[x][y]) {
        visitedY[y] = true;
        if (nodes[y] == -1 || canFindWeightedMatch(nodes[y])) {
          nodes[y] = x;
          return true;
        }
      }
    }
    return false;
  }  
  
  public int getKM(int judge) {
    int len = map.length;
    if (judge == -1) {  // to get the min matching of bipartite graph
	    for (int i = 0; i < len; i++) {
	      for (int j = 0; j < len; j++) {
		      map[i][j] = -1 * map[i][j]; // negate the weight -> find max match    
	      }	  
	    }
	  }
	
	  // initialize lx and ly 
	  for (int i = 0; i < len; i++) {
	    ly[i] = 0;
      lx[i] = Integer.MIN_VALUE;
      for (int j = 0; j < len; j++) {
        if (map[i][j] > lx[i]) lx[i] = map[i][j]; 
	    }	  
	  }
	  
	  // initialize nodes array 
	  for (int i = 0; i < len; i++) {
	    nodes[i] = -1;	
	  }
	
    /*
    iterate group A, matching each of the objects in it     
    */
	  for (int x = 0; x < len; x++) {
      while (true) {
        visitedX = new boolean[len];
        visitedY = new boolean[len];
        
        // if x can find augmenting path, then break loop
        if (canFindWeightedMatch(x)) break; 
        
        //if x cannot find augmenting path, we adjust lx and ly
        
        // first, we need to find min d
        int d = Integer.MIN_VALUE;
        
        
      }      
    }
  
  }
  
  
  

  
}


















