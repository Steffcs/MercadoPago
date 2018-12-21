package com.jappy.mercadopago.domain.model.cardModel;

public class CardBin {
    private String mPattern;
    private String mInstallments_pattern;
    private String mExclusion_pattern;

    public String getPattern() {
        return mPattern;
    }

    public String getInstallments_pattern() {
        return mInstallments_pattern;
    }

    public String getExclusion_pattern() {
        return mExclusion_pattern;
    }
}
