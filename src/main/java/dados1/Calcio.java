package dados1;

public class Calcio {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double TeorCaOCorretivo;
    private int FonteCorretivo;
    private double FonteFosforoUtilizar;
    private double ParticipacaoCalcioCTCAtual;
    private double ParticipacaoCalcioCTCDesejada;
    private double ValorHAL;
    private double ValorPotassio;
    private double ValorMagnesio;

    public Calcio() { }

    public double getValor() {
        return Valor;
    }

    public double getValorIdeal() {
        return ValorIdeal;
    }

    public double getValorAposCorrecao() {
        return ValorAposCorrecao;
    }

    public double getTeorCaOCorretivo() {
        return TeorCaOCorretivo;
    }

    public int getFonteCorretivo() {
        return FonteCorretivo;
    }

    public double getFonteFosforoUtilizar() {
        return FonteFosforoUtilizar;
    }

    public double getParticipacaoCalcioCTCAtual() {
        return ParticipacaoCalcioCTCAtual;
    }

    public double getParticipacaoCalcioCTCDesejada() {
        return ParticipacaoCalcioCTCDesejada;
    }

    public double getValorHAL() {
        return ValorHAL;
    }

    public double getValorPotassio() {
        return ValorPotassio;
    }

    public double getValorMagnesio() {
        return ValorMagnesio;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public void setTeorCaOCorretivo(double teorCaOCorretivo) {
        TeorCaOCorretivo = teorCaOCorretivo;
    }

    public void setFonteCorretivo(int fonteCorretivo) {
        FonteCorretivo = fonteCorretivo;
    }

    public void setFonteFosforoUtilizar(double fonteFosforoUtilizar) {
        FonteFosforoUtilizar = fonteFosforoUtilizar;
    }

    public void setParticipacaoCalcioCTCAtual(double participacaoCalcioCTCAtual) {
        ParticipacaoCalcioCTCAtual = participacaoCalcioCTCAtual;
    }

    public void setParticipacaoCalcioCTCDesejada(double participacaoCalcioCTCDesejada) {
        ParticipacaoCalcioCTCDesejada = participacaoCalcioCTCDesejada;
    }

    public void setValorHAL(double valorHAL) {
        ValorHAL = valorHAL;
    }

    public void setValorPotassio(double valorPotassio) {
        ValorPotassio = valorPotassio;
    }

    public void setValorMagnesio(double valorMagnesio) {
        ValorMagnesio = valorMagnesio;
    }

    public void calculaValorIdeal(int texturaSolo) {
        switch (texturaSolo) {

            case 1:
                ValorIdeal = 6.0;
                break;

            case 2:
                ValorIdeal = 4.0;
                break;

            default:
                ValorIdeal = 0;
                break;

        }
    }
    //e48=SEERRO(P10;"")
    // d23 = FonteFosforoUtilizar
    // o117 = =SE(P88>0,0001;P88;"0,0")
    // p88 =  =SE(P91>0,001;P91;SE(P91<=13;""0,0""))"
    // p91 = ((Valor * 'ParticipacaoCalcioCTCDesejada / ParticipacaoCalcioCTCAtual) - Valor - (M22 / 2,42 * (='EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000) / (calculoTeorCaOCorretivoPorcentagem + (M22 / 2,42 * (='EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000))
    // am40 = =SE(D23=1;"0,49924";SE(D23=2;"0,33877";SE(D23=3;"0,0";SE(D23=4;"0,0";SE(D23=5;"0,49924";SE(D23=6;"0,92716";SE(D23>=7;AM41;"")))))))1
    // am41 = =SE(D23=7;"0,92716";SE(D23=8;"0,80235";SE(D23=9;"0,49924";SE(D23=10;"0,795218";SE(D23=11;"0,0";SE(D23=12;"0,0";""))))))
    // m22 = =SE(D23=1;'Memória de cálculo'!B24*0,28;SE(D23=2;'Memória de cálculo'!B24*0,2;SE(D23=3;'Memória de cálculo'!B24*0,09;SE(D23=4;'Memória de cálculo'!B24*0,16;SE(D23=5;'Memória de cálculo'!B24*0,28;SE(D23=6;'Memória de cálculo'!B24*0,52;SE(D23>=7;N28;"")))))))
    public void calculaValorAposCorrecao() {
        ParticipacaoCalcioCTCAtual = Valor / (Valor + ValorPotassio + ValorMagnesio + ValorHAL) * 100;

        double corretivos = this.calculaCorretivos();
        double calculoTeorCaOCorretivoPorcentagem = ((TeorCaOCorretivo > 0.01 ? TeorCaOCorretivo : corretivos) * 0.01783);
        double valorCalculoAuxiliar = Valor + ((calculoTeorCaOCorretivoPorcentagem + ((M22 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000)) * ('EQUILIBRIO E CORREÇÃO NA CTC'!O117)) + ((M22 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000);

        if (false) {
            ValorAposCorrecao = 0;
            return;
        }
        ValorAposCorrecao = 0;
    }

    private double calculaCorretivos() {
        switch (FonteCorretivo) {
            case 1:
                return 30.4;

            case 2:
                return 56;

            case 3:
                return 54;

            case 4:
                return 29;

            case 5:
                return 75.7;

            case 6:
                return 35;

            default:
                return 0;
        }
    }

}
