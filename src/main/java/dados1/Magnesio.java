package dados1;

import enums.EFonteFosforo;
import enums.ETexturaSolo;

public class Magnesio   {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private ETexturaSolo ValorTexturaSolo;
    private double ValorCelulaD23Planilha;
    private int FonteDeCorretivoParaUsar;
    private EFonteFosforo FonteDeFosforoParaUtilizar;
    private EFonteFosforo EFonteFosforo;


    public double calculaValorIdealMagnesio() {
        switch (this.ValorTexturaSolo) {
            case SOLO_ARGILOSO:
                this.ValorIdeal = 1.5;
                break;

            case SOLO_TEXTURA_MEDIA:
                this.ValorIdeal = 1.0;
                break;

            default:
                this.ValorIdeal = 0.0;
                break;
        }
        return this.ValorIdeal;
    }

    //H11+M60+O66+N70
    public double calculaValorAposCorrecao() {
        this.ValorAposCorrecao = this.Valor + this.calculaEquilibrioMagnesio() + this.calculoCelulaN70() + this.calculoCelulaN67();
        return this.ValorAposCorrecao;
    }

    //M60
    private double calculaEquilibrioMagnesio() {
        //M60 = SE(D52=1;N61;SE(D52=6;N62;SE(D52=2;N63;"0")))
        double celulaT70 = this.calculaCelulaT70();


        switch (this.FonteDeCorretivoParaUsar) {
            case 1:
                return 18 * 0.0248 * celulaT70;

            case 2:
                return 5 * 0.0248 * celulaT70;

            case 6:
                return 10 * 0.0248 * celulaT70;

            default:
                return 0;
        }
    }

    private double calculaCelulaT70() {
        //T70 = SE(P88>0,0001;P88;"0,0")
        //P88 = SE(P91>0,001;P91;SE(P91<=13;"0,0"))
        //P91 = 'Memória de cálculo'!F96/'Memória de cálculo'!G109
        //G109=I107+I105
        //I107=E101*0,01783
        //TODO - desenvolver este calcul complexo, analisar melhor
        double valorCalculoP88 = 0;
        if (valorCalculoP88 > 0.0001) {
            return valorCalculoP88;
        }
        return 0;
    }

    //N70
    private double calculoCelulaN70() {
        if (this.FonteDeFosforoParaUtilizar == this.EFonteFosforo.SUPERFOSFATO_TRIPLO_FONTE_FOSFORO) {
            return 0.0248 * 15;
        }
        return 0;
    }

    //N67
    private double calculoCelulaN67() {
        if (this.ValorCelulaD23Planilha == 5) {
            return 0.0248 * 15;
        }
        return 0;
    }
}
