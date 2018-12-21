package com.jappy.mercadopago.domain.model.cardModel;

public class CardSettings {
    private CardNumber mCardNumber;
    private CardBin mCardBin;
    private CardSecurity mCardSecurity;

    public CardNumber getCardNumber() {
        return mCardNumber;
    }

    public CardBin getCardBin() {
        return mCardBin;
    }

    public CardSecurity getCardSecurity() {
        return mCardSecurity;
    }
}
