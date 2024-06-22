import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class main{


    static String[] getMedidas(String medidas){
      String[] medidasSeparadas = medidas.split(" ");
      Arrays.sort(medidasSeparadas);
        return medidasSeparadas;
    }

    public static void main(String args[]) throws FileNotFoundException{
        //Caixa teste = new Caixa();

        /*
        * 
        String[] valores = getMedidas("225 889 888");
        
        for(int i =0; i<valores.length; i++)
        {
            System.out.println("Valores -> " + valores[i]);
            }
            */
            
        Digraph grafo = new Digraph();
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

      

        for(int i = 0; i < lines.size(); i++){
            String[] measures = getMedidas(lines.get(i));

            for(int j = 0; j < lines.size(); j++){
                if(i !=j){
                    String[] nextMeasures= getMedidas(lines.get(j));
                    boolean result = false;
                    for(int k = 0; k <measures.length; k++)
                        if (Integer.parseInt(measures[k]) >= Integer.parseInt(nextMeasures[k])) {
                            result = false;
                        }
                        
                        if(result){
                            
                            grafo.addEdge(lines.get(i),lines.get(i+1));
                        }
                    }    
            /*
            for (int j = 0; j < measures.length; j++) {
                if(Integer.parseInt(measures[j]) >= Integer.parseInt(nextMeasures[j]))
                {
                    grafo.addEdge(measures[j], nextMeasures[j]);
                }
            }
            */
            
            }
             System.out.println(grafo.toDot());
        }
    }
}