package enums;

public enum ETexturaSolo {
    SOLO_OUTRO(0),
    SOLO_ARGILOSO(1),
    SOLO_TEXTURA_MEDIA(2);

    public int descricao;

    ETexturaSolo(int descricao) {
        this.descricao = descricao;
    }
}
