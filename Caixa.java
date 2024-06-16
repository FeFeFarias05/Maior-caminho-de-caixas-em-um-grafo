import java.util.Arrays;

public class Caixa {


    
String[] getMedidas(String medidas){
  String[] medidasSeparadas = medidas.split(" ");

  Arrays.sort(medidasSeparadas);

    return medidasSeparadas;
}
}
