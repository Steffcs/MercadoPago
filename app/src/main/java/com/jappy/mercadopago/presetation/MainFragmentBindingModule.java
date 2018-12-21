package com.jappy.mercadopago.presetation;

import com.jappy.mercadopago.presetation.amount.AmountFragment;
import com.jappy.mercadopago.presetation.bank.BankListsFragment;
import com.jappy.mercadopago.presetation.card.CardListFragment;
import com.jappy.mercadopago.presetation.fee.FeeFragment;
import com.jappy.mercadopago.presetation.trade.TradeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract AmountFragment provideAmountFragment();

    @ContributesAndroidInjector
    abstract CardListFragment provideCardListFragment();

    @ContributesAndroidInjector
    abstract BankListsFragment provideBankFragment();

    @ContributesAndroidInjector
    abstract FeeFragment provideFeeFragment();

    @ContributesAndroidInjector
    abstract TradeFragment provideTradeFragment();
}