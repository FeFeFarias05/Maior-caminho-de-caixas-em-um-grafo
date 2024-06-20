import java.util.Arrays;
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

        Digraph grafo = new Digraph();

        String[] valores = getMedidas("225 889 888");

        for(int i =0; i<valores.length; i++)
        {
            System.out.println("Valores -> " + valores[i]);
        }

        File arq =  new File("caso00010.txt");
        Scanner t = new Scanner(arq);

        while(t.hasNextLine())
        {
            String linha = t.nextLine();
            System.out.println(linha);
        }
    }
}