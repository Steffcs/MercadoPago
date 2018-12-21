package com.jappy.mercadopago.presetation.card;

import com.jappy.mercadopago.base.UseCaseObserver;
import com.jappy.mercadopago.domain.model.cardModel.Card;
import com.jappy.mercadopago.domain.useCase.GetCardsUseCase;
import com.jappy.mercadopago.utils.Status;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CardViewModel extends ViewModel {

    private final MutableLiveData<List<Card>> mcard = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Status> message = new MutableLiveData<>();

    private GetCardsUseCase mGetCardsUseCase;

    @Inject
    public CardViewModel(GetCardsUseCase getCardsUseCase) {
        this.mGetCardsUseCase = getCardsUseCase;
        loading.setValue(true);
        init();
    }

    MutableLiveData<List<Card>> getResponse() {
        return mcard;
    }

    MutableLiveData<Boolean> getLoader() {
        return loading;
    }

    MutableLiveData<Status> getMessage() {
        return message;
    }

    public void init() {
        mGetCardsUseCase.execute(new UseCaseObserver<List<Card>>() {

            @Override
            public void onSuccess(final List<Card> value) {
                if(value!=null && value.size()>0){
                    mcard.setValue(value);
                    loading.setValue(false);
                }else {
                    message.setValue(Status.EMPTY);
                    loading.setValue(false);
                }

            }

            @Override
            public void onError(final Throwable e) {
                message.setValue(Status.ERROR);
                loading.setValue(false);
            }
        });
    }
}


