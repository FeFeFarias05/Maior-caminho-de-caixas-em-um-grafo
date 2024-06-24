import java.io.FileNotFoundException;

public class main {

    public static void main(String[] args) throws FileNotFoundException {
        Digraph digrafo = new Digraph("caso00200.txt");
    

        /*
         * 
         System.out.println("Vertices do grafo:");
         for (String vertex : digraph.getVerts()) {
            System.out.println(vertex);
        }
        */

        
         /*
          * 
          System.out.println("Arestas do grafo:");
          for (String vertex : digrafo.getVerts()) {
            System.out.println("Adjacências de " + vertex + ": " + digrafo.getAdj(vertex));
        }
        */
        

        DFS dfs = new DFS(digrafo);
        System.out.println("Maior caminho encontrado: " + digrafo.countVerticesInLargestPath());
/* 
 * 
 System.out.println(digraph.toDot());
 */
    }
}
