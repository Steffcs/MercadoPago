package com.jappy.mercadopago.presetation.fee;

import com.jappy.mercadopago.base.UseCaseObserver;
import com.jappy.mercadopago.domain.model.feeModel.Fee;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.domain.useCase.GetFeeUseCase;
import com.jappy.mercadopago.domain.useCase.SaveTradeUseCase;
import com.jappy.mercadopago.utils.Status;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;

public class FeeViewModel extends ViewModel {

    private final MutableLiveData<List<Fee>> mFeed = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    private final MutableLiveData<Status> mMessage = new MutableLiveData<>();

    private GetFeeUseCase mGetFeeUseCase;

    private SaveTradeUseCase mSaveTradeUseCase;
    MutableLiveData<List<Fee>> getResponse() {
        return mFeed;
    }

    MutableLiveData<Boolean> getLoader() {
        return mLoading;
    }

    MutableLiveData<Status> getMessage() {
        return mMessage;
    }

    @Inject
    public FeeViewModel(GetFeeUseCase getFeeUseCase,SaveTradeUseCase save) {
        this.mGetFeeUseCase = getFeeUseCase;
        this.mSaveTradeUseCase = save;
    }

    public void init(final String payment, final String amount, final String issuer_id) {
        mLoading.setValue(true);
        getBank(payment, amount, issuer_id);
    }

    public void getBank(final String payment, final String amount, final String issuer_id) {
        mGetFeeUseCase.setData(payment, amount, issuer_id).execute(new UseCaseObserver<List<Fee>>() {
            @Override
            public void onSuccess(final List<Fee> value) {

                if (value != null && value.size() > 0) {
                    mFeed.setValue(value);
                    mLoading.setValue(false);
                } else {
                    mMessage.setValue(Status.EMPTY);
                    mLoading.setValue(false);
                }
            }

            @Override
            public void onError(final Throwable e) {
                mLoading.setValue(false);
                mMessage.setValue(Status.ERROR);
            }
        });
    }

    public Completable saveTrade(final Trade trade) {
        return Completable.fromAction(() -> {

            // if there's no use, create a new user.
            // if we already have a user, then, since the user object is immutable,
            // create a new user, with the id of the previous user and the updated user name.
            mSaveTradeUseCase.setData(trade).execute(new UseCaseObserver<Trade>() {

                @Override
                public void onComplete() {
                    super.onComplete();
                }
            });

        });
    }
}
