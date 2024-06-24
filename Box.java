import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Box  {

  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<String>> digraph;
  protected Set<String> vertices;
  protected int totalVertices;
  protected int totalEdges;

  public Box() {
    digraph = new HashMap<>();
    vertices = new HashSet<>();
    totalVertices = totalEdges = 0;
  }

  public Box(String filename) {
    this();
    try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        Map<String, int[]> lineMeasures = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                int[] measures = getMeasures(line);
                lineMeasures.put(line, measures);
            }
        }

        for (Map.Entry<String, int[]> entryI : lineMeasures.entrySet()) {
            for (Map.Entry<String, int[]> entryJ : lineMeasures.entrySet()) {
                if (!entryI.getKey().equals(entryJ.getKey())) {
                    boolean result = true;
                    for (int k = 0; k < entryI.getValue().length; k++) {
                        if (entryI.getValue()[k] >= entryJ.getValue()[k]) {
                            result = false;
                            break;
                        }
                    }
                    if (result) {
                        addEdge(entryI.getKey(), entryJ.getKey()); // Adiciona a aresta no grafo
                    }
                }
            }
        }

        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
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

  int[] getMeasures(String measures) {
    String[] separatedMeasures = measures.split(" ");
    int[] measuresNum = new int[separatedMeasures.length];

    for (int i = 0; i < separatedMeasures.length; i++) {
        measuresNum[i] = Integer.parseInt(separatedMeasures[i]);
    }

    Arrays.sort(measuresNum);
    return measuresNum;
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


  public int biggerPath() {
    int biggerPath = 0;
    Map<String, Integer> length = new HashMap<>(); 
    for (String vertex : getVerts()) {
        HashSet<String> visited = new HashSet<>();
        int pathLength = findLargestPath(vertex, visited, length);
        biggerPath = Math.max(biggerPath, pathLength);
    }
    return biggerPath ; 
}

  private int findLargestPath(String vertex, Set<String> visited, Map<String, Integer> length) {
      if (length.containsKey(vertex)) {
          return length.get(vertex); 
      }
      visited.add(vertex);
      int pathLength = 1; 

      for (String adjacent : getAdj(vertex)) {
          if (!visited.contains(adjacent)) {
              visited.add(adjacent); 
              pathLength = Math.max(pathLength, 1 + findLargestPath(adjacent, visited, length));
              visited.remove(adjacent); 
          }
      }

      length.put(vertex, pathLength); 
      return pathLength;
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
