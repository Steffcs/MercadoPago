package com.jappy.mercadopago.base;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import dagger.internal.Preconditions;


public abstract class CompletableUseCase {
private CompositeDisposable compositeDisposable = new CompositeDisposable();


public void execute( UseCaseObserver disposableObserver) {
        Preconditions.checkNotNull(disposableObserver);
        Completable observable = this.createObservableUseCase()
        .subscribeOn(getSubscribeOn())
        .observeOn(getObserveOn());
        UseCaseObserver observer = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
        }

public Completable getObservable() {
        return createObservableUseCase();
        }

protected Scheduler getSubscribeOn() {return Schedulers.io();}
protected Scheduler getObserveOn(){return AndroidSchedulers.mainThread();}

public void dispose() {
        if (!compositeDisposable.isDisposed()) {
        compositeDisposable.dispose();
        }
        }

protected abstract Completable createObservableUseCase();
}
