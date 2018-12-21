package com.jappy.mercadopago.presetation.bank;

import com.jappy.mercadopago.base.UseCaseObserver;
import com.jappy.mercadopago.domain.model.bankModel.Bank;
import com.jappy.mercadopago.domain.useCase.GetBankUseCase;
import com.jappy.mercadopago.utils.Status;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BankViewModel extends ViewModel {

    private final MutableLiveData<List<Bank>> mbank = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Status> message = new MutableLiveData<>();

    private GetBankUseCase mGetBankUseCase;

    MutableLiveData<List<Bank>> getResponse() {
        return mbank;
    }

    MutableLiveData<Boolean> getLoader() {
        return loading;
    }

    MutableLiveData<Status> getMessage() {
        return message;
    }

    @Inject
    public BankViewModel(GetBankUseCase getBankUseCase) {
        this.mGetBankUseCase = getBankUseCase;
    }

    public void init(String payment) {
        loading.setValue(true);
        getBank(payment);
    }

    private void getBank(String payment) {
        mGetBankUseCase.setData(payment).execute(new UseCaseObserver<List<Bank>>() {
            @Override
            public void onSuccess(final List<Bank> value) {

                if (value != null && value.size() > 0) {
                    mbank.setValue(value);
                    loading.setValue(false);
                } else {
                    message.setValue(Status.EMPTY);
                    loading.setValue(false);
                }
            }

            @Override
            public void onError(final Throwable e) {

                loading.setValue(false);
                message.setValue(Status.ERROR);
            }
        });
    }
}