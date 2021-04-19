package dados1;

import enums.ETexturaSolo;

public class Fosforo {

    private double Valor;
    private double ValorIdeal;
    private double ValorAposCorrecao;
    private double ValorParaAtingir;
    private ETexturaSolo ValorTexturaDoSolo;

    public double calculaValorIdeal(int texturaSolo) {
        switch (this.ValorTexturaDoSolo) {

            case SOLO_ARGILOSO:
                this.ValorIdeal = 9;
                break;

            case SOLO_TEXTURA_MEDIA:
                this.ValorIdeal = 12;
                break;

            default:
                this.ValorIdeal = 0;
                break;

        }
        return this.ValorIdeal;
    }

    public void calculaValorAposCorrecao() {
        if (this.ValorParaAtingir > 0.01) {
            this.ValorAposCorrecao = this.ValorParaAtingir;
            return;
        }
        this.ValorAposCorrecao = 0;
    }

}
