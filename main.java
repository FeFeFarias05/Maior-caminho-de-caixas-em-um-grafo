public class main{
    public static void main(String args[]){
        Caixa teste = new Caixa();
        String[] valores = teste.getMedidas("225 889 888");

        for(int i =0; i<valores.length; i++)
        {
            System.out.println("Valores -> " + valores[i]);
        }
    }
}