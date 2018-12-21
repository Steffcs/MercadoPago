package com.jappy.mercadopago.domain.model.trade;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "trade")
public class Trade {

    @PrimaryKey (autoGenerate = true)
    public int id;
    public final String amount;
    public final int fee;
    public final String issuer_id;
    public final String payment;
    public final String date;

    public Trade(final String amount, final int fee, final String issuer_id, final String payment, final String date) {
        this.amount = amount;
        this.fee = fee;
        this.issuer_id = issuer_id;
        this.payment = payment;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public int getFee() {
        return fee;
    }

    public String getIssuer_id() {
        return issuer_id;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }
}
