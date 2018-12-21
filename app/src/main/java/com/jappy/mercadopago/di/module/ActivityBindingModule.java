package com.jappy.mercadopago.di.module;

import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.presetation.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector (modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
