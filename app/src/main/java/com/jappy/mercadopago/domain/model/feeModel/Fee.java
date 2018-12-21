package com.jappy.mercadopago.domain.model.feeModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fee {

    @SerializedName ("payment_method_id")
    private String mPayment_method_id;

    @SerializedName ("payment_type_id")
    private String mPayment_type_id;

    @SerializedName ("processing_mode")
    private String mProcessing_mode;

    @SerializedName ("payer_costs")
    private List<Payer> mPayer_costs;

    public Fee(final String payment_method_id, final String payment_type_id, final String processing_mode, final List<Payer> payer_costs) {
        mPayment_method_id = payment_method_id;
        mPayment_type_id = payment_type_id;
        mProcessing_mode = processing_mode;
        mPayer_costs = payer_costs;
    }

    public String getPayment_method_id() {
        return mPayment_method_id;
    }

    public String getPayment_type_id() {
        return mPayment_type_id;
    }

    public String getProcessing_mode() {
        return mProcessing_mode;
    }

    public List<Payer> getPayer_costs() {
        return mPayer_costs;
    }
}
