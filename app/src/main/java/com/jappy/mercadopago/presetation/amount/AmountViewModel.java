package com.jappy.mercadopago.presetation.amount;

import com.jappy.mercadopago.data.repository.CardRepositoryImp;
import com.jappy.mercadopago.domain.model.cardModel.Card;
import com.jappy.mercadopago.domain.useCase.GetTradeUseCase;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class AmountViewModel extends ViewModel {

    private final MutableLiveData<Card> cards = new MutableLiveData<>();

    private CardRepositoryImp mCardRepository;
    private CompositeDisposable mdisposable;
    private GetTradeUseCase mGetTradeUseCase;

    @Inject
    public AmountViewModel(CardRepositoryImp counterRepositoryImp, GetTradeUseCase getTradeUseCase) {
        this.mCardRepository = counterRepositoryImp;
        this.mdisposable = new CompositeDisposable();
        this.mGetTradeUseCase = getTradeUseCase;
    }
}
