import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class main{


    static int[] getMeasures(String mesures){
      String[] mesuresSeparadas = mesures.split(" ");
      int[] mesuresNum = new int[mesuresSeparadas.length];
      Arrays.sort(mesuresSeparadas);

      for(int i = 0; i < mesuresNum.length; i++)
      {
        mesuresNum[i] = Integer.parseInt(mesuresSeparadas[i]);
      }
        return mesuresNum;
    }

    public static void main(String args[]) throws FileNotFoundException{
        //Caixa teste = new Caixa();

        /*
        * 
        String[] valores = getMeasures("225 889 888");
        
        for(int i =0; i<valores.length; i++)
        {
            System.out.println("Valores -> " + valores[i]);
            }
            */
            
        Digraph digraph = new Digraph("casoPDF.txt");
        /* 
         * 
         File arq =  new File("casoPDF.txt");
         Scanner t = new Scanner(arq);
         
         List<String> lines = new LinkedList<>();
         while(t.hasNextLine())
         {
            String line = t.nextLine();
            lines.add(line);
        }
        t.close();
        
        
        //System.out.println(lines);
        
        for (int i = 0; i < lines.size(); i++) {
            int[] measures = getMeasures(lines.get(i));
            
            for (int j = 0; j < lines.size(); j++) {
                if (i != j) { 
                    int[] nextMesuares = getMeasures(lines.get(j));
                    boolean result = true;
                    for (int k = 0; k < measures.length; k++) {
                        if (measures[k] >= nextMesuares[k]) { 
                            result = false;
                            break;
                        }
                    }
                    if (result) {
                        digraph.addEdge(lines.get(i), lines.get(j)); 
                    }
                }
                
            }
        }
        */
        BFS bfs = new BFS(digraph, "0");
        System.out.println(digraph.toDot());
        System.out.println(digraph.getAdj("5"));
        System.out.println("O maior caminho é " + bfs.biggerPath(digraph));
        
    }
}
