package com.jappy.mercadopago.data.repository;

import com.jappy.mercadopago.BuildConfig;
import com.jappy.mercadopago.data.remote.MercadoPagoApi;
import com.jappy.mercadopago.domain.model.bankModel.Bank;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class BankRepositoryImp implements BankRepository {

    MercadoPagoApi apiService;

    @Inject
    public BankRepositoryImp(MercadoPagoApi apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Bank>> getBank(final String payment_method_id) {
        return apiService.getBank(BuildConfig.MERCADOPAGO_APIKEY, payment_method_id).map(banks -> banks);
    }
}
