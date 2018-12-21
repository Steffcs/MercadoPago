package com.jappy.mercadopago.data.repository;

import com.jappy.mercadopago.domain.model.bankModel.Bank;

import java.util.List;

import io.reactivex.Single;

public interface BankRepository {
    Single<List<Bank>> getBank(final String payment_method_id);
}

