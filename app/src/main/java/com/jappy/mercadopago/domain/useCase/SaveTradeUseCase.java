package com.jappy.mercadopago.domain.useCase;

import com.jappy.mercadopago.base.CompletableUseCase;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.utils.MDatabase;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class SaveTradeUseCase extends CompletableUseCase {

    private Trade mTrade;
    private MDatabase mMDatabase;

    public SaveTradeUseCase setData(final Trade trade) {
        this.mTrade = trade;
        return this;
    }

    @Inject
    public SaveTradeUseCase(MDatabase database) {
        this.mMDatabase = database;
    }

    @Override
    protected Completable createObservableUseCase() {
        return Completable.fromAction(() ->
                mMDatabase.mTradeDao().insertAll(mTrade));
    }
}
