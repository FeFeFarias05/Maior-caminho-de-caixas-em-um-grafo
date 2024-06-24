import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class DFS {

    private Set<String> marked;
    private Map<String, String> edgeTo;
    private String start;
    private Set<String> visited;

    public DFS(Digraph digraph) {
        visited = new HashSet<>();
        for (String vertex : digraph.getVerts()) {
            if (!visited.contains(vertex)) {
                dfs(digraph, vertex);
            }
        }
    }

    private void dfs(Digraph digraph, String vertex) {
        visited.add(vertex);
        System.out.println("Visitando: " + vertex);
        for (String adj : digraph.getAdj(vertex)) {
            if (!visited.contains(adj)) {
                dfs(digraph, adj);
            }
        }
    }

    public boolean hasPathTo(String v) {
        return marked.contains(v); // Retorna true se o conjunto marked contém v, caso contrário retorna false
    }

    public Iterable<String> pathTo(String v) {
        if (!hasPathTo(v)) return null; // se não tem caminho retorna null
        ArrayList<String> path = new ArrayList<>();
        for (String w = v; !w.equals(start); w = edgeTo.get(w)) {
            path.add(0, w); // insere no início da lista, empurrando os elementos existentes para a direita
        }
        path.add(0, start); // adiciona o vértice inicial no início do caminho
        return path;
    }

    // Método para encontrar o maior caminho
public int biggerPath(Digraph digraph) {
    int maxPathLength = 0; // Armazena o comprimento do maior caminho
    Map<String, Boolean> visited = new HashMap<>(); // Para marcar os vértices visitados

    // Função DFS interna para explorar os caminhos
    Function<String, Integer> dfs = new Function<String, Integer>() {
        @Override
        public Integer apply(String vertex) {
            visited.put(vertex, true);
            int pathLength = 1; // Inicializa o comprimento do caminho como 1 para contar o vértice atual
            int maxLengthFromVertex = 0;

            // Explora todos os vértices adjacentes
            for (String adj : digraph.getAdj(vertex)) {
                if (!visited.getOrDefault(adj, false)) {
                    maxLengthFromVertex = Math.max(maxLengthFromVertex, apply(adj));
                }
            }

            visited.put(vertex, false); // Marca o vértice como não visitado para permitir novas buscas
            return pathLength + maxLengthFromVertex; // Retorna o comprimento do caminho incluindo este vértice
        }
    };

    // Chama DFS para cada vértice e atualiza o maior caminho encontrado
    for (String vertex : digraph.getVerts()) {
        visited.clear(); // Limpa o mapa de visitados para cada nova busca
        maxPathLength = Math.max(maxPathLength, dfs.apply(vertex));
    }

    return maxPathLength;
}

// DFS com memorização
void dfs(String v, Map<String, Integer> maiorCaminhoMemorizado, Digraph g) {
    // Inicializa o maior caminho a partir de v como 0
    int maxPathFromV = 0;
    // Verifica todos os vértices adjacentes
    for (String u : g.getAdj(v)) {
        if (!maiorCaminhoMemorizado.containsKey(u)) {
            dfs(u, maiorCaminhoMemorizado, g);
        }
        // Atualiza o maior caminho a partir de v
        maxPathFromV = Math.max(maxPathFromV, 1 + maiorCaminhoMemorizado.get(u));
    }
    // Memoriza o resultado
    maiorCaminhoMemorizado.put(v, maxPathFromV);
}
}