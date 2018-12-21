package com.jappy.mercadopago.domain.useCase;

import com.jappy.mercadopago.base.UseCase;
import com.jappy.mercadopago.data.repository.BankRepository;
import com.jappy.mercadopago.domain.model.bankModel.Bank;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBankUseCase extends UseCase<List<Bank>> {

    private final BankRepository mbankRepository;
    private String mdata;

    public GetBankUseCase setData(String data) {
        this.mdata = data;

        return this;
    }

    @Inject
    public GetBankUseCase(BankRepository bankRepository) {
        this.mbankRepository = bankRepository;
    }

    @Override
    protected Single<List<Bank>> createObservableUseCase() {
        return mbankRepository.getBank(mdata);
    }
}
