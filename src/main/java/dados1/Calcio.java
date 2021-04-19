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
        switch (this.FonteFosforoUtilizar) {
            case SUPERFOSFATO_SIMPLES_FONT_FOSFORO:
                return valorAuxiliar * 0.28;

            case DAP_FONTE_FOSFORO:
                return valorAuxiliar * 0.2;

            case FOSFATO_GAFSA_FONTE_FOSFORO:
                return valorAuxiliar * 0.09;

            case ESCORIA_DE_THOMAS_FONTE_FOSFORO:
                return valorAuxiliar * 0.16;

            case SUPERFOSFATO_TRIPLO_FONTE_FOSFORO:
                return valorAuxiliar * 0.28;

            case YOORIN_FONTE_FOSFORO:
                return valorAuxiliar * 0.52;

            case FOSFATO_DAOUI_FONTE_FOSFORO:
                return valorAuxiliar * 0.52;

            case ACIDO_FOSFORICO_FONTE_FOSFORO:
                return valorAuxiliar * 0.45;

            case MAP_FONTE_FOSFORO:
                return valorAuxiliar * 0.28;

            case FOSFATO_ARAD_FONTE_FOSFORO:
                return valorAuxiliar * 0.44;

            case FOSFATO_PATO_MINAS_FONTE_FOSFORO:
                return valorAuxiliar * 0;

            case MULTIFOSFATO_MAGNESIANO_FONTE_FOSFORO:
                return valorAuxiliar * 0.18;

            default:
                return 0.0;
        }
    }


    private double calculaCorretivos() {
        switch (this.FonteCorretivo) {
            case CALCARIO_DOLOMITICO_FONTE_CALCIO_MAGNESIO:
                return 30.4;

            case CALCARIO_CALCITICO_FONTE_CALCIO_MAGNESIO:
                return 56;

            case CALCARIO_DE_CONCHA_FONTE_CALCIO_MAGNESIO:
                return 54;

            case GESSO_AGRICOLA_FONTE_CALCIO_MAGNESIO:
                return 29;

            case HIDROXIDO_CALCIO_FONTE_CALCIO_MAGNESIO:
                return 75.7;

            case CALCARIO_MAGNESIANO_FONTE_CALCIO_MAGNESIO:
                return 35;

            default:
                return 0;
        }
    }

    private double calculaKgAlqueire() {
        switch (this.FonteFosforoUtilizar) {
            case SUPERFOSFATO_SIMPLES_FONT_FOSFORO:
                return 18;

            case DAP_FONTE_FOSFORO:
                return 41;

            case FOSFATO_GAFSA_FONTE_FOSFORO:
                return 48;

            case ESCORIA_DE_THOMAS_FONTE_FOSFORO:
                return 45;

            case SUPERFOSFATO_TRIPLO_FONTE_FOSFORO:
                return 18;

            case YOORIN_FONTE_FOSFORO:
                return 33;

            case FOSFATO_DAOUI_FONTE_FOSFORO:
                return 29;

            case ACIDO_FOSFORICO_FONTE_FOSFORO:
                return 32;

            case MAP_FONTE_FOSFORO:
                return 24;

            case FOSFATO_ARAD_FONTE_FOSFORO:
                return 18.5;

            default:
                return 0;
        }
    }

}
