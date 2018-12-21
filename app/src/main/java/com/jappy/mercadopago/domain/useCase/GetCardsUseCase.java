package com.jappy.mercadopago.domain.useCase;

import com.jappy.mercadopago.base.UseCase;
import com.jappy.mercadopago.data.repository.CardRepository;
import com.jappy.mercadopago.domain.model.cardModel.Card;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetCardsUseCase extends UseCase<List<Card>> {

    CardRepository mCardRepository;

    @Inject
    public GetCardsUseCase(CardRepository mCardRepository) {
        this.mCardRepository = mCardRepository;
    }

    @Override
    protected Single<List<Card>> createObservableUseCase() {
        return mCardRepository.getCards();
    }
}
