package dados1;

import enums.EFonteCorretivoCalcioMagnesio;
import enums.EFonteFosforo;
import enums.ETexturaSolo;


public class Calcio {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double TeorCaOCorretivo;
    private EFonteCorretivoCalcioMagnesio FonteCorretivo;
    private EFonteFosforo FonteFosforoUtilizar;
    private double ParticipacaoCalcioCTCAtual;
    private double ParticipacaoCalcioCTCDesejada;
    private double ValorHAL;
    private double ValorPotassio;
    private double ValorMagnesio;
    private double ValorFosforo;
    private double EficienciaFosforo;
    private double TeorFosforoAtingir;
    private ETexturaSolo ValorTexturaDoSolo;
    private EFonteFosforo EFonteFosforo;
    private enums.EFonteCorretivoCalcioMagnesio EFonteCorretivoCalcioMagnesio;
  
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
        double[] opcoesCalculoAuxiliar = {0.28, 0.2, 0.09, 0.16, 0.28, 0.52, 0.52, 0.45, 0.28, 0.44, 0, 0.18};
        int fonteFosforoUtilizarInteiro = Integer.parseInt(this.FonteFosforoUtilizar.toString());
        if (fonteFosforoUtilizarInteiro > 0 && fonteFosforoUtilizarInteiro < 13) {
            return opcoesCalculoAuxiliar[fonteFosforoUtilizarInteiro];
        }
        return 0;
    }


    private double calculaCorretivos() {
        double[] opcoesCalculoCorretivo = {30.4, 56, 54, 29, 75.7, 35};
        int fonteCorretivoInteiro = Integer.parseInt(this.FonteCorretivo.toString());
        if (fonteCorretivoInteiro > 0 && fonteCorretivoInteiro < 7) {
            return opcoesCalculoCorretivo[fonteCorretivoInteiro];
        }
        return 0;
    }

    private double calculaKgAlqueire() {
        double[] opcoesCalculoKgAlqueire = {18, 41, 48, 45, 18, 33, 29, 32, 24, 18.5};
        int fonteFosoforoUtilizarInteiro = Integer.parseInt(this.FonteFosforoUtilizar.toString());
        if (fonteFosoforoUtilizarInteiro > 0 && fonteFosoforoUtilizarInteiro < 11) {
            return opcoesCalculoKgAlqueire[fonteFosoforoUtilizarInteiro];
        }
        return 0;
    }

}
