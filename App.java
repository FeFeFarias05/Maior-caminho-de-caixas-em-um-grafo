import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.nanoTime();
        Box digrafo = new Box("casoPDF.txt");
        System.out.println("Maior caminho encontrado: " + digrafo.biggerPath());
        System.out.println(digrafo.toDot());

        long end = System.nanoTime();
        long dur = end - start;
        System.out.println("Tempo de duração do teste em nanossegundos: " + dur);
        System.out.println("Tempo de duração do teste em segundos: " + (dur / 1_000_000_000.0));
    }
}
