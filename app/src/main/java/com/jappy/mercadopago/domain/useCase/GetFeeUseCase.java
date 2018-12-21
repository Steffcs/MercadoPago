package com.jappy.mercadopago.domain.useCase;

import com.jappy.mercadopago.base.UseCase;
import com.jappy.mercadopago.data.repository.FeeRepository;
import com.jappy.mercadopago.domain.model.feeModel.Fee;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetFeeUseCase extends UseCase<List<Fee>> {

    private FeeRepository mFeeRepository;
    private String mPayment_method_id;
    private String mAmount;
    private String mIssuer_id;

    public GetFeeUseCase setData(final String payment_method_id, final String amount,
            final String issuer_id) {
        this.mPayment_method_id = payment_method_id;
        this.mAmount = amount;
        this.mIssuer_id = issuer_id;

        return this;
    }

    @Inject
    public GetFeeUseCase(FeeRepository mFeeRepository) { this.mFeeRepository = mFeeRepository; }

    @Override
    protected Single<List<Fee>> createObservableUseCase() {
        return mFeeRepository.getFee(mPayment_method_id,mAmount,mIssuer_id);
    }
}
