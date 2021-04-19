package dados1;

import enums.ETexturaSolo;

public class Enxofre {

    private double Valor;
    private double ValorIdeal;
    private ETexturaSolo ValorTexturaDoSolo;

    public double calculaValorIdealEnxofre() {
        switch (this.ValorTexturaDoSolo) {
            case SOLO_ARGILOSO:
                this.ValorIdeal = 9.0;
                break;

            case SOLO_TEXTURA_MEDIA:
                this.ValorIdeal = 6.0;
                break;

            default:
                this.Valor = 0;
                break;
        }
        return this.ValorIdeal;
    }

}
