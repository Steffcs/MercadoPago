package com.jappy.mercadopago.domain.model.cardModel;

public class CardSecurity {
    private int mlength;
    private String mCard_location;
    private String mMode;

    public int getMlength() {
        return mlength;
    }

    public void setMlength(final int mlength) {
        this.mlength = mlength;
    }

    public String getCard_location() {
        return mCard_location;
    }

    public void setCard_location(final String card_location) {
        mCard_location = card_location;
    }

    public String getMode() {
        return mMode;
    }

    public void setMode(final String mode) {
        mMode = mode;
    }
}
