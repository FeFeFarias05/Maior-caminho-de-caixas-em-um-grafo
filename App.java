import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        Digraph digrafo = new Digraph("casoPDF.txt");
        System.out.println("Maior caminho encontrado: " + digrafo.biggerPath());
        System.out.println(digrafo.toDot());
    }
}
