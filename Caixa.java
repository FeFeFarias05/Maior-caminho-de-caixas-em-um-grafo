import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Caixa {

    protected Map<String, List<String>> graph;
    protected Set<String> vertices;
    protected int totalVertices;
    protected int totalEdges;
    
    public Caixa (String Filename) throws FileNotFoundException{
        File arq =  new File(Filename);
        Scanner t = new Scanner(arq);

        while(t.hasNextLine())
        {
            String linha = t.nextLine();
            System.out.println(linha);
        }
        }
    }

