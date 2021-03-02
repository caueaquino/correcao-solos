package dados1;

public class Fosforo {

    private double Valor;
    private double ValorAposCorrecao;
    private double ValorIdeal;
    private double ValorParaAtingir;

    public Fosforo() { }

    public double getValor() {
        return Valor;
    }

    public double getValorAposCorrecao() {
        return ValorAposCorrecao;
    }

    public double getValorIdeal() {
        return ValorIdeal;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public void setValorParaAtingir(double valorParaAtingir) {
        ValorParaAtingir = valorParaAtingir;
    }

    public void calculaValorIdeal(int texturaSolo) {
        switch (texturaSolo) {

            case 1:
                ValorIdeal = 9;
                break;

            case 2:
                ValorIdeal = 12;
                break;

            default:
                ValorIdeal = 0;
                break;

        }
    }

    public void calculaValorAposCorrecao() {
        if (ValorParaAtingir > 0.01) {
            ValorAposCorrecao = ValorParaAtingir;
            return;
        }
        ValorAposCorrecao = 0;
    }

}
