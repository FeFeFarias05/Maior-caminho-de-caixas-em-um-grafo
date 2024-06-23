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
        try {
            File file = new File(filename);
            Scanner t = new Scanner(file);

            List<String> lines = new LinkedList<>();
            while (t.hasNextLine()) {
                String line = t.nextLine();
                if (!line.isEmpty()) {
                    lines.add(line); 
                }
            }
            for (int i = 0; i < lines.size(); i++) {
                int[] measures = getMeasures(lines.get(i));

                for (int j = 0; j < lines.size(); j++) {
                    if (i != j) {
                        int[] nextMeasures = getMeasures(lines.get(j));
                        boolean result = true;
                        for (int k = 0; k < measures.length; k++) {
                            if (measures[k] >= nextMeasures[k]) {
                                result = false;
                                break;
                            }
                        }
                        if (result) {
                            addEdge(lines.get(i), lines.get(j)); // Adiciona a aresta no grafo
                        }
                    }
                }
            }

            t.close();
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

   int[] getMeasures(String mesures){
      String[] mesuresSeparadas = mesures.split(" ");
      int[] mesuresNum = new int[mesuresSeparadas.length];
      Arrays.sort(mesuresSeparadas);

      for(int i = 0; i < mesuresNum.length; i++)
      {
        mesuresNum[i] = Integer.parseInt(mesuresSeparadas[i]);
      }
        return mesuresNum;
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
