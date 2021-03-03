package dados1;

public class Calcio {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double TeorCaOCorretivo;
    private int FonteCorretivo;
    private int FonteFosforoUtilizar;
    private double ParticipacaoCalcioCTCAtual;
    private double ParticipacaoCalcioCTCDesejada;
    private double ValorHAL;
    private double ValorPotassio;
    private double ValorMagnesio;
    private double ValorFosforo;
    private double EficienciaFosforo;
    private double TeorFosforoAtingir;

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

    public double getValorFosforo() {
        return ValorFosforo;
    }

    public double getEficienciaFosforo() {
        return EficienciaFosforo;
    }

    public double getTeorFosforoAtingir() {
        return TeorFosforoAtingir;
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

    public void setValorFosforo(double valorFosforo) {
        ValorFosforo = valorFosforo;
    }

    public void setEficienciaFosforo(double eficienciaFosforo) {
        EficienciaFosforo = eficienciaFosforo;
    }

    public void setTeorFosforoAtingir(double teorFosforoAtingir) {
        TeorFosforoAtingir = teorFosforoAtingir;
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

    //b24 = ((((diferencaMetaFosforo * 2) * 2,29) * 100 / EficienciaFosforo / 100) * 100 / kgAlqueire) * 2,42
    // e48=SEERRO(P10;"")
    // d23 = FonteFosforoUtilizar
    // o117 = =SE(P88>0,0001;P88;"0,0")
    // p88 =  =SE(P91>0,001;P91;SE(P91<=13;""0,0""))"
    // p91 = ((Valor * 'ParticipacaoCalcioCTCDesejada / ParticipacaoCalcioCTCAtual) - Valor - (M22 / 2,42 * (='EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000) / (calculoTeorCaOCorretivoPorcentagem + (M22 / 2,42 * (='EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000))
    // am40 = =SE(D23=1;"0,49924";SE(D23=2;"0,33877";SE(D23=3;"0,0";SE(D23=4;"0,0";SE(D23=5;"0,49924";SE(D23=6;"0,92716";SE(D23>=7;AM41;"")))))))1
    // am41 = =SE(D23=7;"0,92716";SE(D23=8;"0,80235";SE(D23=9;"0,49924";SE(D23=10;"0,795218";SE(D23=11;"0,0";SE(D23=12;"0,0";""))))))

    public void calculaValorAposCorrecao() {
        ParticipacaoCalcioCTCAtual = Valor / (Valor + ValorPotassio + ValorMagnesio + ValorHAL) * 100;

        double diferencaMetaFosforo = this.calculaDiferencaObjetivoFosforo();
        double kgAlqueire = this.calculaKgAlqueire();
        double corretivos = this.calculaCorretivos();
        double calculoTeorCaOCorretivoPorcentagem = ((TeorCaOCorretivo > 0.01 ? TeorCaOCorretivo : corretivos) * 0.01783);

        double ValorCalculoAuxiliar1 = ((((diferencaMetaFosforo * 2) * 2.29) * 100 / EficienciaFosforo / 100) * 100 / kgAlqueire) * 2.42; // B24
        double ValorCalculoAuxiliar2 = this.calculoAuxiliar2(ValorCalculoAuxiliar1);
        double valorCalculoAuxiliar3 = Valor + ((calculoTeorCaOCorretivoPorcentagem + ((ValorCalculoAuxiliar2 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000)) * ('EQUILIBRIO E CORREÇÃO NA CTC'!O117)) + ((ValorCalculoAuxiliar2 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000);

        if (false) {
            ValorAposCorrecao = 0;
            return;
        }
        ValorAposCorrecao = 0;
    }

    private double calculaDiferencaObjetivoFosforo() {
        double valorAuxiliar = TeorFosforoAtingir - ValorFosforo;
        if (valorAuxiliar < 0.01) {
            return 0.0;
        }
        return valorAuxiliar;
    }

    private double calculoAuxiliar2(double valorAuxiliar) {
        switch (FonteFosforoUtilizar) {
            case 1:
                return valorAuxiliar * 0.28;

            case 2:
                return valorAuxiliar * 0.2;

            case 3:
                return valorAuxiliar * 0.09;

            case 4:
                return valorAuxiliar * 0.16;

            case 5:
                return valorAuxiliar * 0.28;

            case 6:
                return valorAuxiliar * 0.52;

            case 7:
                return valorAuxiliar * 0.52;

            case 8:
                return valorAuxiliar * 0.45;

            case 9:
                return valorAuxiliar * 0.28;

            case 10:
                return valorAuxiliar * 0.44;

            case 11:
                return valorAuxiliar * 0;

            case 12:
                return valorAuxiliar * 0.18;

            default:
                return 0.0;
        }
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

    private double calculaKgAlqueire() {
        switch (FonteFosforoUtilizar) {
            case 1:
                return 18;

            case 2:
                return 41;

            case 3:
                return 48;

            case 4:
                return 45;

            case 5:
                return 18;

            case 6:
                return 33;

            case 7:
                return 29;

            case 8:
                return 32;

            case 9:
                return 24;

            case 10:
                return 18.5;

            default:
                return 0;
        }
    }

}
