package com.jappy.mercadopago.utils;

import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.domain.model.trade.TradeDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database (entities = {Trade.class}, version = 1)
public abstract class MDatabase extends RoomDatabase {

    public abstract TradeDao mTradeDao();
}