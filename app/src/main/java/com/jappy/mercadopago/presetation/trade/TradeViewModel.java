package com.jappy.mercadopago.presetation.trade;

import com.jappy.mercadopago.base.UseCaseObserver;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.domain.useCase.GetTradeUseCase;
import com.jappy.mercadopago.utils.Status;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TradeViewModel extends ViewModel {
    private final MutableLiveData<List<Trade>> mFeed = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    private final MutableLiveData<Status> mMessage = new MutableLiveData<>();

    private GetTradeUseCase mGetTradeUseCase;

    MutableLiveData<List<Trade>> getResponse() {
        return mFeed;
    }

    MutableLiveData<Boolean> getLoader() {
        return mLoading;
    }

    MutableLiveData<Status> getMessage() {
        return mMessage;
    }

    @Inject
    public TradeViewModel(GetTradeUseCase getTradeUseCase) {
        this.mGetTradeUseCase = getTradeUseCase;
        mLoading.setValue(true);
        getTrade();
    }

    public void getTrade() {
        mGetTradeUseCase.setData().execute(new UseCaseObserver<List<Trade>>() {
            @Override
            public void onSuccess(final List<Trade> value) {
                if (value != null && value.size() > 0) {
                    mFeed.setValue(value);
                    mLoading.setValue(false);
                } else {
                    mLoading.setValue(false);
                    mMessage.setValue(Status.EMPTY);
                }
            }

            @Override
            public void onError(final Throwable e) {
                super.onError(e);
                mLoading.setValue(false);
                mMessage.setValue(Status.ERROR);
            }
        });
    }
}
