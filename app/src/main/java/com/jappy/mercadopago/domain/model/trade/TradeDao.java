package com.jappy.mercadopago.domain.model.trade;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface TradeDao {

    @Query ("SELECT * FROM trade")
    Single<List<Trade>> getAll();

    @Insert
    void insertAll(Trade... users);

    @Query ("DELETE FROM trade")
    void delete();
}
