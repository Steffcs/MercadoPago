package com.jappy.mercadopago.domain.model.bankModel;

import com.google.gson.annotations.SerializedName;

public class Bank {
    @SerializedName ("id")
    private  String mId ;

    @SerializedName ("name")
    private  String mName;

    @SerializedName ("secure_thumbnail")
    private String mSecure_thumbnail;

    @SerializedName ("thumbnail")
    private String mThumbnail;

    @SerializedName ("processing_mode")
    private String mProcessing_mode;

    @SerializedName ("merchant_account_id")
    private int mMerchant_account_id;

    public Bank(final String id, final String name, final String secure_thumbnail, final String thumbnail, final String processing_mode,
            final int merchant_account_id) {
        mId = id;
        mName = name;
        mSecure_thumbnail = secure_thumbnail;
        mThumbnail = thumbnail;
        mProcessing_mode = processing_mode;
        mMerchant_account_id = merchant_account_id;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSecure_thumbnail() {
        return mSecure_thumbnail;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getProcessing_mode() {
        return mProcessing_mode;
    }

    public int getMerchant_account_id() {
        return mMerchant_account_id;
    }
}
