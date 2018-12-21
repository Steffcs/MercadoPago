package com.jappy.mercadopago.di.module;

import com.jappy.mercadopago.data.remote.ApiServiceFactory;
import com.jappy.mercadopago.data.remote.MercadoPagoApi;
import com.jappy.mercadopago.data.repository.BankRepository;
import com.jappy.mercadopago.data.repository.BankRepositoryImp;
import com.jappy.mercadopago.data.repository.CardRepository;
import com.jappy.mercadopago.data.repository.CardRepositoryImp;
import com.jappy.mercadopago.data.repository.FeeRepository;
import com.jappy.mercadopago.data.repository.FeeRepositoryImp;
import com.jappy.mercadopago.domain.model.trade.TradeDao;
import com.jappy.mercadopago.presetation.bank.BankListsAdapter;
import com.jappy.mercadopago.presetation.card.CardListsAdapter;
import com.jappy.mercadopago.presetation.fee.FeeListsAdapter;
import com.jappy.mercadopago.presetation.trade.TradeListAdapter;
import com.jappy.mercadopago.utils.MDatabase;

import android.app.Application;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module (includes = ViewModelModule.class)
public class ApplicationModule {

    @Provides
    @Singleton
    MDatabase providesEventDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(), MDatabase.class, "pago_db").build();
    }

    @Provides
    @Singleton
    TradeDao provideMovieDao(MDatabase mDatabase) {
        return mDatabase.mTradeDao();
    }

    @Provides
    CardListsAdapter providesAdapter() {
        return new CardListsAdapter();
    }

    @Provides
    CardRepository providesRepository(MercadoPagoApi api) {
        return new CardRepositoryImp(api);
    }

    @Provides
    BankListsAdapter providesBankAdapter() {
        return new BankListsAdapter();
    }

    @Provides
    FeeRepository providesFeeRepository(MercadoPagoApi api) {
        return new FeeRepositoryImp(api);
    }

    @Provides
    FeeListsAdapter providesFeeAdapter() {
        return new FeeListsAdapter();
    }

    @Provides
    TradeListAdapter providesTradeAdapter() {
        return new TradeListAdapter();
    }


    @Provides
    BankRepository providesBankRepository(MercadoPagoApi api) {
        return new BankRepositoryImp(api);
    }

    @Provides
    MercadoPagoApi providesApiService() {
        return ApiServiceFactory.build(new OkHttpClient(), MercadoPagoApi.class);
    }
}
