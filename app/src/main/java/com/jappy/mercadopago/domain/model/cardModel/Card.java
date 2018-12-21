package com.jappy.mercadopago.domain.model.cardModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Card {
    @SerializedName ("id")
    private String mId;

    @SerializedName ("name")
    private String mName;

    @SerializedName ("payment_type_id")
    private String mPayment_type_id;

    @SerializedName ("status")
    private String mStatus;

    @SerializedName ("secure_thumbnailtatus")
    private String mSecure_thumbnail;

    @SerializedName ("thumbnail")
    private String mThumbnail;

    @SerializedName ("deferred_capture")
    private String mDeferred_capture;

    @SerializedName ("min_allowed_amount")
    private Double mMin_allowed_amount;

    @SerializedName ("max_allowed_amount")
    private Double mMax_allowed_amount;

    @SerializedName ("settings")
    private List<CardSettings> mSettings;

    public Card(final String id, final String name, final String payment_type_id, final String status, final String secure_thumbnail, final String thumbnail,
            final String deferred_capture,
            final Double min_allowed_amount, final Double max_allowed_amount, final List<CardSettings> settings) {
        mId = id;
        mName = name;
        mPayment_type_id = payment_type_id;
        mStatus = status;
        mSecure_thumbnail = secure_thumbnail;
        mThumbnail = thumbnail;
        mDeferred_capture = deferred_capture;
        mMin_allowed_amount = min_allowed_amount;
        mMax_allowed_amount = max_allowed_amount;
        mSettings = settings;

    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPayment_type_id() {
        return mPayment_type_id;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getSecure_thumbnail() {
        return mSecure_thumbnail;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getDeferred_capture() {
        return mDeferred_capture;
    }

    public Double getMin_allowed_amount() {
        return mMin_allowed_amount;
    }

    public Double getMax_allowed_amount() {
        return mMax_allowed_amount;
    }

    public List<CardSettings> getSettings() {
        return mSettings;
    }
}
