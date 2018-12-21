package com.jappy.mercadopago.domain.useCase;

import com.jappy.mercadopago.base.UseCase;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.utils.MDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetTradeUseCase extends UseCase<List<Trade>> {

    private MDatabase mMDatabase;

    public GetTradeUseCase setData() {
        return this;
    }

    @Inject
    public GetTradeUseCase(MDatabase database) {
        this.mMDatabase = database;
    }

    @Override
    protected Single<List<Trade>> createObservableUseCase() {
        return mMDatabase.mTradeDao().getAll();
    }
}
