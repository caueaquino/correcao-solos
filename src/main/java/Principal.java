import tabelaTeores.Aluminio;

public class Principal {

    public static Aluminio aluminio;

    public static void main(String[] args) {
        aluminio = new Aluminio(1);
        System.out.println("Valor aluminio: " + aluminio.getValor());
    }

}
