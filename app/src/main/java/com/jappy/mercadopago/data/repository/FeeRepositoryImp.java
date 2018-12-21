package com.jappy.mercadopago.data.repository;

import com.jappy.mercadopago.BuildConfig;
import com.jappy.mercadopago.data.remote.MercadoPagoApi;
import com.jappy.mercadopago.domain.model.feeModel.Fee;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FeeRepositoryImp implements FeeRepository {

    MercadoPagoApi apiService;

    @Inject
    public FeeRepositoryImp(MercadoPagoApi apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Fee>> getFee(final String payment_method_id, final String amount, final String issuer_id) {
        return apiService.getFee(BuildConfig.MERCADOPAGO_APIKEY, payment_method_id, amount, issuer_id).map(fees -> fees);
    }
}
