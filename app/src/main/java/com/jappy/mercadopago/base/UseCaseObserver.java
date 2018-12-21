package com.jappy.mercadopago.base;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCaseObserver<T> extends DisposableObserver<T> implements SingleObserver<T>,CompletableObserver {
    @Override
    public void onNext(final T t) {

    }

    @Override
    public void onSuccess(final T o) {

    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}