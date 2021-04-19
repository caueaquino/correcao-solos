package enums;

public enum EFonteCorretivoCalcioMagnesio {

        OUTRA_FONTE_CALCIO_MAGNESIO(0),
        CALCARIO_DOLOMITICO_FONTE_CALCIO_MAGNESIO(1),
        CALCARIO_CALCITICO_FONTE_CALCIO_MAGNESIO(2),
        CALCARIO_DE_CONCHA_FONTE_CALCIO_MAGNESIO(3),
        GESSO_AGRICOLA_FONTE_CALCIO_MAGNESIO(4),
        HIDROXIDO_CALCIO_FONTE_CALCIO_MAGNESIO(5),
        CALCARIO_MAGNESIANO_FONTE_CALCIO_MAGNESIO(6);


        public int descricao;

        EFonteCorretivoCalcioMagnesio(int descricao) {
            this.descricao = descricao;
        }
}
