package dados1;

import enums.ETexturaSolo;

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
    private ETexturaSolo ValorTexturaDoSolo;

    public Calcio() { }

    public double calculaValorIdeal() {
        switch (this.ValorTexturaDoSolo) {

            case SOLO_ARGILOSO:
                this.ValorIdeal = 6.0;
                break;

            case SOLO_TEXTURA_MEDIA:
                this.ValorIdeal = 4.0;
                break;

            default:
                this.ValorIdeal = 0;
                break;

        }
        return this.ValorIdeal;
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
        this.ParticipacaoCalcioCTCAtual = this.Valor / (this.Valor + this.ValorPotassio + this.ValorMagnesio + this.ValorHAL) * 100;

        double diferencaMetaFosforo = this.calculaDiferencaObjetivoFosforo();
        double kgAlqueire = this.calculaKgAlqueire();
        double corretivos = this.calculaCorretivos();
        double calculoTeorCaOCorretivoPorcentagem = ((this.TeorCaOCorretivo > 0.01 ? this.TeorCaOCorretivo : corretivos) * 0.01783);

        double ValorCalculoAuxiliar1 = ((((diferencaMetaFosforo * 2) * 2.29) * 100 / this.EficienciaFosforo / 100) * 100 / kgAlqueire) * 2.42; // B24
        double ValorCalculoAuxiliar2 = this.calculoAuxiliar2(ValorCalculoAuxiliar1);
        double valorCalculoAuxiliar3 = this.Valor + ((calculoTeorCaOCorretivoPorcentagem + ((ValorCalculoAuxiliar2 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000)) * ('EQUILIBRIO E CORREÇÃO NA CTC'!O117)) + ((ValorCalculoAuxiliar2 / 2.42) * ('EQUILIBRIO E CORREÇÃO NA CTC'!AM40) / 1000);

        if (false) {
            this.ValorAposCorrecao = 0;
            return;
        }
        this.ValorAposCorrecao = 0;
    }

    private double calculaDiferencaObjetivoFosforo() {
        double valorAuxiliar = this.TeorFosforoAtingir - this.ValorFosforo;
        if (valorAuxiliar < 0.01) {
            return 0.0;
        }
        return valorAuxiliar;
    }

    private double calculoAuxiliar2(double valorAuxiliar) {
        switch (this.FonteFosforoUtilizar) {
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
        switch (this.FonteCorretivo) {
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
        switch (this.FonteFosforoUtilizar) {
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
