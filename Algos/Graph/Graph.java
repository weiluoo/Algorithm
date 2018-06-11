
import java.util.*;

public class Graph {
  
  public static class Node {
    public Object val;
    public int pathIn = 0;
    public Node(Object val) {
      this.val = val;
    }
  }

  private Set<Node> nodes;
  private Map<Node, Set<Node>> adj;
  
  public Graph() {
    nodes = new HashSet<>();
    adj = new HashMap<>();
  }
  
  public void addNodes(Node start, Node end) {
    if (!nodes.contains(start)) {
      nodes.add(start);
    }
    if (!nodes.contains(end)) {
      nodes.add(end);
    }
  
    adj.putIfAbsent(start, new HashSet<>());
    adj.putIfAbsent(end, new HashSet<>());
    if (!adj.get(start).contains(end)) {
      adj.get(start).add(end);
      end.pathIn++;
    }
  }
}
