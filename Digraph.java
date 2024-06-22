import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Digraph  {

  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<String>> digraph;
  protected Set<String> vertices;
  protected int totalVertices;
  protected int totalEdges;

  public Digraph() {
    digraph = new HashMap<>();
    vertices = new HashSet<>();
    totalVertices = totalEdges = 0;
  }

  public Digraph(String filename) {
    this();
    In in = new In(filename);
    String line;
    while ((line = in.readLine()) != null) {
      String[] edge = line.split(" ");
      addEdge(edge[0], edge[1]);
    }
    in.close();
  }

    // Adiciona um vértice adjacente a outro, criando a lista
  // de adjacências caso ainda não exista no dicionário
  protected List<String> addToList(String v, String w) {
    List<String> list = digraph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(w);
    digraph.put(v, list);
    totalEdges++;
    return list;
  }

  public void addEdge(String v, String w) {
    addToList(v, w);
    if (!vertices.contains(v)) {
      vertices.add(v);
      totalVertices++;
    }
    if (!vertices.contains(w)) {
      vertices.add(w);
      totalVertices++;
    }
  }

  
  public Iterable<String> getAdj(String v) {
    List<String> res = digraph.get(v);
    if (res == null)
      res = new LinkedList<>();
    return res;
  }

  public Set<String> getVerts() {
    return vertices;
  }

  public int getTotalVertices() {
    return totalVertices;
  }

  public int getTotalEdges() {
    return totalEdges;
  }


  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (String v : getVerts().stream().sorted().toList())
      for (String w : getAdj(v))
        sb.append("\"" + v + "\"" + " -> " + "\"" + w + "\"" + NEWLINE);
    sb.append("}" + NEWLINE);
    return sb.toString();
  }
}
