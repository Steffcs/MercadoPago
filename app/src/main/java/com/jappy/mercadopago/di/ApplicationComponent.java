package com.jappy.mercadopago.di;

import com.jappy.mercadopago.MercadoAplication;
import com.jappy.mercadopago.base.BaseApplication;
import com.jappy.mercadopago.di.module.ActivityBindingModule;
import com.jappy.mercadopago.di.module.ApplicationModule;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component (modules = {ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}