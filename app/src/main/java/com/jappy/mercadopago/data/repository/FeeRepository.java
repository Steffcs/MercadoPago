package com.jappy.mercadopago.data.repository;

import com.jappy.mercadopago.domain.model.feeModel.Fee;

import java.util.List;

import io.reactivex.Single;

public interface FeeRepository {
    Single<List<Fee>> getFee(final String payment_method_id, final String amount,
            final String issuer_id);
}
