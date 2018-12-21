package com.jappy.mercadopago.domain.model.feeModel;

import com.google.gson.annotations.SerializedName;

public class Payer {

    @SerializedName ("installments")
    private int mInstallments;

    @SerializedName ("installment_rate")
    private Double mInstallment_rate;

    @SerializedName ("discount_rate")
    private int mDiscount_rate;

    @SerializedName ("min_allowed_amount")
    private int mMin_allowed_amount;

    @SerializedName ("max_allowed_amount")
    private int mMax_allowed_amount;

    @SerializedName ("recommended_message")
    private String mRecommended_message;

    @SerializedName ("installment_amount")
    private Double mInstallment_amount;

    @SerializedName ("total_amount")
    private Double mtotal_amount;

    public Payer(final int installments, final Double installment_rate, final int discount_rate, final int min_allowed_amount, final int max_allowed_amount,
            final String recommended_message,
            final Double installment_amount, final Double mtotal_amount) {
        mInstallments = installments;
        mInstallment_rate = installment_rate;
        mDiscount_rate = discount_rate;
        mMin_allowed_amount = min_allowed_amount;
        mMax_allowed_amount = max_allowed_amount;
        mRecommended_message = recommended_message;
        mInstallment_amount = installment_amount;
        this.mtotal_amount = mtotal_amount;
    }

    public int getInstallments() {
        return mInstallments;
    }

    public Double getInstallment_rate() {
        return mInstallment_rate;
    }

    public int getDiscount_rate() {
        return mDiscount_rate;
    }

    public int getMin_allowed_amount() {
        return mMin_allowed_amount;
    }

    public int getMax_allowed_amount() {
        return mMax_allowed_amount;
    }

    public String getRecommended_message() {
        return mRecommended_message;
    }

    public Double getInstallment_amount() {
        return mInstallment_amount;
    }

    public Double getMtotal_amount() {
        return mtotal_amount;
    }
}
