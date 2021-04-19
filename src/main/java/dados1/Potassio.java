package dados1;

import enums.ETexturaSolo;

public class Potassio {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double ParticipacaoCTCAtual;
    private double ParticipacaoCTCDesejada;
    private ETexturaSolo ValorTexturaDoSolo;

    public double calculaValorIdeal(ETexturaSolo texturaSolo) {
        switch (texturaSolo) {
            case SOLO_ARGILOSO:
                this.ValorIdeal = 0.35;
                break;

            case SOLO_TEXTURA_MEDIA:
                this.ValorIdeal = 0.25;
                break;

            default:
                this.ValorIdeal = 0;
                break;

        }
        return this.ValorIdeal;
    }

    public double calculaValorAposCorrecao() {
        double valorCalculoAuxiliar = (this.Valor * this.ParticipacaoCTCDesejada / this.ParticipacaoCTCAtual) - this.Valor;
        if (this.Valor > 0.5 || valorCalculoAuxiliar < 0.01) {
            this.ValorAposCorrecao = this.Valor;
        } else if (valorCalculoAuxiliar > 0.01) {
            this.ValorAposCorrecao = (this.Valor + valorCalculoAuxiliar);
        } else {
            this.ValorAposCorrecao = 0;
        }
        return ValorAposCorrecao;
    }

}
