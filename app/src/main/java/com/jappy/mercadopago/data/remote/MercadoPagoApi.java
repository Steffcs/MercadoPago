package com.jappy.mercadopago.data.remote;

import com.jappy.mercadopago.domain.model.bankModel.Bank;
import com.jappy.mercadopago.domain.model.cardModel.Card;
import com.jappy.mercadopago.domain.model.feeModel.Fee;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MercadoPagoApi {

    @GET ("payment_methods")
    Single<List<Card>> getCards(@Query ("public_key") String public_key);

    @GET ("payment_methods/card_issuers")
    Single<List<Bank>> getBank(@Query ("public_key") String public_key, @Query ("payment_method_id") String payment_method_id);

    @GET ("payment_methods/installments")
    Single<List<Fee>> getFee(@Query ("public_key") String public_key, @Query ("payment_method_id") String payment_method_id,
            @Query ("amount") String amount,@Query ("issuer.id") String issuer_id);
}
