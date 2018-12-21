package com.jappy.mercadopago.data.repository;

import com.jappy.mercadopago.domain.model.cardModel.Card;

import java.util.List;

import io.reactivex.Single;

public interface CardRepository {
    Single<List<Card>> getCards();
}
