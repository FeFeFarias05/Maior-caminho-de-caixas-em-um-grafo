import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BFS {
    private HashSet<String> marked; // Vértices visitados
    private HashMap<String, String> edgeTo; // Último vértice no caminho conhecido até este vértice
    private HashMap<String, Integer> distTo; // Distância do vértice de origem
    private final String start; // Vértice de origem

    public BFS(Digraph g, String start) {
        this.start = start;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        bfs(g, start);
    }

    private boolean hasPathTo(String v) {
        if(v.equals(start))
            return false;
        return marked.contains(v);
    }

    private int distTo(String v) {
        if(!hasPathTo(v))
            return -1; // Não chegou lá
        return distTo.get(v);
    }

    public Iterable<String> pathTo(String v) {
        if (!hasPathTo(v)) return null;
        ArrayList<String> path = new ArrayList<>();
        String w = v;
        while(!w.equals(start)) {
            path.add(0,w); // insere no início da lista
            w = edgeTo.get(w);
        }
        path.add(0,start);
        return path;
    }

    public int biggerPath(Digraph g){
        int maxValue = 0;
        for (String valueVertices : g.getVerts()) {
            int distance = distTo(valueVertices);
            System.out.println("Vertex: " + valueVertices + ", Distance: " + distance);
            if(maxValue < distance) {
                maxValue = distance;
            }
        }
        return maxValue;    
    }

    private void bfs(Digraph g, String s) {
        LinkedList<String> queue = new LinkedList<>();
        queue.add(s);
        marked.add(s);
        distTo.put(s, 0);
        System.out.println("Starting BFS from: " + s); 
        while (!queue.isEmpty()) {
            s = queue.removeFirst();
            System.out.println("Visiting: " + s); 
            for (String w : g.getAdj(s)) {
                if (!marked.contains(w)) {
                    queue.add(w);
                    marked.add(w);
                    edgeTo.put(w, s);
                    distTo.put(w, distTo.get(s) + 1);
                }
            }
        }
    }
}