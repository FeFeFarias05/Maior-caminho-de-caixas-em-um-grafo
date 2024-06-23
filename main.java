import java.io.FileNotFoundException;

public class main {

    public static void main(String[] args) throws FileNotFoundException {
        Digraph digrafo = new Digraph("caso00010.txt");
        String startVertex = digrafo; 

        /*
         * 
         System.out.println("Vertices do grafo:");
         for (String vertex : digraph.getVerts()) {
            System.out.println(vertex);
        }
        */

        
         /* 
         System.out.println("Arestas do grafo:");
         for (String vertex : digraph.getVerts()) {
            System.out.println("Adjacências de " + vertex + ": " + digraph.getAdj(vertex));
        }
        */

        BFS bfs = new BFS(digrafo, startVertex);
        System.out.println("Maior caminho encontrado: " + bfs.biggerPath(digrafo));
/* 
 * 
 System.out.println(digraph.toDot());
 */
    }
}
