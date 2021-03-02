package dados1;

public class Potassio {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double ParticipacaoCTCAtual;
    private double ParticipacaoCTCDesejada;

    public Potassio() { }

    public double getValorIdeal() {
        return ValorIdeal;
    }

    public double getValor() {
        return Valor;
    }

    public double getValorCorrigido() {
        return ValorAposCorrecao;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public void setParticipacaoCTCAtual(double participacaoCTCAtual) {
        ParticipacaoCTCAtual = participacaoCTCAtual;
    }

    public void setParticipacaoCTCDesejada(double participacaoCTCDesejada) {
        ParticipacaoCTCDesejada = participacaoCTCDesejada;
    }

    public void calculaValorIdeal(int texturaSolo) {
        switch (texturaSolo) {

            case 1:
                ValorIdeal = 0.35;
                break;

            case 2:
                ValorIdeal = 0.25;
                break;

            default:
                ValorIdeal = 0;
                break;

        }
    }

    public void calculaValorAposCorrecao() {
        double valorCalculoAuxiliar = (Valor * ParticipacaoCTCDesejada / ParticipacaoCTCAtual) - Valor;
        if (Valor > 0.5 || valorCalculoAuxiliar < 0.01) {
            ValorAposCorrecao = Valor;
            return;
        }
        if (valorCalculoAuxiliar > 0.01) {
            ValorAposCorrecao = (Valor + valorCalculoAuxiliar);
            return;
        }
        ValorAposCorrecao = 0;
    }

}
