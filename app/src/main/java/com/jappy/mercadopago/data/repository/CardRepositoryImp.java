package com.jappy.mercadopago.data.repository;

import com.google.gson.Gson;

import com.jappy.mercadopago.BuildConfig;
import com.jappy.mercadopago.data.remote.MercadoPagoApi;
import com.jappy.mercadopago.domain.model.cardModel.Card;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CardRepositoryImp implements CardRepository {

    MercadoPagoApi apiService;

    @Inject
    public CardRepositoryImp(MercadoPagoApi apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Card>> getCards() {
        return apiService.getCards(BuildConfig.MERCADOPAGO_APIKEY).map(cards -> {
            Gson a = new Gson();
            Log.d("CARDDD33", a.toJson(cards.get(0).getName()));
            return  cards;
        });
    }
}
