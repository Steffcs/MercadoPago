package com.jappy.mercadopago.di.module;

import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.presetation.amount.AmountViewModel;
import com.jappy.mercadopago.presetation.bank.BankViewModel;
import com.jappy.mercadopago.presetation.card.CardViewModel;
import com.jappy.mercadopago.presetation.fee.FeeViewModel;
import com.jappy.mercadopago.presetation.trade.TradeViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey (AmountViewModel.class)
    abstract ViewModel bindAmountViewModel(AmountViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey (CardViewModel.class)
    abstract ViewModel bindCardViewModel(CardViewModel cardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey (BankViewModel.class)
    abstract ViewModel bindBankViewModel(BankViewModel bankViewModel);

    @Binds
    @IntoMap
    @ViewModelKey (FeeViewModel.class)
    abstract ViewModel bindFeeViewModel(FeeViewModel bankViewModel);

    @Binds
    @IntoMap
    @ViewModelKey (TradeViewModel.class)
    abstract ViewModel bindTradeViewModel(TradeViewModel tradeViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
